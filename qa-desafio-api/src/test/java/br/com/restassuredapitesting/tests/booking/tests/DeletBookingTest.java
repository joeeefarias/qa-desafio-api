package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.base.BaseTest;
import br.com.restassuredapitesting.suites.AcceptanceCriticalTests;
import br.com.restassuredapitesting.suites.AcceptanceExceptionTests;
import br.com.restassuredapitesting.suites.AllTests;
import br.com.restassuredapitesting.suites.SecurityTests;
import br.com.restassuredapitesting.tests.auth.requests.PostAuthRequest;
import br.com.restassuredapitesting.tests.booking.requests.DeletBookingRequest;
import br.com.restassuredapitesting.tests.booking.requests.PostBookingRequest;
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
import static org.junit.Assert.assertThat;

@Feature("Feature - Deletar reservas")
public class DeletBookingTest extends BaseTest {

    PostAuthRequest postAuthRequest = new PostAuthRequest();
    DeletBookingRequest deletBookingRequest = new DeletBookingRequest();
    PostBookingRequest postBookingRequest = new PostBookingRequest();

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Deleta uma reserva utilizando um token")
    @Category({AllTests.class, AcceptanceCriticalTests.class})
    public void validaDelecaoDeUmaReserva(){

       int id = postBookingRequest.createBooking(BookingPayloads.openFieldsValidPayload("", "",
                "", "")).then().extract().path("bookingid");

        deletBookingRequest.deleteBooking(id, postAuthRequest.getToken())
                .then()
                .statusCode(201);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Deleta uma reserva que não existe")
    @Category({AllTests.class, AcceptanceExceptionTests.class})
    public void validaDelecaoDeUmaReservaInexistente(){

        int id = -3;

        Response response = deletBookingRequest.deleteBooking(id, postAuthRequest.getToken());

        assertThat(Arrays.asList(200, 201, 202), not(hasItem(response.getStatusCode())));
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Deleta uma reserva sem autorização")
    @Category({AllTests.class, SecurityTests.class})
    public void validaDelecaoDeUmaReservaSemAutorizacao(){

        int id = postBookingRequest.createBooking(BookingPayloads.openFieldsValidPayload("", "",
                "", "")).then().extract().path("bookingid");

        deletBookingRequest.unauthorizedDeleteBooking(id)
                .then()
                .statusCode(403);
    }

}


