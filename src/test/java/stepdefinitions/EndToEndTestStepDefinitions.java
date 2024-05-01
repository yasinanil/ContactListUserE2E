package stepdefinitions;

import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

import io.cucumber.java.en.When;

import pages.ContactListAddUserPage;
import pages.ContactListHomePage;

import utilities.Driver;



public class EndToEndTestStepDefinitions {

    ContactListHomePage contactListHomePage;
    ContactListAddUserPage contactListAddUserPage;
    String firstname;
    String lastname;
    public static String email;


    @Given("user goes to {string}")
    public void user_goes_to(String url) {

        Driver.getDriver().get(url);

    }

    @When("user clicks on sign up button")
    public void user_clicks_on_sign_up_button() {

        contactListHomePage = new ContactListHomePage();
        contactListHomePage.signup.click();

    }

    @When("User enters firstname, lastname, email, password")
    public void user_enters_firstname_lastname_email_password() {

        Faker faker = new Faker();
        firstname = faker.name().firstName();
        lastname = faker.name().lastName();
        email = faker.internet().emailAddress();

        contactListAddUserPage = new ContactListAddUserPage();
        contactListAddUserPage.firstName.sendKeys(firstname);
        contactListAddUserPage.lastName.sendKeys(lastname);
        contactListAddUserPage.email.sendKeys(email);
        contactListAddUserPage.password.sendKeys("Password.123");

    }

    @When("user clicks on submit button")
    public void user_clicks_on_submit_button() throws InterruptedException {

        contactListAddUserPage.submit.click();
        Thread.sleep(1000);

    }


    @And("user closes browser")
    public void userClosesBrowser() {

        Driver.closeDriver();

    }
}
