package br.com.restassuredapitesting.tests.booking.requests;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class DeletBookingRequest {

    @Step("Deleta uma reserva com um totken")
    public Response deleteBooking(int id, String token){
        return  given()
                .header("Content_Type", "application/json")
                .header("Cookie", token)
                .when()
                .delete("booking/" + id);
    }

    @Step("Deleta uma reserva sem um totken")
    public Response unauthorizedDeleteBooking(int id){
        return  given()
                .header("Content_Type", "application/json")
                .when()
                .delete("booking/" + id);
    }
}
