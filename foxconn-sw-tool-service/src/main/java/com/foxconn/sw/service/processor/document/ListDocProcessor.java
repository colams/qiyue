package com.foxconn.sw.service.processor.document;

import com.foxconn.sw.business.SwAppendResourceBusiness;
import com.foxconn.sw.business.account.UserBusiness;
import com.foxconn.sw.common.context.RequestContext;
import com.foxconn.sw.business.oa.SwDocumentBusiness;
import com.foxconn.sw.business.oa.SwDocumentHistoryBusiness;
import com.foxconn.sw.business.oa.SwDocumentPermissionBusiness;
import com.foxconn.sw.business.system.EmployeeBusiness;
import com.foxconn.sw.common.utils.ConvertUtils;
import com.foxconn.sw.common.utils.DateTimeUtils;
import com.foxconn.sw.data.dto.entity.acount.EmployeeVo;
import com.foxconn.sw.data.dto.entity.document.DocumentVo;
import com.foxconn.sw.data.dto.entity.document.HistoryVo;
import com.foxconn.sw.data.dto.entity.universal.IntegerParams;
import com.foxconn.sw.data.dto.request.document.SearchDocParams;
import com.foxconn.sw.data.entity.SwAppendResource;
import com.foxconn.sw.data.entity.SwDocument;
import com.foxconn.sw.data.entity.SwDocumentHistory;
import com.foxconn.sw.data.entity.SwEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class ListDocProcessor {

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

    public List<DocumentVo> list(SearchDocParams data) {
        List<SwDocument> documents = documentBusiness.queryDocumentList(data);
        return convert2Vo(documents);
    }

    private List<DocumentVo> convert2Vo(List<SwDocument> documents) {
        List<DocumentVo> vos = new ArrayList<>();
        documents.forEach(e -> {
            boolean isCreator = RequestContext.getEmployeeNo().equalsIgnoreCase(e.getCreator());
            SwAppendResource resource = appendResourceBusiness.getAppendResources(e.getResourceId());
            DocumentVo vo = new DocumentVo();
            vo.setDocumentID(e.getId());
            vo.setCategory(e.getCategory());
            vo.setDocumentName(e.getDocumentName());
            vo.setDownloadUrl(ConvertUtils.urlPreFix(resource.getId(), resource.getFilePath()));
            vo.setViewUrl(appendResourceBusiness.getResourceUrl(resource));
            if (Integer.valueOf(0).equals(e.getSecretLevel()) || Integer.valueOf(1).equals(e.getSecretLevel())) {
                vo.setTitle("公開");
            } else if (Integer.valueOf(2).equals(e.getSecretLevel())) {
                vo.setTitle("一般機密");
            } else if (Integer.valueOf(3).equals(e.getSecretLevel())) {
                vo.setTitle("絕對機密");
            }
            vo.setLevel(e.getSecretLevel());
            vo.setProject(e.getProject());
            vo.setFileVersion(e.getFileVersion());
            vo.setCanView(isCreator || documentPermissionBusiness.getViewPermission(RequestContext.getEmployeeNo(), e.getId()));
            vo.setCanDownload(isCreator || (Objects.nonNull(e.getDisableDown()) && e.getDisableDown() == 0));
            vo.setCanUpdate(isCreator);
            EmployeeVo employeeVo = new EmployeeVo();
            SwEmployee ee = employeeBusiness.selectEmployeeByENo(e.getCreator());
            employeeVo.setName(ee.getName());
            employeeVo.setEmployeeNo(ee.getEmployeeNo());
            employeeVo.setDepartmentId(ee.getDepartmentId());
            vo.setPublisher(employeeVo);
            vo.setCreateTime(DateTimeUtils.format(e.getCreateTime()));
            vo.setUpdateTime(DateTimeUtils.format(e.getDatetimeLastchange()));
            vos.add(vo);
        });
        return vos;
    }

    public List<HistoryVo> history(IntegerParams data) {
        SwDocument document = documentBusiness.queryDocumentByID(data.getParams());
        List<SwDocumentHistory> histories = documentHistoryBusiness.queryDocumentHistoryList(data.getParams());
        return convert2HistoryVo(histories, document);
    }

    private List<HistoryVo> convert2HistoryVo(List<SwDocumentHistory> histories, SwDocument document) {
        List<HistoryVo> vos = new ArrayList<>();
        histories.forEach(e -> {
            SwAppendResource resource = appendResourceBusiness.getAppendResources(e.getResourceId());
            HistoryVo vo = new HistoryVo();
            vo.setHistoryID(e.getId());
            vo.setDocumentName(e.getDocumentName());
            vo.setDownloadUrl(ConvertUtils.urlPreFix(resource.getId(), resource.getFilePath()));
            vo.setViewUrl(appendResourceBusiness.getResourceUrl(resource));
            vo.setAvatar(userBusiness.queryUserInfo(e.getCreator()).getAvatar());
            vo.setPublisher(e.getCreator());
            vo.setUpdateTime(DateTimeUtils.format(e.getCreateTime()));
            vos.add(vo);
        });
        return vos;
    }
}
