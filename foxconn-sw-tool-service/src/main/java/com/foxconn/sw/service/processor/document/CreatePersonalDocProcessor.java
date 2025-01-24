package com.foxconn.sw.service.processor.document;

import com.foxconn.sw.business.oa.SwDocumentBusiness;
import com.foxconn.sw.business.oa.SwDocumentHistoryBusiness;
import com.foxconn.sw.business.oa.SwDocumentPermissionBusiness;
import com.foxconn.sw.business.system.DepartmentBusiness;
import com.foxconn.sw.common.constanst.NumberConstants;
import com.foxconn.sw.data.context.RequestContext;
import com.foxconn.sw.data.dto.entity.universal.IntegerParams;
import com.foxconn.sw.data.dto.request.document.CreateDocParams;
import com.foxconn.sw.data.dto.request.document.DeleteDocParams;
import com.foxconn.sw.data.dto.request.document.ReviseDocParams;
import com.foxconn.sw.data.dto.request.document.UpdateDocParams;
import com.foxconn.sw.data.entity.SwDocument;
import com.foxconn.sw.data.entity.SwDocumentHistory;
import com.foxconn.sw.data.exception.BizException;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

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


    public boolean create(CreateDocParams params) {
        if (Objects.isNull(params.getResourceID()) || params.getResourceID() < 0) {
            throw new BizException(4, "参数错误，缺少资源文件");
        }

        boolean sameFile = documentBusiness.hasSameFile(params, NumberConstants.ONE);
        if (sameFile) {
            throw new BizException(1, "存在重複文件！");
        }

        int documentID = documentBusiness.createDoc(params);
        if (documentID > 0) {
            if (CollectionUtils.isEmpty(params.getDepartmentIDs()) && CollectionUtils.isEmpty(params.getEmployeeNos())) {
                documentPermissionBusiness.insertDocumentPermission(documentID, Lists.newArrayList("0"), params.getExtra(), 1);
            }

            if (!CollectionUtils.isEmpty(params.getDepartmentIDs())) {
                documentPermissionBusiness.insertDocumentPermission(documentID, params.getDepartmentIDs(), params.getExtra(), 1);
            }

            if (!CollectionUtils.isEmpty(params.getEmployeeNos())) {
                documentPermissionBusiness.insertDocumentPermission(documentID, params.getEmployeeNos(), params.getExtra(), 2);
            }


            SwDocumentHistory documentHistory = new SwDocumentHistory();
            documentHistory.setDocumentId(documentID);
            documentHistory.setDocumentName(params.getFileName());
            documentHistory.setResourceId(params.getResourceID());
            documentHistory.setCreator(RequestContext.getEmployeeNo());
            documentHistoryBusiness.insertHistory(documentHistory);
        }
        return documentID > 0;
    }


    public boolean revise(ReviseDocParams data) {
        SwDocument document = documentBusiness.queryDocumentByID(data.getDocumentID());

        SwDocument updateDoc = new SwDocument();
        updateDoc.setId(document.getId());
        updateDoc.setDocumentName(data.getFileName());
        updateDoc.setCategory(data.getCategory());
        updateDoc.setFileVersion(data.getFileVersion());
        updateDoc.setResourceId(data.getResourceID());
        documentBusiness.updateDocument(updateDoc);


        SwDocumentHistory documentHistory = new SwDocumentHistory();
        documentHistory.setDocumentId(document.getId());
        documentHistory.setDocumentName(document.getDocumentName());
        documentHistory.setResourceId(document.getResourceId());
        documentHistory.setCreator(RequestContext.getEmployeeNo());
        documentHistoryBusiness.insertHistory(documentHistory);
        return true;
    }

    public boolean update(UpdateDocParams data) {
        SwDocument updateDoc = new SwDocument();
        updateDoc.setId(data.getDocumentID());
        updateDoc.setDocumentName(data.getFileName());
        updateDoc.setCategory(data.getCategory());
        updateDoc.setFileVersion(data.getFileVersion());
        updateDoc.setProject(data.getProject());
        updateDoc.setSecretLevel(data.getSecretLevel());
        updateDoc.setExpireDate(data.getExpireDate());
        updateDoc.setDisableDown(data.getDisableDown());
        documentBusiness.updateDocument(updateDoc);

//        int documentID = data.getDocumentID();
//
//        if (CollectionUtils.isEmpty(data.getDepartmentIDs()) && CollectionUtils.isEmpty(data.getEmployeeNos())) {
//            documentPermissionBusiness.insertDocumentPermission(documentID, Lists.newArrayList("0"), data.getExtra(), 1);
//        }
//
//        if (!CollectionUtils.isEmpty(data.getDepartmentIDs())) {
//            documentPermissionBusiness.insertDocumentPermission(documentID, data.getDepartmentIDs(), data.getExtra(), 1);
//        }
//
//        if (!CollectionUtils.isEmpty(data.getEmployeeNos())) {
//            documentPermissionBusiness.insertDocumentPermission(documentID, data.getEmployeeNos(), data.getExtra(), 2);
//        }


        return true;
    }


    public boolean delete(DeleteDocParams data) {
        return documentBusiness.delete(data);
    }

    public boolean deleteHistory(IntegerParams data) {
        return documentHistoryBusiness.delete(data);
    }
}
