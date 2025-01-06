package com.foxconn.sw.service.processor.collaboration;

import com.foxconn.sw.business.SwCapexSetBusiness;
import com.foxconn.sw.business.collaboration.CollaborationDetailBusiness;
import com.foxconn.sw.business.collaboration.CollaborationDetailSpareBusiness;
import com.foxconn.sw.business.collaboration.CollaborationUserBusiness;
import com.foxconn.sw.business.oa.SwTaskBusiness;
import com.foxconn.sw.business.oa.SwTaskEmployeeRelationBusiness;
import com.foxconn.sw.business.system.EmployeeBusiness;
import com.foxconn.sw.common.context.RequestContext;
import com.foxconn.sw.common.utils.FilePathUtils;
import com.foxconn.sw.data.constants.enums.TaskRoleFlagEnums;
import com.foxconn.sw.data.constants.enums.oa.TaskStatusEnums;
import com.foxconn.sw.data.dto.entity.ResourceVo;
import com.foxconn.sw.data.dto.entity.acount.EmployeeVo;
import com.foxconn.sw.data.dto.entity.collaboration.CollaborationVo;
import com.foxconn.sw.data.dto.entity.oa.CapexParamsVo;
import com.foxconn.sw.data.dto.request.collaboration.CollaborationDetailParams;
import com.foxconn.sw.data.entity.*;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class CollaborationDetailProcessor {

    @Autowired
    CollaborationDetailBusiness collaborationDetail;
    @Autowired
    CollaborationUserBusiness collaborationUser;
    @Autowired
    EmployeeBusiness employeeBusiness;
    @Autowired
    FilePathUtils filePathUtils;
    @Autowired
    SwTaskEmployeeRelationBusiness taskEmployeeRelationBusiness;
    @Autowired
    SwTaskBusiness taskBusiness;
    @Autowired
    SwCapexSetBusiness capexSetBusiness;
    @Autowired
    CollaborationDetailSpareBusiness collaborationDetailSpareBusiness;

    /**
     * int taskID = 75;
     *
     * @param params
     * @return
     * @throws FileNotFoundException
     */
    public CollaborationVo detail(CollaborationDetailParams params) throws FileNotFoundException {
        return detail(params, false);
    }


    /**
     * int taskID = 75;
     *
     * @param params
     * @return
     * @throws FileNotFoundException
     */
    public CollaborationVo detail(CollaborationDetailParams params, boolean isExport) throws FileNotFoundException {

        List<SwCollaborationUser> collaborationUsers = collaborationUser.queryCollaborationUser(params.getTaskID());
        SwTask swTask = taskBusiness.getTaskById(params.getTaskID());
        List<SwTaskEmployeeRelation> relations = taskEmployeeRelationBusiness.getRelationsByTaskIdAndRole(params.getTaskID(), TaskRoleFlagEnums.Manager_Flag);
        List<CapexParamsVo> capexParamsVos = capexSetBusiness.queryCapexParams(params.getTaskID());

        ResourceVo resourceVo = collaborationUser.getResourceVo(params.getTaskID());
        List<String> header = collaborationUser.getTaskHeader(params.getTaskID());
        CollaborationVo vo = new CollaborationVo();
        vo.setHeaders(header);
        vo.setResource(resourceVo);
        vo.setTaskNo(swTask.getTaskNo());
        vo.setTaskTitle(swTask.getTitle());
        vo.setCanFinish(RequestContext.getEmployeeNo().equalsIgnoreCase(swTask.getProposerEid()));
        vo.setCanSubmit(relations.stream().anyMatch(e -> e.getEmployeeNo().equalsIgnoreCase(RequestContext.getEmployeeNo())) || (!CollectionUtils.isEmpty(collaborationUsers) && collaborationUsers.stream().anyMatch(e -> e.getEmployeeNo().equalsIgnoreCase(RequestContext.getEmployeeNo())) && collaborationUsers.stream().anyMatch(e -> !e.getStatus().equals(2))));
        vo.setCapexParamsVos(capexParamsVos);
        vo.setPropose(swTask.getProposerEid().equalsIgnoreCase(RequestContext.getEmployeeNo()));
        vo.setFinish(swTask.getStatus().equals(TaskStatusEnums.COMPLETED.getCode()));
        vo.setBgStatus(collaborationUsers.get(0).getBgStatus());

        if (!CollectionUtils.isEmpty(capexParamsVos)) {
            vo.setContent(initMapList2(collaborationUsers.get(0).getId(), header));
        } else {
            vo.setContent(initMapList(header, params.getTaskID(), swTask.getProposerEid(), isExport));
        }
        return vo;
    }

    private List<Map<String, Object>> initMapList2(Long scuId, List<String> header) {

        List<SwCollaborationDetail> detailList = collaborationDetail.queryCollaborationDetail(Lists.newArrayList(scuId));

        Map<Integer, List<SwCollaborationDetail>> mapsList = detailList.stream()
                .filter(e -> e.getRowIndex() > 0)
                .collect(Collectors.groupingBy(SwCollaborationDetail::getRowIndex));

        List<Map<String, Object>> list = new ArrayList<>();
        for (Map.Entry<Integer, List<SwCollaborationDetail>> entry : mapsList.entrySet()) {
            Map<String, Object> objectMap = new HashMap<>();
            list.add(objectMap);
            int index = 0;
            objectMap.put("rowIndex", entry.getKey());
            for (SwCollaborationDetail collaborationDetail : entry.getValue()) {
                SwCollaborationDetailSpare detailSpare = collaborationDetailSpareBusiness.
                        getCollaborationDetail(collaborationDetail.getId());
                objectMap.put(header.get(index++), CollaborationDetailMapper.CollaborationDetail2ItemValue(collaborationDetail, detailSpare));
            }
        }
        return list;
    }

    private List<Map<String, Object>> initMapList(List<String> headers, int taskID, String proposerEid, boolean isExport) {
        List<SwCollaborationUser> collaborationUsers = collaborationUser.queryCollaborationUser(taskID);

        List<SwTaskEmployeeRelation> relations = taskEmployeeRelationBusiness.getRelationsByTaskId(taskID);
        boolean isPropose = relations.stream().anyMatch(e -> e.getEmployeeNo().equalsIgnoreCase(RequestContext.getEmployeeNo())
                && TaskRoleFlagEnums.Proposer_Flag.test(e.getRoleFlag()));

        List<Long> longIds = collaborationUsers.stream()
                .map(SwCollaborationUser::getId)
                .collect(Collectors.toList());
        List<SwCollaborationDetail> detailList = collaborationDetail.queryCollaborationDetail(longIds);
        Map<Long, List<SwCollaborationDetail>> map = detailList.stream()
                .collect(Collectors.groupingBy(SwCollaborationDetail::getScuId));

        List<Map<String, Object>> list = new ArrayList<>();
        for (SwCollaborationUser collaborationUser : collaborationUsers) {
            if (isExport
                    && !RequestContext.getEmployeeNo().equalsIgnoreCase(proposerEid)
                    && !RequestContext.getEmployeeNo().equalsIgnoreCase(collaborationUser.getEmployeeNo())) {
                continue;
            }

            List<SwCollaborationDetail> swCollaborationDetails = map.get(collaborationUser.getId());
            if (CollectionUtils.isEmpty(swCollaborationDetails)) {
                list.add(initDefaultMap(collaborationUser, headers, isPropose));
            } else {
                list.add(initMap(collaborationUser, swCollaborationDetails, isPropose));
            }
        }
        return sortList(list);
    }


    private List<Map<String, Object>> sortList(List<Map<String, Object>> list) {
        list.sort((e1, e2) -> {
            Integer status1 = (Integer) e1.get("desc");
            Integer status2 = (Integer) e2.get("desc");
            int statusComparison = status1.compareTo(status2);
            if (statusComparison != 0) {
                return statusComparison;
            }

            EmployeeVo employeeVo1 = (EmployeeVo) e1.get("handler");
            EmployeeVo employeeVo2 = (EmployeeVo) e2.get("handler");
            if (employeeVo1.getEmployeeNo().compareTo(employeeVo2.getEmployeeNo()) == 0) {
                Long e1ID = (Long) e1.get("id");
                Long e2ID = (Long) e2.get("id");
                return e2ID.compareTo(e1ID);
            }
            return employeeVo1.getEmployeeNo().compareTo(employeeVo2.getEmployeeNo());
        });
        return list;
    }

    private Map<String, Object> initDefaultMap(SwCollaborationUser collaborationUser, List<String> headers, boolean isPropose) {
        String employeeNo = RequestContext.getEmployeeNo();
        SwEmployee employee = employeeBusiness.selectEmployeeByENo(collaborationUser.getEmployeeNo());
        EmployeeVo vo = new EmployeeVo();
        vo.setName(employee.getName());
        vo.setEmployeeNo(employee.getEmployeeNo());

        Map<String, Object> map = new HashMap<>();
        for (String head : headers) {
            map.put(head, null);
        }
        map.put("id", collaborationUser.getId());
        map.put("status", collaborationUser.getStatus());
        map.put("edit", collaborationUser.getEmployeeNo().equalsIgnoreCase(employeeNo) || isPropose);
        map.put("handler", vo);
        map.put("desc", collaborationUser.getEmployeeNo().equalsIgnoreCase(employeeNo) ? 0 : 1);
        return map;
    }

    private Map<String, Object> initMap(SwCollaborationUser collaborationUser,
                                        List<SwCollaborationDetail> swCollaborationDetails,
                                        boolean isPropose) {
        String employeeNo = RequestContext.getEmployeeNo();
        SwEmployee employee = employeeBusiness.selectEmployeeByENo(collaborationUser.getEmployeeNo());
        EmployeeVo vo = new EmployeeVo();
        vo.setName(employee.getName());
        vo.setEmployeeNo(employee.getEmployeeNo());

        Map<String, Object> map = new HashMap<>();
        for (SwCollaborationDetail detail : swCollaborationDetails.stream()
                .filter(e -> e.getRowIndex() <= 1).collect(Collectors.toList())) {

            SwCollaborationDetailSpare detailSpare = collaborationDetailSpareBusiness.getCollaborationDetail(detail.getId());
            map.put(detail.getItem(), CollaborationDetailMapper.CollaborationDetail2ItemValue(detail, detailSpare));
            map.put("rowIndex", detail.getRowIndex());
        }
        map.put("id", collaborationUser.getId());
        map.put("status", collaborationUser.getStatus());
        map.put("edit", collaborationUser.getEmployeeNo().equalsIgnoreCase(employeeNo) && collaborationUser.getStatus() != 2 || isPropose);
        map.put("handler", vo);
        map.put("desc", collaborationUser.getEmployeeNo().equalsIgnoreCase(employeeNo) ? 0 : 1);
        return map;
    }


}
