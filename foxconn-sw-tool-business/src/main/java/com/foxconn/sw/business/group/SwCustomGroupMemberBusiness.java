package com.foxconn.sw.business.group;

import com.foxconn.sw.data.entity.SwCustomGroupMember;
import com.foxconn.sw.data.mapper.extension.group.SwCustomGroupMemberExtMapper;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SwCustomGroupMemberBusiness {

    @Autowired
    SwCustomGroupMemberExtMapper customGroupMemberExtMapper;
    @Autowired
    SqlSessionFactory sqlSessionFactory;

    public boolean batchInsert(List<SwCustomGroupMember> groupMembers) {
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
        SwCustomGroupMemberExtMapper mapper = sqlSession.getMapper(SwCustomGroupMemberExtMapper.class);
        for (SwCustomGroupMember groupMember : groupMembers) {
            mapper.insertSelective(groupMember);
        }
        sqlSession.commit();
        sqlSession.close();
        return true;
    }
}
