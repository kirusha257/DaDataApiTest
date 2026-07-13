package specs;

import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class Specification {

    public static RequestSpecification requestSpec(String url) {
        String token = System.getenv("DADATA_TOKEN");

        return given()
                .baseUri(url)
                .contentType("application/json")
                .auth()
                .oauth2("Token " + System.getenv("DADATA_TOKEN"));
    }
}