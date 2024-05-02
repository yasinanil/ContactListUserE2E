package stepdefinitions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import pojos.ContactPojo;

import static base_urls.ContactListBaseUrl.spec;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class ContactListContactStepDefinitions {
    ContactPojo expectedData;
    Response response;

    @Given("set the url for adding contact")
    public void set_the_url_for_adding_contact() {
        //https://thinking-tester-contact-list.herokuapp.com/contacts
        spec.pathParams("first","contacts");
    }

    @Given("set the expected data for adding contact")
    public void set_the_expected_data_for_adding_contact() throws JsonProcessingException {
        String strJson = """
                {
                    "firstName": "John",
                    "lastName": "Doe",
                    "birthdate": "1970-01-01",
                    "email": "jdoe@fake.com",
                    "phone": "8005555555",
                    "street1": "1 Main St.",
                    "street2": "Apartment A",
                    "city": "Anytown",
                    "stateProvince": "KS",
                    "postalCode": "12345",
                    "country": "USA"
                }""";

        expectedData = new ObjectMapper().readValue(strJson, ContactPojo.class);
        System.out.println("expectedData = " + expectedData);


    }

    @When("send the post request for adding contact")
    public void send_the_post_request_for_adding_contact() {
          response = given(spec).body(expectedData).post("{first}");
          response.prettyPrint();
    }

    @Then("do assertion for adding contact")
    public void do_assertion_for_adding_contact() {
        ContactPojo actualData = response.as(ContactPojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(201, response.statusCode());
        assertEquals(expectedData.getFirstName(), actualData.getFirstName());
        //Rest is homework!!


    }


}
