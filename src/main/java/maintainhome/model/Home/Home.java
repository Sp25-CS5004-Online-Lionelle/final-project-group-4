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

    public String getHomeId() {
        return homeId;
    }
    
    public String getHomeName() {
        return homeName;
    }

    public int getHomeNum() {
        return num;
    }

    public String getAddress() {
        return address;
    }

    public String getZip() {
        return zip;
    }

    public List<IUnit> getUnitItems() {
        return unitItems;
    }

    
    public String[] getUnitJList() {
        String[] unitList = new String[getUnitItems().size()];
        for (int i = 0; i < getUnitItems().size(); i++) {
            unitList[i] = getUnitItems().get(i).getUnitId();
        } 
        return unitList;
    }

    public List<String[]> getUnitRows() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
        List<String[]> rows = new ArrayList<>();
        for (IUnit unit:getUnitItems()) {
            rows.add(new String[] {
                unit.getUnitId(), unit.getItemName(), unit.getUnitType().getUnitType(),
                unit.getRoomName(), unit.getInstallDate().format(formatter), unit.getMaintainedDate().format(formatter),
                Integer.toString(unit.getMaintenanceFrequency()) + " " + unit.getFrequencyMeasure(),
                //unit.getIssue(),
                unit.getPriority().toString()
            });
        }
        return rows;
    }

    public void setUnitItem(IUnit unitItem) {
        unitItems.add(unitItem);
    }

    /**
     * Adds a user home's unit item to the unit items list.
     */
    public void setUnitItems(List<IUnit> unitItems) {
        for (IUnit unitItem:unitItems) {
            setUnitItem(unitItem);
        }
        // by default add kitchen, dining room, living room, bedroom, bathroom, 
    }


}
