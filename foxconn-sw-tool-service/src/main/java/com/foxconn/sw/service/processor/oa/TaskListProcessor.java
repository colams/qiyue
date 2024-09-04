package com.foxconn.sw.service.processor.oa;

import com.foxconn.sw.business.oa.SwTaskBusiness;
import com.foxconn.sw.business.oa.SwTaskFollowBusiness;
import com.foxconn.sw.data.constants.enums.OperateTypeEnum;
import com.foxconn.sw.data.dto.Header;
import com.foxconn.sw.data.dto.PageEntity;
import com.foxconn.sw.data.dto.PageParams;
import com.foxconn.sw.data.dto.entity.acount.UserInfo;
import com.foxconn.sw.data.dto.entity.oa.InfoColorVo;
import com.foxconn.sw.data.dto.entity.oa.TaskBriefListVo;
import com.foxconn.sw.data.dto.entity.oa.TaskParams;
import com.foxconn.sw.data.dto.entity.universal.OperateEntity;
import com.foxconn.sw.data.entity.SwTaskFollow;
import com.foxconn.sw.service.processor.oa.utils.*;
import com.foxconn.sw.service.processor.user.CommonUserUtils;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class TaskListProcessor {
    @Autowired
    CommonUserUtils userUtils;
    @Autowired
    SwTaskBusiness taskBusiness;
    @Autowired
    SwTaskFollowBusiness followBusiness;

    public PageEntity<TaskBriefListVo> list(PageParams<TaskParams> taskParams, Header head) {
        UserInfo userInfo = userUtils.queryUserInfo(head.getToken());
        List<TaskBriefListVo> briefVos = taskBusiness.listBriefVos(taskParams, userInfo.getEmployeeNo());
        processAfter(briefVos, userInfo);
        int totalCount = taskBusiness.getTotalCountByParams(taskParams.getParams(), userInfo.getEmployeeNo());
        PageEntity<TaskBriefListVo> voPageEntity = new PageEntity<>(totalCount, briefVos);
        return voPageEntity;
    }

    private void processAfter(List<TaskBriefListVo> briefVos, UserInfo userInfo) {
        List<Integer> taskIDs = briefVos.stream().map(TaskBriefListVo::getId).collect(Collectors.toList());
        List<SwTaskFollow> follows = followBusiness.queryFollow(taskIDs);
        Map<Integer, List<SwTaskFollow>> map = follows.stream().collect(Collectors.groupingBy(SwTaskFollow::getTaskId));

        briefVos.forEach(e -> {
            InfoColorVo statusInfoVo = TaskStatusUtils.processStatus(e.getStatus(), e.getRejectStatus(), e.getHandler());
            e.setStatusInfoVo(statusInfoVo);
            e.setOperateList(processOperate(e, userInfo.getEmployeeNo()));
            e.setLevelInfo(TaskLevelUtils.processLevel(e.getLevel()));
            e.setProject(TaskProjectUtils.processProject(e.getProject()));
            e.setCategory(TaskCategoryUtils.processCategory(e.getTopCategory(), e.getCategory()));
            e.setFollowStatus(getFollowStatus(map, e.getId()));
            e.setManagerEID(getProcessEID(e));
            e.setHandler(getProcessEID(e));
        });
    }

    private String getProcessEID(TaskBriefListVo e) {
        if (StringUtils.isEmpty(e.getHandler())) {
            return e.getHandler2();
        }
        return e.getHandler();
    }

    private Integer getFollowStatus(Map<Integer, List<SwTaskFollow>> map, Integer taskID) {
        return map.getOrDefault(taskID, Lists.newArrayList()).size();
    }

    private List<OperateEntity> processOperate(TaskBriefListVo e, String employeeNo) {
        List<OperateEntity> entityList = new ArrayList<>();

        for (OperateTypeEnum op : OperateTypeEnum.values()) {
            if (op.getPage().equalsIgnoreCase("list")) {
                OperateEntity operate = TaskOperateUtils.processOperate(employeeNo, e, op);
                entityList.add(operate);
            }
        }
        return entityList;
    }
}
