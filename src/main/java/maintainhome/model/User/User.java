package maintainhome.model.User;

import maintainhome.model.Home.Home;

import java.util.List;

/**
 * The User class to store the user object that will maintain home(s).
 */
public class User {
    private int userId;
    private String email;
    private List<Home> homes;

    public User(int userId, String email, List<Home> homes) {

    };

    public List<Home> getHomes() {
        return homes;
    }
}
