package com.foxconn.sw.service.processor.document;

import com.foxconn.sw.business.context.RequestContext;
import com.foxconn.sw.business.oa.SwDocumentBusiness;
import com.foxconn.sw.business.oa.SwDocumentHistoryBusiness;
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

    public boolean create(CreateDocParams params) {

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


    public boolean revise(ReviseDocParams data) {

        SwDocument document = documentBusiness.queryDocumentByID(data.getDocumentID());
        document.setDocumentName(data.getFileName());
        document.setResourceId(data.getResourceID());
        document.setCategory(data.getCategory());
        document.setSource(data.getSource());
        document.setDepartment(0);
        document.setCreator(RequestContext.getEmployeeNo());
        documentBusiness.updateDocument(document);


        SwDocumentHistory documentHistory = new SwDocumentHistory();
        documentHistory.setDocumentId(data.getDocumentID());
        documentHistory.setDocumentName(data.getFileName());
        documentHistory.setResourceId(data.getResourceID());
        documentHistory.setCreator(RequestContext.getEmployeeNo());
        documentHistoryBusiness.insertHistory(documentHistory);
        return true;
    }


    public boolean delete(DeleteDocParams data) {
        return documentBusiness.delete(data);
    }

    public boolean deleteHistory(IntegerParams data) {
        return documentHistoryBusiness.delete(data);
    }
}
