package PageObjects;

import Utils.WaitHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    WebDriver loginWebDriver;
    WaitHelper loginWaitHelper;
    @FindBy(name = "uid")
    @CacheLookup
    WebElement userIDField;
    @FindBy(name = "password")
    @CacheLookup
    WebElement passwordField;
    @FindBy(name = "btnLogin")
    @CacheLookup
    WebElement loginButton;

    @FindBy(xpath = "//a[normalize-space()='Log out']")
    @CacheLookup
    WebElement logoutLink;

    public LoginPage(WebDriver webDriver) {
        loginWebDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        loginWaitHelper = new WaitHelper(webDriver);
    }

    public void enterUserID(String userID) {
        loginWaitHelper.waitForElement(userIDField, 60);
        userIDField.clear();
        userIDField.sendKeys(userID);
    }

    public void enterPassword(String password) {
        loginWaitHelper.waitForElement(passwordField, 60);
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void clickLogin() {
        loginWaitHelper.waitForElement(loginButton, 60);
        loginButton.click();
    }

    public void clickLogout() {
        loginWaitHelper.waitForElement(logoutLink, 60);
        logoutLink.click();
    }

}
