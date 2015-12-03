package com.selenium.test.pages;

import org.jcp.xml.dsig.internal.dom.Utils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

/**
 * Created on 30/11/15.
 */
public class DeviceRegistrationForgotPasswordPage {

	@FindBy(name="email")
	private WebElement email;

	@FindBy(css="button[type=submit]")
	private WebElement resetPasswordButton;

	@FindBy(partialLinkText="Already have an account?")
	private WebElement loginLink;


    public DeviceRegistrationLoginPage requestNewPassword(String email){
        this.email.clear();
        this.email.sendKeys(email);
        this.resetPasswordButton.click();
        com.selenium.test.pages.Utils.Utils.waitForPageLoad(2, TimeUnit.SECONDS);
        return new DeviceRegistrationLoginPage();
    }



}

