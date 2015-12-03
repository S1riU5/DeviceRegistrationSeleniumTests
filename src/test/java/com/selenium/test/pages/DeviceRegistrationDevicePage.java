package com.selenium.test.pages;

import com.selenium.test.pages.Utils.Utils;
import com.selenium.test.webtestsbase.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

/**
 * Created on 30/11/15.
 */
public class DeviceRegistrationDevicePage {

	@FindBy(className="dropdown-toggle")
	private WebElement menu = WebDriverFactory.getDriver().findElement(By.className("dropdown-toggle"));;

	@FindBy(linkText="Logout")
	private WebElement logoutLink;

    public DeviceRegistrationLoginPage Logout(){
        menu.click();
        logoutLink = WebDriverFactory.getDriver().findElement(By.linkText("Logout"));
        logoutLink.click();
        Utils.waitForPageLoad(2,TimeUnit.SECONDS);
        return new DeviceRegistrationLoginPage();
    }


}
