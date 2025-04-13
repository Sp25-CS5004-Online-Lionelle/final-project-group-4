package maintainhome.model;

import java.util.List;
import maintainhome.model.Home.Home;
import maintainhome.model.Home.UnitItems.IUnit;
import maintainhome.model.User.User;
import maintainhome.model.Utilities.CsvLoader;

/**
 * The model that processes the commands from the user to act on home maintenance items.
 */
public class Model {
    private User user;
    /**
     * Default DomainName Constructor.
     * @param str .
     * @throws .
     */
    public Model() {
        
    }

    public void setUser(String user) {
        // set user
        this.user = CsvLoader.loadUserFile(user);
    }

    public User getUser() {
        return user;
    }
}
