package br.com.restassuredapitesting.runners;

import br.com.restassuredapitesting.tests.booking.tests.DeletBookingTest;
import br.com.restassuredapitesting.tests.booking.tests.GetBookingTest;
import br.com.restassuredapitesting.tests.booking.tests.PostBookingTest;
import br.com.restassuredapitesting.tests.booking.tests.PutbookingTest;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory(br.com.restassuredapitesting.suites.AcceptanceCriticalTests.class)
@Suite.SuiteClasses({
        DeletBookingTest.class,
        GetBookingTest.class,
        PostBookingTest.class,
        PutbookingTest.class

})

public class AcceptanceCriticalTests {
}
