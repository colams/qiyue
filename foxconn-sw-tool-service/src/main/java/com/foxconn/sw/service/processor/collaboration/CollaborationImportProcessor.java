package com.foxconn.sw.service.processor.collaboration;

import com.foxconn.sw.business.collaboration.CollaborationDetailBusiness;
import com.foxconn.sw.business.collaboration.CollaborationUserBusiness;
import com.foxconn.sw.business.oa.SwTaskBusiness;
import com.foxconn.sw.common.aspects.Metric;
import com.foxconn.sw.common.context.RequestContext;
import com.foxconn.sw.common.utils.ExcelUtils;
import com.foxconn.sw.common.utils.SpringUtils;
import com.foxconn.sw.data.dto.entity.universal.IntegerParams;
import com.foxconn.sw.data.entity.SwCollaborationDetail;
import com.foxconn.sw.data.entity.SwCollaborationUser;
import com.foxconn.sw.data.exception.BizException;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.python.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class CollaborationImportProcessor {
    private static final Logger logger = LoggerFactory.getLogger(CollaborationImportProcessor.class);

    @Autowired
    CollaborationDetailBusiness collaborationDetail;
    @Autowired
    CollaborationUserBusiness collaborationUserBusiness;
    @Autowired
    CollaborationUserBusiness collaborationUser;
    @Autowired
    SwTaskBusiness taskBusiness;

    @Metric
    public Boolean importExcel(IntegerParams data, MultipartFile multipartFile) throws IOException {
        List<SwCollaborationUser> users = collaborationUser.queryCollaborationUser(data.getParams(), RequestContext.getEmployeeNo());
        List<Map<String, String>> maps = SpringUtils.getBean(CollaborationImportProcessor.class)
                .explainExcel(data.getParams(), multipartFile);

        if (!CollectionUtils.isEmpty(users)) {
            List<Long> ids = users.stream().map(e -> e.getId()).collect(Collectors.toList());
            collaborationUser.deleteCollaborationUser(ids);
        }

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        List<SwCollaborationUser> collaborationUsers = new ArrayList<>();
        maps.stream().forEach(e -> {
            SwCollaborationUser user = new SwCollaborationUser();
            user.setTaskId(data.getParams());
            user.setEmployeeNo(RequestContext.getEmployeeNo());
            collaborationUsers.add(user);
        });
        List<List<SwCollaborationUser>> listList = Lists.partition(collaborationUsers, 100);
        listList.parallelStream().forEach(e -> {
            collaborationUser.insertBatchCollaborationUser(e);
        });
        stopWatch.stop();
        logger.info(maps.size() + "-insertBatchCollaborationUser-:" + stopWatch.getTime());
        stopWatch.reset();
        stopWatch.start();

        List<Long> userIds = collaborationUser.queryCollaborationUserIds(data.getParams());

        stopWatch.stop();
        logger.info("-queryCollaborationUserIds-:" + stopWatch.getTime());
        stopWatch.reset();
        stopWatch.start();

        List<SwCollaborationDetail> collaborationDetails = new ArrayList<>();

        int i = 0;
        for (Map<String, String> map : maps) {
            Long scuId = userIds.get(i++);
            for (Map.Entry<String, String> entry : map.entrySet()) {
                SwCollaborationDetail detail = new SwCollaborationDetail();
                detail.setScuId(scuId);
                detail.setItem(entry.getKey());
                detail.setItemValue(entry.getValue());
                collaborationDetails.add(detail);
            }
        }

        stopWatch.stop();
        logger.info(collaborationDetails.size() + "-collaborationDetails-:" + stopWatch.getTime());
        stopWatch.reset();
        stopWatch.start();

        List<List<SwCollaborationDetail>> lists = Lists.partition(collaborationDetails, 100);
        lists.parallelStream().forEach(e -> {
            collaborationDetail.insertBatchCollaborationUserDetail(e);
        });

        stopWatch.stop();
        logger.info("-insertBatchCollaborationUserDetail-:" + stopWatch.getTime());
        return true;
    }

    @Metric
    public List<Map<String, String>> explainExcel(Integer taskID, MultipartFile multipartFile) throws IOException {
        Map<Integer, String> headMap = new HashMap<>();
        Sheet sheet = SpringUtils.getBean(CollaborationImportProcessor.class).getExcelSheet(taskID, multipartFile, headMap);
        List<Map<String, String>> mapList = row2Map(sheet, headMap);
        return mapList;
    }

    @Metric
    public List<Map<String, String>> row2Map(Sheet sheet, Map<Integer, String> headMap) {

        List<Map<String, String>> mapList = new ArrayList<>();
        for (int rowNo = 1; rowNo <= sheet.getLastRowNum(); rowNo++) {
            Map<String, String> map = new HashMap<>();
            Row row = sheet.getRow(rowNo);
            for (Map.Entry<Integer, String> entry : headMap.entrySet()) {
                map.put(entry.getValue(), ExcelUtils.getCellValueAsString(row.getCell(entry.getKey())));
            }
            mapList.add(map);
        }
        return mapList;
    }

    public Sheet getExcelSheet(Integer taskID, MultipartFile multipartFile, Map<Integer, String> headMap) throws IOException {
        List<String> header = collaborationUser.getTaskHeader(taskID);

        Workbook workbook = new XSSFWorkbook(multipartFile.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        Map<Integer, String> headMap1 = getFileHeader(sheet, header);

        if (headMap1.size() != header.size()) {
            throw new BizException(4, "上傳文件不識別！");
        }
        headMap.putAll(headMap1);
        return sheet;
    }

    public Map<Integer, String> getFileHeader(Sheet sheet, List<String> header) {
        Map<Integer, String> map = new HashMap<>();
        int i = 0;
        for (Cell cell : sheet.getRow(0)) {
            if (header.contains(cell.getStringCellValue())) {
                map.put(i++, cell.getStringCellValue());
            }
        }
        return map;
    }
}
