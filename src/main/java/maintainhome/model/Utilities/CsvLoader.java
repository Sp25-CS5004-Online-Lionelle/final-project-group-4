package maintainhome.model.Utilities;

import maintainhome.model.Home.AbstractUnit;
import maintainhome.model.Home.ApplianceUnit;
import maintainhome.model.Home.ElectricUnit;
import maintainhome.model.Home.PlumbingUnit;
import maintainhome.model.Home.RoomType;
import maintainhome.model.Home.UnitType;
import maintainhome.model.Home.IUnit;
import maintainhome.model.User.User;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.io.IOException;
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
public class CsvLoader {

    /** Standard csv delim. */
    private static final String DELIMITER = ",";

    /**
     * Converts a line from the csv file into a User object.
     * 
     * @param line      the line to convert
     * @param columnMap the map of columns to index
     * @return a User object
     */
    private static User toUser(String line, Map<UserData, Integer> columnMap) {
        String[] columns = line.split(DELIMITER);
        if (columns.length < columnMap.values().stream().max(Integer::compareTo).get()) {
            return null;
        }

        try {
            User user = new User(columns[columnMap.get(UserData.ID)],
                columns[columnMap.get(UserData.NAME)],
                columns[columnMap.get(UserData.EMAIL)]);
            return user;
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
    private static Map<UserData, Integer> processHeader(String header) {
        Map<UserData, Integer> columnMap = new HashMap<>();
        // 
        String[] columns = header.split(DELIMITER);
        for (int i = 0; i < columns.length; i++) {
            try {
                UserData col = UserData.fromColumnName(columns[i]);
                columnMap.put(col, i);
            } catch (IllegalArgumentException e) {
                // System.out.println("Ignoring column: " + columns[i]);
            }
        }
        return columnMap;
    }

    /**
     * Loads the users from the csv file into a set of User objects.
     * 
     * @param filename the name of the file to load
     * @return a set of User objects
     */
    public static Set<User> loadFile(String filename) {

        Set<User> users = new HashSet<>();
        String filePath = new File("").getAbsolutePath();
        //File file = new File("C:\\path\\to\\your\\file.txt");
        filePath = filePath.concat("\\src\\main\\resources\\files\\" + filename);
        //System.out.println(filePath);
        List<String> lines;
        try {
            // this is so we can store the files in the resources folder
            
            // InputStream is = CsvLoader.class.getResourceAsStream(filename);
            InputStream is = new FileInputStream(filePath);
            InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(isr);
            lines = reader.lines().collect(Collectors.toList());
            reader.close();
        } catch (Exception e) {
            System.err.println("Error reading file: " + e.getMessage());
            return users;
        }
        if (lines == null || lines.isEmpty()) {
            return users;
        }

        Map<UserData, Integer> columnMap = processHeader(lines.remove(0));

        users = lines.stream().map(line -> toUser(line, columnMap))
                .filter(user -> user != null).collect(Collectors.toSet());

        return users;

    }
    public static List<IUnit> loadUnits(String filename) {
    List<IUnit> units = new ArrayList<>();
    String filePath = new File("").getAbsolutePath() + "/src/main/resources/files/" + filename;

    try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8))) {
        // Read all lines from the CSV file
        List<String> lines = reader.lines().collect(Collectors.toList());
        if (lines.isEmpty()) return units;

        // Extract header and use it to build a map of column name -> index
        String header = lines.remove(0);
        String[] headers = header.split(DELIMITER);
        Map<UnitItemData, Integer> columnMap = new HashMap<>();
        for (int i = 0; i < headers.length; i++) {
            try {
                columnMap.put(UnitItemData.fromColumnName(headers[i]), i);
            } catch (IllegalArgumentException ignored) {}
            // Skip unrecognized columns
        }

        // Parse each remaining line into a specific unit type
        for (String line : lines) {

            String[] fields = line.split(DELIMITER);
            String id = fields[columnMap.get(UnitItemData.ID)];
            String name = fields[columnMap.get(UnitItemData.NAME)];
            String unitTypeStr = fields[columnMap.get(UnitItemData.UNIT_TYPE)];
            String roomTypeStr = fields[columnMap.get(UnitItemData.ROOM_TYPE)];
            String roomName = fields[columnMap.get(UnitItemData.ROOM_NAME)];
            String installDateStr = fields[columnMap.get(UnitItemData.INSTALL_DATE)];
            String maintainedDateStr = fields[columnMap.get(UnitItemData.MAINTAINED_DATE)];
            int freq = Integer.parseInt(fields[columnMap.get(UnitItemData.MAINTAIN_FREQ)]);
            String freqMeas = fields[columnMap.get(UnitItemData.FREQ_MEAS)];

            // Parse the date strings to LocalDate objects
            LocalDate installDate = IUnit.parseDate(installDateStr);
            LocalDate maintainedDate = IUnit.parseDate(maintainedDateStr);
            RoomType roomType = RoomType.toRoomType(roomTypeStr);

            // Create the correct unit subclass based on UnitType
            UnitType unitType = UnitType.toUnitType(unitTypeStr);
            switch (unitType) {
                case APPLIANCE:
                    units.add(new ApplianceUnit(id, name, roomType, roomName, installDate, maintainedDate, freq, freqMeas, 1000, 20, 20, 20));
                    break;
                case ELECTRIC_UNIT:
                    units.add(new ElectricUnit(id, name, roomType, roomName, installDate, maintainedDate, freq, freqMeas, 500));
                    break;
                case PLUMBING_UNIT:
                    units.add(new PlumbingUnit(id, name, roomType, roomName, installDate, maintainedDate, freq, freqMeas, 200));
                    break;
                default:
                    System.err.println("Unknown unit type: " + unitTypeStr);
            }
        }

    } catch (IOException e) {
        System.err.println("Error loading units CSV: " + e.getMessage());
    }

    return units;
}


}
