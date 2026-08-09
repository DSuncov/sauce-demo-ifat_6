package enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorMessage {
    PASSWORD_REQUIRED("Epic sadface: Password is required"),
    USERNAME_REQUIRED("Epic sadface: Username is required"),
    LOCKED_USER("Epic sadface: Sorry, this user has been locked out."),
    INVALID_LOGIN_OR_PASSWORD("Epic sadface: Username and password do not match any user in this service"),
    FIRST_NAME_REQUIRED("Error: First Name is required"),
    LAST_NAME_REQUIRED("Error: Last Name is required"),
    POSTAL_CODE_REQUIRED("Error: Postal Code is required");

    private final String message;
}
