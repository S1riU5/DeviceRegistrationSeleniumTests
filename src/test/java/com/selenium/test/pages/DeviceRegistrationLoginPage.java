package com.selenium.test.pages;

import com.selenium.test.pages.Utils.Utils;
import com.selenium.test.webtestsbase.BasePage;
import com.selenium.test.webtestsbase.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

/**
 * Created on 23/11/15.
 */
public class DeviceRegistrationLoginPage extends BasePage{

    private static String BASE_URL = "http://localhost:9000/#/login";

	@FindBy(name = "email")
	private WebElement emailField;

	@FindBy(name = "password")
	private WebElement passwordField;

	@FindBy(css = "button[type=submit]")
	private WebElement submitButton;

	@FindBy(linkText="Forgot password?")
	private WebElement forgotPasswordLink;

	@FindBy(linkText="Create an account")
	private WebElement createAccountLink;






    public DeviceRegistrationLoginPage() {
        super(true);
    }

    @Override
    protected void openPage() {
        getDriver().get(BASE_URL);
    }

    @Override
    public boolean isPageOpened() {
        return passwordField.isDisplayed();
    }

    public DeviceRegistrationDevicePage login(String email, String password){
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        submitButton.click();
        Utils.waitForPageLoad(2,TimeUnit.SECONDS);
        return new DeviceRegistrationDevicePage();
    }

    public DeviceRegistrationForgotPasswordPage forgotPassword(){
        forgotPasswordLink.click();
        Utils.waitForPageLoad(2,TimeUnit.SECONDS);
        return new DeviceRegistrationForgotPasswordPage();
    }

    public DeviceRegistrationCreateAccountPage createAccount(){
        createAccountLink.click();
        Utils.waitForPageLoad(2,TimeUnit.SECONDS);

        return new DeviceRegistrationCreateAccountPage();
    }

}
