package com.foxconn.sw.business.system;

import com.foxconn.sw.common.utils.IntegerExtUtils;
import com.foxconn.sw.data.entity.SwChangeLog;
import com.foxconn.sw.data.entity.SwChangeLogExample;
import com.foxconn.sw.data.mapper.extension.system.SwChangeLogExtMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class SwChangeLogBusiness {
    @Autowired
    SwChangeLogExtMapper changeLogExtMapper;

    public List<SwChangeLog> getChangeLogList() {
        SwChangeLogExample example = new SwChangeLogExample();
        SwChangeLogExample.Criteria criteria = example.createCriteria();
        criteria.andCreateTimeGreaterThan(LocalDateTime.now().minusYears(1));
        return changeLogExtMapper.selectByExample(example);
    }

    public SwChangeLog getLatestChangeLog() {
        return changeLogExtMapper.getLatestChangeLog();
    }

    public Integer insertOrUpdate(SwChangeLog changeLog) {
        if (IntegerExtUtils.isPk(changeLog.getId())) {
            return changeLogExtMapper.updateByPrimaryKeySelective(changeLog);
        } else {
            changeLogExtMapper.insertSelective(changeLog);
            return changeLog.getId();
        }
    }
}
