package TestCases;

import PageObjects.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SimpleLoginTest extends CommonActions {

    LoginPage loginPage;

    @Test
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

        if (isAlertPresent()){
            globalWebDriver.switchTo().alert().accept();
            globalWebDriver.switchTo().defaultContent();
            Assert.assertEquals(globalWebDriver.getTitle(),homeTitle,"Login Failed");
        }else {
            Assert.assertEquals(globalWebDriver.getTitle(),homeTitle,"Login Failed");
        }
    }

    public boolean isAlertPresent(){
        try {
            globalWebDriver.switchTo().alert();
            return true;
        }catch (Exception e){
            return false;
        }
    }

}
