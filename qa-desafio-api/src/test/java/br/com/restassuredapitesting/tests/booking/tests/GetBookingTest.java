package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.base.BaseTest;
import br.com.restassuredapitesting.suites.AcceptanceCriticalTests;
import br.com.restassuredapitesting.suites.AcceptanceExceptionTests;
import br.com.restassuredapitesting.suites.AllTests;
import br.com.restassuredapitesting.suites.SchemaTests;
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
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

@Feature("Feature - Busca retorno de reservas")
public class GetBookingTest extends BaseTest {
    GetBookingRequest getBookingRequest = new GetBookingRequest();
    PostBookingRequest postBookingRequest = new PostBookingRequest();

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Listar Ids de reservas")
    @Category({AllTests.class, AcceptanceCriticalTests.class})
    public void validaListagemDeIdDasReservas(){

        getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Listar uma reserva especifica")
    @Category({AllTests.class, AcceptanceCriticalTests.class})
    public void validaFiltroReservaPorId(){

        int id = postBookingRequest.createBooking(BookingPayloads.openFieldsValidPayload("", "",
                "", "")).then().extract().path("bookingid");

        getBookingRequest.bookingById(id)
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Listar reservas pelo primeiro nome")
    @Category({AllTests.class, AcceptanceCriticalTests.class})
    public void validaFiltroReservaPrimeiroNome(){
        Map<String, String> filtro = new HashMap<>();
        filtro.put("firstname", "Tyler");

        postBookingRequest.createBooking(BookingPayloads.openFieldsValidPayload(filtro.get("firstname"),"",
                "","")).then().statusCode(200);

        getBookingRequest.bookingFilter(filtro)
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }
    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Listar reservas pelo ultimo nome")
    @Category({AllTests.class, AcceptanceCriticalTests.class})
    public void validaFiltroReservaUltimoNome(){
        Map<String, String> filtro = new HashMap<>();
        filtro.put("lastname", "Durdeen");

        postBookingRequest.createBooking(BookingPayloads.openFieldsValidPayload("", filtro.get("lastname"),
                "","")).then().statusCode(200);

        getBookingRequest.bookingFilter(filtro)
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Listar reservas por data de checkin")
    @Category({AllTests.class, AcceptanceCriticalTests.class})
    public void validaFiltroReservaCheckin(){
        Map<String, String> filtro = new HashMap<>();
        filtro.put("checkin", "2028-01-31");

        postBookingRequest.createBooking(BookingPayloads.openFieldsValidPayload("", "",
                "2028-02-01","")).then().statusCode(200);

        getBookingRequest.bookingFilter(filtro)
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Listar reservas por data de checkout")
    @Category({AllTests.class, AcceptanceCriticalTests.class})
    public void validaFiltroReservaCheckout(){
        Map<String, String> filtro = new HashMap<>();
        filtro.put("checkout", "1931-01-02");

        postBookingRequest.createBooking(BookingPayloads.openFieldsValidPayload("", "",
                "","1931-01-01")).then().statusCode(200);

        getBookingRequest.bookingFilter(filtro)
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Listar reservas por data de checkin e checkout")
    @Category({AllTests.class, AcceptanceCriticalTests.class})
    public void validaFiltroReservaCheckinECheckout(){
        Map<String, String> filtro = new HashMap<>();
        filtro.put("checkin", "2028-03-01");
        filtro.put("checkout", "2028-03-11");

        postBookingRequest.createBooking(BookingPayloads.openFieldsValidPayload("", "",
                "2028-03-02","2028-03-10")).then().statusCode(200);

        getBookingRequest.bookingFilter(filtro)
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Listar reservas por nome, data de checkin e checkout")
    @Category({AllTests.class, AcceptanceCriticalTests.class})
    public void validaFiltroReservaNomeCheckinECheckout(){
        Map<String, String> filtro = new HashMap<>();
        filtro.put("checkin", "2028-12-01");
        filtro.put("checkout", "2028-12-11");
        filtro.put("firstname", "Goku");

        postBookingRequest.createBooking(BookingPayloads.openFieldsValidPayload(filtro.get("firstname"), "",
                "2028-12-02","2028-12-10")).then().statusCode(200);

        getBookingRequest.bookingFilter(filtro)
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Listar reservas com um filtro mal formatado")
    @Category({AllTests.class, AcceptanceExceptionTests.class})
    public void validaFiltroReservaInvalido(){
        Map<String, String> filtro = new HashMap<>();
        filtro.put("checkin", "2022-14-32");

        postBookingRequest.createBooking(BookingPayloads.openFieldsValidPayload("", "",
                "","")).then().statusCode(200);

        Response response = getBookingRequest.bookingFilter(filtro);
        assertThat(Arrays.asList(200, 201, 202), not(hasItem(response.getStatusCode())));

    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Garantir o Schema de retorno da listagem das reservas")
    @Category({AllTests.class, SchemaTests.class})
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
    @Category({AllTests.class, SchemaTests.class})
    public void validaSchemaListagemDeReservaEspecifica(){

        int id = postBookingRequest.createBooking(BookingPayloads.openFieldsValidPayload("", "",
                "", "")).then().extract().path("bookingid");

        getBookingRequest.bookingById(id)
                .then()
                .statusCode(200)
                 .body(matchesJsonSchema(new File(Utils.getSchemBasePath("booking",
                        "bookingById"))));
    }
}
