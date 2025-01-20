package com.foxconn.sw.business.meeting;

import com.foxconn.sw.common.constanst.NumberConstants;
import com.foxconn.sw.data.entity.SwMeetingMinuteDetail;
import com.foxconn.sw.data.entity.SwMeetingMinuteDetailExample;
import com.foxconn.sw.data.mapper.extension.meeting.SwMeetingMinuteDetailExtensionMapper;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SwMeetingMinutesDetailBusiness {

    @Autowired
    SwMeetingMinuteDetailExtensionMapper meetingMinutesDetailExtensionMapper;
    @Autowired
    SqlSessionFactory sqlSessionFactory;

    public List<SwMeetingMinuteDetail> queryMeetingMinuteDetail(Long meetingMinutesId) {
        SwMeetingMinuteDetailExample example = new SwMeetingMinuteDetailExample();
        SwMeetingMinuteDetailExample.Criteria criteria = example.createCriteria();
        criteria.andMeetingMinutesIdEqualTo(meetingMinutesId);
        criteria.andIsDeleteEqualTo(NumberConstants.ZERO);
        return meetingMinutesDetailExtensionMapper.selectByExample(example);
    }


    public boolean batchInsert(List<SwMeetingMinuteDetail> detailList, Long meetingMinutesId) {
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
        SwMeetingMinuteDetailExtensionMapper mapper = sqlSession.getMapper(SwMeetingMinuteDetailExtensionMapper.class);
        for (SwMeetingMinuteDetail minutesDetail : detailList) {
            minutesDetail.setMeetingMinutesId(meetingMinutesId);
            mapper.insertSelective(minutesDetail);
        }
        sqlSession.commit();
        sqlSession.close();
        return true;
    }
}
