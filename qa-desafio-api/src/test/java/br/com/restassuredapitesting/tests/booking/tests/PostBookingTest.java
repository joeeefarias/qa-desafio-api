package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.base.BaseTest;
import br.com.restassuredapitesting.suites.AcceptanceCriticalTests;
import br.com.restassuredapitesting.suites.AcceptanceExceptionTests;
import br.com.restassuredapitesting.suites.AllTests;
import br.com.restassuredapitesting.suites.SchemaTests;
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

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

@Feature("Feature - Criação de Reservas")
public class PostBookingTest extends BaseTest {

    PostBookingRequest postBookingRequest = new PostBookingRequest();

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Cria uma reserva")
    @Category({AcceptanceCriticalTests.class,AllTests.class})
    public void validaCriacaoDeUmaReserva(){

        postBookingRequest.createBooking(BookingPayloads.openFieldsValidPayload("", "",
                        "", ""))
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Cria uma reserva com um payload inválido")
    @Category({AcceptanceExceptionTests.class,AllTests.class})
    public void validaCriacaoDeUmaReservaComPayloadInvalido(){

        Response response = postBookingRequest.createBooking(BookingPayloads.
                invalidPayload("Jun"));

        assertThat(Arrays.asList(200, 201, 202), not(hasItem(response.getStatusCode())));
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Cria mais de uma reserva em sequencia")
    @Category({AcceptanceExceptionTests.class,AllTests.class})
    public void validaCriacaoDeMaisDeUmaReservaSequencial(){

        postBookingRequest.createBooking(BookingPayloads.openFieldsValidPayload("", "",
                        "", ""))
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));

        postBookingRequest.createBooking(BookingPayloads.openFieldsValidPayload("", "",
                        "", ""))
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));

        postBookingRequest.createBooking(BookingPayloads.openFieldsValidPayload("", "",
                        "", ""))
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Cria uma reserva com campos adicionais no payload")
    @Category({AcceptanceExceptionTests.class,AllTests.class})
    public void validaCriacaoDeUmaReservaComCamposAdicionais(){

        postBookingRequest.createBooking(BookingPayloads.extraFieldsValidPayload())
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Garantir o Schema de retorno da criação de reservas")
    @Category({AllTests.class, SchemaTests.class})
    public void validaSchemaCriacaoDeReserva(){
        postBookingRequest.createBooking(BookingPayloads.openFieldsValidPayload("", "",
                        "", ""))
                .then()
                .statusCode(200)
                .body(matchesJsonSchema(new File(Utils.getSchemBasePath("booking",
                        "create"))));
    }

}
