package maintainhome.model.Utilities;

import maintainhome.model.User.User;
import maintainhome.model.Home.ApplianceUnit;
import maintainhome.model.Home.ElectricUnit;
import maintainhome.model.Home.Home;
import maintainhome.model.Home.IUnit;
import maintainhome.model.Home.PlumbingUnit;
import maintainhome.model.Home.Types.PriorityType;
import maintainhome.model.Home.Types.RoomType;
import maintainhome.model.Home.Types.UnitType;
import maintainhome.model.Utilities.Types.FileType;
import maintainhome.model.Utilities.Types.ColumnData;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Loads IUnit objects from a CSV file.
 * Supports unit types: ElectricUnit, PlumbingUnit, ApplianceUnit.
 */
public class CsvLoader implements ICsvLoader {

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
    private static User toUser(String line, Map<ColumnData, Integer> columnMap) {
        String[] columns = trimValues(line.split(DELIMITER));
        if (columns.length < columnMap.values().stream().max(Integer::compareTo).get()) {
            return null;
        }
        
        User user = new User(
            columns[columnMap.get(ColumnData.USER_ID)]
            , columns[columnMap.get(ColumnData.NAME)]
            , columns[columnMap.get(ColumnData.EMAIL)]
            );
            
        return user;
    }

    
    /**
     * Loads the users from the csv file into a set of User objects.
     * 
     * @param filename the name of the file to load
     * @return a set of User objects
     */
    public static User loadUserFile(String userId) {
        String path = filePath.concat(FileType.USER.getFileName());
        User user = null;
        
        List<String> lines;
        try {
            // this is so we can store the files in the resources folder
            
            // InputStream is = CsvLoader.class.getResourceAsStream(filename);
            InputStream is = new FileInputStream(path);
            InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(isr);
            lines = reader.lines().collect(Collectors.toList());
            reader.close();
        } catch (Exception e) {
            System.err.println("Error reading file: " + e.getMessage());
            return user;
        }
        if (lines == null || lines.isEmpty()) {
            return user;
        }

        Map<ColumnData, Integer> columnMap = processHeader(lines.remove(0));
        for (String line:lines) {
            User checkuser = toUser(line, columnMap);
            if (checkuser.getUserId().equals(userId)) {
                user = checkuser;
                return user;
            }
        }

        return user;
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
    private static Map<ColumnData, Integer> processHeader(String header) {
        Map<ColumnData, Integer> columnMap = new HashMap<>();
        // 
        String[] columns = trimValues(header.split(DELIMITER));
        for (int i = 0; i < columns.length; i++) {
            try {
                ColumnData col = ColumnData.fromColumnName(columns[i]);
                columnMap.put(col, i);
            } catch (IllegalArgumentException e) {
                // System.out.println("Ignoring column: " + columns[i]);
            }
        }
        return columnMap;
    }

    /**
     * Converts a line from the csv file into a Home object.
     * 
     * @param line      the line to convert
     * @param columnMap the map of columns to index
     * @return a Home object
     */
    private static Home toHome(String line, Map<ColumnData, Integer> columnMap) {
        String[] columns = trimValues(line.split(DELIMITER));
        if (columns.length < columnMap.values().stream().max(Integer::compareTo).get()) {
            return null;
        }

        Home home = new Home(
            columns[columnMap.get(ColumnData.HOME_ID)],
            columns[columnMap.get(ColumnData.ADDRESS)],
            columns[columnMap.get(ColumnData.ZIP)]);
        return home;
    }

    
    /**
     * Loads the homes from the csv file into a list of Home objects.
     * 
     * @param filename the name of the file to load
     * @return a set of User objects
     */
    public static List<Home> loadHomesFile(String userId) {
        String path = filePath.concat(FileType.HOMES.getFileName());
        List<Home> homes = new ArrayList<>();
        // set the list of homes for the user
        Set<String> homeIds = loadUserHomesFile(userId);

        List<String> lines;
        try {
            // this is so we can store the files in the resources folder
            
            // InputStream is = CsvLoader.class.getResourceAsStream(filename);
            InputStream is = new FileInputStream(path);
            InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(isr);
            lines = reader.lines().collect(Collectors.toList());
            reader.close();
        } catch (Exception e) {
            System.err.println("Error reading file: " + e.getMessage());
            return homes;
        }
        if (lines == null || lines.isEmpty()) {
            return homes;
        }
        
        Map<ColumnData, Integer> columnMap = processHeader(lines.remove(0));

        homes = lines.stream().map(line -> toHome(line, columnMap))
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
        String path = filePath.concat(FileType.USER_HOMES.getFileName());
        Set<String> userHomes = new HashSet<>();

        List<String> lines;
        try {
            // this is so we can store the files in the resources folder
            
            // InputStream is = CsvLoader.class.getResourceAsStream(filename);
            InputStream is = new FileInputStream(path);
            InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(isr);
            lines = reader.lines().collect(Collectors.toList());
            reader.close();
        } catch (Exception e) {
            System.err.println("Error reading file: " + e.getMessage());
            return userHomes;
        }
        if (lines == null || lines.isEmpty()) {
            return userHomes;
        }

        Map<ColumnData, Integer> columnMap = processHeader(lines.remove(0));
        for (String line:lines) {
            
            String[] columns = trimValues(line.split(DELIMITER));
            if (columns.length < columnMap.values().stream().max(Integer::compareTo).get()) {
                continue;
            }
            String id = columns[columnMap.get(ColumnData.USER_ID)];
            if (id.equalsIgnoreCase(userId)) {
                userHomes.add(columns[columnMap.get(ColumnData.HOME_ID)]);
            }
        
        }

        return userHomes;

    }


    /**
     * Converts a line from the csv file into a unit item object.
     * 
     * @param line      the line to convert
     * @param columnMap the map of columns to index
     * @return a User object
     */
    private static IUnit toUnitItems(String line, Map<ColumnData, Integer> columnMap) {
        String[] columns = trimValues(line.split(DELIMITER));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
        
        if (columns.length < columnMap.values().stream().max(Integer::compareTo).get()) {
            return null;
        }

        String userId = columns[columnMap.get(ColumnData.USER_ID)].trim();
        String homeId = columns[columnMap.get(ColumnData.HOME_ID)].trim();
        String itemId = columns[columnMap.get(ColumnData.UNIT_ID)].trim();
        String itemName = columns[columnMap.get(ColumnData.ITEM_NAME)].trim();
        UnitType type = UnitType.toUnitType(columns[columnMap
            .get(ColumnData.UNIT_TYPE)].trim());
        String roomType = columns[columnMap.get(ColumnData.ROOM_TYPE)].trim();
        String roomName = columns[columnMap.get(ColumnData.ROOM_NAME)].trim();
        String installDate = columns[columnMap.get(ColumnData.INSTALL_DATE)].trim();
        String maintainedDate = columns[columnMap.get(ColumnData.MAINTAINED_DATE)].trim();
        String maintainFreq = columns[columnMap.get(ColumnData.MAINTAIN_FREQ)].trim();
        String freqMeas = columns[columnMap.get(ColumnData.FREQ_MEAS)].trim();
        String issue = columns[columnMap.get(ColumnData.ISSUE)].trim();
        int priority = PriorityType.containsValues(columns[columnMap.get(ColumnData.PRIORITY)].trim()).getPriorityType();
        
        String electricWatt = "0";
        String plumbingGallon = "0";
        String height = "0";
        String width = "0";
        String depth = "0";
        
        IUnit unit = null;
        
        try {
            switch(type) {
                case UnitType.ELECTRIC_UNIT:
                    unit = new ElectricUnit(
                    userId
                    , homeId
                    , itemId
                    , itemName
                    , type
                    , RoomType.toRoomType(roomType)
                    , roomName
                    , LocalDate.parse(installDate, formatter)
                    , LocalDate.parse(maintainedDate, formatter)
                    , Integer.parseInt(maintainFreq)
                    , freqMeas
                    , issue
                    , PriorityType.toPriorityType(priority)
                    , Integer.parseInt(electricWatt)
                    );
                    break;
                case UnitType.PLUMBING_UNIT:
                    unit = new PlumbingUnit(
                    userId
                    , homeId
                    , itemId
                    , itemName
                    , type
                    , RoomType.toRoomType(roomType)
                    , roomName
                    , LocalDate.parse(installDate, formatter)
                    , LocalDate.parse(maintainedDate, formatter)
                    , Integer.parseInt(maintainFreq)
                    , freqMeas
                    , issue
                    , PriorityType.toPriorityType(priority)
                    ,Integer.parseInt(plumbingGallon)
                    );
                    break;
                case UnitType.APPLIANCE:
                    unit = new ApplianceUnit(
                    userId
                    , homeId
                    , itemId
                    , itemName
                    , type
                    , RoomType.toRoomType(roomType)
                    , roomName
                    , LocalDate.parse(installDate, formatter)
                    , LocalDate.parse(maintainedDate, formatter)
                    , Integer.parseInt(maintainFreq)
                    , freqMeas
                    , issue
                    , PriorityType.toPriorityType(priority)
                    , Integer.parseInt(electricWatt)
                    , Integer.parseInt(height)
                    , Integer.parseInt(width)
                    , Integer.parseInt(depth)
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
      * Loads IUnit objects from a CSV file.
        * Supports unit types: ElectricUnit, PlumbingUnit, ApplianceUnit.
     * 
     * @param filename the name of the file to load
     * @return a set of User objects
     */
    public static List<IUnit> loadUnitItemsFile(String userId, String homeId) {
        String path = filePath.concat(FileType.UNIT_ITEMS.getFileName());
        List<IUnit> units = new ArrayList<>();

        List<String> lines;
        try {
            // this is so we can store the files in the resources folder
            
            // InputStream is = CsvLoader.class.getResourceAsStream(filename);
            InputStream is = new FileInputStream(path);
            InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(isr);
            lines = reader.lines().collect(Collectors.toList());
            reader.close();
        } catch (Exception e) {
            System.err.println("Error reading file: " + e.getMessage());
            return units;
        }
        if (lines == null || lines.isEmpty()) {
            return units;
        }
        Map<ColumnData, Integer> columnMap = processHeader(lines.remove(0));
        
        units = lines.stream().map(line -> toUnitItems(line, columnMap))
                .filter(unit -> unit.getUserId().equals(userId) && unit.getHomeId().equals(homeId)
                && unit != null).collect(Collectors.toList());

        return units;

    }

}
