package com.foxconn.sw.data.dto.request.document;

import java.util.List;

public class DeleteDocParams {

    private List<Integer> documentIDs;

    public List<Integer> getDocumentIDs() {
        return documentIDs;
    }

    public void setDocumentIDs(List<Integer> documentIDs) {
        this.documentIDs = documentIDs;
    }
}
