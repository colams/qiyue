package com.foxconn.sw.business.oa;

import com.foxconn.sw.common.context.RequestContext;
import com.foxconn.sw.data.dto.request.task.UpdateTaskParams;
import com.foxconn.sw.data.entity.SwTaskContentHistory;
import com.foxconn.sw.data.mapper.extension.oa.SwTaskContentHistoryExtMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SwTaskContentHistoryBusiness {

    @Autowired
    SwTaskContentHistoryExtMapper taskContentHistoryExtMapper;


    public boolean insertHistory(Integer id, UpdateTaskParams taskParams) {
        SwTaskContentHistory contentHistory = new SwTaskContentHistory();
        contentHistory.setTaskId(taskParams.getBriefTaskVo().getId());
        contentHistory.setProgressId(id);
        contentHistory.setOperator(RequestContext.getEmployeeNo());
        contentHistory.setContent(taskParams.getBriefTaskVo().getDescription());
        return taskContentHistoryExtMapper.insertSelective(contentHistory) > 0;
    }

    public String getHistoryContent(Integer contentHistoryID) {
        SwTaskContentHistory contentHistory = taskContentHistoryExtMapper.selectByPrimaryKey(contentHistoryID);
        return contentHistory.getContent();
    }
}
