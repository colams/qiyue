package com.foxconn.sw.service.processor.document;

import com.foxconn.sw.business.SwAppendResourceBusiness;
import com.foxconn.sw.business.account.UserBusiness;
import com.foxconn.sw.business.oa.SwDocumentBusiness;
import com.foxconn.sw.business.oa.SwDocumentHistoryBusiness;
import com.foxconn.sw.common.utils.ConvertUtils;
import com.foxconn.sw.common.utils.StringExtUtils;
import com.foxconn.sw.data.dto.entity.document.DocumentVo;
import com.foxconn.sw.data.dto.entity.document.HistoryVo;
import com.foxconn.sw.data.dto.entity.universal.IntegerParams;
import com.foxconn.sw.data.dto.request.document.SearchDocParams;
import com.foxconn.sw.data.entity.SwAppendResource;
import com.foxconn.sw.data.entity.SwDocument;
import com.foxconn.sw.data.entity.SwDocumentHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ListDocProcessor {

    @Autowired
    SwDocumentBusiness documentBusiness;
    @Autowired
    SwDocumentHistoryBusiness documentHistoryBusiness;
    @Autowired
    SwAppendResourceBusiness appendResourceBusiness;
    @Autowired
    UserBusiness userBusiness;

    public List<DocumentVo> list(SearchDocParams data) {
        List<SwDocument> documents = documentBusiness.queryDocumentList(data);
        return convert2Vo(documents);
    }

    private List<DocumentVo> convert2Vo(List<SwDocument> documents) {
        List<DocumentVo> vos = new ArrayList<>();
        documents.forEach(e -> {
            SwAppendResource resource = appendResourceBusiness.getAppendResources(e.getResourceId());
            DocumentVo vo = new DocumentVo();
            vo.setDocumentID(e.getId());
            vo.setCategory("");
            vo.setDocumentName(e.getDocumentName());
            vo.setDownloadUrl(ConvertUtils.urlPreFix(resource.getId(), resource.getFilePath()));
            vo.setViewUrl(appendResourceBusiness.getResourceUrl(resource));
//            vo.setDescription(e.getSource());
            vo.setDepartment("");
//            vo.setPublisher(e.getCreator());
            vo.setUpdateTime(StringExtUtils.toString(e.getCreateTime()));
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
            vo.setUpdateTime(StringExtUtils.toString(e.getCreateTime()));
            vos.add(vo);
        });
        return vos;
    }
}
