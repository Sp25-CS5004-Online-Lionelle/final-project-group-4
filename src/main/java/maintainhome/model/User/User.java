package maintainhome.model.User;

import maintainhome.model.Home.Home;
import java.util.List;

/**
 * The User class to store the user object that will maintain home(s).
 */
public class User {
    private String userId;
    private String name;
    private String email;
    private List<Home> homes;

    public User(String userId, String name, String email, List<Home> homes) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.homes = homes;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<Home> getHomes() {
        return homes;
    }

    public void setHome(Home home) {
        homes.add(home);
    }

}
