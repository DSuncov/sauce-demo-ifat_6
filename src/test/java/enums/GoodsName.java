package enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum GoodsName {
    BOLT_T_SHIRT("Sauce Labs Bolt T-Shirt"),
    BACKPACK("Sauce Labs Backpack"),
    FLEECE_JACKET("Sauce Labs Fleece Jacket"),
    BIKE_LIGHT("Sauce Labs Bike Light"),
    ONESIE("Sauce Labs Onesie"),
    T_SHIRT_RED("Test.allTheThings() T-Shirt (Red)");

    private final String name;
}
