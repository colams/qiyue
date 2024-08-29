package com.foxconn.sw.service.processor.acount;

import com.foxconn.sw.business.account.SwContactGatherBusiness;
import com.foxconn.sw.data.dto.Header;
import com.foxconn.sw.data.dto.entity.acount.AddressGatherParams;
import com.foxconn.sw.data.entity.SwContactGather;
import com.foxconn.sw.service.processor.user.CommonUserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class GatherAddressBookProcessor {

    @Autowired
    private CommonUserUtils userUtils;
    @Autowired
    private SwContactGatherBusiness gatherBusiness;

    public boolean gather(Header head, AddressGatherParams data) {
        String employeeNo = userUtils.getEmployeeNo(head.getToken());
        SwContactGather gather = gatherBusiness.queryGatherInfo(employeeNo, data.getEmployeeNo());
        if (Objects.nonNull(gather)) {
            gather.setStatus(data.getGatherType());
            gatherBusiness.updateGatherStatus(gather);
        } else {
            gather = new SwContactGather();
            gather.setEmployeeNo(employeeNo);
            gather.setGatherEmployeeNo(data.getEmployeeNo());
            gather.setStatus(data.getGatherType());
            gatherBusiness.addGather(gather);
        }
        return true;
    }
}
