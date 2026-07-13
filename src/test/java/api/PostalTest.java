package api;

import constants.Endpoints;
import dto.AddressResponse;
import dto.RequestBody;
import dto.Suggestion;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import specs.Specification;
import utils.Config;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class PostalTest {

    @Test
    void testSuggestByPostalCode() {
        System.out.println(
                Thread.currentThread().getName()
                        + " -> PostalTest"
        );

        String postalCode = Config.get("postal.query");
        String expectedCity = Config.get("postal.expected.city");

        RequestBody requestBody = new RequestBody(postalCode);

        Response response =
                given()
                        .spec(Specification.requestSpec(Config.get("suggestions.url")))
                        .body(requestBody)  // ← Используем DTO
                        .when()
                        .post(Endpoints.SUGGEST_ADDRESS);

        response.then()
                .log().body()
                .statusCode(200);

        AddressResponse result = response.as(AddressResponse.class);

        assertNotNull(result.getSuggestions());
        assertFalse(result.getSuggestions().isEmpty(), "No suggestions found for postal code: " + postalCode);

        Suggestion suggestion = result.getSuggestions().get(0);

        String city = suggestion.getData().getCity();
        assertNotNull(city, "City is null in response");

        assertEquals(expectedCity, city,
                "Expected city: " + expectedCity + " for postal code " + postalCode + ", but got: " + city);

        String country = suggestion.getData().getCountry();
        assertNotNull(country);
        assertEquals(Config.get("expected.country"), country);  // ← берем из конфига

        String value = suggestion.getValue();
        assertNotNull(value);
        assertTrue(value.contains(city),
                "Address value doesn't contain city " + city + ". Actual: " + value);

        System.out.println("Postal code: " + postalCode);
        System.out.println("Found address: " + value);
        System.out.println("City: " + city);
        System.out.println("Country: " + country);
    }
}