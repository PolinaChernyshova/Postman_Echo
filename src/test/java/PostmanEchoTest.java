import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import org.junit.Test;

public class PostmanEchoTest {
    @Test
    public void testGetRequest_thenStatus200() {
        given()
                .queryParam("foo1", "bar1")
                .queryParam("foo2", "bar2")
                .baseUri("https://postman-echo.com")
                .when()
                .get("/get")
                .then().log().all()
                .body("args.foo1", equalTo("bar1"))
                .body("args.foo2", equalTo("bar2"))
                .statusCode(200);
    }
    @Test
    public void testPostRawText_thenStatus200() {
        given()
                .body("This is expected to be sent back as part of response body.")
                .baseUri("https://postman-echo.com")
                .when()
                .post("/post")
                .then().log().all()
                .body("data",equalTo("This is expected to be sent back as part of response body."))
                .body("json", equalTo(null))
                .statusCode(200);
    }
    @Test
    public void testPostFormDataJson_thenStatus200() {
        given()
                .contentType("application/json")
                .body("{\"foo1\":\"bar1\",\"foo2\":\"bar2\"}")
                .baseUri("https://postman-echo.com")
                .when()
                .post("/post")
                .then().log().all()
                .body("json.foo1", equalTo("bar1"))
                .body("json.foo2", equalTo("bar2"))
                .statusCode(200);
    }
    @Test
    public void testPutRequest_thenStatus200() {
        given()
                .contentType("application/json")
                .body("{\"method\":\"PUT\"}")
                .baseUri("https://postman-echo.com")
                .when()
                .put("/put")
                .then().log().all()
                .body("json.method", equalTo("PUT"))
                .statusCode(200);
    }
    @Test
    public  void TestPatchRequest_thenStatus200() {
        given()
                .contentType("application/json")
                .body("{\"method\":\"PATCH\"}")
                .baseUri("https://postman-echo.com")
                .when()
                .patch("/patch")
                .then().log().all()
                .body("json.method", equalTo("PATCH"))
                .statusCode(200);
    }
    @Test
    public void TestDeleteRequest_thenStatus200() {
        given()
                .contentType("application/json")
                .body("{\"method\": \"DELETE\"}")
                .baseUri("https://postman-echo.com")
                .when()
                .delete("/delete")
                .then().log().all()
                .body("json.method", equalTo("DELETE"))
                .statusCode(200);
    }
}
