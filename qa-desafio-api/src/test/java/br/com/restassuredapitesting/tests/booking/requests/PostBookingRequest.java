package br.com.restassuredapitesting.tests.booking.requests;

import br.com.restassuredapitesting.tests.booking.requests.payloads.BookingPayloads;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class PostBookingRequest {

    BookingPayloads bookingPayloads = new BookingPayloads();

    @Step("Cria uma reserva")
    public Response createBooking(JSONObject payload){
        return given()
                .header("Content-Type", "application/json")
                .when()
                .body(payload.toString())
                .log().all()
                .post("booking");
    }
}
