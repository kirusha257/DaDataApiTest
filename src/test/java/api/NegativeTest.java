package api;

import constants.Endpoints;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import specs.Specification;
import utils.Config;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NegativeTest {

    @Test
    void invalidTokenTest() {

        Response response =
                given()
                        .spec(
                                Specification.requestSpec(
                                        Config.get("suggestions.url")
                                )
                        )
                        .header(
                                "Authorization",
                                "Token wrong_token"
                        )
                        .body("""
                                {
                                  "query": "Москва"
                                }
                                """)
                        .when()
                        .post(Endpoints.SUGGEST_ADDRESS);

        response.then()
                .log().body()
                .statusCode(400);
    }

    @Test
    void requestWithoutTokenTest() {

        Response response =
                given()
                        .baseUri(Config.get("suggestions.url"))
                        .contentType("application/json")
                        .body("""
                                {
                                  "query": "Москва"
                                }
                                """)
                        .when()
                        .post(Endpoints.SUGGEST_ADDRESS);

        response.then()
                .log().body()
                .statusCode(401);
    }

    @Test
    void emptyQueryTest() {

        Response response =
                given()
                        .spec(
                                Specification.requestSpec(
                                        Config.get("suggestions.url")
                                )
                        )
                        .body("""
                                {
                                  "query": ""
                                }
                                """)
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

        Response response =
                given()
                        .spec(
                                Specification.requestSpec(
                                        Config.get("suggestions.url")
                                )
                        )
                        .body("""
                                {
                                  "query": "qwerty123456789"
                                }
                                """)
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