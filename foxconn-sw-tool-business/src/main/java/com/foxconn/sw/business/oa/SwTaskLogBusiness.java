package com.foxconn.sw.business.oa;

import com.foxconn.sw.data.entity.SwTaskLog;
import com.foxconn.sw.data.entity.SwTaskLogExample;
import com.foxconn.sw.data.mapper.extension.oa.SwTaskLogExtensionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SwTaskLogBusiness {

    @Autowired
    SwTaskLogExtensionMapper taskLogExtensionMapper;

    public List<SwTaskLog> selectLogs(Integer taskId) {
        SwTaskLogExample example = new SwTaskLogExample();
        SwTaskLogExample.Criteria criteria = example.createCriteria();
        criteria.andTaskIdEqualTo(taskId);
        return taskLogExtensionMapper.selectByExample(example);
    }

    public boolean addTaskLog(Integer taskID, String operator, String content) {
        SwTaskLog log = new SwTaskLog();
        log.setTaskId(taskID);
        log.setOperator(operator);
        log.setContent(content);
        return taskLogExtensionMapper.insertSelective(log) > 0;
    }
}
