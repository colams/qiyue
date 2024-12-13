package com.foxconn.sw.service.processor.group;

import com.foxconn.sw.business.system.DepartmentBusiness;
import com.foxconn.sw.business.system.EmployeeBusiness;
import com.foxconn.sw.common.context.RequestContext;
import com.foxconn.sw.data.dto.entity.system.AuthorizedVo;
import com.foxconn.sw.data.dto.request.enums.AuthorizedEnums;
import com.foxconn.sw.data.entity.SwDepartment;
import com.foxconn.sw.data.entity.SwEmployee;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class AuthorizedProcessor {

    @Autowired
    EmployeeBusiness employeeBusiness;
    @Autowired
    DepartmentBusiness departmentBusiness;

    /**
     * 支持项目根据参数确定
     *
     * @param data
     * @return
     */
    public List<AuthorizedVo> authorized(List<AuthorizedEnums> data) {
        if (CollectionUtils.isEmpty(data)) {
            return Lists.newArrayList();
        }

        List<AuthorizedVo> vos = new ArrayList<>();
        data.forEach(authorizedEnums -> {
            vos.add(getAuthorizeConfig(authorizedEnums));
        });
        return vos;
    }

    private AuthorizedVo getAuthorizeConfig(AuthorizedEnums authorizedEnums) {
        SwEmployee ee = employeeBusiness.selectEmployeeByENo(RequestContext.getEmployeeNo());
        SwDepartment department = departmentBusiness.getDepartment(ee.getDepartmentId());
        boolean result = ee.getManagerLevel() < 4 || RequestContext.getEmployeeNo().equalsIgnoreCase(department.getManagerNo());

        AuthorizedVo authorizedVo = new AuthorizedVo();
        authorizedVo.setAuthorizedEnums(authorizedEnums);
        authorizedVo.setResult(result);
        return authorizedVo;
    }
}
