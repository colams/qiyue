package com.foxconn.sw.business.meeting;

import com.foxconn.sw.common.constanst.NumberConstants;
import com.foxconn.sw.data.entity.SwMeetingMinutesDetail;
import com.foxconn.sw.data.entity.SwMeetingMinutesDetailExample;
import com.foxconn.sw.data.mapper.extension.meeting.SwMeetingMinutesDetailExtensionMapper;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SwMeetingMinutesDetailBusiness {

    @Autowired
    SwMeetingMinutesDetailExtensionMapper meetingMinutesDetailExtensionMapper;
    @Autowired
    SqlSessionFactory sqlSessionFactory;

    public List<SwMeetingMinutesDetail> queryMeetingMinuteDetail(Long meetingMinutesId) {
        SwMeetingMinutesDetailExample example = new SwMeetingMinutesDetailExample();
        SwMeetingMinutesDetailExample.Criteria criteria = example.createCriteria();
        criteria.andMeetingMinutesIdEqualTo(meetingMinutesId);
        criteria.andIsDeleteEqualTo(NumberConstants.ZERO);
        return meetingMinutesDetailExtensionMapper.selectByExample(example);
    }


    public boolean batchInsert(List<SwMeetingMinutesDetail> detailList, Long meetingMinutesId) {
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
        SwMeetingMinutesDetailExtensionMapper mapper = sqlSession.getMapper(SwMeetingMinutesDetailExtensionMapper.class);
        for (SwMeetingMinutesDetail minutesDetail : detailList) {
            minutesDetail.setMeetingMinutesId(meetingMinutesId);
            mapper.insertSelective(minutesDetail);
        }
        sqlSession.commit();
        sqlSession.close();
        return true;
    }
}
