package TestCases;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class CommonActions {

    public String homeURL= "https://demo.guru99.com/V4/";
    public String userID= "mngr534667";
    public String password= "habamuj";
    public String homeTitle= "Guru99 Bank Manager HomePage";
    public Logger logger;
    public static WebDriver globalWebDriver;
    @BeforeClass
    public void setup(){
        globalWebDriver= new ChromeDriver();
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
