package api;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import utils.Config;

import static io.restassured.RestAssured.given;

public class GetTest {

    @Test
    void testGetAddress() {

        RestAssured.baseURI = "https://dadata.ru";

        given()
                .header("Authorization", "Token " + Config.get("token"))
                .when()
                .get("/api/v2/detectAddressByIp?ip=8.8.8.8")
                .then()
                .log().all()
                .statusCode(200);
    }
}