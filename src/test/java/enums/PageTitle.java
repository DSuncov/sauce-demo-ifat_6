package enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PageTitle {
    PRODUCTS("Products"),
    CART("Your Cart"),
    CHECKOUT_INFORMATION("Checkout: Your Information"),
    CHECKOUT_OVERVIEW("Checkout: Overview"),
    CHECKOUT_COMPLETE("Checkout: Complete!");

    private final String title;
}
