package cucumber.steps;

import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import nopCommerceAuto.pages.CartPage;
import nopCommerceAuto.pages.CheckoutPage;

public class CartSteps {
    private final CartPage cartPage;
    private CheckoutPage checkoutPage;

    public CartSteps(CartPage cartPage) {
        this.cartPage = cartPage;
    }

    @When("user accepts TOS")
    public void userAcceptsTermsOfService() {
        cartPage.clickTOSCheckbox();
    }

    @When("user proceeds to checkout")
    public void userProceedsToCheckout() {
        checkoutPage = cartPage.openCheckoutPage();
    }
}