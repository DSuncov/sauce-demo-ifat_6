package enums;

public enum ErrorMessage {
    PASSWORD_REQUIRED("Epic sadface: Password is required"),
    USERNAME_REQUIRED("Epic sadface: Username is required"),
    LOCKED_USER("Epic sadface: Sorry, this user has been locked out."),
    INVALID_LOGIN_OR_PASSWORD("Epic sadface: Username and password do not match any user in this service");

    private final String value;

    ErrorMessage(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
