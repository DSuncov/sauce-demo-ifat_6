package enums;

public enum Attribute {
    DATA_TEST("data-test"),
    PLACEHOLDER("placeholder"),
    CLASS("class");

    private final String value;

    Attribute(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
