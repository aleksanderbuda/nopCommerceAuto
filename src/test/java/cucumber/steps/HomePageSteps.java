package cucumber.steps;

import cucumber.context.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import nopCommerceAuto.pages.HomePage;
import nopCommerceAuto.pages.RegisterPage;
import org.junit.Assert;

public class HomePageSteps {
    private final HomePage homePage;
    private RegisterPage registerPage;
    private final TestContext testContext;

    public HomePageSteps(TestContext testContext) {
        this.testContext = testContext;
        this.homePage = new HomePage(testContext.getDriver());
    }

    @Given("the user is on the home page")
    public void userIsOnHomePage() {
        homePage.open();
    }

    @Given("the home page carousel is initialized")
    public void homePageCarouselIsInitialized() {
        homePage.waitForCarouselInitialization();
    }

    @When("the user switches to the next carousel image")
    public void userSwitchesToNextCarouselImage() {
        homePage.switchToNextImage();
    }

    @Then("the carousel should display image with URL {string}")
    public void carouselShouldDisplayImage(String expectedImageUrl) {
        boolean imageDisplayed = homePage.waitForCarouselImage(expectedImageUrl);
        Assert.assertTrue("Carousel did not display expected image: " + expectedImageUrl, imageDisplayed);
    }

    @Then("the active carousel image URL should be captured")
    public void captureActiveCarouselImageUrl() {
        String imageUrl = homePage.getActiveImageUrl();
    }

    @When("the user clicks on the register button")
    public void userClicksRegisterButton() {
        registerPage = homePage.openRegisterPage();
    }

    @Then("the register page should be opened")
    public void verifyRegisterPageOpened() {
    }

    @Then("there should be {int} slider images available")
    public void verifySliderImageCount(int expectedCount) {
        int actualCount = homePage.getSliderImageLinks().size();
        Assert.assertEquals("Incorrect number of slider images", expectedCount, actualCount);
    }
}