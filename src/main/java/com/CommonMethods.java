package com;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.Duration;
import java.util.Random;
import java.util.random.RandomGenerator;

public class CommonMethods {
    public boolean click(WebDriver driver, WebElement we) {
        try {
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'});", we);
            we.click();
            Thread.sleep(1000);
            return true;
        } catch (Exception e) {
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
            return false;
        }
    }

    public boolean compareString(String s1, String s2) {
        try {
            return s1.replaceAll(" ", "").equalsIgnoreCase(s2.replaceAll(" ", ""));
        } catch (Exception e) {
            return false;
        }
    }

    public String emailGenerator() {
        try {
            long l = System.currentTimeMillis();
            return "testuser" + String.valueOf(l).substring(6) + "@gmail.com";
        } catch (Exception e) {
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
            return "";
        }
    }

    public String getMobileNumber(int idx) {
        try {
            File fileName = new File("src/main/resources/Data.xlsx");

            FileInputStream file = new FileInputStream(fileName);

            XSSFWorkbook workbook = new XSSFWorkbook(file);

            XSSFSheet sheet = workbook.getSheet("Login Mobile Number");

            int lastRowNum = sheet.getLastRowNum();

            return sheet.getRow(lastRowNum).getCell(0).getStringCellValue();
        } catch (Exception e) {
            return "";
        }
    }
}