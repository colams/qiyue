package com.foxconn.sw.service.processor.document;

import com.foxconn.sw.data.dto.request.document.CreateDocParams;
import com.foxconn.sw.data.dto.request.document.DeleteDocParams;
import org.springframework.stereotype.Component;

@Component
public class CreateDocProcessor {

    public boolean create(CreateDocParams params) {

        return true;
    }

    public boolean delete(DeleteDocParams data) {
        return true;
    }
}
