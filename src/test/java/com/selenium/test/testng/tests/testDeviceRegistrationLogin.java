package com.selenium.test.testng.tests;

import com.selenium.test.pages.*;
import com.selenium.test.pages.enums.DeviceInformation;
import com.selenium.test.webtestsbase.WebDriverFactory;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by simongyimah on 23/11/15.
 */
public class testDeviceRegistrationLogin {

    @BeforeTest
    public void beforeTest() {
        WebDriverFactory.startBrowser(true);
    }

    @Test
    public void playground() {
        LoginPage loginPage = new LoginPage();
        CreateAccountPage createAccountPage = loginPage.createAccount();
        createAccountPage.Register("TEst", "test", "test", "test");
    }

    @Test
    public void testLogin() {
        DevicePage devicePage;
        LoginPage deviceRegistrationLoginPage = new LoginPage();
        devicePage = deviceRegistrationLoginPage.login("admin@ceventis.com", "password");
        AssertJUnit.assertEquals(WebDriverFactory.getDriver().getCurrentUrl(), "http://localhost:9000/#/devices");
        devicePage.logout();
    }


    @Test
    public void registration() {
        LoginPage loginPage = new LoginPage();
        loginPage.createAccount();
    }

    @Test
    public void forgotPassword() {
        LoginPage loginPage = new LoginPage();
        loginPage.forgotPassword();
    }


    private DevicePage validLogin() {
        LoginPage loginPage = new LoginPage();
        return loginPage.login("admin@ceventis.com", "password");
    }

    @Test
    public void deviceViewContainsCorrectData() {

        DevicePage devicePage = validLogin();
        String label;
        String serial;
        String group;
        String category;

        HashMap<DeviceInformation, String> deviceInformations = devicePage.getDeviceInformation(1);
        label = deviceInformations.get(DeviceInformation.LABEL);
        serial = deviceInformations.get(DeviceInformation.SERIAL_NUMBER);
        group = deviceInformations.get(DeviceInformation.GROUP);
        category = deviceInformations.get(DeviceInformation.CATEGORY);
        //TODO write test

    }


    @Test
    public void editDevice() {
        DevicePage devicePage = validLogin();
        CreateAndEditDevicePage createAndEditDevicePage = devicePage.edit(0);
        createAndEditDevicePage.setCategory(1);
        createAndEditDevicePage.setMedium(2);
        createAndEditDevicePage.setGroup(0);
        createAndEditDevicePage.setComment("test");
        createAndEditDevicePage.setLabels("123123123");
        createAndEditDevicePage.setSerialNumber("1231231231");
        createAndEditDevicePage.setDesignation("mein neuer name");
        createAndEditDevicePage.toggleMaintenance();
        createAndEditDevicePage.toggleNotification();
        createAndEditDevicePage.setInterval(1);
        createAndEditDevicePage.setStartDate(new Date());
        createAndEditDevicePage.setReminder(1);
        createAndEditDevicePage.setEmail("test@test.de");

        String cat = createAndEditDevicePage.getCategory();
        String med = createAndEditDevicePage.getMedium();
        String group = createAndEditDevicePage.getGroup();
        String comment = createAndEditDevicePage.getComment();
        String labels = createAndEditDevicePage.getLabels();
        String number = createAndEditDevicePage.getSerialNumber();
        String desig = createAndEditDevicePage.getDesignation();
        String email = createAndEditDevicePage.getEmail();
        String reminder = createAndEditDevicePage.getReminder();
        String interval = createAndEditDevicePage.getInterval();
        String start = createAndEditDevicePage.getStartDate();

        devicePage = createAndEditDevicePage.save();

        DetailView detailView = devicePage.viewDevice(0);
        String catView = detailView.getCategory();
        String medView = detailView.getMedium();
        String groupView = detailView.getGroup();
        String commentView = detailView.getComment();
        String labelsView = detailView.getLabels();
        String numberView = detailView.getSerialNumber();
        String desigView = detailView.getDesignation();
        String emailView = detailView.getEmail();
        String reminderVeiw = detailView.getReminder();
        String intervalView = detailView.getInterval();
        String startView = detailView.getPeriodStart();

        AssertJUnit.assertEquals(cat, catView);
        AssertJUnit.assertEquals(med, medView);
        AssertJUnit.assertEquals(group, groupView);
        AssertJUnit.assertEquals(comment, commentView);
        AssertJUnit.assertEquals(labels, labelsView);
        AssertJUnit.assertEquals(number, numberView);
        AssertJUnit.assertEquals(desig, desigView);
        AssertJUnit.assertEquals(reminder, reminderVeiw);
        AssertJUnit.assertEquals(email, emailView);
        AssertJUnit.assertEquals(interval, intervalView);
        AssertJUnit.assertEquals(start, startView);


    }

    @Test
    public void deleteDevice() {
        DevicePage devicePage = validLogin();
        int beforeDelete;
        int afterDelete;
        String labelOld;
        String labelNew;

        beforeDelete = devicePage.deviceCount();
        labelOld = devicePage.getDeviceInformation(0).get(DeviceInformation.LABEL);
        devicePage.delete(0);

        afterDelete = devicePage.deviceCount();
        labelNew = devicePage.getDeviceInformation(0).get(DeviceInformation.LABEL);

        devicePage.logout();

        AssertJUnit.assertNotSame(beforeDelete, afterDelete);
        AssertJUnit.assertNotSame(labelOld, labelNew);


    }

    @Test
    public void search() {
        DevicePage devicePage = validLogin();
        int countBefore;
        int countAfter;
        String searchLabel = "device #1";

        countBefore = devicePage.deviceCount();
        devicePage.search(searchLabel);
        countAfter = devicePage.deviceCount();

        AssertJUnit.assertEquals(true, devicePage.deviceContains(searchLabel));
        AssertJUnit.assertNotSame(countBefore, countAfter);
        AssertJUnit.assertEquals(1, countAfter);


    }


    @AfterTest
    public void afterTest() {
        WebDriverFactory.finishBrowser();
    }
}


