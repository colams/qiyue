package com.foxconn.sw.service.processor.document;

import com.foxconn.sw.business.SwAppendResourceBusiness;
import com.foxconn.sw.business.account.UserBusiness;
import com.foxconn.sw.business.oa.SwDocumentBusiness;
import com.foxconn.sw.business.oa.SwDocumentHistoryBusiness;
import com.foxconn.sw.business.oa.SwDocumentPermissionBusiness;
import com.foxconn.sw.business.system.EmployeeBusiness;
import com.foxconn.sw.common.constanst.NumberConstants;
import com.foxconn.sw.data.context.RequestContext;
import com.foxconn.sw.common.utils.ConvertUtils;
import com.foxconn.sw.common.utils.DateTimeUtils;
import com.foxconn.sw.data.dto.entity.acount.EmployeeVo;
import com.foxconn.sw.data.dto.entity.document.DocumentDetailVo;
import com.foxconn.sw.data.dto.entity.universal.IntegerParams;
import com.foxconn.sw.data.entity.SwAppendResource;
import com.foxconn.sw.data.entity.SwDocument;
import com.foxconn.sw.data.entity.SwEmployee;
import com.foxconn.sw.service.processor.utils.EmployeeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DetailDocProcessor {

    @Autowired
    SwDocumentBusiness documentBusiness;
    @Autowired
    SwDocumentPermissionBusiness documentPermissionBusiness;
    @Autowired
    SwDocumentHistoryBusiness documentHistoryBusiness;
    @Autowired
    SwAppendResourceBusiness appendResourceBusiness;
    @Autowired
    UserBusiness userBusiness;
    @Autowired
    EmployeeBusiness employeeBusiness;
    @Autowired
    EmployeeUtils employeeUtils;


    public DocumentDetailVo detail(IntegerParams data) {
        SwDocument document = documentBusiness.queryDocumentByID(data.getParams());
        return convert2Vo(document);
    }

    private DocumentDetailVo convert2Vo(SwDocument e) {
        boolean isCreator = RequestContext.getEmployeeNo().equalsIgnoreCase(e.getCreator());
        SwAppendResource resource = appendResourceBusiness.getAppendResources(e.getResourceId());

        DocumentDetailVo vo = new DocumentDetailVo();
        vo.setDocumentID(e.getId());
        vo.setCategory(e.getCategory());
        vo.setDocumentName(e.getDocumentName());
        vo.setDownloadUrl(ConvertUtils.urlPreFix(resource.getId(), resource.getFilePath()));
        vo.setFileID(resource.getId());
        vo.setViewUrl(appendResourceBusiness.getResourceUrl(resource));
        if (NumberConstants.TWO.equals(e.getSecretLevel())) {
            vo.setTitle("一般機密");
        } else if (NumberConstants.THREE.equals(e.getSecretLevel())) {
            vo.setTitle("絕對機密");
        } else {
            vo.setTitle("公開");
        }
        vo.setLevel(e.getSecretLevel());
        vo.setProject(e.getProject());
        vo.setFileVersion(e.getFileVersion());
        vo.setCanView(isCreator || documentPermissionBusiness.getViewPermission(RequestContext.getEmployeeNo(), e.getId()));
        vo.setDisableDown(e.getDisableDown());
        vo.setCanUpdate(isCreator);
        EmployeeVo employeeVo = new EmployeeVo();
        SwEmployee ee = employeeBusiness.selectEmployeeByENo(e.getCreator());
        employeeVo.setName(ee.getName());
        employeeVo.setEmployeeNo(ee.getEmployeeNo());
        employeeVo.setDepartmentId(ee.getDepartmentId());
        vo.setPublisher(employeeVo);
        vo.setAuthor(employeeUtils.mapEmployee(e.getAuthor()));
        vo.setCreateTime(DateTimeUtils.format(e.getCreateTime()));
        vo.setUpdateTime(DateTimeUtils.format(e.getDatetimeLastchange()));

        vo.setDepartmentIDs(documentPermissionBusiness.getPermissionSet(1, e.getId()));
        vo.setEmployeeNos(documentPermissionBusiness.getPermissionSet(2, e.getId()));
        vo.setExtra(documentPermissionBusiness.getPermissionExtra(e.getId()));

        return vo;
    }
}
