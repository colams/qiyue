package com.foxconn.sw.service.processor.project;

import com.foxconn.sw.business.project.ProjectBusiness;
import com.foxconn.sw.business.project.ProjectItemBusiness;
import com.foxconn.sw.data.dto.entity.project.HeaderVo;
import com.foxconn.sw.data.entity.SwProject;
import com.foxconn.sw.data.entity.SwProjectItem;
import com.foxconn.sw.service.utils.ExcelProjectUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
        Map<Integer, List<HeaderVo>> headerVoMap = ExcelProjectUtils.initHeaderMap(sheet);

        for (int i = 5; i < sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            List<String> values = new ArrayList<>();
            if (Objects.isNull(row.getCell(0))) {
                continue;
            }

            String projectIds = row.getCell(0).toString();
            System.out.println(projectIds);
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
}
