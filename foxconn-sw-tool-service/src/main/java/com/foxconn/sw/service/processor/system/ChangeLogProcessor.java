package com.foxconn.sw.service.processor.system;

import com.foxconn.sw.business.system.EmployeeBusiness;
import com.foxconn.sw.business.system.SwChangeLogBusiness;
import com.foxconn.sw.common.utils.DateTimeUtils;
import com.foxconn.sw.data.dto.entity.ChangeLogVo;
import com.foxconn.sw.data.entity.SwChangeLog;
import com.foxconn.sw.service.processor.utils.EmployeeUtils;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ChangeLogProcessor {

    @Autowired
    SwChangeLogBusiness changeLogBusiness;
    @Autowired
    EmployeeUtils employeeUtils;
    @Autowired
    EmployeeBusiness employeeBusiness;

    public List<ChangeLogVo> list() {
        List<SwChangeLog> changeLogs = changeLogBusiness.getChangeLogList();
        return Optional.ofNullable(changeLogs).orElse(Lists.newArrayList())
                .stream()
                .map(e -> map2Vo(e))
                .collect(Collectors.toList());
    }

    private ChangeLogVo map2Vo(SwChangeLog e) {
        ChangeLogVo changeLogVo = new ChangeLogVo();
        changeLogVo.setId(e.getId());
        changeLogVo.setOperator(employeeBusiness.selectEmployeeByENo(e.getLastUpdater()).getName());
        changeLogVo.setReleaseNote(e.getReleaseNote());
        changeLogVo.setCreateTime(DateTimeUtils.format(e.getCreateTime()));
        changeLogVo.setReleaseVersion(e.getReleaseVersion());
        return changeLogVo;
    }
}
