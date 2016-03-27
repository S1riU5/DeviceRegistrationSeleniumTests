package com.selenium.test.pages.Utils;

import com.selenium.test.webtestsbase.WebDriverFactory;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

/**
 * Created on 30/11/15.
 */
public class Utils {

    public static final int TIMEOUTINSECONDS = 10;

    public static void waitForPageLoad(long time, TimeUnit timeUnit) {
        WebDriverFactory.getDriver().manage().timeouts().pageLoadTimeout(time, timeUnit);
    }

    public static void waitImplicit(long time, TimeUnit timeUnit) {
        WebDriverFactory.getDriver().manage().timeouts().implicitlyWait(time, timeUnit);
    }

    public static void waitForScript(long time, TimeUnit timeUnit) {
        WebDriverFactory.getDriver().manage().timeouts().setScriptTimeout(time, timeUnit);
    }


    public static void clickAndWait(WebElement webElement) {
        webElement.click();
        waitImplicit(TIMEOUTINSECONDS, TimeUnit.SECONDS);
    }

    public static void enterAndWait(WebElement webElement, String insert) {
        webElement.clear();
        webElement.sendKeys(insert);
        waitImplicit(TIMEOUTINSECONDS, TimeUnit.SECONDS);
    }


}
