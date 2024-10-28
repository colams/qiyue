package com.foxconn.sw.business;

import com.foxconn.sw.data.entity.SwLog;
import com.foxconn.sw.data.mapper.extension.SwLogExtensionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LogBusiness {

    @Autowired
    SwLogExtensionMapper logExtensionMapper;

    /**
     * 操作日志
     *
     * @param operator
     * @param operateType
     * @param remark
     * @param intervals
     * @param ip
     * @return
     */
    public boolean log(String operator, String operateType, String remark, Long intervals, String ip) {
        SwLog log = new SwLog();
        log.setOperator(operator);
        log.setOperatetype(operateType);
        log.setRemark(remark);
        log.setIntervals(intervals);
        log.setIp(ip);
        return logExtensionMapper.insertSelective(log) > 1;
    }

}
