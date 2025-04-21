package maintainhome.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import maintainhome.model.Home.Home;
import maintainhome.model.Home.Types.PriorityType;
import maintainhome.model.Home.Types.RoomType;
import maintainhome.model.Home.Types.UnitType;
import maintainhome.model.Home.UnitItems.ApplianceUnit;
import maintainhome.model.Home.UnitItems.ElectricUnit;
import maintainhome.model.Home.UnitItems.IUnit;
import maintainhome.model.User.User;
import maintainhome.model.Utilities.CsvLoader;
import maintainhome.model.Utilities.CsvUpdater;
import maintainhome.model.Utilities.Operations.Filters;
import maintainhome.model.Utilities.Operations.Sorters;
import maintainhome.model.Utilities.Types.ColumnData;
import maintainhome.model.Utilities.Types.FileType;

/**
 * The model that processes the commands from the user to act on home maintenance items.
 */
public class Model {

    
    /** The User who has logged into the application */
    private User user;

    /** New Home that is created from the user's inputs/selection */
    private Home newHome;
    
    /** New Unit that is created form the user's inputs/selection */
    private IUnit newUnit;

    /** User inputs/selections collected from the View. */
    private Map<String, String> data;
    
    /**
     * Filtered units list.
     */
    private List<IUnit> filteredUnits = new ArrayList<>();

    /**
     * Selected home for the unit items.
     */
    private Home selectedHome;

    /**
     * Default Model Constructor.
     */
    public Model() {
        // empty
    }

    /**
     * Sets the filtered units in the Add tab for the Units button.
     * 
     * @param units the String array of values to trim
     */
    public void setFilteredUnits(List<IUnit> units) {
        this.filteredUnits = units;
    }
    
    /**
     * Gets the filtered units in the Add tab for the Units button.
     * 
     * @return the filtered units
     */
    public List<IUnit> getFilteredUnits() {
        return filteredUnits;
    }

    /**
     * Gets the filtered units in the Add tab for the Units button.
     * 
     * @param roomTypeStr the usr selected room type to filter on
     */
    public void filterUnitsByRoomType(String roomTypeStr) {
        RoomType selectedType = RoomType.toRoomType(roomTypeStr);
        List<IUnit> filtered = Filters.filterByRoom(
            getUser().getHomes().get(0).getUnitItems(),
            selectedType.getRoomType()
        );
        setFilteredUnits(filtered);
    }    

    /**
     * Sets the user information from the user file.
     */
    public void setUser(String user) {
        // set user
        this.user = CsvLoader.loadUserFile(user);
    }

    /**
     * Gets the the user.
     * 
     * @return the user object
     */
    public User getUser() {
        return user;
    }
    
    /**
     * Gets the new unit from the app that was input in by the user.
     * 
     * @return the new unit
     */
    public IUnit getNewUnit() {
        return newUnit;
    }

    /**
     * Adds the new unit to the list of unit items of the user selected home.
     */
    public void addUnit() {
        getSelectedHome().setUnitItem(getNewUnit());
    }

    /**
     * Sets the user selected home from the JComboBox.
     */
    private void setSelectedHome(Home home) {
        selectedHome = home;
    }

    /**
     * Gets the user selected home of the JComboBox.
     * 
     * @return the selected home
     */
    private Home getSelectedHome() {
        return selectedHome;
    }

    /**
     * Creates a new unit ID for the new unit that was input by the user through the app.
     * 
     * @param userId the user id that the new unit is associated with
     * @param homeId the home id that the new unit is associated with
     * @return the newly created unit id from the user input/selection
     */
    private String newUnitId(String userId, String homeId) {
        return userId + "h" + homeId + "u" + getSelectedHome().getUnitItems().size() + 1; // placeholder: newHome - need to get user selected home
    }
    
    /**
     * Constructs and sets the new unit by calling the appropraite unit constructor.
     */
    public void setNewUnit() {
        String homeName = data.get(ColumnData.UnitItemData.home_id.toString());
        setSelectedHome(getUser().findHomeByName(homeName));

        String userId = getUser().getUserId();
        String homeId = getSelectedHome().getHomeId();
        String unitId = newUnitId(userId, homeId);
        String unitName = data.get(ColumnData.UnitItemData.item_name.toString());
        UnitType unitType = UnitType.toUnitType(data.get(ColumnData.UnitItemData.unit_type.toString()));
        RoomType roomType = RoomType.toRoomType(data.get(ColumnData.UnitItemData.room_type.toString()));
        String roomName = data.get(ColumnData.UnitItemData.room_name.toString());
        LocalDate installDate = IUnit.parseDate(data.get(ColumnData.UnitItemData.install_date.toString()));
        LocalDate maintainedDate = IUnit.parseDate(data.get(ColumnData.UnitItemData.maintained_date.toString()));
        int maintenanceFrequency = Integer.parseInt(data.get(ColumnData.UnitItemData.maintenance_freq.toString()));
        String frequencyMeas = "YEAR"; // need to expand this to calculate or logically determine this value YEAR or MONTH
        String issue = data.get(ColumnData.UnitItemData.issue.toString());
        PriorityType priority = PriorityType.containsValues(data.get(ColumnData.UnitItemData.priority.toString())); // also would like to auto-determine this

        int electricWatt = Integer.parseInt("0");
        int height = Integer.parseInt("0");
        int width = Integer.parseInt("0");
        int depth = Integer.parseInt("0");
        int plumbingGallon = Integer.parseInt("0");

        switch(unitType) {
            case UnitType.APPLIANCE:
                this.newUnit = new ApplianceUnit(
                    userId, homeId,
                    unitId, unitName, unitType, roomType, roomName
                    , installDate, maintainedDate, maintenanceFrequency, frequencyMeas,
                    issue, priority, electricWatt, height, width, depth);
                break;
            case UnitType.ELECTRIC_UNIT:
                this.newUnit = new ElectricUnit(
                        userId, homeId,
                        unitId, unitName, unitType, roomType, roomName
                        , installDate, maintainedDate, maintenanceFrequency, frequencyMeas,
                        issue, priority, electricWatt);
                break;
            case UnitType.PLUMBING_UNIT:
                this.newUnit = new ElectricUnit(
                    userId, homeId,
                    unitId, unitName, unitType, roomType, roomName
                    , installDate, maintainedDate, maintenanceFrequency, frequencyMeas,
                    issue, priority, plumbingGallon);
                break;            
        }
    }

    /**
     * Saves the unit out to csv file.
     */
    public void saveUnit() {
        data.put( // home_id
            ColumnData.UnitItemData.user_id.toString()
            , getNewUnit().getUserId()); // accounting for header
        data.put( // home_id
            ColumnData.UnitItemData.home_id.toString()
            , getNewUnit().getHomeId());
        data.put( // home_num
            ColumnData.UnitItemData.unit_id.toString()
            , getNewUnit().getUnitId());
        data.put( // home_num
            ColumnData.UnitItemData.unit_id.toString()
            , getNewUnit().getFrequencyMeasure());

        CsvUpdater.writeCsvFile(FileType.UNIT_ITEMS, data);
    }

    /**
     * Sets the new home.
     */
    public void setNewHome() {
        this.newHome = new Home (
            Integer.toString(CsvUpdater.getLastRow(FileType.HOMES))
            , getUser().getNextHomeNum()
            , data.get(ColumnData.HomeData.home_name.toString())
            , data.get(ColumnData.HomeData.address.toString())
            , data.get(ColumnData.HomeData.zip.toString())
        );
    }

    /**
     * Gets the the new home.
     * 
     * @return the new home
     */
    public Home getNewHome() {
        return newHome;
    }

    /**
     * Adds the new home to the user's homes list.
     */
    public void addHome() {
        getUser().setHome(getNewHome());
    }

    /**
     * Sets the user's homes pulled from the csv file.
     */
    public void setUserHomes() {
        List<Home> homes = CsvLoader.loadHomesFile(getUser().getUserId());
        getUser().setHomes(homes);
    }

    /**
     * Sets the data collected form the user to the data Map collection.
     * 
     * @param data the user input/selected data collected from the View
     */
    public void setData(Map<String, String> data) {
        this.data = data;
    }

    /**
     * Sets the user items by pulling from the unit items csv file.
     */
    public void setUserItems() {
        for (Home home:user.getHomes()) {
            List<IUnit> units = CsvLoader.loadUnitItemsFile(user.getUserId(), home.getHomeId());
            home.setUnitItems(units);
            // set in view: Units Panel
            // view.addUnitsList(home.getUnitJList());
        }
    }
    
    /**
     * Gets the list for the Homes JList display.
     * 
     * @return the JList list
     */
    public String[] getHomeJList() {
        String[] homeList = new String[getUser().getHomes().size()];
        for (int i = 0; i < getUser().getHomes().size(); i++) {
            homeList[i] = getUser().getHomes().get(i).getHomeName();
        } 
        return homeList;
    }

    /**
     * Gets the user's homes as rows to display in the JTable.
     * 
     * @return the user's homes as rows
     */
    public List<String[]> getHomeRows() {
        List<String[]> rows = new ArrayList<>();
        getUser().getHomes().sort(Sorters.BY_HOME_NUM);
        for (Home home:getUser().getHomes()) {
            rows.add(home.getHomeRow());
        }
        return rows;
    }

    /**
     * Gets the unit items as rows for the JTable.
     * 
     * @return the unit items as rows for the JTable display
     */
    public List<String[]> getUnitRows() {
        List<String[]> rows = new ArrayList<>();
        for (Home home:getUser().getHomes()) {
            for (String[] row : getUnitRows(home.getUnitItems())) {
                rows.add(row);
            }
        }
        return rows;
    }

    /**
     * Gets the unit items as rows for the JTable.
     * 
     * @param units the user homes' units to display
     * @return the rows of the unit items
     */
    public List<String[]> getUnitRows(List<IUnit> units) {
        List<String[]> rows = new ArrayList<>();
        units.sort(Sorters.BY_MAINTAIN_DATE);
        for (IUnit unit : units) {
            rows.add(unit.getUnitRow());
        }
        return rows;
    }
    
    /**
     * Gets the unit items to display in the JList.
     * 
     * @return unit items JList list to display
     */
    public String[] getUnitsJList() {
        String[] jList;
        
        List<String> values = new ArrayList<>();
        for (RoomType val: RoomType.values()) {
            values.add(val.getRoomType());
        }
        jList = values.toArray(new String[0]);
        Arrays.sort(jList);
        return jList;
    }
    
    /**
     * Saves the home out to csv file.
     */
    public void saveHome() {
        data.put( // home_id
            ColumnData.HomeData.home_id.toString()
            , getNewHome().getHomeId()); // accounting for header
        data.put( // home_num
            ColumnData.HomeData.home_num.toString()
            , Integer.toString(getNewHome().getHomeNum()));

        CsvUpdater.writeCsvFile(FileType.HOMES, data);

        data.put( // home_num
            ColumnData.UserHomeData.user_id.toString()
            , getUser().getUserId());
            
        CsvUpdater.writeCsvFile(FileType.USER_HOMES, data);
    }

}
