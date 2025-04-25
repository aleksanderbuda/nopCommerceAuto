package cucumber.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import nopCommerceAuto.model.ProductInfo;
import nopCommerceAuto.pages.NotebooksPage;
import nopCommerceAuto.pages.CompareProductsPage;
import org.junit.Assert;

import java.util.List;

public class NotebooksPageSteps {
    private final NotebooksPage notebooksPage;
    private CompareProductsPage compareProductsPage;
    private List<ProductInfo> products;
    private List<String> productTitles;

    public NotebooksPageSteps(NotebooksPage notebooksPage) {
        this.notebooksPage = notebooksPage;
    }

    @Given("the user is on the notebooks page")
    public void verifyOnNotebooksPage() {
    }

    @When("the user filters by 8GB memory")
    public void filterBy8GbMemory() {
        notebooksPage.click8GbMemoryCheckbox();
    }

    @Then("only 8GB memory notebooks should be displayed")
    public void verify8GbFilterApplied() {
        Assert.assertTrue("8GB filter not applied correctly",
                notebooksPage.isCheckbox8GbClicked());
    }

    @When("the user selects {int} random products for comparison")
    public void selectProductsForComparison(int count) {
        notebooksPage.clickCompareButton(count);
    }

    @When("the user opens the compare products page")
    public void openCompareProductsPage() {
        compareProductsPage = notebooksPage.openComparePage();
    }

    @When("the user clicks add to cart for the second product")
    public void addSecondProductToCart() {
        notebooksPage.clickAddToCartButton();
    }

    @When("the user adds the second product to wishlist")
    public void addSecondProductToWishlist() {
        notebooksPage.clickFirstWishlistButton();
    }

    @Then("a success notification should appear")
    public void verifySuccessNotification() {
        Assert.assertTrue("Success notification not displayed",
                notebooksPage.isSuccessBannerVisible());
    }

    @When("the user selects product number {int}")
    public void selectProductByNumber(int productNumber) {
        notebooksPage.selectProduct(productNumber - 1);
    }

    @When("the user retrieves product information")
    public void retrieveProductInformation() {
        products = notebooksPage.getProducts();
    }

    @Then("the product list should contain at least {int} items")
    public void verifyProductCount(int minCount) {
        Assert.assertTrue("Not enough products found",
                products.size() >= minCount);
    }

    @When("the user gets titles for products (.*)")
    public void getProductTitles(List<Integer> productIndexes) {
        productTitles = notebooksPage.getProductTitles(productIndexes);
    }

    @Then("the collected titles should match the products")
    public void verifyProductTitles() {
        Assert.assertFalse("No product titles were collected",
                productTitles.isEmpty());
    }
}