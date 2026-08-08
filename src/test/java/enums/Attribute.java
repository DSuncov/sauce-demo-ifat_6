package enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Attribute {
    DATA_TEST("data-test"),
    PLACEHOLDER("placeholder"),
    CLASS("class");

    private final String attribute;
}
