package com.foxconn.sw.service.processor.project;

import com.foxconn.sw.data.context.RequestContext;
import com.foxconn.sw.business.project.ProjectBusiness;
import com.foxconn.sw.business.project.ProjectItemBusiness;
import com.foxconn.sw.business.system.DepartmentBusiness;
import com.foxconn.sw.business.system.EmployeeBusiness;
import com.foxconn.sw.data.dto.entity.KvPairs;
import com.foxconn.sw.data.dto.entity.project.Header2Vo;
import com.foxconn.sw.data.dto.entity.project.HeaderVo;
import com.foxconn.sw.data.dto.entity.project.ProjectItemVo;
import com.foxconn.sw.data.dto.entity.project.ProjectListVo;
import com.foxconn.sw.data.entity.SwDepartment;
import com.foxconn.sw.data.entity.SwEmployee;
import com.foxconn.sw.data.entity.SwProject;
import com.foxconn.sw.data.entity.SwProjectItem;
import com.foxconn.sw.service.utils.ExcelProjectUtils;
import com.google.common.collect.Lists;
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
import java.lang.reflect.Field;
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
    @Autowired
    DepartmentBusiness departmentBusiness;
    @Autowired
    EmployeeBusiness employeeBusiness;


    public ProjectListVo list() throws IOException {
        List<List<HeaderVo>> headers = initHeader();
        List<Header2Vo> headers2 = initHeader2();
        ProjectListVo vo = new ProjectListVo();
        vo.setHeader(headers);
        vo.setHeader2(headers2);
        vo.setProjectItems(getProjectItems(headers.get(4)));
        return vo;
    }


    private List<List<HeaderVo>> initHeader() throws IOException {
        Resource resource = resourceLoader.getResource(ResourceUtils.CLASSPATH_URL_PREFIX + "/templates/project_list_template.xlsx");
        Workbook workbook = new XSSFWorkbook(resource.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        Map<Integer, List<HeaderVo>> map = ExcelProjectUtils.initHeaderMap(sheet);
        return Lists.newArrayList(map.get(0), map.get(1), map.get(2), map.get(3), map.get(4));
    }

    private List<Header2Vo> initHeader2() throws IOException {
        Resource resource = resourceLoader.getResource(ResourceUtils.CLASSPATH_URL_PREFIX + "/templates/project_list_template.xlsx");
        Workbook workbook = new XSSFWorkbook(resource.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        List<Header2Vo> header2Vos = ExcelProjectUtils.initHeaderMap2(sheet);
        return header2Vos;
    }


    private List<ProjectItemVo> getProjectItems(List<HeaderVo> headerVos) {
        List<SwProject> projectList = projectBusiness.queryProjectList();
        List<SwProjectItem> projectItems = projectItemBusiness.queryProjectItems();
        SwEmployee swEmployee = employeeBusiness.selectEmployeeByENo(RequestContext.getEmployeeNo());
        SwDepartment department = departmentBusiness.getDepartment(swEmployee.getDepartmentId());
        boolean hasPower = department.getLevel() <= 3
                || "F5400227".equalsIgnoreCase(RequestContext.getEmployeeNo())
                || "G1658973".equalsIgnoreCase(RequestContext.getEmployeeNo());
        List<ProjectItemVo> vos = Lists.newArrayList();

        projectList.forEach(e -> {
            ProjectItemVo vo = initVo(e, projectItems, headerVos, hasPower);
            vos.add(vo);
        });

        return vos;
    }

    private ProjectItemVo initVo(SwProject project,
                                 List<SwProjectItem> projectItems,
                                 List<HeaderVo> headerVos,
                                 boolean hasPower) {
        List<SwProjectItem> items = projectItems
                .stream()
                .filter(e -> e.getProjectId().equals(project.getId()))
                .collect(Collectors.toList());

        ProjectItemVo vo = new ProjectItemVo();
        vo.setId(project.getId());
        vo.setYear(new KvPairs(project.getYears(), false));
        vo.setKvPairsMap(initPairMap(project, items, headerVos, hasPower));
        return vo;
    }

    private Map<String, KvPairs<String, Boolean>> initPairMap(SwProject project,
                                                              List<SwProjectItem> items,
                                                              List<HeaderVo> headerVos,
                                                              boolean hasPower) {
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
            String textValue;
            boolean power = hasPower;
            if (i <= 10) {
                power = false;
                textValue = getProjectText(project, i);
            } else {
                textValue = map.getOrDefault(header.getTitle(), "");
            }
            processMap(kvPairsMap, header.getTitle(), textValue, power);
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

    private void processMap(Map<String, KvPairs<String, Boolean>> kvPairsMap,
                            String title,
                            String text,
                            boolean hasPower) {
        kvPairsMap.put(title, new KvPairs<>(text, hasPower));
    }

    public static Map<String, Object> convert(SwProject obj) throws IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        for (Field field : SwProject.class.getDeclaredFields()) {
            field.setAccessible(true);
            String fieldName = field.getName();
            map.put(field.getName(), field.get(obj));
        }
        return map;
    }

    private static HashMap<String, String> tableMap = new HashMap<>();
    private static HashMap<String, String> tableMap2 = new HashMap<>();

    static {
        tableMap.put("years", "Year");
        tableMap.put("project_code", "Project Code");
        tableMap.put("customer_name", "Customer name");
        tableMap.put("full_name", "Full name");
        tableMap.put("manufacturing_model", "Manufacturing Model");
        tableMap.put("status", "Status");
        tableMap.put("rfq_time", "RFQ時間");
        tableMap.put("customer", "Customer");
        tableMap.put("customer_part_no", "Customer Part NO.(Model)");
        tableMap.put("application", "Application");

        tableMap2.put("Year", "years");
        tableMap2.put("Project Code", "project_code");
        tableMap2.put("Customer name", "customer_name");
        tableMap2.put("Full name", "full_name");
        tableMap2.put("Manufacturing Model", "manufacturing_model");
        tableMap2.put("Status", "status");
        tableMap2.put("RFQ時間", "rfq_time");
        tableMap2.put("Customer", "customer");
        tableMap2.put("Customer Part NO.(Model)", "customer_part_no");
        tableMap2.put("Application", "application");
    }
}
