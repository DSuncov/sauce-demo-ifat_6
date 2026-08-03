package enums;

public enum PageTitle {
    PRODUCTS("Products"),
    YOUR_CART("Your Cart");

    private final String value;

    PageTitle(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
