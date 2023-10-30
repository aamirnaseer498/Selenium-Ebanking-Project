package Utils;

import TestCases.CommonActions;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.internal.TestResult;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GenerateReport extends TestListenerAdapter {

    public ExtentHtmlReporter htmlReporter;
    public ExtentReports reports;
    public ExtentTest test;
    public Logger logger;

    @Override
    public void onStart(ITestContext testContext) {
        logger= Logger.getLogger("Ebanking");
        String reportName= "Test-Report.html";
        htmlReporter= new ExtentHtmlReporter(System.getProperty("user.dir")+ "/Reports/"+reportName);

        reports= new ExtentReports();

        reports.attachReporter(htmlReporter);
        reports.setSystemInfo("Host Name", "localhost");
        reports.setSystemInfo("Environment", "QA");
        reports.setSystemInfo("user", "aamir");

        htmlReporter.config().setDocumentTitle("Selenium-Ebanking-Project");
        htmlReporter.config().setReportName("Ebanking Test Report");
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setTheme(Theme.DARK);
        logger.info("Test " + testContext.getName() + " Started");
    }

    @Override
    public void onTestSuccess(ITestResult tr) {
        test= reports.createTest(tr.getName());
        test.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));
        logger.info(tr.getName() + " Passed");
    }

    @Override
    public void onTestFailure(ITestResult tr) {
        test= reports.createTest(tr.getName());
        test.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));
        logger.info(tr.getName() + " Failed");
        captureScreenShot(tr);
    }

    @Override
    public void onTestSkipped(ITestResult tr) {
        test= reports.createTest(tr.getName());
        test.log(Status.SKIP,MarkupHelper.createLabel(tr.getName(),ExtentColor.ORANGE));
        logger.info(tr.getName() + " Skipped");
    }

    @Override
    public void onFinish(ITestContext testContext) {
        reports.flush();
        logger.info("Test " + testContext.getName() + " Finished");
    }

        public void captureScreenShot(ITestResult tr){

        TakesScreenshot screenshot= (TakesScreenshot) CommonActions.globalWebDriver;
        File source= screenshot.getScreenshotAs(OutputType.FILE);
        File target= new File(System.getProperty("user.dir") + "./Screenshots/" + tr.getName() + ".png");
        try {
            FileUtils.copyFile(source,target);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String screenShotPath= System.getProperty("user.dir") + "./Screenshots/" + tr.getName() + ".png";
        File file= new File(screenShotPath);

        if (file.exists()){
            try {
                test.fail("Screenshot is below: " + test.addScreenCaptureFromPath(screenShotPath));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

}
