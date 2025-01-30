package nopCommerceAuto.constants;

import nopCommerceAuto.utils.PropertiesReader;

public interface Constants {

    interface Urls {

        String URL = PropertiesReader.getProperty("url");

        String BASE_URL = "https://demo.nopcommerce.com/";

        String HOME_PAGE_URL = URL;

        String REGISTER_PAGE_URL = URL + "/register?returnUrl=%2F";


    }

    interface PageTitles {

        String HOME_PAGE_TITLE = "nopCommerce demo store";

        String REGISTER_PAGE_TITLE = "";



    }
}
