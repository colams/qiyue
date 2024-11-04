package com.foxconn.sw.service.processor.project;

import com.foxconn.sw.business.project.ProjectBusiness;
import com.foxconn.sw.business.project.ProjectItemBusiness;
import com.foxconn.sw.data.dto.entity.project.HeaderVo;
import com.foxconn.sw.data.entity.SwProject;
import com.foxconn.sw.data.entity.SwProjectItem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class ProjectImportProcessor {
    private static final Logger logger = LoggerFactory.getLogger(ProjectImportProcessor.class);

    @Autowired
    ProjectBusiness projectBusiness;
    @Autowired
    ProjectItemBusiness projectItemBusiness;
    @Autowired
    private ResourceLoader resourceLoader;


    public Boolean importExcel(Object data) throws IOException {
        Resource resource = resourceLoader.getResource(ResourceUtils.CLASSPATH_URL_PREFIX + "/templates/project_list_template.xlsx");
        Workbook workbook = new XSSFWorkbook(resource.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);

        Map<Integer, List<HeaderVo>> headerVoMap = initHeaderMap(sheet);

        for (int i = 5; i < sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            List<String> values = new ArrayList<>();

            for (int j = 0; j < row.getLastCellNum(); j++) {
                Cell cell = row.getCell(j);
                if (Objects.isNull(cell)) {
                    continue;
                }
                values.add(cell.toString());

                if (j < 11) {
                    continue;
                }
                SwProjectItem projectItem = new SwProjectItem();
                projectItem.setProjectId((int) row.getCell(0).getNumericCellValue());
                projectItem.setModuleType(getText(1, j, headerVoMap));
                projectItem.setUpdateBy(getText(2, j, headerVoMap));
                projectItem.setDetailType(getText(3, j, headerVoMap));
                projectItem.setProjectItem(getText(4, j, headerVoMap));
                projectItem.setProjectValue(cell.toString());
                projectItemBusiness.insertProjectItem(projectItem);
            }

            if (Objects.isNull(row.getCell(0))) {
                continue;
            }
            SwProject project = new SwProject();
            project.setId((int) row.getCell(0).getNumericCellValue());
            project.setYears((int) row.getCell(1).getNumericCellValue());
            project.setProjectCode(values.get(2));
            project.setCustomerName(values.get(3));
            project.setFullName(values.get(4));
            project.setManufacturingModel(values.get(4));
            project.setStatus(values.get(6));
            project.setRfqTime(values.get(7));
            project.setCustomer(values.get(8));
            project.setCustomerPartNo(values.get(9));
            project.setApplication(values.get(10));
            projectBusiness.insert(project);
        }
        return false;
    }

    private String getText(Integer rowNo, Integer colNo, Map<Integer, List<HeaderVo>> headerVoMap) {
        String text = "";
        List<HeaderVo> headerVos = headerVoMap.get(rowNo);
        for (HeaderVo vo : headerVos) {
            int firstCourse = vo.getColIndex();
            int lastCourse = vo.getColIndex() + vo.getColSpan() - 1;

            if (firstCourse <= colNo && lastCourse >= colNo) {
                text = vo.getTitle();
                break;
            }
        }
        return text;
    }

    private Map<Integer, List<HeaderVo>> initHeaderMap(Sheet sheet) {
        List<HeaderVo> vos = new ArrayList<>();
        for (int i = 0; i < sheet.getNumMergedRegions(); i++) {
            CellRangeAddress rangeAddress = sheet.getMergedRegion(i);
            int firstRow = rangeAddress.getFirstRow();

            if (firstRow >= 4) {
                continue;
            }

            int firstCol = rangeAddress.getFirstColumn();

            int lastRow = rangeAddress.getLastRow();
            int lastCol = rangeAddress.getLastColumn();

            Cell cell = sheet.getRow(firstRow).getCell(firstCol);

            HeaderVo vo = new HeaderVo();
            vo.setTitle(cell.toString().replace("\n", " ").trim());
            vo.setColSpan(lastCol - firstCol + 1);
            vo.setRowSpan(lastRow - firstRow + 1);
            vo.setRowIndex(firstRow);
            vo.setColIndex(firstCol);
            vos.add(vo);
        }

        List<HeaderVo> vo4 = new ArrayList<>();
        for (int i = 0; i < sheet.getRow(4).getLastCellNum(); i++) {
            Cell cell = sheet.getRow(4).getCell(i);
            HeaderVo vo = new HeaderVo();
            vo.setTitle(cell.toString().replace("\n", " ").trim());
            vo.setColSpan(1);
            vo.setRowSpan(1);
            vo.setRowIndex(4);
            vo.setColIndex(i);
            vo4.add(vo);
        }

        Map<Integer, List<HeaderVo>> map = vos
                .stream()
                .sorted(Comparator.comparing(HeaderVo::getColIndex))
                .collect(Collectors.groupingBy(HeaderVo::getRowIndex));
        map.put(4, vo4);
        return map;
    }
}
