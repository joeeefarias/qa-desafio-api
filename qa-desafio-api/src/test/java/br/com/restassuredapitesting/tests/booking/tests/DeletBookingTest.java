package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.base.BaseTest;
import br.com.restassuredapitesting.suites.AllTests;
import br.com.restassuredapitesting.tests.auth.requests.PostAuthRequest;
import br.com.restassuredapitesting.tests.booking.requests.DeletBookingRequest;
import br.com.restassuredapitesting.tests.booking.requests.GetBookingRequest;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Feature("Feature - Deleta reservas")
public class DeletBookingTest extends BaseTest {

    GetBookingRequest getBookingRequest = new GetBookingRequest();
    PostAuthRequest postAuthRequest = new PostAuthRequest();
    DeletBookingRequest deletBookingRequest = new DeletBookingRequest();

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Deleta uma reserva utilizando um token")
    @Category({AllTests.class})
    public void validaDelecaoDeUmaReserva(){
        int id = getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .extract()
                .path("[0].bookingid");

        deletBookingRequest.deleteBooking(id, postAuthRequest.getToken())
                .then()
                .statusCode(201);
    }

}


