package TestCases;

import PageObjects.AddCustomerPage;
import PageObjects.LoginPage;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddCustomerTest extends CommonActions{

    LoginPage loginPage;
    AddCustomerPage addCustomerPage;

    @Test
    public void addNewCustomer(){

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

        addCustomerPage= new AddCustomerPage(globalWebDriver);

        addCustomerPage.clickOnNewCustomerLink();
        logger.info("Clicked on New Customer link");

        addCustomerPage.enterCustomerName(generateName());
        logger.info("Customer Name is entered");
        addCustomerPage.selectGender("male");
        logger.info("Gender is selected");
        addCustomerPage.enterDateOfBirth("10","10","1994");
        logger.info("Date of Birth is entered");
        addCustomerPage.enterAddress("RevolveAI");
        logger.info("Address is entered");
        addCustomerPage.enterCity("Rawalpindi");
        logger.info("City is entered");
        addCustomerPage.enterState("Punjab");
        logger.info("State is entered");
        addCustomerPage.enterPIN("123456");
        logger.info("PIN is entered");
        addCustomerPage.enterPhoneNo("03331234567");
        logger.info("Phone No is entered");
        addCustomerPage.enterEmail(generateEmail() + "@mailinator.com");
        logger.info("Email is entered");
        addCustomerPage.enterPassword(generatePassword());
        logger.info("Password is entered");

        addCustomerPage.clickOnSubmitButton();
        logger.info("Clicked on submit button");

        Assert.assertTrue(globalWebDriver.getPageSource().contains("Customer Registered Successfully!!!"),"Operation Failed! kindly try again");

    }

    public String generateEmail(){
        return RandomStringUtils.randomAlphabetic(5);
    }

    public String generatePassword(){
        return RandomStringUtils.randomNumeric(8);
    }

    public String generateName(){
        return RandomStringUtils.randomAlphabetic(5);
    }

}
