package com.foxconn.sw.business.oa;

import com.foxconn.sw.data.context.RequestContext;
import com.foxconn.sw.data.entity.SwTaskContentHistory;
import com.foxconn.sw.data.mapper.extension.oa.SwTaskContentHistoryExtMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SwTaskContentHistoryBusiness {

    @Autowired
    SwTaskContentHistoryExtMapper taskContentHistoryExtMapper;


    public SwTaskContentHistory getHistoryContent(Integer contentHistoryID) {
        SwTaskContentHistory contentHistory = taskContentHistoryExtMapper.selectByPrimaryKey(contentHistoryID);
        return contentHistory;
    }

    public boolean insertHistory(Integer id, Integer taskID, String old, String newContent) {
        SwTaskContentHistory contentHistory = new SwTaskContentHistory();
        contentHistory.setTaskId(taskID);
        contentHistory.setProgressId(id);
        contentHistory.setOperator(RequestContext.getEmployeeNo());
        contentHistory.setOldContent(old);
        contentHistory.setNewContent(newContent);
        return taskContentHistoryExtMapper.insertSelective(contentHistory) > 0;
    }
}
