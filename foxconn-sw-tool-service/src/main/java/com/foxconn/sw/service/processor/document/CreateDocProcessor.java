package com.foxconn.sw.service.processor.document;

import com.foxconn.sw.business.context.RequestContext;
import com.foxconn.sw.business.oa.SwDocumentBusiness;
import com.foxconn.sw.business.oa.SwDocumentHistoryBusiness;
import com.foxconn.sw.business.system.DepartmentBusiness;
import com.foxconn.sw.data.dto.entity.universal.IntegerParams;
import com.foxconn.sw.data.dto.request.document.CreateDocParams;
import com.foxconn.sw.data.dto.request.document.DeleteDocParams;
import com.foxconn.sw.data.dto.request.document.ReviseDocParams;
import com.foxconn.sw.data.entity.SwDocument;
import com.foxconn.sw.data.entity.SwDocumentHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateDocProcessor {

    @Autowired
    SwDocumentBusiness documentBusiness;
    @Autowired
    SwDocumentHistoryBusiness documentHistoryBusiness;
    @Autowired
    DepartmentBusiness departmentBusiness;

    public boolean create(CreateDocParams params) {

        int documentID = documentBusiness.createDoc(params);
        return documentID > 0;
    }


    public boolean revise(ReviseDocParams data) {
        SwDocument document = documentBusiness.queryDocumentByID(data.getDocumentID());

        SwDocumentHistory documentHistory = new SwDocumentHistory();
        documentHistory.setDocumentId(document.getId());
        documentHistory.setDocumentName(document.getDocumentName());
        documentHistory.setResourceId(document.getResourceId());
        documentHistory.setCreator(RequestContext.getEmployeeNo());
        documentHistoryBusiness.insertHistory(documentHistory);

        document.setDocumentName(data.getFileName());
        document.setCategory(data.getCategory());
        document.setDescription(data.getDescription());
        document.setFileVersion(data.getFileVersion());
        document.setResourceId(data.getResourceID());
        documentBusiness.updateDocument(document);
        return true;
    }


    public boolean delete(DeleteDocParams data) {
        return documentBusiness.delete(data);
    }

    public boolean deleteHistory(IntegerParams data) {
        return documentHistoryBusiness.delete(data);
    }
}
