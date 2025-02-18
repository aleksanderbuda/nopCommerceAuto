package nopCommerceAuto.constants;

import nopCommerceAuto.utils.PropertiesReader;

public interface Constants {

    interface Urls {

        String URL = PropertiesReader.getProperty("url");

        String BASE_URL = "http://127.0.0.1:5000";

        String HOME_PAGE_URL = URL;

        String NOTEBOOKS_PAGE_URL = URL + "/notebooks";

        String REGISTER_PAGE_URL = URL + "/register?returnUrl=%2F";

        String COMPARE_PRODUCT_PAGE_URL = URL + "/compareproducts";

        String PRODUCT_PAGE_URL = URL + "/example";

        String CHECKOUT_PAGE_URL = URL + "/onepagecheckout#opc-billing";

        String CART_PAGE_URL = URL +"/cart";



        String NOTEBOOKS_8GB_URL = URL + "/notebooks?viewmode=grid&orderby=0&pagesize=6&specs=9";

        String CAROUSEL_GALAXY_IMAGE_URL = "https://demo.nopcommerce.com/images/thumbs/0000093_Slider Galaxy.png";

        String CAROUSEL_IPHONE_IMAGE_URL = "https://demo.nopcommerce.com/images/thumbs/0000094_Slider iphone.png";

    }

    interface PageTitles {

        String HOME_PAGE_TITLE = "Your store. Home page title";

        String REGISTER_PAGE_TITLE = "Your store. Register";

        String NOTEBOOKS_PAGE_TITLE = "Your store. Notebooks";

        String COMPARE_PRODUCTS_PAGE_TITLE = "Your store. Compare Products";

        String PRODUCT_PAGE_TITLE = "";

        String CHECKOUT_PAGE_TITLE = "Your store. Checkout";

        String CART_PAGE_TITLE = "Your store. Shopping Cart";



    }
}
