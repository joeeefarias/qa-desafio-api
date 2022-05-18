package br.com.restassuredapitesting.tests.booking.requests;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GetBookingRequest {

    @Step("Retorna os Ids da listagem de reservas")
    public Response bookinkReturnIds(){
        return  given()
                .when()
                .get("booking");
    }
}
