package maintainhome.model.Utilities;

import maintainhome.model.Home.AbstractUnit;
import maintainhome.model.Home.ApplianceUnit;
import maintainhome.model.Home.ElectricUnit;
import maintainhome.model.Home.PlumbingUnit;
import maintainhome.model.Home.IUnit;
import maintainhome.model.Home.UnitType;
import maintainhome.model.Home.RoomType;

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
public class CsvUnitItemsLoader {

    /** Standard csv delim. */
    private static final String DELIMITER = ",";
    private static String filename = FileType.UNIT_ITEMS.getFileName();

    /**
     * Converts a line from the csv file into a User object.
     * 
     * @param line      the line to convert
     * @param columnMap the map of columns to index
     * @return a User object
     */
    private static IUnit toUnitItems(String line, Map<UnitItemData, Integer> columnMap) {
        
        String[] columns = line.split(DELIMITER);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
        
        if (columns.length < columnMap.values().stream().max(Integer::compareTo).get()) {
            return null;
        }

        String itemId = columns[columnMap.get(UnitItemData.ID)].trim();
        
        String itemName = columns[columnMap.get(UnitItemData.NAME)].trim();
        UnitType type = UnitType.toUnitType(columns[columnMap
            .get(UnitItemData.UNIT_TYPE)].trim());
        String roomType = columns[columnMap.get(UnitItemData.ROOM_TYPE)].trim();
        String roomName = columns[columnMap.get(UnitItemData.ROOM_NAME)].trim();
        String installDate = columns[columnMap.get(UnitItemData.INSTALL_DATE)].trim();
        String maintainedDate = columns[columnMap.get(UnitItemData.MAINTAINED_DATE)].trim();
        String maintainFreq = columns[columnMap.get(UnitItemData.MAINTAIN_FREQ)].trim();
        String freqMeas = columns[columnMap.get(UnitItemData.FREQ_MEAS)].trim();
        
        String electricWatt = "0";
        String plumbingGallon = "0";
        String height = "0";
        String width = "0";
        String depth = "0";
        
        IUnit unit = null;
        
        try {
            switch(type) {
                case UnitType.ELECTRIC_UNIT:
                    unit = new ElectricUnit(itemId
                    , itemName
                    , type
                    , RoomType.toRoomType(roomType)
                    , roomName
                    , LocalDate.parse(installDate, formatter)
                    , LocalDate.parse(maintainedDate, formatter)
                    , Integer.parseInt(maintainFreq)
                    , freqMeas
                    , Integer.parseInt(electricWatt)
                    );
                    break;
                case UnitType.PLUMBING_UNIT:
                    unit = new PlumbingUnit(itemId
                    , itemName
                    , type
                    , RoomType.toRoomType(roomType)
                    , roomName
                    , LocalDate.parse(installDate, formatter)
                    , LocalDate.parse(maintainedDate, formatter)
                    , Integer.parseInt(maintainFreq)
                    , freqMeas
                    ,Integer.parseInt(plumbingGallon)
                    );
                    break;
                case UnitType.APPLIANCE:
                    unit = new ApplianceUnit(itemId
                    , itemName
                    , type
                    , RoomType.toRoomType(roomType)
                    , roomName
                    , LocalDate.parse(installDate, formatter)
                    , LocalDate.parse(maintainedDate, formatter)
                    , Integer.parseInt(maintainFreq)
                    , freqMeas
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
     * Processes the header line to determine the column mapping.
     * 
     * It is common to do this for csv files as the columns can be in any order.
     * This makes it order independent by taking a moment to link the columns
     * with their actual index in the file.
     * 
     * @param header the header line
     * @return a map of column to index
     */
    private static Map<UnitItemData, Integer> processHeader(String header) {
        Map<UnitItemData, Integer> columnMap = new HashMap<>();
        // 
        String[] columns = header.split(DELIMITER);
        for (int i = 0; i < columns.length; i++) {
            try {
                UnitItemData col = UnitItemData.fromColumnName(columns[i].trim());
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
    public static List<IUnit> loadFile(String filePath) {
        filePath = filePath.concat(filename);
        List<IUnit> units = new ArrayList<>();
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
            return units;
        }
        if (lines == null || lines.isEmpty()) {
            return units;
        }
        Map<UnitItemData, Integer> columnMap = processHeader(lines.remove(0));
        
        units = lines.stream().map(line -> toUnitItems(line, columnMap))
                .filter(unit -> unit != null).collect(Collectors.toList());
        

        return units;

    }
    

}
