package com.foxconn.sw.service.processor.project;

import com.foxconn.sw.business.project.ProjectBusiness;
import com.foxconn.sw.business.project.ProjectItemBusiness;
import com.foxconn.sw.common.context.RequestContext;
import com.foxconn.sw.data.dto.request.project.ProjectSaveParams;
import com.foxconn.sw.data.dto.request.project.ProjectUpdateParams;
import com.foxconn.sw.data.entity.SwProject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProjectSaveProcessor {
    private static final Logger logger = LoggerFactory.getLogger(ProjectSaveProcessor.class);

    @Autowired
    ProjectBusiness projectBusiness;
    @Autowired
    ProjectItemBusiness projectItemBusiness;


    public Boolean save(ProjectSaveParams params) {
        SwProject swProject = new SwProject();
        swProject.setId(params.getId());
        swProject.setYears(params.getYears());
        swProject.setProjectCode(params.getProjectCode());
        swProject.setCustomerName(params.getCustomerName());
        swProject.setFullName(params.getFullName());
        swProject.setManufacturingModel(params.getManufacturingModel());
        swProject.setStatus(params.getStatus());
        swProject.setRfqTime(params.getRfqTime());
        swProject.setCustomer(params.getCustomer());
        swProject.setCustomerPartNo(params.getCustomerPartNo());
        swProject.setApplication(params.getApplication());
        swProject.setOperator(RequestContext.getEmployeeNo());
        return projectBusiness.insertOrUpdate(swProject);
    }

    public Boolean update(ProjectUpdateParams params) {
        return projectItemBusiness.updateProjectValue(params);
    }


}
