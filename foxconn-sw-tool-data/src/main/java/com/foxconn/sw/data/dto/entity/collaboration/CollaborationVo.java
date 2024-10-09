package com.foxconn.sw.data.dto.entity.collaboration;

import java.util.List;
import java.util.Map;

public class CollaborationVo {

    private List<String> headers;
    private List<Map<String, Object>> content;

    public List<String> getHeaders() {
        return headers;
    }

    public void setHeaders(List<String> headers) {
        this.headers = headers;
    }

    public List<Map<String, Object>> getContent() {
        return content;
    }

    public void setContent(List<Map<String, Object>> content) {
        this.content = content;
    }
}
