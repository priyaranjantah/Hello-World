package org.Stage;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.Session;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.Utility.ReadConfig;
import org.Utility.Reporting;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.w3c.dom.Document;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.TimeZone;

public class BaseClass extends ReadConfig {
    public static WebDriver driver;
    public static String query;
    public static Connection con = null;
    public static Statement stmt = null;
    public static ResultSet rs = null;
    public static Properties config;
    public static String solr;
    public static String solr1;
    public static String solr2;
    public static Document doc;
    public static RequestSpecification httpRequest;
    public static Response response;
    public static ExtentReports extReport;
    public static ExtentTest extLogger;
    public static String CSVReportName;
    public static XSSFWorkbook wb;
    public static FileInputStream fis;
    public static XSSFSheet sheet;
    public static int statuscode;
    public static String statusline;
    public static Logger logger;
    public static String trigerMail;
    public static ITestResult result;
    public static Document docb;
    public static Document docb1;
    public static String Resturl;
    public static String SolrURL;
    public static Session session;
    public static Properties obj;

    public Reporting reporting = new Reporting();
    ReadConfig readConfig = new ReadConfig();



    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//drivers//chromedriver.exe");
        System.setProperty("phantomjs.binary.path", System.getProperty("user.dir") + "//drivers//phantomjs-2.1.1-windows//bin//phantomjs.exe");
        logger = Logger.getLogger(this.getClass().getName());
        PropertyConfigurator.configure("./src/test/resources/Log4j.xml");
        logger = Logger.getLogger("devpinoyLogger");

        obj = new Properties();
        try {
            obj.load(new FileInputStream(new File(System.getProperty("user.dir") + "//ObjectRepository/OR.properties")));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            logger.info("OR file missing check the path");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            logger.info("OR file missing check the path");
        }
    }


    @AfterClass
    public static void tearDown() {

        RestAssured.reset();
//        driver.close();

    }



}


