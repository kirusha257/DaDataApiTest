package api;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import utils.Config;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;

public class PostTest {

    @Test
    void testSuggestAddress() {

        RestAssured.baseURI = "https://suggestions.dadata.ru";

        String search = Config.get("search");
        String token = Config.get("token");

        String requestBody = """
                {
                    "query": "%s"
                }
                """.formatted(search);

        System.out.println("SEARCH = " + search);
        System.out.println("REQUEST BODY = " + requestBody);

        given()
                .header("Authorization", "Token " + token)
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/suggestions/api/4_1/rs/suggest/address")
                .then()
                .log().all()
                .statusCode(200)
                .body("suggestions.size()", greaterThan(0));
    }
}