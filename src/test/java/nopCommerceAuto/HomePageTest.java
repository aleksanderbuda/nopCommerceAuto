package nopCommerceAuto;

import org.testng.annotations.Test;

public class HomePageTest extends BasePageTest {

    @Test
    public void testExample() {
        driver.get("https://demo.nopcommerce.com/");
        System.out.println(driver.getTitle());
        System.out.println(driver.getCurrentUrl());
    }
}
