package br.com.restassuredapitesting.tests.booking.requests;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class PutBookingRequest {

   @Step("Atualiza uma reserva especifica com o parâmetro token")
    public Response updateBookingWithToken(JSONObject payload, int id, String token){

        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Cookie", token)
                .when()
                .body(payload.toString())
                .put("booking/" + id);
    }

    @Step("Atualiza uma reserva especifica utilizando Basic auth")
    public Response updateBookingWithBasicAuth(JSONObject payload, int id){

        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .auth().preemptive().basic("admin", "password123")
                .when()
                .body(payload.toString())
                .put("booking/" + id);
    }

    @Step("Atualiza uma reserva especifica sem token")
    public Response updateBookingWithoutToken(JSONObject payload, int id){

        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .when()
                .body(payload.toString())
                .put("booking/" + id);
    }
}
