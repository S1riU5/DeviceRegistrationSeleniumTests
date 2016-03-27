package com.selenium.test.pages;

import com.selenium.test.pages.Utils.Utils;
import com.selenium.test.pages.enums.DeviceInformation;
import com.selenium.test.webtestsbase.BasePage;
import com.selenium.test.webtestsbase.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class DevicePage extends BasePage {

    private static final String BASE_URL = "http://localhost:9000/#/devices";
    private static final String DEVICES_TAG_NAME = "md-card";
    private static final String SEARCH_FIELD_CSS = "input[type=text]";
    private static final String LABEL_FIELDS_CSS = "ng-binding";
    public static final int VIEW_DEVICE_LINK_POSITION = 1;


    @FindBy(className = "navbar-collapse")
    private WebElement headerNavigation;

    @FindBy(className = "dropdown-toggle")
    private WebElement menu; // = WebDriverFactory.getDriver().findElement(By.className("dropdown-toggle"));

    @FindBy(css = SEARCH_FIELD_CSS)
    private WebElement searchField;

    private WebElement logoutLink;

    private WebElement adminLink;

    private WebElement profileLink;

    public DevicePage() {
        super(true);
    }

    public DevicePage(boolean openPageByUrl) {
        super(openPageByUrl);
    }

    /**
     * Methode to logout
     *
     * @return LoginPage
     */
    public LoginPage logout() {
        menu.click();
        logoutLink = WebDriverFactory.getDriver().findElement(By.linkText("Logout"));
        Utils.clickAndWait(logoutLink);
        return new LoginPage();
    }

    //TODO ADD Admin class to project and return the Admin class
    public void clickOnAdminLink() {
        menu.click();
        adminLink = WebDriverFactory
                .getDriver()
                .findElement(By.linkText("Admin"));
        Utils.clickAndWait(adminLink);
    }

    //TODO ADD Profile class to project and return profileLink class
    public void clickProfileLink() {
        menu.click();
        Utils.clickAndWait(profileLink);

    }

    /**
     * displays all visible devices
     *
     * @return int device count
     */
    public int deviceCount() {
        Integer deviceCount = currentDevices().size();
        return deviceCount;
    }

    /**
     * enter a search term into the search field
     *
     * @param searchTerm
     */
    public void search(String searchTerm) {
        Utils.enterAndWait(searchField, searchTerm);
    }


    /**
     * Checks if one device contains the label
     *
     * @param label String
     * @return boolean
     */
    public boolean deviceContains(final String label) {
        for (int i = 0; i < currentDevices().size(); i++) {
            if (!currentDevices().get(i).getText().toLowerCase().contains(label.toLowerCase())) {
                return false;
            }
        }
        return true;
    }


    /**
     * Delete the item on the given position.
     *
     * @param position int
     */
    public void delete(int position) {
        Utils.clickAndWait(currentDevices().get(position).findElement(By.cssSelector("button.md-icon-button")));
    }


    /**
     * Get the details of a device
     *
     * @param position int
     * @return HashMap
     */
    public HashMap<DeviceInformation, String> getDeviceInformation(int position) {
        List<WebElement> webElementList = currentDevices().get(position).findElement(By.tagName("ul")).findElements(By.tagName("li"));
        HashMap<DeviceInformation, String> deviceInformation = new HashMap<>();
        List<DeviceInformation> tmp = Arrays.asList(
                DeviceInformation.SERIAL_NUMBER,
                DeviceInformation.CATEGORY,
                DeviceInformation.GROUP
        );

        for (int i = 0; i < webElementList.size(); i++) {
            String[] strings = webElementList.get(i).getText().split(": ");
            deviceInformation.put(tmp.get(i), strings[1]);
        }
        deviceInformation.put(DeviceInformation.LABEL, currentDevices().get(position).findElement(By.tagName("h3")).getText());

        return deviceInformation;
    }

    public CreateAndEditDevicePage edit(int position) {
        WebElement webElement = currentDevices().get(position).findElement(By.linkText("EDIT"));
        Utils.clickAndWait(webElement);
        return new CreateAndEditDevicePage(false);
    }

    private List<WebElement> currentDevices() {
        return WebDriverFactory.getDriver().findElements(By.tagName(DEVICES_TAG_NAME));
    }

    public DetailView viewDevice(int position) {
        List<WebElement> ankorList = currentDevices()
                .get(position).findElements(By.tagName("a"));
        Utils.clickAndWait(ankorList.get(VIEW_DEVICE_LINK_POSITION));
        return new DetailView();
    }

    @Override
    protected void openPage() {
        getDriver().get(BASE_URL);
    }

    @Override
    public boolean isPageOpened() {
        try {
            return headerNavigation.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
