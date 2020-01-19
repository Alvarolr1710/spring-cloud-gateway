package gateway;

import acceptance.IntegrationTestBase;
import io.restassured.RestAssured;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertThat;


public class ApplicationTest extends IntegrationTestBase {

    @Test
    public void contextLoads() {
        RestAssured.port = port;
        RestAssured.baseURI = "http://localhost";

        Cookie userNameCookie = new Cookie.Builder("NewUniversalCookie", "some_value")
                .setSecured(true)
                .setComment("some comment")
                .build();
        Cookies cookies = new Cookies(userNameCookie);

        given().log().all()
                .cookies(cookies)
                .when()
                .get("/mortgages/application").then().log().body().statusCode(200);

    }

    @Configuration
    @EnableAutoConfiguration
    @RestController
    public static class TestService {
        @GetMapping(value = "/get/application", produces = {"application/json"})
        public ResponseEntity<String> getAvailable(@RequestHeader("Hello") String language) {
            assertThat(language, Is.is("some_value"));
            return new ResponseEntity<>("{ \"book\": \"test\"  }", HttpStatus.OK);
        }
    }

}