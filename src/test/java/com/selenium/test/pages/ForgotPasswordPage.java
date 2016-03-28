package com.selenium.test.pages;

import com.selenium.test.pages.Utils.Utils;
import com.selenium.test.webtestsbase.BasePage;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

/**
 * Created on 30/11/15.
 */
public class ForgotPasswordPage extends BasePage {

    private static final String BASE_URL = "http://localhost:9000/#/forgotpwd";

    @FindBy(tagName = "form")
    private WebElement forgotPasswordForm;

    @FindBy(name = "emailField")
    private WebElement emailField;

    @FindBy(css = "button[type=submit]")
    private WebElement resetPasswordButton;

    @FindBy(partialLinkText = "Already have an account?")
    private WebElement loginLink;

    public ForgotPasswordPage() {
        super(true);
    }

    public ForgotPasswordPage(boolean openPageByUrl) {
        super(openPageByUrl);
    }


    public LoginPage requestNewPassword(String email) {
        this.emailField.clear();
        this.emailField.sendKeys(email);
        this.resetPasswordButton.click();
        Utils.waitForPageLoad(2, TimeUnit.SECONDS);
        return new LoginPage();
    }


    public LoginPage clickLoginLink() {
        loginLink.click();
        Utils.waitForPageLoad(2, TimeUnit.SECONDS);
        return new LoginPage();
    }


    @Override
    protected void openPage() {
        getDriver().get(BASE_URL);
    }

    @Override
    public boolean isPageOpened() {

        try {
            return forgotPasswordForm.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}

