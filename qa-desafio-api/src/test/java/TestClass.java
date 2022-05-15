import io.restassured.response.Response;
import org.junit.Test;
import java.util.concurrent.TimeUnit;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class TestClass {

    @Test
    public void validaApiOnline(){
        Response responsePing =
                given()
                .header("Content-Type", "application/json")
                .when()
                .get("https://treinamento-api.herokuapp.com/ping");
        responsePing
                .then()
                .statusCode(201)
                .time(lessThan(2L), TimeUnit.SECONDS);
    }
}
