package br.com.restassuredapitesting.tests.booking.requests;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GetBookingRequest {

    @Step("Retorna os Ids da listagem de reservas")
    public Response bookingReturnIds(){
        return  given()
                .when()
                .get("booking");
    }

    @Step("Busca reservas com filtros")
    public Response bookingFilter(String filter1,String name1, String filter2, String name2,
                                  String filter3, String name3){
        return given()
                .header("Content_Type", "application/json")
                .when()
                .queryParam(filter1, name1)
                .queryParam(filter2, name2)
                .queryParam(filter3, name3)
                .get("booking");
    }
}
