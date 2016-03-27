package com.selenium.test.pages;

import com.selenium.test.webtestsbase.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

/**
 * Created on 16/03/16.
 */
public class DetailView extends BasePage {

    public static final int CATEGORY = 0;
    public static final int GROUP = 1;
    public static final int MEDIUM = 2;
    public static final int LABELS = 3;
    public static final int DESIGNATION = 4;
    public static final int SERIAL_NUMBER = 5;
    public static final int COMMENT = 6;
    public static final String PARAGRAPH_TAG = "p";
    public static final int MAINTENANCE_INTERVAL = 0;
    public static final int MAINTENACE_PERIOD_START = 0;
    public static final int NOTIFICATION_REMINDER = 0;
    public static final int NOTIFICATION_EMAIL = 1;
    private static final String BASE_URL = "";
    @FindBy(css = "button.btn-danger")
    private WebElement deleteButton;
    @FindBy(css = "button.btn.btn-default")
    private WebElement editButton;
    @FindBys(@FindBy(tagName = "fieldset"))
    private List<WebElement> sections;

    private WebElement maintenanceSection = sections.get(1);
    private List<WebElement> maintenanceContent = maintenanceSection.findElements(By.tagName(PARAGRAPH_TAG));

    private WebElement deviceSection = sections.get(0);
    private List<WebElement> deviceSectionContent = deviceSection.findElements(By.tagName(PARAGRAPH_TAG));

    private WebElement notification = sections.get(2);
    private List<WebElement> notificationContent = notification.findElements(By.tagName(PARAGRAPH_TAG));

    public DetailView() {
        super(false);
    }

    public String getCategory() {
        return deviceSectionContent.get(CATEGORY).getText();
    }

    public String getGroup() {
        return deviceSectionContent.get(GROUP).getText();
    }

    public String getMedium() {
        return deviceSectionContent.get(MEDIUM).getText();
    }

    public String getLabels() {
        return deviceSectionContent.get(LABELS).getText();
    }

    public String getDesignation() {
        return deviceSectionContent.get(DESIGNATION).getText();
    }

    public String getSerialNumber() {
        return deviceSectionContent.get(SERIAL_NUMBER).getText();
    }

    public String getComment() {
        return deviceSectionContent.get(COMMENT).getText();
    }

    public String getInterval() {
        return maintenanceContent.get(MAINTENANCE_INTERVAL).getText();
    }

    public String getPeriodStart() {
        return maintenanceContent.get(MAINTENACE_PERIOD_START).getText();
    }

    public String getReminder() {
        return notificationContent.get(NOTIFICATION_REMINDER).getText();
    }

    public String getEmail() {
        return notificationContent.get(NOTIFICATION_EMAIL).getText();
    }

    @Override
    protected void openPage() {
        getDriver().get(BASE_URL);
    }

    @Override
    public boolean isPageOpened() {
        return editButton.isDisplayed();
    }
}
