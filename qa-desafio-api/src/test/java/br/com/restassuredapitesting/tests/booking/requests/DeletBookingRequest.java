package br.com.restassuredapitesting.tests.booking.requests;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class DeletBookingRequest {

    @Step("Deleta uma reserva")
    public Response deleteBooking(int id, String token){
        return  given()
                .header("Content_Type", "application/json")
                .header("Cookie", token)
                .when()
                .delete("booking/" + id);
    }
}
