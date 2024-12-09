package com.foxconn.sw.business.oa;

import com.foxconn.sw.common.context.RequestContext;
import com.foxconn.sw.data.entity.SwTask;
import com.foxconn.sw.data.entity.SwTaskContentHistory;
import com.foxconn.sw.data.mapper.extension.oa.SwTaskContentHistoryExtMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SwTaskContentHistoryBusiness {

    @Autowired
    SwTaskContentHistoryExtMapper taskContentHistoryExtMapper;


    public boolean insertHistory(Integer id, SwTask task) {
        SwTaskContentHistory contentHistory = new SwTaskContentHistory();
        contentHistory.setTaskId(task.getId());
        contentHistory.setProgressId(id);
        contentHistory.setOperator(RequestContext.getEmployeeNo());
        contentHistory.setContent(task.getDescription());
        return taskContentHistoryExtMapper.insertSelective(contentHistory) > 0;
    }

    public String getHistoryContent(Integer contentHistoryID) {
        SwTaskContentHistory contentHistory = taskContentHistoryExtMapper.selectByPrimaryKey(contentHistoryID);
        return Optional.ofNullable(contentHistory).map(e -> e.getContent()).orElse("");
    }
}
