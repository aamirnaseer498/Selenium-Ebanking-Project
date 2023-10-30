package TestCases;

import Utils.ReadConfigs;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.IOException;

public class CommonActions {

    public String browser= new ReadConfigs().getBrowser();
    public String homeURL= new ReadConfigs().getHomeURL();
    public String userID= new ReadConfigs().getUserID();
    public String password= new ReadConfigs().getPassword();
    public String homeTitle= new ReadConfigs().getHomeTitle();
    public Logger logger;
    public static WebDriver globalWebDriver;
    @BeforeClass
    public void setup(){

        if (browser.equals("chrome")){
            globalWebDriver= new ChromeDriver();
        } else if (browser.equals("firefox")) {
            globalWebDriver= new FirefoxDriver();
        } else if (browser.equals("edge")) {
            globalWebDriver= new EdgeDriver();
        }

        globalWebDriver.manage().window().maximize();
        logger= Logger.getLogger("Ebanking");
        PropertyConfigurator.configure("Configs/log4j.properties");
        logger.info("Browser launched");

    }

    @AfterClass
    public void tearDown(){
        globalWebDriver.quit();
        logger.info("Browser closed");
    }

}
