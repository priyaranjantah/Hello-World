package org.Stage.Kickdrum;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class Kickdrum {

    public static Logger logger;
    public static String Resturl = "https://qa-test.kickdrumtech.com/v1/login";
    public static RequestSpecification httpRequest;
    public static Response response;

    @Test
    public void TC_01_POST_400() {
        try {

            httpRequest = given();
            JSONObject requestParams = new JSONObject();
            requestParams.put("", "");
            requestParams.put("", "");
            httpRequest.body(requestParams.toJSONString());
            response = httpRequest.request(Method.POST, Resturl);
            int statusCode = response.getStatusCode();
            System.out.println(statusCode);
            logger.info(response.body().prettyPrint());
            Assert.assertEquals(statusCode, 400);
        } catch (Exception e) {
            logger.info("Method Exception Raised" + e.getMessage());
        }
    }

    @Test
    public void TC_02_POST_200() {
        try {
            httpRequest = given();
            JSONObject requestParams = new JSONObject();
            requestParams.put("username", "kickdrum");
            requestParams.put("password", "kickdrum");
            httpRequest.body(requestParams.toJSONString());
            response = httpRequest.request(Method.POST, Resturl);
            int statusCode = response.getStatusCode();
            System.out.println(statusCode);
            logger.info(response.body().prettyPrint());
            Assert.assertEquals(statusCode, 200);

        } catch (Exception e) {
            logger.info("Method Exception Raised" + e.getMessage());
        }
    }


    @Test
    public void TC_03_POST_401() throws IOException {
        try {

            httpRequest = given();
            JSONObject requestParams = new JSONObject();
            requestParams.put("username", "@kickdrum");
            requestParams.put("password", "@kickdrum");
            httpRequest.body(requestParams.toJSONString());
            response = httpRequest.request(Method.POST, Resturl);
            int statusCode = response.getStatusCode();
            System.out.println(statusCode);
            logger.info(response.body().prettyPrint());
            Assert.assertEquals(statusCode, 401);

        } catch (
                Exception e) {
            logger.info("Method Exception Raised" + e.getMessage());
        }
    }

    @Test
    public void TC_04_PUT_200() {
        try {
            RestAssured.baseURI = "https://qa-test.kickdrumtech.com/v1/user";
            RequestSpecification request = given();
            request.queryParam("user", "kickdrum");
            response = request.put("/{user}");
            int statusCode = response.getStatusCode();
            System.out.println(statusCode);
            logger.info(response.body().prettyPrint());
            Assert.assertEquals(statusCode, 200);

        } catch (
                Exception e) {
            logger.info("Method Exception Raised" + e.getMessage());
        }
    }


    @Test
    public void TC_05_PUT_504() {
        try {
            RestAssured.baseURI = "https://qa-test.kickdrumtech.com/v1/user";
            httpRequest = given();
            httpRequest.queryParam("user", "@kickdrum");
            response = httpRequest.put("/{user}");
            int statusCode = response.getStatusCode();
            System.out.println(statusCode);
            logger.info(response.body().prettyPrint());
            Assert.assertEquals(statusCode, 504);

        } catch (
                Exception e) {
            logger.info("Method Exception Raised" + e.getMessage());
        }
    }
}


