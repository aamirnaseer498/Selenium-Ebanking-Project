package Utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GenerateReport extends TestListenerAdapter {

    public ExtentHtmlReporter htmlReporter;
    public ExtentReports reports;
    public ExtentTest test;

    @Override
    public void onStart(ITestContext testContext) {
        String timestamp= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        String reportName= "Test-Report-" + timestamp + ".html";
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
    }

    @Override
    public void onTestSuccess(ITestResult tr) {
        test= reports.createTest(tr.getName());
        test.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));
    }

    @Override
    public void onTestFailure(ITestResult tr) {
        test= reports.createTest(tr.getName());
        test.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));

        String screenShotPath= System.getProperty("user.dir")+"\\Screenshots\\"+tr.getName()+".png";
        File file= new File(screenShotPath);

        if (file.exists()){
            try {
                test.fail("Screenshot is below: " + test.addScreenCaptureFromPath(screenShotPath));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult tr) {
        test= reports.createTest(tr.getName());
        test.log(Status.SKIP,MarkupHelper.createLabel(tr.getName(),ExtentColor.ORANGE));
    }

    @Override
    public void onFinish(ITestContext testContext) {
        reports.flush();
    }
}
