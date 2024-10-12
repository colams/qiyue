package com.foxconn.sw.service.processor.collaboration;

import com.foxconn.sw.business.collaboration.CollaborationDetailBusiness;
import com.foxconn.sw.business.collaboration.CollaborationUserBusiness;
import com.foxconn.sw.business.context.RequestContext;
import com.foxconn.sw.business.system.EmployeeBusiness;
import com.foxconn.sw.common.utils.FilePathUtils;
import com.foxconn.sw.data.dto.entity.acount.EmployeeVo;
import com.foxconn.sw.data.dto.entity.collaboration.CollaborationVo;
import com.foxconn.sw.data.dto.request.collaboration.CollaborationDetailParams;
import com.foxconn.sw.data.entity.SwCollaborationDetail;
import com.foxconn.sw.data.entity.SwCollaborationUser;
import com.foxconn.sw.data.entity.SwEmployee;
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

    /**
     * int taskID = 75;
     *
     * @param params
     * @return
     * @throws FileNotFoundException
     */
    public CollaborationVo detail(CollaborationDetailParams params) throws FileNotFoundException {
        int taskID = params.getTaskID();
        List<String> header = collaborationUser.getTaskHeader(taskID);
        CollaborationVo vo = new CollaborationVo();
        vo.setHeaders(header);
        vo.setContent(initMapList(header, taskID));
        return vo;
    }


    private List<Map<String, Object>> initMapList(List<String> headers, int taskID) {
        List<SwCollaborationUser> collaborationUsers = collaborationUser.queryCollaborationUser(taskID);

        if (CollectionUtils.isEmpty(collaborationUsers)) {
            return Lists.newArrayList();
        }

        List<Long> longIds = collaborationUsers.stream()
                .map(SwCollaborationUser::getId)
                .collect(Collectors.toList());
        List<SwCollaborationDetail> detailList = collaborationDetail.queryCollaborationDetail(longIds);
        Map<Long, List<SwCollaborationDetail>> map = detailList.stream()
                .collect(Collectors.groupingBy(SwCollaborationDetail::getScuId));

        List<Map<String, Object>> list = new ArrayList<>();
        for (SwCollaborationUser collaborationUser : collaborationUsers) {
            List<SwCollaborationDetail> swCollaborationDetails = map.get(collaborationUser.getId());
            if (CollectionUtils.isEmpty(swCollaborationDetails)) {
                list.add(initDefaultMap(collaborationUser, headers));
            } else {
                list.add(initMap(collaborationUser, swCollaborationDetails));
            }
        }
        return list;
    }

    private Map<String, Object> initDefaultMap(SwCollaborationUser collaborationUser, List<String> headers) {
        String employeeNo = RequestContext.getEmployeeNo();
        SwEmployee employee = employeeBusiness.selectEmployeeByENo(employeeNo);
        EmployeeVo vo = new EmployeeVo();
        vo.setName(employee.getName());
        vo.setEmployeeNo(employee.getEmployeeNo());

        Map<String, Object> map = new HashMap<>();
        for (String head : headers) {
            map.put(head, "");
        }
        map.put("id", collaborationUser.getId());
        map.put("status", collaborationUser.getStatus());
        map.put("edit", collaborationUser.getEmployeeNo().equalsIgnoreCase(employeeNo));
        map.put("handler", vo);
        return map;
    }

    private Map<String, Object> initMap(SwCollaborationUser collaborationUser,
                                        List<SwCollaborationDetail> swCollaborationDetails) {
        String employeeNo = RequestContext.getEmployeeNo();
        SwEmployee employee = employeeBusiness.selectEmployeeByENo(employeeNo);
        EmployeeVo vo = new EmployeeVo();
        vo.setName(employee.getName());
        vo.setEmployeeNo(employee.getEmployeeNo());

        Map<String, Object> map = new HashMap<>();
        for (SwCollaborationDetail detail : swCollaborationDetails) {
            map.put(detail.getItem(), detail.getItemValue());
        }
        map.put("id", collaborationUser.getId());
        map.put("status", collaborationUser.getStatus());
        map.put("edit", collaborationUser.getEmployeeNo().equalsIgnoreCase(employeeNo));
        map.put("handler", vo);
        return map;
    }
}
