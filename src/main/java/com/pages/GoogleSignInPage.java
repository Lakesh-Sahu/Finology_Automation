package com.pages;

import com.CommonMethods;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GoogleSignInPage extends CommonMethods {

    WebDriver driver;
    WebDriverWait wait;
    static Logger log = LogManager.getLogger(GoogleSignInPage.class);
    private final String urlPart;

    public GoogleSignInPage(WebDriver driver) {
        this.driver = driver;
        AjaxElementLocatorFactory ajax = new AjaxElementLocatorFactory(driver, 15);
        PageFactory.initElements(ajax, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        urlPart = "https://accounts.google.com/";
    }

    public boolean verifyOnGoogleSignInPage() {
        try {
            wait.until(ExpectedConditions.urlContains(urlPart));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}