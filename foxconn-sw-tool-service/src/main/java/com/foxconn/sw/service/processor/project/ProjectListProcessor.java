package com.foxconn.sw.service.processor.project;

import com.foxconn.sw.business.project.ProjectBusiness;
import com.foxconn.sw.business.project.ProjectItemBusiness;
import com.foxconn.sw.data.dto.entity.KvPairs;
import com.foxconn.sw.data.dto.entity.project.HeaderVo;
import com.foxconn.sw.data.dto.entity.project.ProjectItemVo;
import com.foxconn.sw.data.dto.entity.project.ProjectListVo;
import com.foxconn.sw.data.entity.SwProject;
import com.foxconn.sw.data.entity.SwProjectItem;
import com.google.common.collect.Lists;
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
import org.springframework.util.CollectionUtils;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ProjectListProcessor {
    private static final Logger logger = LoggerFactory.getLogger(ProjectListProcessor.class);

    @Autowired
    ProjectBusiness projectBusiness;
    @Autowired
    ProjectItemBusiness projectItemBusiness;
    @Autowired
    private ResourceLoader resourceLoader;


    public ProjectListVo list() {
        List<List<HeaderVo>> headers = initHeader();
        ProjectListVo vo = new ProjectListVo();
        vo.setHeader(initHeader());
        vo.setProjectItems(getProjectItems(headers.get(4)));
        return vo;
    }


    private List<List<HeaderVo>> initHeader() {
        Resource resource = resourceLoader.getResource(ResourceUtils.CLASSPATH_URL_PREFIX + "/templates/project_list_template.xlsx");

        HeaderVo row1Cell = new HeaderVo();
        row1Cell.setRowSpan(0);
        row1Cell.setColSpan(70);
        row1Cell.setTitle("Project List");
        List<HeaderVo> row1 = Lists.newArrayList(row1Cell);

        HeaderVo row2Cell1 = new HeaderVo();
        row2Cell1.setRowSpan(3);
        row2Cell1.setColSpan(11);
        row2Cell1.setTitle("LH Project  Info.");

        HeaderVo row2Cell2 = new HeaderVo();
        row2Cell2.setRowSpan(0);
        row2Cell2.setColSpan(11);
        row2Cell2.setTitle("Module Info.");

        HeaderVo row2Cell3 = new HeaderVo();
        row2Cell3.setRowSpan(0);
        row2Cell3.setColSpan(30);
        row2Cell3.setTitle("Key Materials Spec.");

        HeaderVo row2Cell4 = new HeaderVo();
        row2Cell4.setRowSpan(0);
        row2Cell4.setColSpan(9);
        row2Cell4.setTitle("Process");

        HeaderVo row2Cell5 = new HeaderVo();
        row2Cell5.setRowSpan(0);
        row2Cell5.setColSpan(9);
        row2Cell5.setTitle("Team Roster");
        List<HeaderVo> row2 = Lists.newArrayList(row2Cell1, row2Cell2, row2Cell3, row2Cell4, row2Cell5);

        HeaderVo row3Cell1 = new HeaderVo();
        row3Cell1.setRowSpan(0);
        row3Cell1.setColSpan(11);
        row3Cell1.setTitle("Update by ME");

        HeaderVo row3Cell2 = new HeaderVo();
        row3Cell2.setRowSpan(0);
        row3Cell2.setColSpan(11);
        row3Cell2.setTitle("Update by EE");

        HeaderVo row3Cell3 = new HeaderVo();
        row3Cell3.setRowSpan(0);
        row3Cell3.setColSpan(30);
        row3Cell3.setTitle("Update by OT");

        HeaderVo row3Cell4 = new HeaderVo();
        row3Cell4.setRowSpan(0);
        row3Cell4.setColSpan(9);
        row3Cell4.setTitle("Update by AA - NPD");

        HeaderVo row3Cell5 = new HeaderVo();
        row3Cell5.setRowSpan(0);
        row3Cell5.setColSpan(7);
        row3Cell5.setTitle("Update by TE");

        HeaderVo row3Cell6 = new HeaderVo();
        row3Cell6.setRowSpan(0);
        row3Cell6.setColSpan(9);
        row3Cell6.setTitle("Update by  All");
        List<HeaderVo> row3 = Lists.newArrayList(row3Cell1, row3Cell2, row3Cell3, row3Cell4, row3Cell5, row3Cell6);

        HeaderVo row4Cell1 = new HeaderVo();
        row4Cell1.setRowSpan(0);
        row4Cell1.setColSpan(10);
        row4Cell1.setTitle("Module Info.");

        HeaderVo row4Cell2 = new HeaderVo();
        row4Cell2.setRowSpan(0);
        row4Cell2.setColSpan(0);
        row4Cell2.setTitle("LCB concern");

        HeaderVo row4Cell3 = new HeaderVo();
        row4Cell3.setRowSpan(0);
        row4Cell3.setColSpan(3);
        row4Cell3.setTitle("Glass");

        HeaderVo row4Cell4 = new HeaderVo();
        row4Cell4.setRowSpan(0);
        row4Cell4.setColSpan(3);
        row4Cell4.setTitle("Stiffener");

        HeaderVo row4Cell5 = new HeaderVo();
        row4Cell5.setRowSpan(0);
        row4Cell5.setColSpan(1);
        row4Cell5.setTitle("VCM");

        HeaderVo row4Cell6 = new HeaderVo();
        row4Cell6.setRowSpan(0);
        row4Cell6.setColSpan(10);
        row4Cell6.setTitle("Sensor");

        HeaderVo row4Cell7 = new HeaderVo();
        row4Cell7.setRowSpan(0);
        row4Cell7.setColSpan(4);
        row4Cell7.setTitle("Connector");

        HeaderVo row4Cell9 = new HeaderVo();
        row4Cell9.setRowSpan(0);
        row4Cell9.setColSpan(3);
        row4Cell9.setTitle("FPC");

        HeaderVo row4Cell10 = new HeaderVo();
        row4Cell10.setRowSpan(0);
        row4Cell10.setColSpan(1);
        row4Cell10.setTitle("OIS");

        HeaderVo row4Cell12 = new HeaderVo();
        row4Cell12.setRowSpan(0);
        row4Cell12.setColSpan(5);
        row4Cell12.setTitle("Lens");

        HeaderVo row4Cell13 = new HeaderVo();
        row4Cell13.setRowSpan(0);
        row4Cell13.setColSpan(2);
        row4Cell13.setTitle("AA");

        HeaderVo row4Cell14 = new HeaderVo();
        row4Cell14.setRowSpan(0);
        row4Cell14.setColSpan(7);
        row4Cell14.setTitle("Testing");

        HeaderVo row4Cell15 = new HeaderVo();
        row4Cell15.setRowSpan(0);
        row4Cell15.setColSpan(9);
        row4Cell15.setTitle("");
        List<HeaderVo> row4 = Lists.newArrayList(row4Cell1, row4Cell2, row4Cell3, row4Cell4, row4Cell5, row4Cell6, row4Cell7, row4Cell9, row4Cell10, row4Cell12, row4Cell13, row4Cell14, row4Cell15);

        List<List<HeaderVo>> headerVos = Lists.newArrayList(row1, row2, row3, row4);

        try (Workbook workbook = new XSSFWorkbook(resource.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            Row row = sheet.getRow(4);
            List<HeaderVo> result = Lists.newArrayList();
            for (Cell cell : row) {
                HeaderVo vo = new HeaderVo();
                vo.setTitle(cell.getStringCellValue().replace("\n", " ").trim());
                vo.setColSpan(0);
                vo.setRowSpan(0);
                result.add(vo);
            }
            headerVos.add(result);
        } catch (IOException e) {
            logger.error("getTaskHeader", e);
        }
        return headerVos;
    }

    private List<ProjectItemVo> getProjectItems(List<HeaderVo> headerVos) {
        List<SwProject> projectList = projectBusiness.queryProjectList();
        List<SwProjectItem> projectItems = projectItemBusiness.queryProjectItems();

        List<ProjectItemVo> vos = Lists.newArrayList();

        projectList.forEach(e -> {
            ProjectItemVo vo = initVo(e, projectItems, headerVos);
            vos.add(vo);
        });

        return vos;
    }

    private ProjectItemVo initVo(SwProject project, List<SwProjectItem> projectItems, List<HeaderVo> headerVos) {
        List<SwProjectItem> items = projectItems
                .stream()
                .filter(e -> e.getProjectId().equals(project.getId()))
                .collect(Collectors.toList());

        ProjectItemVo vo = new ProjectItemVo();
        vo.setId(project.getId());
        vo.setYear(new KvPairs(project.getYears(), false));
        vo.setKvPairsMap(initPairMap(project, items, headerVos));
        return vo;
    }

    private Map<String, KvPairs<String, Boolean>> initPairMap(SwProject project, List<SwProjectItem> items, List<HeaderVo> headerVos) {
        Map<String, KvPairs<String, Boolean>> kvPairsMap = new HashMap<>();

        headerVos.forEach(e -> {
            if (e.getTitle().equalsIgnoreCase("No.")) {
                return;
            }
            kvPairsMap.put(e.getTitle(), new KvPairs<>(e.getTitle(), true));
        });
        Map<String, String> map = new HashMap<>();
        if (!CollectionUtils.isEmpty(items)) {
            map = items.stream()
                    .collect(Collectors.toMap(SwProjectItem::getProjectItem, SwProjectItem::getProjectValue));
        }

        for (int i = 1; i < headerVos.size(); i++) {
            HeaderVo header = headerVos.get(i);
            if (i <= 10) {
                String textValue = getProjectText(project, i);
                processMap(kvPairsMap, header.getTitle(), textValue);
            } else {
                processMap(kvPairsMap, header.getTitle(), map.getOrDefault(header.getTitle(), ""));
            }
        }

        return kvPairsMap;
    }

    private String getProjectText(SwProject project, int index) {
        if (index == 1) {
            return project.getYears().toString();
        } else if (index == 2) {
            return project.getProjectCode();
        } else if (index == 3) {
            return project.getCustomerName();
        } else if (index == 4) {
            return project.getFullName();
        } else if (index == 5) {
            return project.getManufacturingModel();
        } else if (index == 6) {
            return project.getStatus();
        } else if (index == 7) {
            return project.getRfqTime();
        } else if (index == 8) {
            return project.getCustomer();
        } else if (index == 9) {
            return project.getCustomerPartNo();
        } else if (index == 10) {
            return project.getApplication();
        }
        return "";
    }

    private void processMap(Map<String, KvPairs<String, Boolean>> kvPairsMap, String title, String text) {
        kvPairsMap.put(title, new KvPairs<>(text, true));
    }
}
