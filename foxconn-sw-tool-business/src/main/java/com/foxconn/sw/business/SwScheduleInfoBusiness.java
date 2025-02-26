package com.foxconn.sw.business;

import com.foxconn.sw.data.entity.SwScheduleInfo;
import com.foxconn.sw.data.mapper.extension.SwScheduleInfoExtMapper;
import com.google.common.collect.Lists;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class SwScheduleInfoBusiness {

    @Autowired
    SwScheduleInfoExtMapper scheduleInfoExtMapper;
    @Autowired
    SqlSessionFactory sqlSessionFactory;

    public List<SwScheduleInfo> getMyScheduleInfos(String employeeNo, String startOfMonth, String endOfMonth) {
        List<SwScheduleInfo> sch = scheduleInfoExtMapper.getMyScheduleInfos(employeeNo, startOfMonth, endOfMonth);
        return Optional.ofNullable(sch).orElse(Lists.newArrayList());
    }

    public boolean batchInsert(List<SwScheduleInfo> scheduleInfoList) {
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
        SwScheduleInfoExtMapper mapper = sqlSession.getMapper(SwScheduleInfoExtMapper.class);
        for (SwScheduleInfo scheduleInfo : scheduleInfoList) {
            if (Objects.nonNull(scheduleInfo.getId()) && scheduleInfo.getId() > 0) {
                mapper.updateScheduleInfo(scheduleInfo);
            } else {
                mapper.insertSelective(scheduleInfo);
            }
        }
        sqlSession.commit();
        sqlSession.close();
        return true;
    }

    public List<SwScheduleInfo> getTeamScheduleInfos(List<String> employeeNos, String startDate, String endDate) {
        return scheduleInfoExtMapper.getTeamScheduleInfos(employeeNos, startDate, endDate);
    }
}
