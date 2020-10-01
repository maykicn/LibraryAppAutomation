package com.cybertekschool.library.step_definitions.ENDtoEND;

import com.cybertekschool.library.pages.DashboardPage;
import static com.cybertekschool.library.utils.api.Endpoints.*;

import com.cybertekschool.library.utils.api.Endpoints;
import com.cybertekschool.library.utils.api.LibrarianAuthenticationUtility;
import com.cybertekschool.library.utils.ui.Driver;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;

public class DashboardStepDef {




    @When("the user is in dashboard page")
    public void the_user_is_in_dashboard_page() {
        String currentUrl = Driver.getDriver().getCurrentUrl();
        Assert.assertEquals(currentUrl, "http://library2.cybertekschool.com/#dashboard");

    }


    @Then("the informations getting from API and UI should be matched")
    public void the_informations_getting_from_api_and_ui_should_be_matched() {

        DashboardPage dashboardPage = new DashboardPage();
        String bookCountUI = dashboardPage.bookCount.getText();
        String userCountUI = dashboardPage.userCount.getText();
        String barrowedBooksCountUI = dashboardPage.borrowedBooks.getText();

        String token = new LibrarianAuthenticationUtility().getToken();

        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().header("x-library-token", token)
                .when().get(Endpoints.DASHBOARD_STATS);

        response.prettyPrint();


    }

}
