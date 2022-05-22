package br.com.restassuredapitesting.tests.booking.requests;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import java.util.Map;
import static io.restassured.RestAssured.given;

public class GetBookingRequest {

    @Step("Retorna os Ids da listagem de reservas")
    public Response bookingReturnIds(){
        return  given()
                .when()
                .get("booking");
    }

    @Step("Busca reservas por filtros")
    public Response bookingFilter(Map<String, String> filtro){
        return given()
                .header("Content_Type", "application/json")
                .when()
                .queryParams(filtro)
                .get("booking");
    }

    @Step("Busca reservas por Id")
    public Response bookingById(int id){

        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .when()
                .get("booking/" + id);
    }
}
