package maintainhome.model.User;

import maintainhome.model.Home.Home;
import java.util.List;
import java.util.ArrayList;

/**
 * The User class to store the user object that will maintain home(s).
 */
public class User {
    private String userId;
    private String name;
    private String email;
    private List<Home> homes = new ArrayList<>();

    public User(String userId, String name, String email) {
        this.userId = userId;
        this.name = name;
        this.email = email;
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

    public void setHomes(List<Home> homes) {
        for (Home home:homes) {
            setHome(home);
        }
    }

    public int getNextHomeNum() {
        int max = 0;
        for (Home home:getHomes()) {
            max = (home.getHomeNum() > max) ? home.getHomeNum() : max;
        }
        return max + 1;
    }

}
