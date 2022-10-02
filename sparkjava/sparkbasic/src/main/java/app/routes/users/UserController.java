package app.routes.users;

import java.util.Optional;

public class UserController {
    public static boolean authenticate(String userName, String password) {
        Optional<User> user = new UserDao().userByName(userName);
        return user
                .filter(u -> u.password.equals(password))
                .map($ -> true)
                .orElse(false);

    }
}
