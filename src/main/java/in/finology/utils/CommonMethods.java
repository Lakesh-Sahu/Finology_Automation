package in.finology.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Random;

public class CommonMethods extends Base {
    private static Logger log = LogManager.getLogger(CommonMethods.class);

    public boolean click(WebDriver driver, WebElement we) {
        try {
            scrollTO(driver, we);
            we.click();
            Thread.sleep(1000);
            return true;
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while scrolling and clicking a WebElement on CommonMethods");
            return false;
        }
    }

    public boolean sendKeys(WebElement we, String keyToSend) {
        try {
            we.clear();
            we.sendKeys(keyToSend);
            Thread.sleep(1000);
            return true;
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while clearing and sending keys to a WebElement on CommonMethods");
            return false;
        }
    }

    // Scroll to a particular element using java script executor
    public void scrollTO(WebDriver driver, WebElement we) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView({behavior : 'smooth', block : 'center', inline : 'center'});", we);
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while scrolling to a WebElement on CommonMethods");
        }
    }

    public boolean compareTwoStringsRemoveSpaceIgnoreCase(String s1, String s2) {
        try {
            return s1.replaceAll(" ", "").equalsIgnoreCase(s2.replaceAll(" ", ""));
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while comparing Two Strings by Remove Space and Ignore Case on CommonMethods");
            return false;
        }
    }

    public String emailGenerator() {
        try {
            long l = System.currentTimeMillis();
            return "testuser" + String.valueOf(l).substring(6) + "@gmail.com";
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while new email Generation on CommonMethods");
            return "";
        }
    }

    public String passwordGenerator() {
        try {
            StringBuilder sb = new StringBuilder();
            Random ran = new Random();

            String start = String.valueOf(System.currentTimeMillis()).substring(6, 8);
            String smallAlphabet = "abcdefghijklmnopqrstuvwxyz";
            String capitalAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            String symbol = "@#$*";

            sb.append(capitalAlphabet.charAt(ran.nextInt(0, 26)));
            sb.append(start);
            sb.append(capitalAlphabet.charAt(ran.nextInt(0, 26)));
            sb.append(smallAlphabet.charAt(ran.nextInt(0, 26)));
            sb.append(smallAlphabet.charAt(ran.nextInt(0, 26)));
            sb.append(smallAlphabet.charAt(ran.nextInt(0, 26)));
            sb.append(symbol.charAt(ran.nextInt(0, 4)));
            sb.append(capitalAlphabet.charAt(ran.nextInt(0, 26)));

            return sb.toString();
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while new password Generation on CommonMethods");
            return "";
        }
    }

    public String mobileNumberGenerator() {
        try {
            Random ran = new Random();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 10; i++) {
                sb.append(ran.nextInt(0, 10));
            }
            return sb.toString();
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while mobileNumberGenerator on CommonMethods");
            return "";
        }
    }

    public String mobileNumberGenerateAndAppend() {
        try {
            String number = mobileNumberGenerator();

            File fileName = new File("src/main/resources/Data.xlsx");

            FileInputStream file = new FileInputStream(fileName);

            XSSFWorkbook workbook = new XSSFWorkbook(file);

            XSSFSheet sheet = workbook.getSheet("Login Mobile Number");

            int lastRowNum = sheet.getLastRowNum();

            sheet.createRow(++lastRowNum);

            sheet.getRow(lastRowNum).createCell(0).setCellValue(number);

            FileOutputStream os = new FileOutputStream("src/main/resources/Data.xlsx");
            workbook.write(os);

            os.close();
            workbook.close();

            return number;
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while mobile Number Generation and Append to an Excel sheet on CommonMethods");
            return "";
        }
    }

    public String getLastMobileNumber() {
        try {
            File fileName = new File("src/main/resources/Data.xlsx");

            FileInputStream file = new FileInputStream(fileName);

            XSSFWorkbook workbook = new XSSFWorkbook(file);

            XSSFSheet sheet = workbook.getSheet("Login Mobile Number");

            int lastRowNum = sheet.getLastRowNum();

            return sheet.getRow(lastRowNum).getCell(0).getStringCellValue();
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while getting Mobile Number of a specific index from Excel sheet on CommonMethods");
            return "";
        }
    }

    public void setExamplesKeyValueInHashMap(String key, String value) {
        try {
            ObjectManager.getObject().setExamplesKeyValueInHashMap(key, value);
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while setting examples key value in Hash Map on CommonMethods");
        }
    }
}