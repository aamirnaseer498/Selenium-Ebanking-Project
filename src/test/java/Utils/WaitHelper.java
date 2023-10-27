package Utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitHelper {

    WebDriverWait webDriverWait;
    WebDriver waitWebDriver;

    public WaitHelper(WebDriver webDriver) {
        waitWebDriver = webDriver;
    }

    public void waitForElement(WebElement webElement, long timeOutInSeconds) {
        webDriverWait = new WebDriverWait(waitWebDriver, Duration.ofSeconds(timeOutInSeconds));
        webDriverWait.until(ExpectedConditions.visibilityOf(webElement));
    }

}
