package maintainhome.model.User;

import java.util.ArrayList;
import java.util.List;
import maintainhome.model.Home.Home;
import maintainhome.model.Home.Types.UnitType;

/**
 * The User class to store the user object that will maintain home(s).
 */
public class User {
    /** the user ID */
    private String userId;
    /** the name of the user */
    private String name;
    /** the email of the user */
    private String email;
    /** the homes of the user */
    private List<Home> homes = new ArrayList<>();

    /**
     * Constructs a User with core unit and plumbing-specific data.
     *
     * @param userId              the user ID
     * @param name                the name of the user
     * @param email               the email of the user
     */
    public User(String userId, String name, String email) {
        this.userId = userId;
        this.name = name;
        this.email = email;
    }

    /**
     * Gets the user ID.
     *
     * @return the user ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Gets the user's name.
     *
     * @return the user's name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the user's email.
     *
     * @return the user's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the user's name.
     *
     * @param name the user's name
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Sets the user's email.
     *
     * @param name the user's email
     */
    public void setEmail(String email) {
        this.email = email;
    }    

    /**
     * Gets the user's homes.
     *
     * @return the user's homes
     */
    public List<Home> getHomes() {
        return homes;
    }

    /**
     * Sets the user's home.
     *
     * @param home the user's home
     */
    public void setHome(Home home) {
        homes.add(home);
    }

    /**
     * Sets the user's homes.
     *
     * @param homes the user's homes
     */
    public void setHomes(List<Home> homes) {
        for (Home home:homes) {
            setHome(home);
        }
    }

    /**
     * Gets the next row after the user's last home.
     *
     * @return the next row after the user's last home
     */
    public int getNextHomeNum() {
        int max = 0;
        for (Home home:getHomes()) {
            max = (home.getHomeNum() > max) ? home.getHomeNum() : max;
        }
        return max + 1;
    }

    /**
     * Find and get a user's home by name
     *
     * @return user's home found by name
     */
    public Home findHomeByName(String homeName) {
        for (Home home : getHomes()) {
            if (homeName.equals(home.getHomeName())) {
                return home;
            }
        }
        return null;
    }
}
