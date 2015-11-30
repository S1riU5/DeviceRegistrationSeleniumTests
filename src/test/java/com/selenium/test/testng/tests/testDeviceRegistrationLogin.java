package com.selenium.test.testng.tests;

import com.selenium.test.pages.DeviceRegistrationLoginPage;
import com.selenium.test.pages.DeviceRegistrationDevicePage;
import com.selenium.test.webtestsbase.WebDriverFactory;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by simongyimah on 23/11/15.
 */
public class testDeviceRegistrationLogin {

    @BeforeTest
    public void beforeTest() {
        WebDriverFactory.startBrowser(true);
    }


    @Test
    public void testLogin() {
        DeviceRegistrationDevicePage deviceRegistrationDevicePage;
        DeviceRegistrationLoginPage deviceRegistrationLoginPage = new DeviceRegistrationLoginPage();
        deviceRegistrationDevicePage = deviceRegistrationLoginPage.login("local@ceventis.com", "password");
        AssertJUnit.assertEquals(WebDriverFactory.getDriver().getCurrentUrl(), "http://localhost:9000/#/devices");
        deviceRegistrationDevicePage.Logout();
    }



    @AfterTest
    public void afterTest() {
        WebDriverFactory.finishBrowser();
    }
}


