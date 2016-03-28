package com.selenium.test.pages;

import com.selenium.test.pages.Utils.Utils;
import com.selenium.test.webtestsbase.BasePage;
import com.selenium.test.webtestsbase.WebDriverFactory;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

/**
 * Created on 23/11/15.
 */
public class LoginPage extends BasePage {

    private static String BASE_URL = "http://localhost:9000/#/login";

    @FindBy(name = "email")
    private WebElement emailField;

    @FindBy(name = "password")
    private WebElement passwordField;

    @FindBy(css = "button[type=submit]")
    private WebElement submitButton;

    @FindBy(linkText = "Forgot password?")
    private WebElement forgotPasswordLink;

    @FindBy(css = "span.text-u-l")
    private WebElement createAccountLink;

    @FindBy(css = "div.alert")
    private WebElement errorMessage;

    public LoginPage() {
        super(true);
        WebDriverFactory.getDriver().manage().window().maximize();
    }

    @Override
    protected void openPage() {
        getDriver().get(BASE_URL);
    }

    @Override
    public boolean isPageOpened() {
        return passwordField.isDisplayed();
    }

    public DevicePage login(String email, String password) {
        Utils.enterAndWait(emailField, email);
        Utils.enterAndWait(passwordField, password);
        Utils.clickAndWait(submitButton);
        try {
            isErrorDisplayed();
            return null;
        } catch (NoSuchElementException e) {

        }

        return new DevicePage();
    }

    public ForgotPasswordPage forgotPassword() {
        Utils.clickAndWait(forgotPasswordLink);
        Utils.waitForScript(20, TimeUnit.SECONDS);
        return new ForgotPasswordPage();
    }

    public CreateAccountPage createAccount() {
        Utils.clickAndWait(createAccountLink);
        return new CreateAccountPage();
    }

    public boolean isErrorDisplayed() {
        return errorMessage.isDisplayed();
    }

}
