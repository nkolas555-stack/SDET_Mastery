package org.example;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ApiTest {
    static ExtentReports report = WikipediaTest.report; // Usamos el reporte que ya existe
    static ExtentTest test;
    @Test
    public void validateGoogle() {
        // ok
        RestAssured.get("https://google.com")
                .then()
                .statusCode(200);

        System.out.println("✅ API FRAMEWORK IS ALIVE!");
    }

    @Test
    public void createUserTest() {

        String jsonBody = "{" +
                "  \"name\": \"Nicolas\"," +
                "  \"job\": \"Senior SDET\"" +
                "}";


        given()
                .header("Content-Type", "application/json")
                .body(jsonBody)
                .log().all()
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().all()
                .statusCode(201)
                .body("name", is("Nicolas"))
                .body("job", is("Senior SDET"))
                .body("id", notNullValue());

        System.out.println("✅ API POST PASSED: Usuario creado con éxito.");
    }

    @Test
    public void createUserFromExternalFile() throws IOException {
        String rootPath = System.getProperty("user.dir");
        String filePath = rootPath + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "testData.xlsx";
        String jsonBody = new String(Files.readAllBytes(Paths.get(filePath)));

        given()
                .header("Content-Type", "application/json")
                .body(jsonBody)
                .when()
                .post("https://jsonplaceholder.typicode.com/posts")
                .then()
                .log().all()
                .statusCode(201)
                .body("title", is("Lorenzo Nicolas"));

        System.out.println("✅ API EXTERNAL DATA PASSED: ¡succesfully!");
    }
    @Test
    public void validateSimpleApi() {
        // 1. Creamos la entrada en el reporte
        test = WikipediaTest.report.createTest("API Validation: Ping-Pong");

        test.info("sending GET request to Vercel API...");

        given()
                .get("https://vercel.app/ping")
                .then()
                .statusCode(200)
                .body("message", equalTo("pong"));

        test.pass("API respond 200 and message 'pong'");
    }
    @Test
    public void cloudSanityTest() {
        Assert.assertTrue(true, "Cloud execution is working!");
        System.out.println("✅ CLOUD SANITY PASSED");
    }


}
