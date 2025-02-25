package com.foxconn.sw.business;

import com.foxconn.sw.data.entity.SwScheduleInfo;
import com.foxconn.sw.data.mapper.extension.SwScheduleInfoExtMapper;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SwScheduleInfoBusiness {

    @Autowired
    SwScheduleInfoExtMapper scheduleInfoExtMapper;
    @Autowired
    SqlSessionFactory sqlSessionFactory;

    public List<SwScheduleInfo> getMyScheduleInfos(String employeeNo, String startOfMonth, String endOfMonth) {
        return scheduleInfoExtMapper.getMyScheduleInfos(employeeNo, startOfMonth, endOfMonth);
    }

    public boolean batchInsert(List<SwScheduleInfo> scheduleInfoList) {
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
        SwScheduleInfoExtMapper mapper = sqlSession.getMapper(SwScheduleInfoExtMapper.class);
        for (SwScheduleInfo scheduleInfo : scheduleInfoList) {
            mapper.insertSelective(scheduleInfo);
        }
        sqlSession.commit();
        sqlSession.close();
        return true;
    }

    public List<SwScheduleInfo> getTeamScheduleInfos(List<String> employeeNos, String startDate, String endDate) {
        return scheduleInfoExtMapper.getTeamScheduleInfos(employeeNos, startDate, endDate);
    }
}
