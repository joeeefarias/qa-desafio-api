package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.base.BaseTest;
import br.com.restassuredapitesting.suites.AcceptanceTests;
import br.com.restassuredapitesting.suites.AllTests;
import br.com.restassuredapitesting.suites.ContractTests;
import br.com.restassuredapitesting.tests.booking.requests.PostBookingRequest;
import br.com.restassuredapitesting.tests.booking.requests.payloads.BookingPayloads;
import br.com.restassuredapitesting.utils.Utils;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.File;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.Matchers.greaterThan;

@Feature("Feature - Criação de Reservas")
public class PostBookingTest extends BaseTest {

    PostBookingRequest postBookingRequest = new PostBookingRequest();

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Cria uma reserva")
    @Category({AcceptanceTests.class,AllTests.class})
    public void validaCriaçãoDeUmaReserva(){

        postBookingRequest.createBooking(BookingPayloads.validPayloadBooking())
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Garantir o Schema de retorno da criação de reservas")
    @Category({AllTests.class, ContractTests.class})
    public void validaSchemaCriacaoDeReserva(){
        postBookingRequest.createBooking(BookingPayloads.validPayloadBooking())
                .then()
                .statusCode(200)
                .body(matchesJsonSchema(new File(Utils.getSchemBasePath("booking",
                        "create"))));
    }

}
