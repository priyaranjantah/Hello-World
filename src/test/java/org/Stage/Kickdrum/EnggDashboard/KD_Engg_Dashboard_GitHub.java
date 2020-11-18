package org.Stage.Kickdrum.EnggDashboard;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import org.Stage.BaseClass;
import org.Utility.ReadConfig;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

public class KD_Engg_Dashboard_GitHub extends BaseClass {

    //https://developer.github.com/v3/repos/
    public static Logger logger;
    public static RequestSpecification httpRequest;
    public static Response response;
    public static Response response1;

    public static String ranStr= "KD" + RandomStringUtils.randomAlphabetic(10);

    @Test(description = "Generate SonarCube Refresh Token")
    public void TC_01_GenerateNewToken() {
        try {
            RestAssured.baseURI = getAppProperty("Sonar_baseurl");
            RequestSpecification request = RestAssured.
                    given().queryParam("name",ranStr).
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

    @Test(dataProvider = "")
    public void TC_01_GETSonarCube_TokenSearch() {
        try {
            RestAssured.baseURI = getAppProperty("Sonar_baseurl");
            RequestSpecification request = RestAssured.
                    given().
                    auth().
                    preemptive().
                    basic("c0bf2bfe2254a6447e73cb6d1ef284284575c0db", "");
            response = request.get("/api/user_tokens/search");
            System.out.println(response.statusCode());
            System.out.println(response.body().prettyPrint());
        } catch (Exception e) {
            logger.info("Method Exception Raised" + e.getMessage());
        }
    }

    @Test(description = "Get List of API Issues")
    public void TC_01_GETSonarCubeAPIIssues() {
        try {
            RestAssured.baseURI = Resturl;
            RequestSpecification request = RestAssured.
                    given().queryParam("qualifiers","TRK").
                    auth().
                    preemptive().
                    basic("c0bf2bfe2254a6447e73cb6d1ef284284575c0db", "");
            response = request.get("/api/components/search");
            String project = response.jsonPath().getString("components.key[0]");

            RestAssured.baseURI = Resturl;
            RequestSpecification request1 = RestAssured.
                    given().queryParam("project",project)
                    .queryParam("severity","MAJOR")
                    .queryParam("status","OPEN").
                            auth().
                            preemptive().
                            basic("c0bf2bfe2254a6447e73cb6d1ef284284575c0db", "");
            response1 = request1.get("/api/issues/search");
            String severity = response1.jsonPath().getString("issues.severity[0]");
            String project1 = response1.jsonPath().getString("issues.project[0]");

            response1 = request.get("/api/projects/search");



            System.out.println(response.statusCode());
            System.out.println(response.body().prettyPrint());
        } catch (Exception e) {
            logger.info("Method Exception Raised" + e.getMessage());
        }
    }

    @Test(description = "List repositories for the authenticated user")
    public void TC_01_GETGithubAPIList() {
        try {
            RestAssured.baseURI = getAppProperty("Github_baseurl");
            RequestSpecification request = RestAssured.
                    given().
                    auth().
                    preemptive().
                    basic("88c0c1966e6af1991d306a3bde39f539d5220cd6", "");
            response = request.get("/user/repos");
            System.out.println(response.body().prettyPrint());
        } catch (Exception e) {
            logger.info("Method Exception Raised" + e.getMessage());
        }
    }

    @Test(description = "List organization repositories")
    public void TC_01_GETGithubAPIOrgRepo() {
        try {
            RestAssured.baseURI = getAppProperty("Github_baseurl");
            RequestSpecification request = RestAssured.
                    given().
                    auth().
                    preemptive().
                    basic("88c0c1966e6af1991d306a3bde39f539d5220cd6", "");
            response = request.get("/orgs/kickdrum/repos");
            System.out.println(response.body().prettyPrint());
        } catch (Exception e) {
            logger.info("Method Exception Raised" + e.getMessage());
        }
    }

    @Test(description = "List public repositories")
    public void TC_01_GETGithubAPIPublicRepo() {
        try {
            RestAssured.baseURI = getAppProperty("Github_baseurl");
            RequestSpecification request = RestAssured.
                    given().
                    auth().
                    preemptive().
                    basic("88c0c1966e6af1991d306a3bde39f539d5220cd6", "");
            response = request.get("/repositories");
            System.out.println(response.body().prettyPrint());
        } catch (Exception e) {
            logger.info("Method Exception Raised" + e.getMessage());
        }
    }


    @Test(description = "List repositories for a user")
    public void TC_01_GETGithubAPIUserRepo() {
        try {
            RestAssured.baseURI = getAppProperty("Github_baseurl");
            RequestSpecification request = RestAssured.
                    given().
                    auth().
                    preemptive().
                    basic("88c0c1966e6af1991d306a3bde39f539d5220cd6", "");
            response = request.get("/users/priyaranjantah/repos");
            System.out.println(response.body().prettyPrint());
        } catch (Exception e) {
            logger.info("Method Exception Raised" + e.getMessage());
        }
    }


    @Test
    public void TC_01_GETGithubAPICreate() {
        try {

            JSONObject requestParams = new JSONObject();
            requestParams.put("name", ReadConfig.getAppProperty("name"));
            requestParams.put("private", ReadConfig.getAppProperty("private"));
            requestParams.put("has_issues", ReadConfig.getAppProperty("has_issues"));
            requestParams.put("has_projects", ReadConfig.getAppProperty("has_projects"));
            requestParams.put("has_wiki", ReadConfig.getAppProperty("has_wiki"));

            RestAssured.baseURI = getAppProperty("Github_baseurl");
            RequestSpecification request = RestAssured.
                    given().
                    auth().
                    preemptive().
                    basic("88c0c1966e6af1991d306a3bde39f539d5220cd6", "").body(requestParams);
            response = request.post("/user/repos");
            System.out.println(response.statusCode());
            System.out.println(response.body().prettyPrint());
        } catch (Exception e) {
            logger.info("Method Exception Raised" + e.getMessage());
        }
    }

    @Test(description = "Get a repository")
    public void TC_01_GETGithubAPIGetRepo() {
        try {
            RestAssured.baseURI = getAppProperty("Github_baseurl");;
            RequestSpecification request = RestAssured.
                    given().
                    auth().
                    preemptive().
                    basic("88c0c1966e6af1991d306a3bde39f539d5220cd6", "");
            response = request.get("/repos/priyaranjantah/Hello-World");
            System.out.println(response.statusCode());
            System.out.println(response.body().prettyPrint());
        } catch (Exception e) {
            logger.info("Method Exception Raised" + e.getMessage());
        }
    }

    @Test
    public void TC_01_GETGithubAPIPatchReq() {
        try {

            JSONObject requestParams = new JSONObject();
            requestParams.put("name", ReadConfig.getAppProperty("name"));
            requestParams.put("description", ReadConfig.getAppProperty("description"));
            requestParams.put("private", ReadConfig.getAppProperty("private"));
            requestParams.put("has_issues", ReadConfig.getAppProperty("has_issues"));
            requestParams.put("has_projects", ReadConfig.getAppProperty("has_projects"));
            requestParams.put("has_wiki", ReadConfig.getAppProperty("has_wiki"));

            RestAssured.baseURI = getAppProperty("Github_baseurl");
            RequestSpecification request = RestAssured.
                    given().
                    auth().
                    preemptive().
                    basic("88c0c1966e6af1991d306a3bde39f539d5220cd6", "").body(requestParams);
            response = request.patch("/repos/priyaranjantah/Hello-World");
            System.out.println(response.statusCode());
            System.out.println(response.body().prettyPrint());
        } catch (Exception e) {
            logger.info("Method Exception Raised" + e.getMessage());
        }
    }

    @Test(description = "List repository contributors")
    public void TC_01_GETGithubAPIRepoContributors() {
        try {
            RestAssured.baseURI = getAppProperty("Github_baseurl");
            RequestSpecification request = RestAssured.
                    given().
                    auth().
                    preemptive().
                    basic("88c0c1966e6af1991d306a3bde39f539d5220cd6", "");
            response = request.get("/repos/kickdrum/pe-document-review-workflow/contributors");
            System.out.println(response.body().prettyPrint());
        } catch (Exception e) {
            logger.info("Method Exception Raised" + e.getMessage());
        }
    }


    @Test(description = "create a fork")
    public void TC_01_GETGithubAPIRepocreatefork() {
        try {
            RestAssured.baseURI = getAppProperty("Github_baseurl");
            RequestSpecification request = RestAssured.
                    given().
                    auth().
                    preemptive().
                    basic("88c0c1966e6af1991d306a3bde39f539d5220cd6", "");
            response = request.post("/repos/priyaranjantah/javamaventestng/pulls");
            System.out.println(response.body().prettyPrint());
        } catch (Exception e) {
            logger.info("Method Exception Raised" + e.getMessage());
        }
    }

    @Test(description = "create a fork")
    public void TC_01_GETGithubAPIRepoCodeScanning() {
        try {
            RestAssured.baseURI = getAppProperty("Github_baseurl");
            RequestSpecification request = RestAssured.
                    given().
                    auth().
                    preemptive().
                    basic("88c0c1966e6af1991d306a3bde39f539d5220cd6", "");
            response = request.get("/repos/priyaranjantah/javamaventestng/code-scanning/alerts");
            System.out.println(response.body().prettyPrint());
        } catch (Exception e) {
            logger.info("Method Exception Raised" + e.getMessage());
        }
    }


    @Test(description = "List pull requests")
    public void TC_01_GETGithubAPIRepoCreatePullRequest() {
        try {

            JSONObject requestParams = new JSONObject();
            requestParams.put("title", ReadConfig.getAppProperty("title"));
            requestParams.put("body", ReadConfig.getAppProperty("body"));
            requestParams.put("head", ReadConfig.getAppProperty("head"));
            requestParams.put("base", ReadConfig.getAppProperty("base"));

            RestAssured.baseURI = getAppProperty("Github_baseurl");
            RequestSpecification request = RestAssured.
                    given().
                    auth().
                    preemptive().
                    basic("88c0c1966e6af1991d306a3bde39f539d5220cd6", "").body(requestParams);
            response = request.post("/repos/priyaranjantah/javamaventestng/pulls");
            System.out.println(response.body().prettyPrint());
        } catch (Exception e) {
            logger.info("Method Exception Raised" + e.getMessage());
        }
    }

    @Test(description = "Create or update file contents")
    public void TC_01_POSTGithubAPICreateUpdate() {
        try {
            JSONObject requestParams = new JSONObject();
            requestParams.put("title", ReadConfig.getAppProperty("title"));
            requestParams.put("body", ReadConfig.getAppProperty("body"));
            requestParams.put("head", ReadConfig.getAppProperty("head"));
            requestParams.put("base", ReadConfig.getAppProperty("base"));

            RestAssured.baseURI = getAppProperty("Github_baseurl");
            RequestSpecification request = RestAssured.
                    given().
                    auth().
                    preemptive().
                    basic("88c0c1966e6af1991d306a3bde39f539d5220cd6", "").body(requestParams);
            response = request.get("/repos/priyaranjantah/Hello-World/contents/path");
            System.out.println(response.body().prettyPrint());
        } catch (Exception e) {
            logger.info("Method Exception Raised" + e.getMessage());
        }
    }


    @Test
    public void TC_01_GETGithubAPIDeleteRepo() {
        try {
            RestAssured.baseURI = getAppProperty("Github_baseurl");
            RequestSpecification request = RestAssured.
                    given().
                    auth().
                    preemptive().
                    basic("88c0c1966e6af1991d306a3bde39f539d5220cd6", "");
            response = request.delete("/repos/priyaranjantah/Hello-World");
            System.out.println(response.statusCode());
            System.out.println(response.body().prettyPrint());
        } catch (Exception e) {
            logger.info("Method Exception Raised" + e.getMessage());
        }
    }

}
