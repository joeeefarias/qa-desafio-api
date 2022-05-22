package br.com.restassuredapitesting.tests.booking.requests;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.json.JSONObject;
import static io.restassured.RestAssured.given;

public class PostBookingRequest {


    @Step("Cria uma reserva")
    public Response createBooking(JSONObject payload){
        return given()
                .header("Content-Type", "application/json")
                .when()
                .body(payload.toString())
                .post("booking");
    }

}
