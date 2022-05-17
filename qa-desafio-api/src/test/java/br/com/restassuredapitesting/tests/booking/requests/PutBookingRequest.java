package br.com.restassuredapitesting.tests.booking.requests;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class PutBookingRequest {

    public Response udateBookingWithToken(int id, String token){
        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "applicatio/json")
                .header("Cookie", token)
                .when()
                .body()
                .put("bookingg/" + id);
    }
}
