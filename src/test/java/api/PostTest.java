package api;

import constants.Endpoints;
import dto.AddressResponse;
import dto.RequestBody;
import dto.Suggestion;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import specs.Specification;
import utils.Config;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class PostTest {

    @Test
    @DisplayName("Подсказка адреса по названию города возвращает корректную страну и город")
    void testSuggestAddress() {
        System.out.println(
                Thread.currentThread().getName()
                        + " -> PostTest"
        );

        RequestBody requestBody = new RequestBody(Config.get("search"));

        Response response =
                given()
                        .spec(Specification.requestSpec(Config.get("suggestions.url")))
                        .body(requestBody)
                        .when()
                        .post(Endpoints.SUGGEST_ADDRESS);

        response.then()
                .log().body()
                .statusCode(200);

        AddressResponse result = response.as(AddressResponse.class);

        assertNotNull(result.getSuggestions());
        assertFalse(result.getSuggestions().isEmpty());

        Suggestion suggestion = result.getSuggestions().get(0);

        assertNotNull(suggestion.getValue());
        assertEquals(Config.get("expected.country"), suggestion.getData().getCountry());
        assertEquals(Config.get("expected.city"), suggestion.getData().getCity());
    }
}