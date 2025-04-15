package maintainhome.model.Utilities;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import maintainhome.model.Home.Home;
import maintainhome.model.Home.Types.PriorityType;
import maintainhome.model.Home.Types.RoomType;
import maintainhome.model.Home.Types.UnitType;
import maintainhome.model.Home.UnitItems.ApplianceUnit;
import maintainhome.model.Home.UnitItems.ElectricUnit;
import maintainhome.model.Home.UnitItems.IUnit;
import maintainhome.model.Home.UnitItems.PlumbingUnit;
import maintainhome.model.User.User;
import maintainhome.model.Utilities.Types.ColumnData;
import maintainhome.model.Utilities.Types.FileType;
import maintainhome.model.Utilities.Types.IColumnEnum;

/**
 * Loads this apps objects from a CSV file.
 * Supports unit types: ElectricUnit, PlumbingUnit, ApplianceUnit.
 */
public class CsvLoader implements ICsvSource {

    /** Standard csv delim. */
    private static final String DELIMITER = ",";
    
    private static String[] trimValues(String[] values) {
        String[] result = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            result[i] = values[i].trim();
        }
        return result;
    }

    /**
     * Converts a line from the csv file into a User object.
     * 
     * @param line      the line to convert
     * @param columnMap the map of columns to index
     * @return a User object
     */
    private static Object toObject(String line, Map<IColumnEnum, Integer> columnMap, FileType filetype) {
        String[] columns = trimValues(line.split(DELIMITER));
        if (columns.length < columnMap.values().stream().max(Integer::compareTo).get()) {
            return null;
        }
        
        switch(filetype) {
            case USER:
                User user = new User(
                    columns[columnMap.get(ColumnData.UserData.user_id)]
                    , columns[columnMap.get(ColumnData.UserData.name)]
                    , columns[columnMap.get(ColumnData.UserData.email)]
                    );
                return user;
            case HOMES:
                Home home = new Home(
                    columns[columnMap.get(ColumnData.HomeData.home_id)],
                    Integer.parseInt(columns[columnMap.get(ColumnData.HomeData.home_num)]),
                    columns[columnMap.get(ColumnData.HomeData.home_name)],
                    columns[columnMap.get(ColumnData.HomeData.address)],
                    columns[columnMap.get(ColumnData.HomeData.zip)]);
                return home;
            case UNIT_ITEMS:
                return toUnitItems(columns, columnMap);
            default:
                return null;
        }
    }

    
    /**
     * Converts a line from the csv file into a unit item object.
     * 
     * @param line      the line to convert
     * @param columnMap the map of columns to index
     * @return a User object
     */
    private static IUnit toUnitItems(String[] columns, Map<IColumnEnum, Integer> columnMap) {
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
        // mapping for this file has both UserData and HomeData
        String userId = columns[columnMap.get(ColumnData.UnitItemData.user_id)].trim();
        String homeId = columns[columnMap.get(ColumnData.UnitItemData.home_id)].trim();
        String itemId = columns[columnMap.get(ColumnData.UnitItemData.unit_id)].trim();
        String itemName = columns[columnMap.get(ColumnData.UnitItemData.item_name)].trim();
        UnitType type = UnitType.toUnitType(columns[columnMap
            .get(ColumnData.UnitItemData.unit_type)].trim());
        RoomType roomType = RoomType.toRoomType(columns[columnMap.get(ColumnData.UnitItemData.room_type)].trim());
        String roomName = columns[columnMap.get(ColumnData.UnitItemData.room_name)].trim();
        LocalDate installDate = LocalDate.parse(columns[columnMap.get(ColumnData.UnitItemData.install_date)].trim(), formatter);
        LocalDate maintainedDate = LocalDate.parse(columns[columnMap.get(ColumnData.UnitItemData.maintained_date)].trim(), formatter);
        int maintainFreq =  Integer.parseInt(columns[columnMap.get(ColumnData.UnitItemData.maintenance_freq)].trim());
        String freqMeas = columns[columnMap.get(ColumnData.UnitItemData.frequency_meas)].trim();
        String issue = columns[columnMap.get(ColumnData.UnitItemData.issue)].trim();
        PriorityType priority = PriorityType.containsValues(columns[columnMap.get(ColumnData.UnitItemData.priority)].trim());
        
        int electricWatt = Integer.parseInt("0");
        int plumbingGallon = Integer.parseInt("0");
        int height = Integer.parseInt("0");
        int width = Integer.parseInt("0");
        int depth = Integer.parseInt("0");
        
        IUnit unit = null;
        
        try {
            switch(type) {
                case ELECTRIC_UNIT:
                    unit = new ElectricUnit(
                    userId
                    , homeId
                    , itemId
                    , itemName
                    , type
                    , roomType
                    , roomName
                    , installDate
                    , maintainedDate
                    , maintainFreq
                    , freqMeas
                    , issue
                    , priority
                    , electricWatt
                    );
                    break;
                case PLUMBING_UNIT:
                    unit = new PlumbingUnit(
                    userId
                    , homeId
                    , itemId
                    , itemName
                    , type
                    , roomType
                    , roomName
                    , installDate
                    , maintainedDate
                    , maintainFreq
                    , freqMeas
                    , issue
                    , priority
                    , plumbingGallon
                    );
                    break;
                case APPLIANCE:
                    unit = new ApplianceUnit(
                    userId
                    , homeId
                    , itemId
                    , itemName
                    , type
                    , roomType
                    , roomName
                    , installDate
                    , maintainedDate
                    , maintainFreq
                    , freqMeas
                    , issue
                    , priority
                    , electricWatt
                    , height
                    , width
                    , depth
                    );
                    break;
            }
            return unit;
        } catch (NumberFormatException e) {
            // skip if there is an issue
            return null;
        }
        
    }

    
    /**
     * Processes the header line to determine the column mapping.
     * 
     * It is common to do this for csv files as the columns can be in any order.
     * This makes it order independent by taking a moment to link the columns
     * with their actual index in the file.
     * 
     * @param header the header line
     * @return a map of column to index
     */
    private static Map<IColumnEnum, Integer> processHeader(String header, FileType filetype) {
        Map<IColumnEnum, Integer> columnMap = new HashMap<>();
        // 
        String[] columns = trimValues(header.split(DELIMITER));
        for (int i = 0; i < columns.length; i++) {
            try {
                IColumnEnum col = ColumnData.fromString(columns[i], filetype);
                columnMap.put(col, i);
            } catch (IllegalArgumentException e) {
                // System.out.println("Ignoring column: " + columns[i]);
            }
        }
        return columnMap;
    }

    /**
     * Gets the file path based on file type
     * @return the filepath to the selected file
     */
    private static String getFilePath(FileType fileType) {
        return filePath + fileType.getFileName(); // Update this path
    }

    private static List<String> processLines(FileType filetype) {
        String path = getFilePath(filetype);
        List<String> lines;
        try {
            // this is so we can store the files in the resources folder
            
            InputStream is = new FileInputStream(path);
            InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(isr);
            lines = reader.lines().collect(Collectors.toList());
            reader.close();
            return lines;
        } catch (Exception e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return null;
    }

    /**
     * Loads the users from the csv file into a set of User objects.
     * 
     * @param filename the name of the file to load
     * @return a set of User objects
     */
    public static User loadUserFile(String userId) {
        FileType fType = FileType.USER;
        User user = null;
        
        List<String> lines = processLines(fType);
        
        if (lines == null || lines.isEmpty()) {
            return user;
        }
        
        Map<IColumnEnum, Integer> columnMap = processHeader(lines.remove(0), fType);
        
        for (String line:lines) {
            User checkuser = (User) toObject(line, columnMap, fType);
            if (checkuser.getUserId().equals(userId)) {
                user = checkuser;
                return user;
            }
        }

        return user;
    }
    
    /**
     * Loads the homes from the csv file into a list of Home objects.
     * 
     * @param filename the name of the file to load
     * @return a set of User objects
     */
    public static List<Home> loadHomesFile(String userId) {
        FileType fType = FileType.HOMES;

        List<Home> homes = new ArrayList<>();
        // set the list of homes for the user
        Set<String> homeIds = loadUserHomesFile(userId);

        List<String> lines = processLines(fType);
        
        if (lines == null || lines.isEmpty()) {
            return homes;
        }
        
        Map<IColumnEnum, Integer> columnMap = processHeader(lines.remove(0), fType);

        homes = lines.stream().map(line -> (Home)toObject(line, columnMap, fType))
            .filter(home -> homeIds.contains(home.getHomeId()) && home != null).collect(Collectors.toList());
        
        return homes;

    }
    
    /**
     * Loads the user's homes from the csv file to set in User object.
     * 
     * @param filename the name of the file to load
     * @return a set of User objects
     */
    
    public static Set<String> loadUserHomesFile(String userId) {
        FileType fType = FileType.USER_HOMES;
        
        Set<String> userHomes = new HashSet<>();

        List<String> lines = processLines(fType);

        if (lines == null || lines.isEmpty()) {
            return userHomes;
        }
        
        Map<IColumnEnum, Integer> columnMap = processHeader(lines.remove(0), fType);
        for (String line:lines) {
            
            String[] columns = trimValues(line.split(DELIMITER));
            if (columns.length < columnMap.values().stream().max(Integer::compareTo).get()) {
                continue;
            }
            String id = columns[columnMap.get(ColumnData.UserHomeData.user_id)];
            if (id.equalsIgnoreCase(userId)) {
                userHomes.add(columns[columnMap.get(ColumnData.UserHomeData.home_id)]);
            }
        }

        return userHomes;

    }

    /**
      * Loads IUnit objects from a CSV file.
        * Supports unit types: ElectricUnit, PlumbingUnit, ApplianceUnit.
     * 
     * @param filename the name of the file to load
     * @return a set of User objects
     */
    public static List<IUnit> loadUnitItemsFile(String userId, String homeId) {
        FileType fType = FileType.UNIT_ITEMS;
        
        List<IUnit> units = new ArrayList<>();

        List<String> lines = processLines(fType);
        Map<IColumnEnum, Integer> columnMap = processHeader(lines.remove(0), fType);
        
        units = lines.stream().map(line -> (IUnit)toObject(line, columnMap, fType))
                .filter(
                    unit ->
                    //unit.getUserId().equals(userId) && unit.getHomeId().equals(homeId) &&
                    unit != null).collect(Collectors.toList());

        return units;
    }


}
