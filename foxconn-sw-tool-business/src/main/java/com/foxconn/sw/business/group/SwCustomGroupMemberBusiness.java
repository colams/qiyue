package com.foxconn.sw.business.group;

import com.foxconn.sw.common.constanst.NumberConstants;
import com.foxconn.sw.common.context.RequestContext;
import com.foxconn.sw.data.entity.SwCustomGroupMember;
import com.foxconn.sw.data.entity.SwCustomGroupMemberExample;
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


    /**
     * 查詢公開群組
     *
     * @param keywords
     * @return
     */
    public List<SwCustomGroupMember> listJoinGroup() {
        SwCustomGroupMemberExample example = new SwCustomGroupMemberExample();
        SwCustomGroupMemberExample.Criteria criteria = example.createCriteria();
        criteria.andMemberEqualTo(RequestContext.getEmployeeNo());
        criteria.andIsDeleteEqualTo(NumberConstants.ZERO);
        return customGroupMemberExtMapper.selectByExample(example);
    }

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

    public List<SwCustomGroupMember> getGroupMember(Integer groupID) {
        SwCustomGroupMemberExample example = new SwCustomGroupMemberExample();
        SwCustomGroupMemberExample.Criteria criteria = example.createCriteria();
        criteria.andCustomGroupIdEqualTo(groupID);
        criteria.andIsDeleteEqualTo(NumberConstants.ZERO);
        return customGroupMemberExtMapper.selectByExample(example);
    }
}
