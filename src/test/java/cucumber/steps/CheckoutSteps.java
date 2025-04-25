package cucumber.steps;

import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import nopCommerceAuto.pages.CheckoutPage;

public class CheckoutSteps {
    private final CheckoutPage checkoutPage;

    public CheckoutSteps(CheckoutPage checkoutPage) {
        this.checkoutPage = checkoutPage;
    }

    @When("user picks random country")
    public String userSelectsRandomCountry() {
        return checkoutPage.selectRandomCountry();
    }

    @When("user enters city {string}")
    public void userEntersCity(String city) {
        checkoutPage.fillCity(city);
    }

    @When("user enter address {string}")
    public void userEntersAddress(String address) {
        checkoutPage.fillAddress(address);
    }

    @When("user enter zip code {string}")
    public void userEntersZipCode(String zipCode) {
        checkoutPage.fillZipCode(zipCode);
    }

    @When("user enter phone number{string}")
    public void userEntersPhoneNumber(String phoneNumber) {
        checkoutPage.fillPhoneNumber(phoneNumber);
    }

    @When("user clicks continue button")
    public void userClicksContinueButton() {
        checkoutPage.clickContinueButton();
    }

    @Then("user checks saved billing address")
    public String verifySavedBillingAddress() {
        return checkoutPage.getSavedBillingAddressInfo();
    }
}