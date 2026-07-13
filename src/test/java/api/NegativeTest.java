package api;

import constants.Endpoints;
import dto.RequestBody;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import specs.Specification;
import utils.Config;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NegativeTest {

    @Test
    void invalidTokenTest() {
        System.out.println(
                Thread.currentThread().getName()
                        + " -> NegativeTest"
        );

        RequestBody requestBody = new RequestBody(Config.get("negative.query"));

        Response response =
                given()
                        .baseUri(Config.get("suggestions.url"))
                        .contentType("application/json")
                        .header("Authorization", "Token " + Config.get("negative.invalid.token"))
                        .body(requestBody)
                        .when()
                        .post(Endpoints.SUGGEST_ADDRESS);

        response.then()
                .log().body()
                .statusCode(403);
    }

    @Test
    void requestWithoutTokenTest() {
        RequestBody requestBody = new RequestBody(Config.get("negative.query"));

        Response response =
                given()
                        .baseUri(Config.get("suggestions.url"))
                        .contentType("application/json")
                        .body(requestBody)
                        .when()
                        .post(Endpoints.SUGGEST_ADDRESS);

        response.then()
                .log().body()
                .statusCode(401);
    }

    @Test
    void emptyQueryTest() {
        RequestBody requestBody = new RequestBody("");

        Response response =
                given()
                        .spec(Specification.requestSpec(Config.get("suggestions.url")))
                        .body(requestBody)
                        .when()
                        .post(Endpoints.SUGGEST_ADDRESS);

        response.then()
                .log().body()
                .statusCode(200);

        assertTrue(
                response.jsonPath()
                        .getList("suggestions")
                        .isEmpty()
        );
    }

    @Test
    void incorrectQueryTest() {
        RequestBody requestBody = new RequestBody(Config.get("negative.incorrect.query"));

        Response response =
                given()
                        .spec(Specification.requestSpec(Config.get("suggestions.url")))
                        .body(requestBody)
                        .when()
                        .post(Endpoints.SUGGEST_ADDRESS);

        response.then()
                .log().body()
                .statusCode(200);

        assertTrue(
                response.jsonPath()
                        .getList("suggestions")
                        .isEmpty()
        );
    }
}