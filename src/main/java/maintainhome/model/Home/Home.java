package maintainhome.model.Home;

import maintainhome.model.Home.IUnit;

import java.util.List;

/**
 * The Home class to store the user's home and relevant information.
 */
public class Home {
    private int homeId;
    private String address;
    private List<IUnit> unitItems; // change datatype

    public Home(int homeId, String address, List<IUnit>unitItems) {
        
    }

}
