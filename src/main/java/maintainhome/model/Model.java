package maintainhome.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import maintainhome.model.Home.Home;
import maintainhome.model.Home.Types.RoomType;
import maintainhome.model.Home.UnitItems.IUnit;
import maintainhome.model.User.User;
import maintainhome.model.Utilities.CsvLoader;
import maintainhome.model.Utilities.CsvUpdater;
import maintainhome.model.Utilities.Types.ColumnData;
import maintainhome.model.Utilities.Types.FileType;

/**
 * The model that processes the commands from the user to act on home maintenance items.
 */
public class Model {

    private User user;

    private Home newHome;
    
    /** Data of the application. */
    private Map<String, String> data;
    
    /**
     * Filtered units list.
     */
    private List<IUnit> filteredUnits = new ArrayList<>();

    /**
     * Default DomainName Constructor.
     * @param str .
     * @throws .
     */
    public Model() {
        
    }

    public void setFilteredUnits(List<IUnit> units) {
        this.filteredUnits = units;
    }
    
    public List<IUnit> getFilteredUnits() {
        return filteredUnits;
    }

    public void filterUnitsByRoomType(String roomTypeStr) {
        RoomType selectedType = RoomType.toRoomType(roomTypeStr);
        List<IUnit> filtered = Filters.filterByRoom(
            getUser().getHomes().get(0).getUnitItems(),
            selectedType.getRoomType()
        );
        setFilteredUnits(filtered);
    }    

    public void setUser(String user) {
        // set user
        this.user = CsvLoader.loadUserFile(user);
    }

    public User getUser() {
        return user;
    }

    public void addHome(Home home) {
        getUser().setHome(home);
    }

    public void setNewHome() {
        this.newHome = new Home (
            Integer.toString(CsvUpdater.getLastRow(FileType.HOMES))
            , getUser().getNextHomeNum()
            , data.get(ColumnData.HomeData.home_name.toString())
            , data.get(ColumnData.HomeData.address.toString())
            , data.get(ColumnData.HomeData.zip.toString())
        );
    }

    public Home getNewHome() {
        return newHome;
    }

    public void setUserHomes() {
        List<Home> homes = CsvLoader.loadHomesFile(getUser().getUserId());
        getUser().setHomes(homes);
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }


    public void setUserItems() {
        for (Home home:user.getHomes()) {
            List<IUnit> units = CsvLoader.loadUnitItemsFile(user.getUserId(), home.getHomeId());
            home.setUnitItems(units);
            // set in view: Units Panel
            // view.addUnitsList(home.getUnitJList());
        }
    }
    
    public String[] getHomeJList() {
        String[] homeList = new String[getUser().getHomes().size()];
        for (int i = 0; i < getUser().getHomes().size(); i++) {
            homeList[i] = getUser().getHomes().get(i).getHomeName();
        } 
        return homeList;
    }

    public List<String[]> getHomeRows() {
        List<String[]> rows = new ArrayList<>();
        for (Home home:getUser().getHomes()) {
            rows.add(home.getHomeRow());
        }
        return rows;
    }

    public List<String[]> getUnitRows() {
        List<String[]> rows = new ArrayList<>();
        for (Home home:getUser().getHomes()) {
            for (IUnit unit:home.getUnitItems()) {
                rows.add(unit.getUnitRow());
            }   
        }
        return rows;
    }

    public List<String[]> getUnitRows(List<IUnit> units) {
        List<String[]> rows = new ArrayList<>();
        for (IUnit unit : units) {
            rows.add(unit.getUnitRow());
        }
        return rows;
    }
    

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
    
    public void saveHome() {
        data.put( // home_id
            ColumnData.HomeData.home_id.toString()
            , getNewHome().getHomeId()); // accounting for header
        data.put( // home_num
            ColumnData.HomeData.home_num.toString()
            , Integer.toString(getNewHome().getHomeNum()));

        CsvUpdater.updateCsvFile(FileType.HOMES, data);

        data.put( // home_num
            ColumnData.UserHomeData.user_id.toString()
            , getUser().getUserId());
            
        CsvUpdater.updateCsvFile(FileType.USER_HOMES, data);
    }

}
