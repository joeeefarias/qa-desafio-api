package br.com.restassuredapitesting.runners;

import br.com.restassuredapitesting.tests.booking.tests.DeletBookingTest;
import br.com.restassuredapitesting.tests.booking.tests.PutbookingTest;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory(br.com.restassuredapitesting.suites.SecurityTests.class)
@Suite.SuiteClasses({
        DeletBookingTest.class,
        PutbookingTest.class
})

public class SecurityTests {
}
