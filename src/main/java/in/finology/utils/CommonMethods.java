package in.finology.utils;

import com.github.javafaker.Faker;
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

public class CommonMethods extends Base {
    private static final Logger log = LogManager.getLogger(CommonMethods.class);

    public boolean click(WebDriver driver, WebElement we) {
        try {
            scrollTO(driver, we);
            we.click();
            Thread.sleep(1000);
            return true;
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while scrolling and clicking a WebElement on CommonMethods class");
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
            logWarningInLogFileAndExtentReport(log, e, "Exception while clearing and sending keys to a WebElement on CommonMethods class");
            return false;
        }
    }

    // Scroll to a particular element using java script executor
    public void scrollTO(WebDriver driver, WebElement we) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView({behavior : 'smooth', block : 'center', inline : 'center'});", we);
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while scrolling to a WebElement on CommonMethods class");
        }
    }

    public boolean compareTwoStringsRemoveSpaceIgnoreCase(String s1, String s2) {
        try {
            return s1.replaceAll(" ", "").equalsIgnoreCase(s2.replaceAll(" ", ""));
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while comparing Two Strings by Remove Space and Ignore Case on CommonMethods class");
            return false;
        }
    }

    public String nameGenerator() {
        try {
            Faker faker = new Faker();
            return faker.name().firstName() + " " + faker.name().lastName();
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while new name Generation in CommonMethods class");
            return "";
        }
    }

    public String emailGenerator() {
        try {
            Faker faker = new Faker();
            return faker.internet().emailAddress();
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while new email Generation on CommonMethods class");
            return "";
        }
    }

    public String passwordGenerator() {
        try {
            Faker faker = new Faker();
            return faker.internet().password(6, 12, true, true, true);
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while new password Generation on CommonMethods class");
            return "";
        }
    }

    public String mobileNumberGenerator() {
        try {
            Faker faker = new Faker();
            return faker.phoneNumber().subscriberNumber(10);
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while mobileNumberGenerator on CommonMethods class");
            return "";
        }
    }

    public String mobileNumberGenerateAndAppend() {
        try {
            String number = mobileNumberGenerator();

            File filePath = new File(System.getProperty("user.dir") + "/src/main/resources/Data.xlsx");

            XSSFWorkbook workbook;

            if (filePath.exists()) {
                FileInputStream file = new FileInputStream(filePath);
                workbook = new XSSFWorkbook(file);
            } else {
                workbook = new XSSFWorkbook();
            }

            XSSFSheet sheet = workbook.getSheet("Login Mobile Number");
            if (sheet == null) {
                sheet = workbook.createSheet("Login Mobile Number");
            }

            int lastRowNum = sheet.getLastRowNum();

            sheet.createRow(++lastRowNum);

            sheet.getRow(lastRowNum).createCell(0).setCellValue(number);

            FileOutputStream os = new FileOutputStream(filePath);
            workbook.write(os);

            os.close();
            workbook.close();

            return number;
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while mobile Number Generation and Append to an Excel sheet on CommonMethods class");
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
            logWarningInLogFileAndExtentReport(log, e, "Exception while getting Mobile Number of a specific index from Excel sheet on CommonMethods class");
            return "";
        }
    }

    // Store the keys and values of Examples used in the cucumber step under the step definition
    public void setExamplesKeyValueInHashMap(String key, String value) {
        try {
            ObjectManager.getObject().setExamplesKeyValueInHashMap(key, value);
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while setting examples key value in Hash Map on CommonMethods class");
        }
    }
}