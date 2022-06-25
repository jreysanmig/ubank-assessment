package tests.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import utils.ConfigUtil;

public class BaseApiTest {
    protected RequestSpecification reqSpec;
    protected ResponseSpecification resSpec;

    @BeforeAll
    public static void setSystemProperties() {
        ConfigUtil.loadProperties();
    }

    @BeforeEach
    public void setup() {
        reqSpec = new RequestSpecBuilder()
                .setBaseUri(System.getProperty("baseUrl.api"))
                .setContentType(ContentType.JSON)
                .build();

        resSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();
    }
}
