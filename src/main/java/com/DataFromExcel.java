package com;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.util.HashMap;

public class DataFromExcel {
    File file;
    String sheetName;

    public DataFromExcel(String filePathName, String sheetName) {
        this.file = new File(filePathName);
        this.sheetName = sheetName;
    }

    // Return the single value of single key
    public String getDataOf(String keyHeaderName, String key, String returnColnHeaderName) {

        try {
            try(XSSFWorkbook workbook = new XSSFWorkbook(file)) {

                XSSFSheet sheet = workbook.getSheet(sheetName);

                int firstRowNum = sheet.getFirstRowNum();
                int lastRowNum = sheet.getLastRowNum();
                int firstColnNum = sheet.getRow(firstRowNum).getFirstCellNum();
                int lastColnNum = sheet.getRow(lastRowNum).getLastCellNum();

                int keyColnIdx = 0;
                int returnColnIdx = 0;

                // Get the key and value header column index
                for (int colnIdx = firstColnNum; colnIdx < lastColnNum; colnIdx++) {
                    String currCellValue = getCellValueOf(sheet.getRow(firstRowNum).getCell(colnIdx));
                    if (currCellValue.trim().equalsIgnoreCase(keyHeaderName)) {
                        keyColnIdx = colnIdx;
                    }
                    if (currCellValue.trim().equalsIgnoreCase(returnColnHeaderName)) {
                        returnColnIdx = colnIdx;
                    }
                }

                // Get the value of required key
                for (int rowIdx = firstRowNum; rowIdx < lastRowNum; rowIdx++) {
                    String currKey = getCellValueOf(sheet.getRow(rowIdx).getCell(keyColnIdx));
                    if (currKey.trim().equalsIgnoreCase(key)) {
                        XSSFCell requiredCell = sheet.getRow(rowIdx).getCell(returnColnIdx);
                        return getCellValueOf(requiredCell);
                    }
                }
            }
        } catch (Exception e) {
        }
        return "";
    }

    // Return values of 2 headers of all the cells
    public HashMap<String, String> getAllKeyValueData(String keyHeaderName, String returnColnHeaderName) {
        HashMap<String, String> keyAndValue = new HashMap<>();

        try {
            try(XSSFWorkbook workbook = new XSSFWorkbook(file)) {

                XSSFSheet sheet = workbook.getSheet(sheetName);

                int firstRowNum = sheet.getFirstRowNum();
                int lastRowNum = sheet.getLastRowNum();
                int firstColnNum = sheet.getRow(firstRowNum).getFirstCellNum();
                int lastColnNum = sheet.getRow(lastRowNum).getLastCellNum();

                int keyColnIdx = 0;
                int returnColnIdx = 0;

                // Get the key and value header column index
                for (int colnIdx = firstColnNum; colnIdx < lastColnNum; colnIdx++) {
                    String curr = sheet.getRow(firstRowNum).getCell(colnIdx).getStringCellValue();
                    if (curr.trim().equalsIgnoreCase(keyHeaderName)) {
                        keyColnIdx = colnIdx;
                    }
                    if (curr.trim().equalsIgnoreCase(returnColnHeaderName)) {
                        returnColnIdx = colnIdx;
                    }
                }

                // Get the value of required key (skipping the header row)
                for (int rowIdx = ++firstRowNum; rowIdx < lastRowNum; rowIdx++) {

                    XSSFCell currKeyCell = sheet.getRow(rowIdx).getCell(keyColnIdx);
                    XSSFCell currValueCell = sheet.getRow(rowIdx).getCell(returnColnIdx);

                    String currKeyValue = "";
                    String currValueValue  = "";

                    XSSFCell[] currKeyValueCell =  new XSSFCell[] {currKeyCell, currValueCell};
                    for(int i = 0; i < currKeyValueCell.length; i++) {
                        String currString = getCellValueOf(currKeyValueCell[i]);

                        if(i == 0) {
                            currKeyValue = currString;
                        } else  {
                            currValueValue = currString;
                        }
                    }
                    keyAndValue.put(currKeyValue, currValueValue);
                }
            }
        } catch (Exception e) {
        }
        return keyAndValue;
    }

    private static String getCellValueOf(XSSFCell currKeyValueCell) {
        String currString;

        currString = switch (currKeyValueCell.getCellType()) {
            case STRING -> String.valueOf(currKeyValueCell.getStringCellValue());
            case BOOLEAN -> String.valueOf(currKeyValueCell.getBooleanCellValue());
            case NUMERIC -> String.valueOf(currKeyValueCell.getNumericCellValue());
            case ERROR -> String.valueOf(currKeyValueCell.getErrorCellString());
            default -> "";
        };
        return currString;
    }
}