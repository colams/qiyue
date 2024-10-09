package com.foxconn.sw.service.processor.document;

import com.foxconn.sw.data.dto.entity.document.DocumentVo;
import com.foxconn.sw.data.dto.entity.document.HistoryVo;
import com.foxconn.sw.data.dto.entity.universal.IntegerParams;
import com.foxconn.sw.data.dto.request.document.SearchDocParams;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ListDocProcessor {


    public List<DocumentVo> list(SearchDocParams data) {
        return Lists.newArrayList();
    }

    public List<HistoryVo> history(IntegerParams data) {
        return Lists.newArrayList();
    }
}
