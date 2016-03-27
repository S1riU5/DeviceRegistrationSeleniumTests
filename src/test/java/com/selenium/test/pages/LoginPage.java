package com.selenium.test.pages;

import com.selenium.test.pages.Utils.Utils;
import com.selenium.test.webtestsbase.BasePage;
import com.selenium.test.webtestsbase.WebDriverFactory;
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
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        Utils.clickAndWait(submitButton);
        if (isErrorDisplayed()) {
            return null;
        }
        
        return new DevicePage();
    }

    public ForgotPasswordPage forgotPassword() {
        forgotPasswordLink.click();
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
