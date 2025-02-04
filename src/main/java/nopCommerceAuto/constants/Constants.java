package nopCommerceAuto.constants;

import nopCommerceAuto.utils.PropertiesReader;

public interface Constants {

    interface Urls {

        String URL = PropertiesReader.getProperty("url");

        String BASE_URL = "https://demo.nopcommerce.com";

        String HOME_PAGE_URL = URL;

        String REGISTER_PAGE_URL = URL + "/register?returnUrl=%2F";

        String COMPARE_PRODUCT_PAGE_URL = URL + "/compareproducts";

        String PRODUCT_PAGE_URL = URL + "example";



        String CAROUSEL_GALAXY_IMAGE_URL = "https://demo.nopcommerce.com/images/thumbs/0000093_Slider Galaxy.png";

        String CAROUSEL_IPHONE_IMAGE_URL = "https://demo.nopcommerce.com/images/thumbs/0000094_Slider iphone.png";

    }

    interface PageTitles {

        String HOME_PAGE_TITLE = "nopCommerce demo store";

        String REGISTER_PAGE_TITLE = "";

        String COMPARE_PRODUCTS_PAGE_TITLE = "Compare products";

        String PRODUCT_PAGE_TITLE = "";



    }
}
