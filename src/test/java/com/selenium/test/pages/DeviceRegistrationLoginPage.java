package com.selenium.test.pages;

import com.selenium.test.webtestsbase.BasePage;

/**
 * Created by simongyimah on 23/11/15.
 */
public class DeviceRegistrationLoginPage extends BasePage{

    private static String BASE_URL = "http://localhost:9000/#/login";

    public DeviceRegistrationLoginPage() {
        super(true);
    }

    @Override
    protected void openPage() {
        getDriver().get(BASE_URL);
    }

    @Override
    public boolean isPageOpened() {
        return false;
    }
}
