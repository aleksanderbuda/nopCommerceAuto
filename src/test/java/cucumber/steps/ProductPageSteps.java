package cucumber.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import nopCommerceAuto.pages.ProductPage;
import org.junit.Assert;

public class ProductPageSteps {
    private final ProductPage productPage;

    public ProductPageSteps(ProductPage productPage) {
        this.productPage = productPage;
    }

    @When("the user views the product details")
    public void verifyOnProductPage() {
    }

    @Then("the product name should be displayed")
    public void verifyProductNameDisplayed() {
        String productName = productPage.getName();
        Assert.assertNotNull("Product name is not displayed", productName);
        Assert.assertFalse("Product name is empty", productName.isEmpty());
    }

    @Then("the product price should be displayed")
    public void verifyProductPriceDisplayed() {
        String productPrice = productPage.getPrice();
        Assert.assertNotNull("Product price is not displayed", productPrice);
        Assert.assertFalse("Product price is empty", productPrice.isEmpty());
    }

    @Then("the product added to comparison notification should appear")
    public void verifyProductComparisonNotification() {
        Assert.assertTrue("Product comparison notification not visible",
                productPage.getGreenBarProductAddedToCompareText().isDisplayed());
    }

    @When("the user clicks the product comparison link in the notification")
    public void clickProductComparisonLink() {
        productPage.getGreenBarProductComparisonLink().click();
    }

    @Then("the product name should match {string}")
    public void verifyProductName(String expectedName) {
        String actualName = productPage.getName().trim();
        Assert.assertEquals("Product name doesn't match", expectedName, actualName);
    }

    @Then("the product price should match {string}")
    public void verifyProductPrice(String expectedPrice) {
        String actualPrice = productPage.getPrice().trim();
        Assert.assertEquals("Product price doesn't match", expectedPrice, actualPrice);
    }
}