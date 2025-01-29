package nopCommerceAuto.constants;

import nopCommerceAuto.utils.PropertiesReader;

public interface Constants {

    interface Urls {

        String URL = PropertiesReader.getProperty("url");

        String BASE_URL = "https://demo.nopcommerce.com/";

        String LANDING_PAGE_URL = URL;

    }

    interface PageTitles {

        String HOME_PAGE_TITLE = "";


    }
}
