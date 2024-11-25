package com.foxconn.sw.service.processor.acount;

import com.foxconn.sw.common.context.RequestContext;
import com.foxconn.sw.business.system.EmployeeBusiness;
import com.foxconn.sw.common.utils.PinyinUtils;
import com.foxconn.sw.data.dto.entity.acount.EmployeeVo;
import com.foxconn.sw.data.dto.request.account.QuerySubEmpParams;
import com.foxconn.sw.data.entity.SwEmployee;
import com.foxconn.sw.service.processor.config.PositionConfig;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class QueryMemberProcessor {
    @Autowired
    EmployeeBusiness employeeBusiness;


    public List<EmployeeVo> queryMembers(QuerySubEmpParams params) {
        List<EmployeeVo> vos = queryMember(params);
        Collections.sort(vos, (a, b) -> {
            return a.getPinyin().compareTo(b.getPinyin());
        });
        return vos;
    }

    public List<EmployeeVo> queryMember(QuerySubEmpParams params) {
        String employeeNo = RequestContext.getEmployeeNo();
        List<SwEmployee> employeeVos = employeeBusiness.queryMembers(employeeNo, params.getDepartId());
        List<EmployeeVo> vos = employeeVos.stream().map(e -> {
            EmployeeVo vo = new EmployeeVo();
            vo.setName(e.getName());
            vo.setEmployeeNo(e.getEmployeeNo());
            vo.setPinyin(PinyinUtils.toPinyin(e.getName()));
            return vo;
        }).collect(Collectors.toList());

        if (Objects.isNull(params.getLevelType()) || params.getLevelType() == 0) {
            return vos;
        }

        List<String> employeeNos = PositionConfig.getPositionEmployees(Lists.newArrayList(params.getLevelType()));
        return vos.stream().filter(e -> employeeNos.contains(e.getEmployeeNo()))
                .collect(Collectors.toList());
    }
}
