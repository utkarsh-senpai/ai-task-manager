package com.utkarsh.aitaskmanager.e2e;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT) // ensure app runs on port 8080
public class E2ETests {

    private static String taskJsonBody;
    private static String userJsonBody;

    @BeforeAll
    static void setup() throws Exception {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;

        // Load JSON test data from resources
        taskJsonBody = new String(Files.readAllBytes(Paths.get("src/test/resources/test-task-data.json")));
        userJsonBody  = new String(Files.readAllBytes(Paths.get("src/test/resources/test-user-data.json")));
    }

    @Test
    public void testCreateAndGetTask() {
        given()
                .contentType(ContentType.JSON)
                .body(userJsonBody)
                .when()
                .post("/users")
                .then()
                .statusCode(200);

        given()
                .contentType(ContentType.JSON)
                .body(taskJsonBody)
                .when()
                .post("/tasks")
                .then()
                .statusCode(200); // Created

        given()
                .when()
                .get("/tasks")
                .then()
                .statusCode(200)
                .body("size()", equalTo(1));
    }
}

