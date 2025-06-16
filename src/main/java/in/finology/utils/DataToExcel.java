package in.finology.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

public class DataToExcel extends Base {
    private static final Logger log = LogManager.getLogger(DataToExcel.class);

    public static void writeReportDataInExcel(List<Object[]> data) {
        try {
            File directory = new File(System.getProperty("user.dir") + "/excelReport/");

            if (!directory.exists()) {
                directory.mkdir();
            }

            File filePath = new File(directory, "excel_report_" + singleTimeStamp + ".xlsx");

            XSSFWorkbook workbook = new XSSFWorkbook();

            workbook.createSheet("Excel Report");
            XSSFSheet sheet = workbook.getSheet("Excel Report");

            for (int rowIdx = 0; rowIdx < data.size(); rowIdx++) {
                XSSFRow row = sheet.createRow(rowIdx);
                Object[] rowData = data.get(rowIdx);
                for (int colnIdx = 0; colnIdx < data.getFirst().length; colnIdx++) {
                    XSSFCell cell = row.createCell(colnIdx);
                    cell.setCellValue(String.valueOf(rowData[colnIdx]));
                }
            }
            FileOutputStream os = new FileOutputStream(filePath);
            workbook.write(os);

            workbook.close();
            os.close();
        } catch (Throwable e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while writing report data in the Excel file on writeReportDataInExcel of DataToExcel");
        }
    }
}
