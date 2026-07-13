package api;

import constants.Endpoints;
import dto.IpAddressResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import specs.Specification;
import utils.Config;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class GetTest {

    @Test
    void testGetAddress() {
        System.out.println(
                Thread.currentThread().getName()
                        + " -> GetTest"
        );

        Response response =
                given()
                        .spec(Specification.requestSpec(Config.get("dadata.url")))
                        .queryParam("ip", Config.get("ip"))
                        .when()
                        .get(Endpoints.ADDRESS_BY_IP);

        response.then()
                .log().all()
                .statusCode(200);

        IpAddressResponse result = response.as(IpAddressResponse.class);

        assertNotNull(result.getLocation());

        assertEquals(
                Config.get("expected.location"),
                result.getLocation().getValue()
        );

        assertEquals(
                Config.get("expected.country"),
                result.getLocation().getData().getCountry()
        );

        assertEquals(
                Config.get("expected.ip.city"),
                result.getLocation().getData().getCity()
        );
    }
}