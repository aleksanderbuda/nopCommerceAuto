package cucumber.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import nopCommerceAuto.model.ProductInfo;
import nopCommerceAuto.pages.CompareProductsPage;

import java.util.List;

public class CompareProductsSteps {
    private final CompareProductsPage compareProductsPage;

    public CompareProductsSteps(CompareProductsPage compareProductsPage) {
        this.compareProductsPage = compareProductsPage;
    }

    @When("user closes green notification bar")
    public void userClosesGreenNotificationBar() {
        compareProductsPage.getGreenBarCloseButton().click();
    }

    @When("user removes single product")
    public void userClicksRemoveProductButton() {
        compareProductsPage.getSingleRemoveButton().click();
    }

    @Then("user checks if the comaprison list is empty")
    public void verifyCompareListIsEmpty() {
        compareProductsPage.getComparePageEmptyListText().isDisplayed();
    }

    @Then("user checks that the compared products are correct")
    public void verifyComparedProductsData() {
        List<ProductInfo> products = compareProductsPage.getProductInfoFromTable();
    }

    @Then("user sees that single product is displayed")
    public void verifySingleProductDisplayed() {
        compareProductsPage.getSinglePicture().isDisplayed();
    }
}