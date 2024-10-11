package com.foxconn.sw.service.processor.document;

import com.foxconn.sw.business.oa.SwDocumentBusiness;
import com.foxconn.sw.business.oa.SwDocumentHistoryBusiness;
import com.foxconn.sw.data.dto.entity.universal.IntegerParams;
import com.foxconn.sw.data.dto.request.document.CreateDocParams;
import com.foxconn.sw.data.dto.request.document.DeleteDocParams;
import com.foxconn.sw.data.dto.request.document.ReviseDocParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateDocProcessor {

    @Autowired
    SwDocumentBusiness documentBusiness;
    @Autowired
    SwDocumentHistoryBusiness documentHistoryBusiness;

    public boolean create(CreateDocParams params) {
        return documentBusiness.createDoc(params);
    }


    public boolean revise(ReviseDocParams data) {

        return true;
    }


    public boolean delete(DeleteDocParams data) {
        return documentBusiness.delete(data);
    }

    public boolean deleteHistory(IntegerParams data) {
        return documentHistoryBusiness.delete(data);
    }
}
