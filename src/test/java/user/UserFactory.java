package user;

import utils.PropertyReader;

public class UserFactory {
    public static User createUser(String loginPath, String password) {
        return new User(
                PropertyReader.getProperty(loginPath),
                password
        );
    }

    public static User withEmptyPassword(String loginPath) {
        return new User(
                PropertyReader.getProperty(loginPath),
                ""
        );
    }

    public static User withEmptyLogin(String password) {
        return new User(
                "",
                password
        );
    }

    public static User lockedUser(String password) {
        return new User(
                PropertyReader.getProperty("saucedemo.locked-out-user"),
                password
        );
    }
}
