package com.foxconn.sw.business.oa;

import com.foxconn.sw.business.context.RequestContext;
import com.foxconn.sw.data.dto.request.document.CreateDocParams;
import com.foxconn.sw.data.dto.request.document.DeleteDocParams;
import com.foxconn.sw.data.dto.request.document.SearchDocParams;
import com.foxconn.sw.data.entity.SwDocument;
import com.foxconn.sw.data.entity.SwDocumentExample;
import com.foxconn.sw.data.mapper.extension.oa.SwDocumentExtensionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SwDocumentBusiness {

    @Autowired
    SwDocumentExtensionMapper documentMapper;

    public Boolean createDoc(CreateDocParams data) {
        SwDocument document = new SwDocument();
        document.setDocumentName(data.getFileName());
        document.setResourceId(data.getSourceID());
        document.setCreator(RequestContext.getEmployeeNo());
        document.setSource(data.getSource());
        document.setCategory(data.getCategory());
        return documentMapper.insertSelective(document) > 0;
    }

    public List<SwDocument> queryDocumentList(SearchDocParams data) {
        SwDocumentExample example = new SwDocumentExample();
        SwDocumentExample.Criteria criteria = example.createCriteria();
        return documentMapper.selectByExample(example);
    }

    public SwDocument queryDocumentByID(Integer params) {
        return documentMapper.selectByPrimaryKey(params);
    }

    public boolean delete(DeleteDocParams data) {
        SwDocumentExample example = new SwDocumentExample();
        SwDocumentExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(data.getDocumentIDs());
        return documentMapper.deleteByExample(example) > 0;
    }
}
