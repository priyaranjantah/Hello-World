package org.Stage.Kickdrum.EnggDashboard;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.authentication.BasicAuthScheme;
import com.jayway.restassured.authentication.PreemptiveBasicAuthScheme;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import io.restassured.http.Method;
import org.Stage.BaseClass;
import org.Utility.ReadConfig;
import org.Utility.XLUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.HashMap;


/***
 * Documentation URL: https://developer.github.com/v3/repos/
 * @param browser
 * @throws InterruptedException
 */


import static com.jayway.restassured.RestAssured.given;

public class KD_Engg_Dashboard extends BaseClass {

    //https://developer.github.com/v3/repos/
    public static Logger logger;
    public static RequestSpecification httpRequest;
    public static Response response;
    public static Response response1;

    public static String ranStr = "KD" + RandomStringUtils.randomAlphabetic(10);

    @Test(description = "Generate SonarCube Refresh Token",enabled = false)
    public void TC_01_GenerateNewToken() {
        try {
            RestAssured.baseURI = getAppProperty("Sonar_baseurl");
            RequestSpecification request = RestAssured.
                    given().queryParam("name", ranStr).
                    auth().
                    preemptive().
                    basic(getAppProperty("SonarToken"), "");
                response = request.post("/api/user_tokens/generate");
            System.out.println(response.statusCode());
            System.out.println(response.body().prettyPrint());

        } catch (Exception e) {
            logger.info("Method Exception Raised" + e.getMessage());
        }
    }

    @Test(dataProvider = "getResponse")
    public void TC_01_GETSonarCube_TokenSearch(String url, String description) {
        try {
            RestAssured.baseURI = ReadConfig.getAppProperty("Sonar_baseurl");
            RequestSpecification request = RestAssured.
                    given().
                    auth().
                    preemptive().
                    basic(getAppProperty("SonarToken"), "");
            response = request.get(url);
            System.out.println(response.statusCode());
            System.out.println(response.body().prettyPrint());
        } catch (Exception e) {
            logger.info("Method Exception Raised" + e.getMessage());
        }
    }

    @Test(description = "Get List of API Issues",enabled = false)
    public void TC_01_GETSonarCubeAPIIssues() {
        try {
            String project = getSonarComponent("TRK","Team Secret");
            RestAssured.baseURI = ReadConfig.getAppProperty("Sonar_baseurl");
            RequestSpecification request = RestAssured.
                    given().queryParam("componentKeys", project)
                    .queryParam("severities", "CRITICAL")
                    .queryParam("status", "OPEN").
                            auth().
                            preemptive().
                            basic(getAppProperty("SonarToken"), "");
            response = request.get("/api/issues/search");
            String issues= response.jsonPath().getString("paging.total");
            System.out.println(response.statusCode());
            System.out.println("Number of critical issues for Team Secret Project :" + issues);
        } catch (Exception e) {
            logger.info("Method Exception Raised" + e.getMessage());
        }
    }

    @DataProvider(name = "getResponse")
    Object[][] getSonarResponse() throws IOException {
        String path = System.getProperty("user.dir") + "/TestSuitData/TestData.xlsx";
        int rownum = XLUtils.getRowCount(path, "Sonar_URL");
        int colcount = XLUtils.getCellCount(path, "Sonar_URL", 1);
        String getData[][] = new String[rownum][colcount];
        for (int k = 1; k <= rownum; k++) {
            for (int m = 0; m < colcount; m++) {
                getData[k - 1][m] = XLUtils.getCellData(path, "Sonar_URL", k, m);
            }
        }
        return getData;
    }
}
