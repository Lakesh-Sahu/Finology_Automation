package in.finology.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.util.HashMap;

public class DataFromExcel extends Base {
    private static Logger log = LogManager.getLogger(DataFromExcel.class);

    File file;
    String sheetName;

    public DataFromExcel(String filePathName, String sheetName) {
        this.file = new File(filePathName);
        this.sheetName = sheetName;
    }

    // Return the single value of single key
    public String getDataOf(String keyHeaderName, String key, String returnColnHeaderName) {

        try {
            try (XSSFWorkbook workbook = new XSSFWorkbook(file)) {

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
            logWarningInLogFileAndExtentReport(log, e, "Exception while getting Data of from Excel keyHeaderName : " + keyHeaderName + " key : " + key + " returnColnHeaderName : " + returnColnHeaderName + " on DataFromExcel");
        }
        return "";
    }

    // Return values of 2 headers of all the cells
    public HashMap<String, String> getAllKeyValueData(String keyHeaderName, String returnColnHeaderName) {
        HashMap<String, String> keyAndValue = new HashMap<>();

        try {
            try (XSSFWorkbook workbook = new XSSFWorkbook(file)) {

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
                    String currValueValue = "";

                    XSSFCell[] currKeyValueCell = new XSSFCell[]{currKeyCell, currValueCell};
                    for (int i = 0; i < currKeyValueCell.length; i++) {
                        String currString = getCellValueOf(currKeyValueCell[i]);

                        if (i == 0) {
                            currKeyValue = currString;
                        } else {
                            currValueValue = currString;
                        }
                    }
                    keyAndValue.put(currKeyValue, currValueValue);
                }
            }
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while getting All Key Value Data from Excel keyHeaderName : " + keyHeaderName + " returnColnHeaderName : " + returnColnHeaderName + " on DataFromExcel");
        }
        return keyAndValue;
    }

    private static String getCellValueOf(XSSFCell currKeyValueCell) {
        try {
            String currString;
            currString = switch (currKeyValueCell.getCellType()) {
                case STRING -> String.valueOf(currKeyValueCell.getStringCellValue());
                case BOOLEAN -> String.valueOf(currKeyValueCell.getBooleanCellValue());
                case NUMERIC -> String.valueOf(currKeyValueCell.getNumericCellValue());
                case ERROR -> String.valueOf(currKeyValueCell.getErrorCellString());
                default -> "";
            };
            return currString;
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while getting Cell Value Of currKeyValueCell : " + currKeyValueCell + " on DataFromExcel");
            return "";
        }
    }
}