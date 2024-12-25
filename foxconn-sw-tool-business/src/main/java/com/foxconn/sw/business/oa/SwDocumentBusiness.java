package com.foxconn.sw.business.oa;

import com.foxconn.sw.business.system.EmployeeBusiness;
import com.foxconn.sw.common.constanst.Constants;
import com.foxconn.sw.common.constanst.NumberConstants;
import com.foxconn.sw.common.context.RequestContext;
import com.foxconn.sw.data.dto.request.document.CreateDocParams;
import com.foxconn.sw.data.dto.request.document.CreatePersonalDocParams;
import com.foxconn.sw.data.dto.request.document.DeleteDocParams;
import com.foxconn.sw.data.dto.request.document.SearchDocParams;
import com.foxconn.sw.data.entity.SwDocument;
import com.foxconn.sw.data.entity.SwDocumentExample;
import com.foxconn.sw.data.mapper.extension.oa.SwDocumentExtensionMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Component
public class SwDocumentBusiness {

    @Autowired
    SwDocumentExtensionMapper documentMapper;
    @Autowired
    EmployeeBusiness employeeBusiness;

    public Integer createDoc(CreateDocParams data) {
        SwDocument document = new SwDocument();
        document.setDocumentName(data.getFileName());
        document.setCategory(data.getCategory());
        document.setSecretLevel(data.getSecretLevel());
        document.setProject(data.getProject());
        document.setDepartment(employeeBusiness.queryEmployeeByEno(RequestContext.getEmployeeNo()).getDepartmentId());
        document.setFileVersion(data.getFileVersion());
        document.setExpireDate(data.getExpireDate());
        document.setCreator(RequestContext.getEmployeeNo());
        document.setResourceId(data.getResourceID());
        document.setDisableDown(data.getDisableDown());
        document.setAuthor(data.getAuthor());
        documentMapper.insertSelective(document);
        return document.getId();
    }

    public Integer createDoc(CreatePersonalDocParams data) {
        SwDocument document = new SwDocument();
        document.setDocumentName(data.getFileName());
        document.setCategory(data.getCategory());
        document.setProject(data.getProject());
        document.setDepartment(employeeBusiness.queryEmployeeByEno(RequestContext.getEmployeeNo()).getDepartmentId());
        document.setCreator(RequestContext.getEmployeeNo());
        document.setResourceId(data.getResourceID());
        document.setContent(data.getContent());
        document.setFileType(1);
        documentMapper.insertSelective(document);
        return document.getId();
    }


    public List<SwDocument> queryDocumentList(SearchDocParams data) {
        SwDocumentExample example = new SwDocumentExample();
        SwDocumentExample.Criteria criteria = example.createCriteria();

        if (StringUtils.isNotEmpty(data.getDocumentName())) {
            criteria.andDocumentNameLike(" %" + data.getDocumentName() + "% ");
        }

        if (StringUtils.isNotEmpty(data.getPublisher())) {
            criteria.andCreatorEqualTo(data.getPublisher());
        }

        if (StringUtils.isNotEmpty(data.getCategory())) {
            criteria.andCategoryEqualTo(data.getCategory());
        }

        int fileType = Constants.PERSONAL.equalsIgnoreCase(data.getFileType()) ? 1 : 0;
        criteria.andFileTypeEqualTo(fileType);
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

    public boolean updateDocument(SwDocument document) {
        return documentMapper.updateByPrimaryKeySelective(document) > 0;
    }

    public boolean hasSameFile(CreateDocParams params) {
        SwDocumentExample example = new SwDocumentExample();
        SwDocumentExample.Criteria criteria = example.createCriteria();
        criteria.andDocumentNameEqualTo(params.getFileName());
        criteria.andIsDeleteEqualTo(NumberConstants.ZERO);
        List<SwDocument> documents = documentMapper.selectByExample(example);
        return !CollectionUtils.isEmpty(documents);
    }
}
