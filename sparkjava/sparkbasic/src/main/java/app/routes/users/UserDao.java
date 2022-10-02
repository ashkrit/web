package app.routes.users;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserDao {

    List<User> users = Arrays.asList(new User("admin", "password"), new User("root", "password"));

    public List<String> userNames() {
        return users.stream().map(u -> u.userName).collect(Collectors.toList());
    }

    public Optional<User> userByName(String user) {
        return users.stream().filter(r -> r.userName.equalsIgnoreCase(user)).findFirst();
    }


    public static UserDao create() {
        return new UserDao();
    }
}
