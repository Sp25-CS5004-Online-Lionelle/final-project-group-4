package maintainhome.model.Utilities;

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
public class CsvUserLoader {

    /** Standard csv delim. */
    private static final String DELIMITER = ",";
    private static String filename = FileType.USER.getFileName();
    
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
    public static Set<User> loadFile(String filePath) {
        filePath = filePath.concat(filename);
        Set<User> users = new HashSet<>();
        //File file = new File("C:\\path\\to\\your\\file.txt");
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
    

}
