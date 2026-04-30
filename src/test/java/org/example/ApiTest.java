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


}
