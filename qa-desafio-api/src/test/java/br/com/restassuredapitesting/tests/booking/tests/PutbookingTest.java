package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.base.BaseTest;
import br.com.restassuredapitesting.suites.AcceptanceCriticalTests;
import br.com.restassuredapitesting.suites.AllTests;
import br.com.restassuredapitesting.suites.SecurityTests;
import br.com.restassuredapitesting.tests.auth.requests.PostAuthRequest;
import br.com.restassuredapitesting.tests.booking.requests.PostBookingRequest;
import br.com.restassuredapitesting.tests.booking.requests.PutBookingRequest;
import br.com.restassuredapitesting.tests.booking.requests.payloads.BookingPayloads;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.junit.Assert.assertThat;

@Feature("Feature - Atualização de Reservas")
public class PutbookingTest extends BaseTest {
    PutBookingRequest putBookingRequest = new PutBookingRequest();
    PostAuthRequest postAuthRequest = new PostAuthRequest();
    PostBookingRequest postBookingRequest = new PostBookingRequest();
    BookingPayloads bookingPayloads = new BookingPayloads();

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Alterar uma reserva utilizando um token")
    @Category({AllTests.class, AcceptanceCriticalTests.class})
    public void validarAlteracaoDeUmaReservaUtilizandoToken(){
        String firstname = "João";
        String lastname = "Souza";
        String checkin = "2022-05-31";
        String checkout = "2022-06-28";

        int id = postBookingRequest.createBooking(BookingPayloads.openFieldsValidPayload("", "",
                "", "")).then().extract().path("bookingid");

        putBookingRequest.updateBookingWithToken(bookingPayloads.openFieldsValidPayload(firstname, lastname,
                        checkin, checkout), id, postAuthRequest.getToken())
                .then()
                .statusCode(200)
                .body("firstname", equalToIgnoringCase(firstname))
                .body("lastname", equalToIgnoringCase(lastname))
                .body("bookingdates.checkin", equalToIgnoringCase(checkin))
                .body("bookingdates.checkout", equalToIgnoringCase(checkout));
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Alterar uma reserva sem um token")
    @Category({AllTests.class, SecurityTests.class})
    public void validarAlteracaoDeUmaReservaSemToken(){
        String firstname = "João";
        String lastname = "Souza";
        String checkin = "2022-05-31";
        String checkout = "2022-06-28";

        int id = postBookingRequest.createBooking(BookingPayloads.openFieldsValidPayload("", "",
                "", "")).then().extract().path("bookingid");

        putBookingRequest.updateBookingWithoutToken(bookingPayloads.openFieldsValidPayload(firstname, lastname,
                        checkin, checkout), id)
                .then()
                .statusCode(403);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Alterar uma reserva utilizando um token inválido")
    @Category({AllTests.class, SecurityTests.class})
    public void validarAlteracaoDeUmaReservaUtilizandoTokenInvalido(){
        String firstname = "João";
        String lastname = "Souza";
        String checkin = "2022-05-31";
        String checkout = "2022-06-28";
        String token = "#$1=1968!Sass";

        int id = postBookingRequest.createBooking(BookingPayloads.openFieldsValidPayload("", "",
                "", "")).then().extract().path("bookingid");

        putBookingRequest.updateBookingWithToken(bookingPayloads.openFieldsValidPayload(firstname, lastname,
                        checkin, checkout), id, token)
                .then()
                .statusCode(403);
     }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Alterar uma reserva utilizando BasicAuth")
    @Category({AllTests.class, AcceptanceCriticalTests.class})
    public void validarAlteracaoDeUmaReservaUtilizandoBasicAuth(){
        String firstname = "João";
        String lastname = "Souza";
        String checkin = "2022-05-31";
        String checkout = "2022-06-28";

        int id = postBookingRequest.createBooking(BookingPayloads.openFieldsValidPayload("", "",
                "", "")).then().extract().path("bookingid");

        putBookingRequest.updateBookingWithBasicAuth(bookingPayloads.openFieldsValidPayload(firstname, lastname,
                        checkin, checkout), id)
                .then()
                .statusCode(200)
                .body("firstname", equalToIgnoringCase(firstname))
                .body("lastname", equalToIgnoringCase(lastname))
                .body("bookingdates.checkin", equalToIgnoringCase(checkin))
                .body("bookingdates.checkout", equalToIgnoringCase(checkout));
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Alterar uma reserva que não existe")
    @Category({AllTests.class, AcceptanceCriticalTests.class})
    public void validarAlteracaoDeUmaReservaInexistente(){
        String firstname = "João";
        String lastname = "Souza";
        String checkin = "2022-05-31";
        String checkout = "2022-06-28";

        int id = -9;

        Response response = putBookingRequest.updateBookingWithToken(bookingPayloads.openFieldsValidPayload(firstname, lastname,
                checkin, checkout), id, postAuthRequest.getToken());

        assertThat(Arrays.asList(200, 201, 202), not(hasItem(response.getStatusCode())));
    }
}
