package nopCommerceAuto.model;

import lombok.Getter;

@Getter
public class ProductInfo {
    @Getter
    private final String title;
    private final String price;

    public ProductInfo(String title, String price) {
        this.title = title;
        this.price = price;
    }

    public String toString() {
        return "ProductInfo {" +
                "title='" + title + '\'' + ", price='" + price + '\'' + '}';
    }


}
