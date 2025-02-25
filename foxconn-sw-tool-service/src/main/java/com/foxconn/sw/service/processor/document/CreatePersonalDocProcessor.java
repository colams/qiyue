package com.foxconn.sw.service.processor.document;

import com.foxconn.sw.business.oa.SwDocumentBusiness;
import com.foxconn.sw.business.oa.SwDocumentHistoryBusiness;
import com.foxconn.sw.business.oa.SwDocumentPermissionBusiness;
import com.foxconn.sw.business.system.DepartmentBusiness;
import com.foxconn.sw.common.constanst.NumberConstants;
import com.foxconn.sw.data.context.RequestContext;
import com.foxconn.sw.data.dto.request.document.CreatePersonalDocParams;
import com.foxconn.sw.data.entity.SwDocumentHistory;
import com.foxconn.sw.data.exception.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CreatePersonalDocProcessor {

    @Autowired
    SwDocumentBusiness documentBusiness;
    @Autowired
    SwDocumentHistoryBusiness documentHistoryBusiness;
    @Autowired
    DepartmentBusiness departmentBusiness;
    @Autowired
    SwDocumentPermissionBusiness documentPermissionBusiness;

    public boolean createPersonalDoc(CreatePersonalDocParams params) {
        if (Objects.isNull(params.getResourceID()) || params.getResourceID() < 0) {
            throw new BizException(4, "参数错误，缺少资源文件");
        }

        boolean sameFile = documentBusiness.hasSameFile(params.getFileName(), NumberConstants.ONE);
        if (sameFile) {
            throw new BizException(1, "存在重複文件！");
        }

        int documentID = documentBusiness.createDoc(params);
        if (documentID > 0) {
            SwDocumentHistory documentHistory = new SwDocumentHistory();
            documentHistory.setDocumentId(documentID);
            documentHistory.setDocumentName(params.getFileName());
            documentHistory.setResourceId(params.getResourceID());
            documentHistory.setCreator(RequestContext.getEmployeeNo());
            documentHistoryBusiness.insertHistory(documentHistory);
        }
        return documentID > 0;
    }
}
