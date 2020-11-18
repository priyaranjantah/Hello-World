package org.Listeners;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {
    static ExtentReports extent;
    final static String filePath = System.getProperty("user.dir") + "/TestReports/TestResult.html";
//    static String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
//    static String repName = "Test-Report-" + timestamp + ".html";
//    static String filePath =  System.getProperty("user.dir") + "/TestReports/";

    public synchronized static ExtentReports getReporter() {
        if (extent == null){
            extent = new ExtentReports(filePath, true);
        }

        return extent;
    }
}