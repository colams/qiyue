package com.foxconn.sw.service.processor.acount;

import com.foxconn.sw.business.account.SwContactGatherBusiness;
import com.foxconn.sw.business.system.DepartmentBusiness;
import com.foxconn.sw.business.system.EmployeeBusiness;
import com.foxconn.sw.data.dto.Header;
import com.foxconn.sw.data.dto.entity.acount.AddressBookParams;
import com.foxconn.sw.data.dto.entity.acount.AddressBookVo;
import com.foxconn.sw.data.dto.entity.system.DepartmentVo;
import com.foxconn.sw.data.entity.SwContactGather;
import com.foxconn.sw.data.entity.SwEmployee;
import com.foxconn.sw.service.processor.user.CommonUserUtils;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
public class ListAddressBookProcessor {

    @Autowired
    EmployeeBusiness employeeBusiness;
    @Autowired
    CommonUserUtils commonUserUtils;
    @Autowired
    SwContactGatherBusiness gatherBusiness;
    @Autowired
    DepartmentBusiness departmentBusiness;

    public List<AddressBookVo> list(Header head, AddressBookParams data) {
        String userNo = commonUserUtils.getEmployeeNo(head.getToken());

        List<SwEmployee> employees = employeeBusiness.queryEmployees(data);
        Map<String, SwContactGather> gatherMap = gatherBusiness.queryGatherInfo(userNo);
        Map<Integer, DepartmentVo> voMap = departmentBusiness.getDepartMap();

        List<AddressBookVo> bookVoList = new ArrayList<>();
        employees.forEach(e -> {
            AddressBookVo vo = new AddressBookVo();

            List<DepartmentVo> voList = getDepartList(voMap, e.getDepartmentId());

            vo.setSeniorDepart(voList.stream().filter(departmentVo -> departmentVo.getName().endsWith("處")).map(v->v.getName()).findFirst().orElse("-"));
            vo.setDepartment(voList.stream().filter(departmentVo -> departmentVo.getName().contains("部")).map(v->v.getName()).findFirst().orElse("-"));
            vo.setEmployeeNo(e.getEmployeeNo());
            vo.setName(e.getName());
            vo.setEnName(String.format("%s %s", e.getFirstName(), e.getLastName()));

            if (e.getGender() == 1) {
                vo.setGender("男");
            } else if (e.getGender() == 2) {
                vo.setGender("女");
            } else {
                vo.setGender("");
            }

            vo.setPhoneMobile(e.getPhoneNumber());
            vo.setLandLine(e.getLandLine());
            vo.setInnerMail(e.getInnerEmail());
            vo.setOuterMail(e.getOuterMail());
            vo.setStatus(Objects.nonNull(gatherMap.get(e.getEmployeeNo())) ? 1 : 0);
            bookVoList.add(vo);
        });
        return bookVoList;
    }

    private List<DepartmentVo> getDepartList(Map<Integer, DepartmentVo> voMap, int departID) {
        List<DepartmentVo> vos = new ArrayList<>();

        DepartmentVo departmentVo = voMap.get(departID);
        if (Objects.isNull(departmentVo.getParentId()) || departmentVo.getParentId() == 0) {
            return vos;
        }
        List<DepartmentVo> temps = getDepartList(voMap, departmentVo.getParentId());
        temps.addAll(Lists.newArrayList(departmentVo));
        vos.addAll(temps);
        return vos;
    }


}
