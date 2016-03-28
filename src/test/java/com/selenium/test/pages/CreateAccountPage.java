package com.selenium.test.pages;

import com.selenium.test.pages.Utils.Utils;
import com.selenium.test.webtestsbase.BasePage;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created on 30/11/15.
 */
public class CreateAccountPage extends BasePage {

    private static final String PAGE_URL = "http://localhost:9000/#/register";

    @FindBy(tagName = "form")
    private WebElement registrationForm;

    @FindBy(css = "input[placeholder='First name']")
    private WebElement firstNameField;


    @FindBy(css = "input[placeholder='Last name']")
    private WebElement lastNameField;


    @FindBy(css = "input[placeholder='Email address']")
    private WebElement emailAdressField;

    @FindBy(css = "input[placeholder='Invitation code']")
    private WebElement invitationCodeField;

    @FindBy(css = "button")
    private WebElement registerButton;

    @FindBy(linkText = "Sign In")
    private WebElement loginLink;

    public CreateAccountPage(boolean openPageByUrl) {
        super(openPageByUrl);
    }


    public CreateAccountPage() {
        super(true);
    }

    /**
     * registers a user with a valide invitation code
     *
     * @param email
     * @param firstName
     * @param lastName
     * @param invitationCode
     * @return DevicePage
     */
    public LoginPage Register(String email, String firstName, String lastName, String invitationCode) {
        emailAdressField.sendKeys(email);
        firstNameField.sendKeys(firstName);
        lastNameField.sendKeys(lastName);
        invitationCodeField.sendKeys(invitationCode);
        Utils.clickAndWait(registerButton);
        return new LoginPage();
    }


    /**
     * clicks on the loginbutton an redirects to the login page
     *
     * @return LoginPage
     */
    public LoginPage clickOnLoginLink() {
        Utils.clickAndWait(loginLink);
        return new LoginPage();
    }


    @Override
    protected void openPage() {
        getDriver().get(PAGE_URL);

    }

    @Override
    public boolean isPageOpened() {

        try {
            return registrationForm.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
