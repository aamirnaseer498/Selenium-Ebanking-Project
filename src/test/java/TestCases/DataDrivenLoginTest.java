package TestCases;

import PageObjects.LoginPage;
import Utils.ExcelHelper;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataDrivenLoginTest extends CommonActions{

    LoginPage loginPage;
    String path= System.getProperty("user.dir") + "/src/test/java/TestData/LoginData.xlsx";

    @Test(dataProvider = "LoginData")
    public void loginDDT(String userID, String password){
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
            globalWebDriver.navigate().refresh();
            Assert.assertEquals(globalWebDriver.getTitle(),homeTitle,"Login Failed");
        }else {
            Assert.assertEquals(globalWebDriver.getTitle(),homeTitle,"Login Failed");
            loginPage.clickLogout();
            logger.info("Logged out successfully");
            globalWebDriver.switchTo().alert().accept();
            globalWebDriver.navigate().refresh();

        }
    }

    @DataProvider(name = "LoginData")
    public String[][] getLoginData(){
        int rowCount= ExcelHelper.getRowCount(path,"Sheet1");
        int colCount= ExcelHelper.getCellCount(path,"Sheet1",1);

        String[][] loginData = new String[rowCount][colCount];

        for (int i=1; i<=rowCount; i++) {
            for (int j=0; j<colCount; j++) {
                loginData[i-1][j]= ExcelHelper.getCellData(path,"Sheet1",i,j);
            }
        }
        return loginData;
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
