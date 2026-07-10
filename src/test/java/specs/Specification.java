package specs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;


public class Specification {


    public static RequestSpecification requestSpec(String url) {

        return new RequestSpecBuilder()
                .setBaseUri(url)
                .setContentType(ContentType.JSON)
                .addHeader(
                        "Authorization",
                        "Token " + System.getenv("DADATA_TOKEN")
                )
                .build();
    }
}