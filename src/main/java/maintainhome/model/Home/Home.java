package maintainhome.model.Home;

import maintainhome.model.Home.IUnit;

import java.util.List;

/**
 * The Home class to store the user's home and relevant information.
 */
public class Home {
    private int homeId;
    private String address;
    private List<Room> rooms; // change datatype

    /**
     * Default Home Constructor.
     * @param homeId id of home
     * @param address address of home to reference in map (might not be able to use due to discomfort of users providing. Maybe can just use current location or zip code)
     */
    public Home(int homeId, String address) {
        this.homeId = homeId;
        this.address = address;
    }

    /**
     * Adds a room to the home.
     */
    public void addRooms(Room room) {
        rooms.add(room);
        // by default add kitchen, dining room, living room, bedroom, bathroom, 
    }

}
