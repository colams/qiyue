package com.foxconn.sw.business.oa;

import com.foxconn.sw.data.dto.entity.universal.IntegerParams;
import com.foxconn.sw.data.entity.SwDocumentHistory;
import com.foxconn.sw.data.entity.SwDocumentHistoryExample;
import com.foxconn.sw.data.mapper.extension.oa.SwDocumentHistoryExtensionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SwDocumentHistoryBusiness {

    @Autowired
    SwDocumentHistoryExtensionMapper documentHistoryMapper;

    public List<SwDocumentHistory> queryDocumentHistoryList(Integer documentID) {
        SwDocumentHistoryExample example = new SwDocumentHistoryExample();
        SwDocumentHistoryExample.Criteria criteria = example.createCriteria();
        criteria.andDocumentIdEqualTo(documentID);
        return documentHistoryMapper.selectByExample(example);
    }


    public boolean delete(IntegerParams data) {
        return documentHistoryMapper.deleteByPrimaryKey(data.getParams()) > 0;
    }
}
