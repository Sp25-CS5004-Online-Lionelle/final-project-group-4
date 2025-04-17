package maintainhome.model.Home;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import maintainhome.model.Home.UnitItems.IUnit;

/**
 * The Home class to store the user's home and relevant information.
 */
public class Home {
    private String homeId;
    private int num;
    private String homeName;
    private String address;
    private String zip;
    private List<IUnit> unitItems = new ArrayList<>(); // change datatype

    /**
     * Default Home Constructor.
     * @param homeId id of home
     * @param address address of home to reference in map (might not be able to use due to discomfort of users providing. Maybe can just use current location or zip code)
     */
    public Home(String homeId, int homeNum, String homeName, String address, String zip) {
        this.homeId = homeId;
        this.num = homeNum;
        this.homeName = homeName;
        this.address = address;
        this.zip = zip;
    }

    /**
     * Gets the home ID.
     * 
     * @return the home ID string
     */
    public String getHomeId() {
        return homeId;
    }
    
    /**
     * Gets the home name.
     * 
     * @return the home name string
     */
    public String getHomeName() {
        return homeName;
    }

    /**
     * Gets the home row number.
     * 
     * @return the home row as int
     */
    public int getHomeNum() {
        return num;
    }

    /**
     * Gets the home address.
     * 
     * @return the home address as string
     */
    public String getAddress() {
        return address;
    }

    /**
     * Gets the home zip.
     * 
     * @return the home zip as string
     */
    public String getZip() {
        return zip;
    }

    /**
     * Gets the home's unit items.
     * 
     * @return the home's unit items as a list
     */
    public List<IUnit> getUnitItems() {
        return unitItems;
    }

    /**
     * Gets the home's unit items.
     * 
     * @return the home's unit items as a list
     */
    public String[] getUnitJList() {
        String[] unitList = new String[getUnitItems().size()];
        for (int i = 0; i < getUnitItems().size(); i++) {
            unitList[i] = getUnitItems().get(i).getUnitId();
        } 
        return unitList;
    }

    /**
     * Sets a unit items to the user's home.
     */
    public void setUnitItem(IUnit unitItem) {
        unitItems.add(unitItem);
    }

    /**
     * Sets a user home's unit item.
     */
    public void setUnitItems(List<IUnit> unitItems) {
        for (IUnit unitItem:unitItems) {
            setUnitItem(unitItem);
        }
        // by default add kitchen, dining room, living room, bedroom, bathroom, 
    }

    /**
     * Gets the home's attributes as a row.
     * 
     * @return the home's attributes as a row
     */
    public String[] getHomeRow() {
        return new String[] {
            Integer.toString(getHomeNum()), getHomeName(), getAddress(), getZip()
        };
    }

}
