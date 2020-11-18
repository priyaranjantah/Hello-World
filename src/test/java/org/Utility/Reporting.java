package org.Utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class Reporting extends TestListenerAdapter {
    public ExtentHtmlReporter htmlReporter;
    public ExtentReports extent;
    public ExtentTest logger;
    public static Properties config;

    Logger log;

    public void onStart(ITestContext testContext) {
        config = new Properties();
        log = Logger.getLogger(this.getClass().getName());
        try {
            config.load(new FileInputStream(new File("./config.properties")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        String repName = "Test-Report-" + timestamp + ".html";

        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/TestReports/" + repName);
        htmlReporter.loadXMLConfig(System.getProperty("user.dir") + "/extent-config.xml");

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Host Name", "LocalHost");
        extent.setSystemInfo("Environment", "Stage");
        extent.setSystemInfo("User Name", config.getProperty("internalUserName"));

        htmlReporter.config().setDocumentTitle("Platform REST API Automation Project");
        htmlReporter.config().setReportName("API Test Report");
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setTheme(Theme.STANDARD);

    }

    public void onTestSuccess(ITestResult tr) {
        logger = extent.createTest(tr.getName());
        logger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));
        logger.log(Status.PASS, "Test Case Passed is " + tr.getName());


        String screenshotPath = System.getProperty("user.dir") + "//Screenshots//" + tr.getName() + ".jpg";
        File f = new File(screenshotPath);
        if (f.exists()) {
            try {
                logger.pass("Screenshot is below:" + logger.addScreenCaptureFromPath(screenshotPath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    public void onTestFailure(ITestResult tr) {
        logger = extent.createTest(tr.getName());
        logger.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));
        if (tr.getStatus() == ITestResult.FAILURE) {
            logger.log(Status.FAIL, "Test Case Failed is " + tr.getName());
            logger.log(Status.FAIL, "Test Case Failed is " + tr.getThrowable());
            log.info(Status.FAIL, tr.getThrowable());
        }



        String screenshotPath = System.getProperty("user.dir") + "//Screenshots//" + tr.getName() + ".jpg";
        File f = new File(screenshotPath);
        if (f.exists()) {
            try {
                logger.fail("Screenshot is below:" + logger.addScreenCaptureFromPath(screenshotPath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void onTestSkipped(ITestResult tr) {
        logger = extent.createTest(tr.getName());
        logger.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));
    }

    public void onFinish(ITestContext testContext) {
//        extent.flush();
    }

}

