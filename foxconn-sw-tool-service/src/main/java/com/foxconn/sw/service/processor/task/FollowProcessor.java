package com.foxconn.sw.service.processor.task;

import com.foxconn.sw.business.oa.SwTaskFollowBusiness;
import com.foxconn.sw.business.oa.SwTaskProgressBusiness;
import com.foxconn.sw.data.dto.Header;
import com.foxconn.sw.data.dto.entity.acount.UserInfo;
import com.foxconn.sw.data.dto.entity.oa.FollowParams;
import com.foxconn.sw.data.entity.SwTaskProgress;
import com.foxconn.sw.service.processor.user.CommonUserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FollowProcessor {


    @Autowired
    CommonUserUtils userUtils;
    @Autowired
    SwTaskProgressBusiness taskProgressBusiness;
    @Autowired
    SwTaskFollowBusiness followBusiness;


    /**
     * 跟进操作
     *
     * @param data
     * @param head
     * @return
     */
    public boolean follow(FollowParams data, Header head) {
        String employeeName = userUtils.getEmployeeName(head.getToken());
        UserInfo userInfo = userUtils.queryUserInfo(head.getToken());


        boolean followRes = followBusiness.addTaskFollow(data.getContent(), data.getTaskID(), employeeName);
        if (followRes) {
            addProcessInfo(userInfo, data);
        }
        return followRes;
    }

    private boolean addProcessInfo(UserInfo userInfo, FollowParams data) {
        SwTaskProgress progress = new SwTaskProgress();
        progress.setTaskId(data.getTaskID());
        progress.setOperateEid(userInfo.getEmployeeNo());
        progress.setContent(data.getContent());
        return taskProgressBusiness.addProcessInfo(progress)>0;
    }
}
