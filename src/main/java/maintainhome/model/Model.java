package maintainhome.model;

import java.util.List;
import maintainhome.model.Home.Home;
import maintainhome.model.Home.IUnit;
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
        try {
            this.user = CsvLoader.loadUserFile(user);
        } catch(Exception e) {
            // NullPointerException
            //System.out.println(e);
            System.out.println("No User Found.");
        };

        /*
        try {
            h1 = this.user.getHomes().get(0);
            System.out.println(h1.getAddress());
        } catch(Exception e) {
            // NullPointerException
            System.out.println("User has no homes listed.");
        }

        List<IUnit> units = CsvLoader.loadUnitItemsFile(this.user.getUserId(), h1.getHomeId());
        for (IUnit unit : units) {
            System.out.println(unit.getItemName());
        }
        */
    }

    public User getUser() {
        return user;
    }
}
