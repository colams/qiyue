package com.foxconn.sw.service.utils;

import com.foxconn.sw.data.dto.entity.project.HeaderVo;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ExcelProjectUtils {

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

}
