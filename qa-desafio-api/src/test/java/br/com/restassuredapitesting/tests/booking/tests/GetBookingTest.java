package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.base.BaseTest;
import br.com.restassuredapitesting.suites.AllTests;
import br.com.restassuredapitesting.suites.ContractTests;
import br.com.restassuredapitesting.tests.booking.requests.GetBookingRequest;
import br.com.restassuredapitesting.tests.booking.requests.PostBookingRequest;
import br.com.restassuredapitesting.tests.booking.requests.payloads.BookingPayloads;
import br.com.restassuredapitesting.utils.Utils;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.Matchers.greaterThan;

@Feature("Feature - Busca retorno de reservas")
public class GetBookingTest extends BaseTest {

    GetBookingRequest getBookingRequest = new GetBookingRequest();
    PostBookingRequest postBookingRequest = new PostBookingRequest();

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Listar Ids de reservas")
    @Category({AllTests.class})
    public void validaListagemDeIdDasReservas(){

        getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Listar reservas pelo primeiro nome")
    @Category({AllTests.class})
    public void validaFiltroReservaPrimeiroNome(){
        Map<String, String> filtro = new HashMap<>();
        filtro.put("firstname", "Johney");

        postBookingRequest.createBooking(BookingPayloads.openFieldsValidPayload(filtro.get("firstname"),"", "","")).then().statusCode(200);

        getBookingRequest.bookingFilter(filtro)
                .then()
                .statusCode(200)
                .log().all()
                .body("size()", greaterThan(0));
    }
    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Listar reservas pelo ultimo nome")
    @Category({AllTests.class})
    public void validaFiltroReservaUltimoNome(){
        Map<String, String> filtro = new HashMap<>();
        filtro.put("lastname", "Stuart");

        postBookingRequest.createBooking(BookingPayloads.openFieldsValidPayload("",filtro.get("lastname"),
                "","")).then().statusCode(200);

        getBookingRequest.bookingFilter(filtro)
                .then()
                .statusCode(200)
                .log().all()
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Garantir o Schema de retorno da listagem das reservas")
    @Category({AllTests.class, ContractTests.class})
    public void validaSchemaListagemDeReserva(){
        getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .body(matchesJsonSchema(new File(Utils.getSchemBasePath("booking",
                        "bookings"))));
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Garantir o Schema de retorno de uma reserva especifíca")
    @Category({AllTests.class, ContractTests.class})
    public void validaSchemaListagemDeReservaEspecifica(){

        int id = getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .extract()
                .path("[0].bookingid");

        getBookingRequest.bookingById(id)
                .then()
                .log().all()
                .statusCode(200)
                 .body(matchesJsonSchema(new File(Utils.getSchemBasePath("booking",
                        "bookingById"))));
    }
}
