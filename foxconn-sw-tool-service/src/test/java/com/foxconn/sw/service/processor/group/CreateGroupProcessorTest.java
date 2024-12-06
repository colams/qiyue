package com.foxconn.sw.service.processor.group;

import com.foxconn.sw.data.dto.request.group.MemberBrief;
import com.foxconn.sw.service.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class CreateGroupProcessorTest extends BaseTest {
    @Autowired
    CreateGroupProcessor groupProcessor;

    @Test
    public void createGroupMember() {
        List<MemberBrief> memberBriefs = new ArrayList<>();
        MemberBrief f1 = new MemberBrief();
        f1.setEmployeeNo("G123456");
        f1.setMemberType(1);

        MemberBrief f2 = new MemberBrief();
        f2.setEmployeeNo("G123456");
        f2.setMemberType(1);

        memberBriefs.add(f1);
        memberBriefs.add(f2);

        groupProcessor.createGroupMember(1, memberBriefs);
    }
}