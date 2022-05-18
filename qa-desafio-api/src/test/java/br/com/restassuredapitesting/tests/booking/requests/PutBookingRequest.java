package br.com.restassuredapitesting.tests.booking.requests;

import br.com.restassuredapitesting.tests.booking.requests.payloads.BookingPayloads;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class PutBookingRequest {
   BookingPayloads bookingPayloads = new BookingPayloads();

    public Response udateBookingWithToken(int id, String token){

        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Cookie", token)
                .when()
                .body(bookingPayloads.validPayloadBooking().toString())
                .put("booking/" + id);
    }
}
