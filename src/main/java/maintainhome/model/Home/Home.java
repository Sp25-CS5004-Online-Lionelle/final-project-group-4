package maintainhome.model.Home;

import java.util.List;
import java.util.ArrayList;

/**
 * The Home class to store the user's home and relevant information.
 */
public class Home {
    private String homeId;
    private String address;
    private String zip;
    private List<IUnit> unitItems = new ArrayList<>(); // change datatype

    /**
     * Default Home Constructor.
     * @param homeId id of home
     * @param address address of home to reference in map (might not be able to use due to discomfort of users providing. Maybe can just use current location or zip code)
     */
    public Home(String homeId, String address, String zip) {
        this.homeId = homeId;
        this.address = address;
        this.zip = zip;
    }

    public String getHomeId() {
        return homeId;
    }

    public String getAddress() {
        return address;
    }

    /**
     * Adds a room to the home.
     */
    public void addUnitItem(IUnit unitItem) {
        unitItems.add(unitItem);
        // by default add kitchen, dining room, living room, bedroom, bathroom, 
    }

}
