package enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PageTitle {
    PRODUCTS("Products"),
    CART("Your Cart");

    private final String title;
}
