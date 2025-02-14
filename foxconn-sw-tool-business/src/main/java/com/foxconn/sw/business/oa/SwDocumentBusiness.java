package com.foxconn.sw.business.oa;

import com.foxconn.sw.business.system.EmployeeBusiness;
import com.foxconn.sw.common.constanst.Constants;
import com.foxconn.sw.common.constanst.NumberConstants;
import com.foxconn.sw.data.context.RequestContext;
import com.foxconn.sw.data.dto.PageParams;
import com.foxconn.sw.data.dto.request.document.CreateDocParams;
import com.foxconn.sw.data.dto.request.document.CreatePersonalDocParams;
import com.foxconn.sw.data.dto.request.document.DeleteDocParams;
import com.foxconn.sw.data.dto.request.document.SearchDocParams;
import com.foxconn.sw.data.entity.SwDocument;
import com.foxconn.sw.data.entity.SwDocumentExample;
import com.foxconn.sw.data.mapper.extension.oa.SwDocumentExtensionMapper;
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
        document.setDepartment(employeeBusiness.selectEmployeeByENo(RequestContext.getEmployeeNo()).getDepartmentId());
        document.setFileVersion(data.getFileVersion());
        document.setCreator(RequestContext.getEmployeeNo());
        document.setResourceId(data.getResourceID());
        document.setDisableDown(data.getDisableDown());
        document.setAuthor(data.getAuthor());

        document.setWorkType(data.getWorkType());
        document.setMainType(data.getMainType());
        document.setSubType(data.getSubType());
        document.setMainPart(data.getMainPart());
        document.setSupplier(data.getSupplier());
        document.setSource(data.getSource());
        document.setExpireDate(data.getExpireDate());

        document.setPhase(data.getPhase());
        document.setConfig(data.getConfig());
        document.setIssueMode(data.getIssueMode());
        document.setProcess(data.getProcess());
        document.setStage(data.getStage());

        documentMapper.insertSelective(document);
        return document.getId();
    }

    public Integer createDoc(CreatePersonalDocParams data) {
        SwDocument document = new SwDocument();
        document.setDocumentName(data.getFileName());
        document.setCategory(data.getCategory());
        document.setProject(data.getProject());
        document.setDepartment(employeeBusiness.selectEmployeeByENo(RequestContext.getEmployeeNo()).getDepartmentId());
        document.setCreator(RequestContext.getEmployeeNo());
        document.setResourceId(data.getResourceID());
        document.setContent(data.getContent());
        document.setFileType(1);
        documentMapper.insertSelective(document);
        return document.getId();
    }


    public List<SwDocument> queryDocumentList(PageParams<SearchDocParams> data) {
        int start = (data.getCurrentPage() - 1) * data.getPageSize();
        int fileType = Constants.PERSONAL.equalsIgnoreCase(data.getParams().getFileType()) ? 1 : 0;
        String employeeNo = "";
        if (Constants.PERSONAL.equalsIgnoreCase(data.getParams().getFileType())) {
            employeeNo = RequestContext.getEmployeeNo();
        }
        return documentMapper.queryDocumentListPages(data.getParams(), start, data.getPageSize(), employeeNo);
    }

    public Long getTotalCountByParams(SearchDocParams params) {
        int fileType = Constants.PERSONAL.equalsIgnoreCase(params.getFileType()) ? 1 : 0;
        String employeeNo = "";
        if (Constants.PERSONAL.equalsIgnoreCase(params.getFileType())) {
            employeeNo = RequestContext.getEmployeeNo();
        }
        return documentMapper.getTotalCountByParams(params, employeeNo);
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

    public boolean hasSameFile(CreateDocParams params, Integer fileType) {
        SwDocumentExample example = new SwDocumentExample();
        SwDocumentExample.Criteria criteria = example.createCriteria();
        criteria.andDocumentNameEqualTo(params.getFileName());
        criteria.andIsDeleteEqualTo(NumberConstants.ZERO);
        criteria.andFileTypeEqualTo(fileType);
        if (NumberConstants.ONE.equals(fileType)) {
            criteria.andCreatorEqualTo(RequestContext.getEmployeeNo());
        }
        List<SwDocument> documents = documentMapper.selectByExample(example);
        return !CollectionUtils.isEmpty(documents);
    }
}
