package heatlh;


import acceptance.IntegrationTestBase;
import io.restassured.RestAssured;
import org.junit.Test;

import static io.restassured.RestAssured.given;


public class HealthTest extends IntegrationTestBase {

    @Test
    public void shouldGetHealthCheck() {
        RestAssured.port = port;
        RestAssured.baseURI = "http://localhost";

        given().log().all()
                .when()
                .get("actuator/health").then().log().body().statusCode(200);

    }
}
