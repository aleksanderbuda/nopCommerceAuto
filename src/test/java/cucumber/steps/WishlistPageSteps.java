package cucumber.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import nopCommerceAuto.pages.CartPage;
import nopCommerceAuto.pages.WishlistPage;
import org.junit.Assert;

public class WishlistPageSteps {
    private final WishlistPage wishlistPage;
    private CartPage cartPage;

    public WishlistPageSteps(WishlistPage wishlistPage) {
        this.wishlistPage = wishlistPage;
    }

    @When("the user clicks on the shareable wishlist URL")
    public void clickShareableWishlistUrl() {
        wishlistPage.clickShareableWishlistUrl();
    }

    @Then("the wishlist title should be {string}")
    public void verifyWishlistTitle(String expectedTitle) {
        String actualTitle = wishlistPage.getTableTitle();
        Assert.assertEquals("Incorrect wishlist title", expectedTitle, actualTitle);
    }

    @Then("the shareable wishlist URL should be retrieved")
    public void getShareableWishlistUrl() {
        String url = wishlistPage.getShareableWishlistUrl();
    }

    @When("the user selects the product from wishlist")
    public void selectProductFromWishlist() {
        wishlistPage.clickAddToCartCheckbox();
    }

    @When("the user clicks the Add to Cart button in wishlist")
    public void clickAddToCartButtonInWishlist() {
        cartPage = wishlistPage.clickAddToCartButton();
    }

    @Then("the user should be redirected to the Cart page")
    public void verifyRedirectedToCartPage() {
        Assert.assertNotNull("Cart page was not opened", cartPage);
    }
}
