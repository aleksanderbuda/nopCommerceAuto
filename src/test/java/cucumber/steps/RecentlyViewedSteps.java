package cucumber.steps;

import io.cucumber.java.en.Then;
import nopCommerceAuto.pages.RecentlyViewedPage;
import org.junit.Assert;

public class RecentlyViewedSteps {

    private final RecentlyViewedPage recentlyViewedPage;

    public RecentlyViewedSteps(RecentlyViewedPage recentlyViewedPage) {
        this.recentlyViewedPage = recentlyViewedPage;
    }

    @Then("the recently viewed product at index {int} should have name {string}")
    public void verifyRecentlyViewedProductName(int index, String expectedName) {
        String actualName = recentlyViewedPage.getRecName(index);
        Assert.assertEquals("Incorrect product name at index " + index, expectedName, actualName);
    }

    @Then("the recently viewed product at index {int} should have price {string}")
    public void verifyRecentlyViewedProductPrice(int index, String expectedPrice) {
        String actualPrice = recentlyViewedPage.getRecPrice(index);
        Assert.assertEquals("Incorrect product price at index " + index, expectedPrice, actualPrice);
    }
}
