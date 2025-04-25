package cucumber.hooks;

import cucumber.context.TestContext;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;

public class TestHooks {
    private final TestContext testContext;

    public TestHooks(TestContext testContext) {
        this.testContext = testContext;
    }

    @After(order = 0)
    public void afterScenario(Scenario scenario) {
        scenario.isFailed();
        testContext.tearDown();
    }
}
