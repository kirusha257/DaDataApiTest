package api;

import constants.Endpoints;
import dto.AddressResponse;
import dto.Suggestion;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import specs.Specification;
import utils.Config;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class PostTest {

    @Test
    void testSuggestAddress() {

        String body = """
                {
                  "query": "%s"
                }
                """.formatted(Config.get("search"));

        Response response =
                given()
                        .spec(
                                Specification.requestSpec(
                                        Config.get("suggestions.url")
                                )
                        )
                        .body(body)
                        .when()
                        .post(Endpoints.SUGGEST_ADDRESS);

        response.then()
                .log().body()
                .statusCode(200);

        AddressResponse result =
                response.as(AddressResponse.class);

        assertNotNull(result.getSuggestions());

        assertFalse(result.getSuggestions().isEmpty());

        Suggestion suggestion =
                result.getSuggestions().get(0);

        assertNotNull(suggestion.getValue());

        assertEquals(
                "Россия",
                suggestion.getData().getCountry()
        );

        assertEquals(
                "Москва",
                suggestion.getData().getCity()
        );
    }
}