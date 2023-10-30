package TestCases;

import PageObjects.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends CommonActions {

    LoginPage loginPage;

    @Test(testName = "Login Test")
    public void loginTest(){
        globalWebDriver.get(homeURL);
        logger.info("Home URL " + homeURL + " opened");

        loginPage= new LoginPage(globalWebDriver);
        logger.info("LoginPage object initialized");

        loginPage.enterUserID(userID);
        logger.info("UserID: " + userID + " is entered");
        loginPage.enterPassword(password);
        logger.info("Password: " + password + " is entered");

        loginPage.clickLogin();
        logger.info("Login button is clicked");

        Assert.assertEquals(globalWebDriver.getTitle(),homeTitle,"Login Failed");

    }

}
