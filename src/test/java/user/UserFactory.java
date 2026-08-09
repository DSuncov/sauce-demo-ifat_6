package user;

import utils.PropertyReader;

public class UserFactory {

    public static User standardUser() {
        return new User(
                PropertyReader.getProperty("saucedemo.valid.login[0]"),
                PropertyReader.getProperty("saucedemo.password")
        );
    }

    public static User lockedUser() {
        return new User(
                PropertyReader.getProperty("saucedemo.valid.login[1]"),
                PropertyReader.getProperty("saucedemo.password")
        );
    }

    public static User withInvalidPassword(String loginPath) {
        return new User(
                PropertyReader.getProperty(loginPath),
                PropertyReader.getProperty("saucedemo.invalid.password")
        );
    }
}
