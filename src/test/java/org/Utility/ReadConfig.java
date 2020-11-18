package org.Utility;


import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.*;


public class ReadConfig {

    Properties config;
    Properties obj;
    public static Response response;

    public ReadConfig() {
        File src = new File("./config.properties");
        config = new Properties();
        try {
            FileInputStream fis = new FileInputStream(src);
            config = new Properties();
            config.load(fis);
        } catch (Exception e) {
            System.out.println("Exception is " + e.getMessage());
        }
    }

    public void controleExecutor() {
        obj = new Properties();
        try {
            obj.load(new FileInputStream(new File("./ObjectRepository/OR.properties")));
        } catch (
                FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
        }
    }

    public static String getAppProperty(String propertyKey) {
        Properties props = new Properties();

        try {
            InputStream appProps = new FileInputStream(System.getProperty("user.dir") + "./config.properties");
            props.load(appProps);
            System.out.println("The 'application.properties' file was loaded");
        } catch (IOException e) {
            System.out.println("*** The 'application.properties' file was not found");
            e.printStackTrace();
        }
        String result = props.getProperty(propertyKey);
        System.out.println("Retrieved the value of " + propertyKey + " from 'application.properties' file");
        return String.valueOf(result);
    }


    public String getSonarComponent(String param,String project_key) {
        RestAssured.baseURI = ReadConfig.getAppProperty("Sonar_baseurl");
        RequestSpecification request = RestAssured.
                given().queryParam("qualifiers", param).
                queryParam("q", project_key).
                auth().preemptive().
                basic(getAppProperty("SonarToken"), "");
        response = request.get("/api/components/search");
        String project = response.jsonPath().getString("components.project");
        return project;
    }
}


