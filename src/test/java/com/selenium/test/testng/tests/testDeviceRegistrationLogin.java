package com.selenium.test.testng.tests;

import com.selenium.test.pages.DeviceRegistrationLoginPage;
import com.selenium.test.pages.YouTubePage;
import com.selenium.test.pages.YouTubeSearchResultsPage;
import com.selenium.test.webtestsbase.WebDriverFactory;
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
    public void testSearch() {
        DeviceRegistrationLoginPage deviceRegistrationLoginPage = new DeviceRegistrationLoginPage();

    }


    @AfterTest
    public void afterTest() {
        WebDriverFactory.finishBrowser();
    }
}


