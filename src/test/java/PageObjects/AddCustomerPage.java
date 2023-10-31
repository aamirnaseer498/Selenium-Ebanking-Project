package PageObjects;

import Utils.WaitHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.awt.*;
import java.awt.event.KeyEvent;

public class AddCustomerPage {

    WebDriver addCustomerWebDriver;
    WaitHelper addCustomerWaitHelper;

    @FindBy(xpath = "//a[normalize-space()='New Customer']")
    @CacheLookup
    WebElement newCustomerLink;

    @FindBy(xpath = "//input[@name='name']")
    @CacheLookup
    WebElement customerNameField;

    @FindBy(xpath = "//input[@value='m']")
    @CacheLookup
    WebElement maleGenderRadioButton;

    @FindBy(xpath = "//input[@value='f']")
    @CacheLookup
    WebElement feMaleGenderRadioButton;

    @FindBy(xpath = "//input[@id='dob']")
    @CacheLookup
    WebElement dateOfBirthField;

    @FindBy(xpath = "//textarea[@name='addr']")
    @CacheLookup
    WebElement addressField;

    @FindBy(xpath = "//input[@name='city']")
    @CacheLookup
    WebElement cityField;

    @FindBy(xpath = "//input[@name='state']")
    @CacheLookup
    WebElement stateField;

    @FindBy(xpath = "//input[@name='pinno']")
    @CacheLookup
    WebElement pinField;

    @FindBy(xpath = "//input[@name='telephoneno']")
    @CacheLookup
    WebElement phoneField;

    @FindBy(xpath = "//input[@name='emailid']")
    @CacheLookup
    WebElement emailField;

    @FindBy(xpath = "//input[@name='password']")
    @CacheLookup
    WebElement passwordField;

    @FindBy(xpath = "//input[@name='sub']")
    @CacheLookup
    WebElement submitButton;

    public AddCustomerPage(WebDriver webDriver) {
        addCustomerWebDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        addCustomerWaitHelper = new WaitHelper(webDriver);
    }

    public void clickOnNewCustomerLink(){
        addCustomerWaitHelper.waitForElement(newCustomerLink,60);
        newCustomerLink.click();
    }

    public void enterCustomerName(String customerName){
        addCustomerWaitHelper.waitForElement(customerNameField,60);
        customerNameField.clear();
        customerNameField.sendKeys(customerName);
    }

    public void selectGender(String gender){
        if (gender.equals("male")){
            addCustomerWaitHelper.waitForElement(maleGenderRadioButton,60);
            maleGenderRadioButton.click();
        }else{
            addCustomerWaitHelper.waitForElement(feMaleGenderRadioButton,60);
            feMaleGenderRadioButton.click();
        }
    }

    public void enterDateOfBirth(String day, String month, String year){
        addCustomerWaitHelper.waitForElement(dateOfBirthField,60);
        dateOfBirthField.clear();
        dateOfBirthField.sendKeys(day);
        dateOfBirthField.sendKeys(month);
        try {
            Robot robot= new Robot();
            robot.keyPress(KeyEvent.VK_RIGHT);
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
        dateOfBirthField.sendKeys(year);
    }

    public void enterAddress(String address){
        addCustomerWaitHelper.waitForElement(addressField,60);
        addressField.clear();
        addressField.sendKeys(address);
    }

    public void enterCity(String city){
        addCustomerWaitHelper.waitForElement(cityField,60);
        cityField.clear();
        cityField.sendKeys(city);
    }
    public void enterState(String state){
        addCustomerWaitHelper.waitForElement(stateField,60);
        stateField.clear();
        stateField.sendKeys(state);
    }
    public void enterPIN(String pin){
        addCustomerWaitHelper.waitForElement(pinField,60);
        pinField.clear();
        pinField.sendKeys(pin);
    }
    public void enterPhoneNo(String phoneNo){
        addCustomerWaitHelper.waitForElement(phoneField,60);
        phoneField.clear();
        phoneField.sendKeys(phoneNo);
    }
    public void enterEmail(String email){
        addCustomerWaitHelper.waitForElement(emailField,60);
        emailField.clear();
        emailField.sendKeys(email);
    }
    public void enterPassword(String password){
        addCustomerWaitHelper.waitForElement(passwordField,60);
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void clickOnSubmitButton(){
        addCustomerWaitHelper.waitForElement(submitButton,60);
        submitButton.click();
    }

}
