package com.foxconn.sw.common.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

import java.util.Objects;

public class ExcelUtils {

    public static String getCellValueAsString(Cell cell) {
        if (Objects.isNull(cell)) {
            return "";
        }

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }

    public static boolean isMerged(Sheet sheet, int row, int column) {
        for (CellRangeAddress rangeAddress : sheet.getMergedRegions()) {
            if (rangeAddress.isInRange(row, column)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isInArea(Cell cell, Integer rowStart, Integer rowEnd, Integer colStart, Integer colEnd) {
        return isGreaterOrEqual(cell.getRowIndex(), rowStart)
                || isGreaterOrEqual(cell.getColumnIndex(), colStart)
                || isLessOrEqual(cell.getRowIndex(), rowEnd)
                || isLessOrEqual(cell.getColumnIndex(), colEnd);
    }

    private static boolean isGreaterOrEqual(Integer number1, Integer number2) {
        return number1.compareTo(number2) >= 0;
    }

    private static boolean isLessOrEqual(Integer number1, Integer number2) {
        return number1.compareTo(number2) >= 0;
    }


}
