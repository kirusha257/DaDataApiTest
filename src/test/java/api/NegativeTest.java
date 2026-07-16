package api;

import constants.Endpoints;
import dto.RequestBody;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import specs.Specification;
import utils.Config;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NegativeTest {

    @Test
    @DisplayName("Запрос с невалидным токеном возвращает 403")
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
    @DisplayName("Запрос без токена возвращает 401")
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
    @DisplayName("Пустой или некорректный query не возвращает подсказок")
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
    @DisplayName("Запрос по IP без результата возвращает пустую локацию")
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