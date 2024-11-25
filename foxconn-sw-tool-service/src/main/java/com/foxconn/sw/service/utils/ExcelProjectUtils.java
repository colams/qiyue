package com.foxconn.sw.service.utils;

import com.foxconn.sw.common.utils.ExcelUtils;
import com.foxconn.sw.data.dto.entity.project.Header2Vo;
import com.foxconn.sw.data.dto.entity.project.HeaderVo;
import com.foxconn.sw.data.dto.entity.project.ProjectItemVo;
import com.foxconn.sw.data.dto.entity.project.ProjectListVo;
import com.google.common.collect.Lists;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class ExcelProjectUtils {

    @Autowired
    private ResourceLoader resourceLoader;

    private static List<HeaderVo> initSingleCellHeader(Sheet sheet) {
        List<HeaderVo> vos = new ArrayList<>();
        for (int i = 0; i < sheet.getLastRowNum(); i++) {
            if (i > 4) {
                break;
            }
            for (int index = 0; index < sheet.getRow(i).getLastCellNum(); index++) {
                if (ExcelUtils.isMerged(sheet, i, index)) {
                    continue;
                }
                HeaderVo vo = new HeaderVo();
                vo.setTitle(sheet.getRow(i).getCell(index).toString().replace("\n", " ").trim());
                vo.setColSpan(1);
                vo.setRowSpan(1);
                vo.setRowIndex(i);
                vo.setColIndex(index);
                vos.add(vo);
            }
        }
        return vos;
    }


    public static Map<Integer, List<HeaderVo>> initHeaderMap(Sheet sheet) {
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
        List<HeaderVo> vos1 = initSingleCellHeader(sheet);
        if (!CollectionUtils.isEmpty(vos1)) {
            vos.addAll(vos1);
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

    public static List<Header2Vo> initHeaderMap2(Sheet sheet) {
        List<Header2Vo> header2Vos = new ArrayList<>();
        Header2Vo header2Vo = new Header2Vo();
        header2Vos.add(header2Vo);

        header2Vo.setTitle("Project List");
        header2Vo.setHeader2Vos(initHeaderVos());
        return header2Vos;
    }

    private static List<Header2Vo> initHeaderVos() {
        List<Header2Vo> header2Vos = new ArrayList<>();
        Header2Vo header2Vo1 = new Header2Vo();
        header2Vo1.setTitle("LH Project Info.");
        header2Vo1.setHeader2Vos(initHeaderVos1());

        Header2Vo header2Vo2 = new Header2Vo();
        header2Vo2.setTitle("Module Info.");
        header2Vo2.setHeader2Vos(initHeaderVos2());

        Header2Vo header2Vo3 = new Header2Vo();
        header2Vo3.setTitle("Key Materials Spec.");
        header2Vo3.setHeader2Vos(initHeaderVos2());

//        Header2Vo header2Vo3 = new Header2Vo();
//        header2Vo3.setTitle("Key Materials Spec.");
//        header2Vo3.setHeader2Vos(initHeaderVos2());


        header2Vos.add(header2Vo1);
        header2Vos.add(header2Vo2);
        return header2Vos;
    }

    private static List<Header2Vo> initHeaderVos1() {
        return Lists.newArrayList();
    }

    private static List<Header2Vo> initHeaderVos2() {
        return Lists.newArrayList();
    }

    private static void processHeaders(List<Header2Vo> header2Vos, Header2Vo header2Vo) {
        header2Vos.add(header2Vo);
    }

    public List<Header2Vo> test(Integer rowIndex, Row row, List<Header2Vo> header2Vos) {
        List<Header2Vo> tempHeaderVos = new ArrayList<>();
        for (Cell cell : row) {
            Header2Vo header2Vo = new Header2Vo();
            tempHeaderVos.add(header2Vo);

            if (Objects.isNull(cell)) {

            }

            header2Vo.setTitle(cell.toString().replace("\n", " ").trim());
            header2Vo.setRowIndexStart(cell.getRowIndex());
            header2Vo.setColIndexStart(cell.getColumnIndex());
            header2Vo.setRowIndexEnd(cell.getRowIndex());
            header2Vo.setColIndexEnd(cell.getColumnIndex());

            Header2Vo tempHeader2Vo = header2Vos.stream()
                    .filter(e -> ExcelUtils.isInArea(cell, e.getRowIndexStart(), e.getRowIndexEnd(), e.getColIndexStart(), e.getColIndexEnd()))
                    .findFirst()
                    .orElse(null);

            if (Objects.isNull(tempHeader2Vo)) {
                break;
            }
            tempHeader2Vo.getHeader2Vos().add(header2Vo);
        }

        List<Header2Vo> vos = new ArrayList<>();
        return vos;
    }


    public Workbook generateExcel(ProjectListVo listVo) throws IOException {
        Resource resource = resourceLoader.getResource(ResourceUtils.CLASSPATH_URL_PREFIX + "/templates/project_list_template_2.xlsx");
        Workbook workbook = new XSSFWorkbook(resource.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        int i = 5;

        for (ProjectItemVo vo : listVo.getProjectItems()) {
            Row row = sheet.createRow(i++);
            int cellIndex = 0;
            Cell noCell = row.createCell(cellIndex++);
            noCell.setCellValue(vo.getId());

            for (HeaderVo headerVo : listVo.getHeader().get(4)) {
                if (headerVo.getTitle().equalsIgnoreCase("No.")) {
                    continue;
                }
                Cell cell = row.createCell(cellIndex++);
                cell.setCellValue(vo.getKvPairsMap().get(headerVo.getTitle()).getText());
            }
        }

        return workbook;
    }
}
