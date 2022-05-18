package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.base.BaseTest;
import br.com.restassuredapitesting.suites.AllTests;
import br.com.restassuredapitesting.suites.ContractTests;
import br.com.restassuredapitesting.tests.booking.requests.GetBookingRequest;
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

@Feature("Feature - Busca retorno de reservas")
public class GetBookingTest extends BaseTest {

    GetBookingRequest getBookingRequest = new GetBookingRequest();

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Listar Ids de reservas")
    @Category({AllTests.class})
    public void validaListagemDeIdDasReservas(){

        getBookingRequest.bookinkReturnIds()
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Garantir o Schema de retorno da listagem das reservas")
    @Category({AllTests.class, ContractTests.class})
    public void validaSchemaListagemDeReserva(){
        getBookingRequest.bookinkReturnIds()
                .then()
                .statusCode(200)
                .body(matchesJsonSchema(new File(Utils.getSchemBasePath("booking",
                        "bookings"))));
    }
}
