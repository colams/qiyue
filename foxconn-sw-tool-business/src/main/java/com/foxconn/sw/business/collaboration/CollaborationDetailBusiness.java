package com.foxconn.sw.business.collaboration;

import com.foxconn.sw.business.SwAppendResourceBusiness;
import com.foxconn.sw.common.utils.ExcelUtils;
import com.foxconn.sw.common.utils.FilePathUtils;
import com.foxconn.sw.data.dto.request.collaboration.CollaborationUpdateCellParams;
import com.foxconn.sw.data.entity.SwAppendResource;
import com.foxconn.sw.data.entity.SwCollaborationDetail;
import com.foxconn.sw.data.entity.SwCollaborationDetailExample;
import com.foxconn.sw.data.exception.BizException;
import com.foxconn.sw.data.mapper.auto.SwCollaborationDetailMapper;
import com.foxconn.sw.data.mapper.extension.collaboration.SwCollaborationDetailExtensionMapper;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

    public Long updateOrInsert(SwCollaborationDetail detail) {
        if (Objects.isNull(detail.getId())) {
            collaborationDetailMapper.insertSelective(detail);
            return detail.getId();
        } else {
            int effectCount = collaborationDetailMapper.updateByPrimaryKeySelective(detail);
            return Long.valueOf(effectCount);
        }
    }

    public boolean updateItemValue(Long scuID, Integer key, String item, String value) {

        SwCollaborationDetail detail = new SwCollaborationDetail();
        detail.setItemValue(value);

        SwCollaborationDetailExample example = new SwCollaborationDetailExample();
        SwCollaborationDetailExample.Criteria criteria = example.createCriteria();
        criteria.andRowIndexEqualTo(key);
        criteria.andItemEqualTo(item);
        criteria.andScuIdEqualTo(scuID);
        return collaborationDetailMapper.updateByExampleSelective(detail, example) > 0;
    }

    public SwCollaborationDetail selectCollaborationDetail(Long scuID, Integer key, String item) {
        SwCollaborationDetailExample example = new SwCollaborationDetailExample();
        SwCollaborationDetailExample.Criteria criteria = example.createCriteria();
        criteria.andRowIndexEqualTo(key);
        criteria.andItemEqualTo(item);
        criteria.andScuIdEqualTo(scuID);
        List<SwCollaborationDetail> details = collaborationDetailMapper.selectByExample(example);
        return details.get(0);
    }

    public boolean readExcelContent(Long scuId, Integer resourceId) throws FileNotFoundException {

        SwAppendResource appendResource = resourceBusiness.getAppendResources(resourceId);
        String filePath = filePathUtils.getFilePath(appendResource.getUploadType()) + appendResource.getFilePath();
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(0);
            List<String> headers = new ArrayList<>();
            for (int i = 0; i < sheet.getLastRowNum(); i++) {
                for (int j = 0; j < sheet.getRow(i).getLastCellNum(); j++) {
                    Cell cell = sheet.getRow(i).getCell(j);
                    String text = ExcelUtils.getCellValueAsString(cell);
                    if (i == 0) {
                        headers.add(text);
                    } else {
                        insert(i, scuId, j, headers.get(j), text);
                    }
                }
            }
        } catch (IOException e) {
            logger.error("getTaskHeader", e);
        }
        return true;
    }

    public boolean insert(Integer rowNum, Long scuId, Integer colNum, String header, String value) {
        SwCollaborationDetail detail = new SwCollaborationDetail();
        detail.setScuId(scuId);
        detail.setRowIndex(rowNum);
        detail.setColIndex(colNum);
        detail.setItem(header);
        detail.setItemValue(value);
        return collaborationDetailMapper.insertSelective(detail) > 0;
    }


    public boolean insertBatchCollaborationUserDetail(List<SwCollaborationDetail> collaborationDetails) {
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
        SwCollaborationDetailMapper mapper = sqlSession.getMapper(SwCollaborationDetailMapper.class);

        for (SwCollaborationDetail detail : collaborationDetails) {
            mapper.insertSelective(detail);
        }
        sqlSession.commit();
        sqlSession.close();
        return true;
    }

    public List<SwCollaborationDetail> queryCollaborationDetail(Long scuID, Integer rowIndex) {
        SwCollaborationDetailExample example = new SwCollaborationDetailExample();
        SwCollaborationDetailExample.Criteria criteria = example.createCriteria();
        criteria.andScuIdEqualTo(scuID);
        criteria.andRowIndexEqualTo(rowIndex);
        return collaborationDetailMapper.selectByExample(example);
    }

    public boolean batchUpdate(List<SwCollaborationDetail> updateDetails) {
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
        SwCollaborationDetailMapper mapper = sqlSession.getMapper(SwCollaborationDetailMapper.class);

        for (SwCollaborationDetail detail : updateDetails) {
            mapper.updateByPrimaryKeySelective(detail);
        }
        sqlSession.commit();
        sqlSession.close();
        return true;
    }

    public SwCollaborationDetail createCollaborationDetail(CollaborationUpdateCellParams data) {
        List<SwCollaborationDetail> detailList = collaborationDetailMapper.selectCollaborationsByTaskID(data.getTaskID().longValue());
        SwCollaborationDetail maxDetail = detailList.stream()
                .max(((o1, o2) -> o1.getRowIndex() - o2.getRowIndex()))
                .orElseThrow(() -> new BizException(4, "處理失敗，若重試仍失敗，請聯繫開發人員"));

        List<SwCollaborationDetail> collaborationDetails = detailList.stream()
                .filter(e -> e.getRowIndex().equals(maxDetail.getRowIndex()))
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(collaborationDetails)) {
            new BizException(4, "處理失敗，若重試仍失敗，請聯繫開發人員");
        }

        SwCollaborationDetail insertDetail;
        SwCollaborationDetail tempDetail = null;
        for (SwCollaborationDetail collaborationDetail : collaborationDetails) {
            insertDetail = new SwCollaborationDetail();
            insertDetail.setScuId(collaborationDetail.getScuId());
            insertDetail.setRowIndex(collaborationDetail.getRowIndex() + 1);
            insertDetail.setColIndex(collaborationDetail.getColIndex());
            insertDetail.setItem(collaborationDetail.getItem());
            insertDetail.setItemValue("");
            collaborationDetailMapper.insertSelective(insertDetail);
            if (collaborationDetail.getColIndex().equals(data.getColIndex())) {
                tempDetail = insertDetail;
            }
        }

        return tempDetail;
    }

    public SwCollaborationDetail selectCollaborationDetail(Long detailId) {
        return collaborationDetailMapper.selectByPrimaryKey(detailId);
    }
}
