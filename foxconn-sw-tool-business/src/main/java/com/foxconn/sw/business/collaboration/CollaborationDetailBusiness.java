package com.foxconn.sw.business.collaboration;

import com.foxconn.sw.business.SwAppendResourceBusiness;
import com.foxconn.sw.common.aspects.Metric;
import com.foxconn.sw.common.utils.ExcelUtils;
import com.foxconn.sw.common.utils.FilePathUtils;
import com.foxconn.sw.data.entity.SwAppendResource;
import com.foxconn.sw.data.entity.SwCollaborationDetail;
import com.foxconn.sw.data.entity.SwCollaborationDetailExample;
import com.foxconn.sw.data.mapper.extension.oa.SwCollaborationDetailExtensionMapper;
import com.google.common.collect.Lists;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
public class CollaborationDetailBusiness {
    private static final Logger logger = LoggerFactory.getLogger(CollaborationUserBusiness.class);
    @Autowired
    SwCollaborationDetailExtensionMapper collaborationDetailMapper;
    @Autowired
    SwAppendResourceBusiness resourceBusiness;
    @Autowired
    FilePathUtils filePathUtils;
    @Autowired
    SqlSessionFactory sqlSessionFactory;

    public List<SwCollaborationDetail> queryCollaborationDetail(List<Long> scuIDs) {
        if (CollectionUtils.isEmpty(scuIDs)) {
            return Lists.newArrayList();
        }
        SwCollaborationDetailExample example = new SwCollaborationDetailExample();
        SwCollaborationDetailExample.Criteria criteria = example.createCriteria();
        criteria.andScuIdIn(scuIDs);
        return collaborationDetailMapper.selectByExample(example);
    }

    public List<SwCollaborationDetail> queryCollaborationDetail(Long scuID) {
        SwCollaborationDetailExample example = new SwCollaborationDetailExample();
        SwCollaborationDetailExample.Criteria criteria = example.createCriteria();
        criteria.andScuIdEqualTo(scuID);
        return collaborationDetailMapper.selectByExample(example);
    }

    public boolean updateOrInsert(SwCollaborationDetail detail) {
        int i = 0;
        if (Objects.isNull(detail.getId())) {
            i = collaborationDetailMapper.insertSelective(detail);
        } else {
            i = collaborationDetailMapper.updateByPrimaryKeySelective(detail);
        }
        return i > 0;
    }

    public boolean updateItemValue(Integer key, String item, String value) {

        SwCollaborationDetailExample exampleSearch = new SwCollaborationDetailExample();
        SwCollaborationDetailExample.Criteria criteriaSearch = exampleSearch.createCriteria();
        criteriaSearch.andScuIdEqualTo(key.longValue());
        criteriaSearch.andItemEqualTo(item);
        List<SwCollaborationDetail> detailList = collaborationDetailMapper.selectByExample(exampleSearch);
        if (CollectionUtils.isEmpty(detailList)) {
            SwCollaborationDetail detail = new SwCollaborationDetail();
            detail.setScuId(key.longValue());
            detail.setItem(item);
            detail.setItemValue(value);
            collaborationDetailMapper.insertSelective(detail);
            return true;
        }

        SwCollaborationDetail detail = new SwCollaborationDetail();
        detail.setScuId(key.longValue());
        detail.setItem(item);
        detail.setItemValue(value);

        SwCollaborationDetailExample example = new SwCollaborationDetailExample();
        SwCollaborationDetailExample.Criteria criteria = example.createCriteria();
        criteria.andScuIdEqualTo(key.longValue());
        criteria.andItemEqualTo(item);
        return collaborationDetailMapper.updateByExampleSelective(detail, example) > 0;
    }

    public boolean readExcelContent(Long scuId, Integer resourceId) throws FileNotFoundException {

        SwAppendResource appendResource = resourceBusiness.getAppendResources(resourceId);
        String filePath = filePathUtils.getFilePath(appendResource.getUploadType()) + appendResource.getFilePath();
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(0);
            for (int i = 0; i < sheet.getLastRowNum(); i++) {
                for (int j = 0; j < sheet.getRow(i).getLastCellNum(); j++) {
                    Cell cell = sheet.getRow(i).getCell(j);
                    insert(i, scuId, j, ExcelUtils.getCellValueAsString(cell));
                }
            }
        } catch (IOException e) {
            logger.error("getTaskHeader", e);
        }
        return true;
    }

    public boolean insert(Integer rowNum, Long scuId, Integer colNum, String value) {
        SwCollaborationDetail detail = new SwCollaborationDetail();
        detail.setScuId(scuId);
        detail.setColIndex(colNum);
        detail.setItemValue(value);
        detail.setRowIndex(rowNum);
        return collaborationDetailMapper.insertSelective(detail) > 0;
    }

    @Metric
    public boolean insertBatchCollaborationUserDetail(List<Map<String, String>> maps, List<Long> userIds) {

        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
        int i = 0;
        for (Map<String, String> map : maps) {
            Long scuId = userIds.get(i++);
            for (Map.Entry<String, String> entry : map.entrySet()) {
                SwCollaborationDetail detail = new SwCollaborationDetail();
                detail.setScuId(scuId);
                detail.setItem(entry.getKey());
                detail.setItemValue(entry.getValue());
                collaborationDetailMapper.insertSelective(detail);
            }
        }
        sqlSession.commit();
        sqlSession.close();
        return true;

    }
}
