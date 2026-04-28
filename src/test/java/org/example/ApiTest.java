package org.example;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ApiTest {
    @Test
    public void validateGoogle() {
        // ok
        RestAssured.get("https://google.com")
                .then()
                .statusCode(200);

        System.out.println("✅ API FRAMEWORK IS ALIVE!");
    }

}
