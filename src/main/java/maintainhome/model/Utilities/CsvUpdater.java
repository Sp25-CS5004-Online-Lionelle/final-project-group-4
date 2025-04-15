package maintainhome.model.Utilities;

import maintainhome.model.User.User;
import maintainhome.model.Home.Home;
import maintainhome.model.Home.UnitItems.IUnit;
import maintainhome.model.Utilities.Types.FileType;
import maintainhome.model.Utilities.Types.ColumnData;
import maintainhome.model.Utilities.Types.IColumnEnum;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class CsvUpdater implements ICsvSource {

    private static final String DELIMITER = ",";

    public static int getLastRow(FileType fileType) {
        List<String> lines = readCsvFile(fileType);
        
        return lines.size();
    }

    
    private static String[] trimValues(String[] values) {
        String[] result = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            result[i] = values[i].trim();
        }
        return result;
    }

    /**
     * Method to update or append to a CSV file
     */
    public static void updateRewriteCsvFile(FileType fileType, Object data) {
        List<String> lines = readCsvFile(fileType);
        Map<IColumnEnum, Integer> columnMap = processHeader(lines.remove(0), fileType);

        switch (fileType) {
            case USER:
                updateUserCsv(lines, columnMap, (User) data);
                break;
            case HOMES:
                updateHomesCsv(lines, columnMap, (Home) data);
                break;
            case UNIT_ITEMS:
                updateUnitItemsCsv(lines, columnMap, (IUnit) data);
                break;
            case USER_HOMES:
                updateUserHomesCsv(lines, columnMap, (User) data);
                break;
            default:
                throw new IllegalArgumentException("Unsupported file type: " + fileType);
        }

        // Write updated content back to the CSV
        writeToFile(fileType, lines);
    }

    
  public static void updateCsvFile(FileType fileType, Map<String, String> data) {
        List<String> lines = readCsvFile(fileType);
        String[] header = trimValues(lines.get(0).split(DELIMITER));
        
        String line = "";
        for (int i = 0; i < header.length; i++) {

            if (i == header.length - 1) {
                line += data.get(header[i]);
            } else {
                line += data.get(header[i]) + DELIMITER;
            }
        }

        lines.add(line);
        // Write updated content back to the CSV
    
        // Write updated content back to the CSV
        writeToFile(fileType, lines);
}

    /**
     * Reads the content of a CSV file.
     */
    private static List<String> readCsvFile(FileType fileType) {
        String filePath = getFilePath(fileType);
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return lines;
    }

    /**
     * Gets the file path based on file type
     */
    private static String getFilePath(FileType fileType) {
        return filePath + fileType.getFileName(); // Update this path
    }

    /**
     * Process the CSV header and return the column-to-index mapping
     */
    private static Map<IColumnEnum, Integer> processHeader(String header, FileType fileType) {
        Map<IColumnEnum, Integer> columnMap = new HashMap<>();
        String[] columns = header.split(DELIMITER);
        for (int i = 0; i < columns.length; i++) {
            try {
                IColumnEnum columnEnum = ColumnData.fromString(columns[i].trim(), fileType);
                columnMap.put(columnEnum, i);
            } catch (IllegalArgumentException e) {
                System.err.println("Ignoring column: " + columns[i]);
            }
        }
        return columnMap;
    }

    /**
     * Write the updated lines back to the file
     */
    private static void writeToFile(FileType fileType, List<String> lines) {
        String filePath = getFilePath(fileType);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, StandardCharsets.UTF_8))) {
            for (String line : lines) {
                
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    /**
     * Update the user.csv file
     */
    private static void updateUserCsv(List<String> lines, Map<IColumnEnum, Integer> columnMap, User user) {
        boolean updated = false;
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            String[] columns = line.split(DELIMITER);
            if (columns[columnMap.get(ColumnData.UserData.user_id)].equals(user.getUserId())) {
                lines.set(i, formatUserLine(user, columnMap));
                updated = true;
                break;
            }
        }
        if (!updated) {
            lines.add(formatUserLine(user, columnMap));
        }
    }

    /**
     * Convert User object to a CSV formatted line
     */
    private static String formatUserLine(User user, Map<IColumnEnum, Integer> columnMap) {
        return user.getUserId() + DELIMITER + user.getName() + DELIMITER + user.getEmail();
    }

    /**
     * Update the homes.csv file
     */
    private static void updateHomesCsv(List<String> lines, Map<IColumnEnum, Integer> columnMap, Home home) {
        boolean updated = false;
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            String[] columns = line.split(DELIMITER);
            if (columns[columnMap.get(ColumnData.HomeData.home_id)].equals(home.getHomeId())) {
                lines.set(i, formatHomeLine(home, columnMap));
                updated = true;
                break;
            }
        }
        if (!updated) {
            lines.add(formatHomeLine(home, columnMap));
        }
    }

    /**
     * Convert Home object to a CSV formatted line
     */
    private static String formatHomeLine(Home home, Map<IColumnEnum, Integer> columnMap) {
        return home.getHomeId() + DELIMITER + home.getHomeNum() + DELIMITER + home.getHomeName()
                + DELIMITER + home.getAddress() + DELIMITER + home.getZip();
    }

    /**
     * Update the unit_items.csv file
     */
    private static void updateUnitItemsCsv(List<String> lines, Map<IColumnEnum, Integer> columnMap, IUnit unit) {
        boolean updated = false;
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            String[] columns = line.split(DELIMITER);
            if (columns[columnMap.get(ColumnData.UnitItemData.unit_id)].equals(unit.getUnitId())) {
                lines.set(i, formatUnitItemLine(unit, columnMap));
                updated = true;
                break;
            }
        }
        if (!updated) {
            lines.add(formatUnitItemLine(unit, columnMap));
        }
    }

    /**
     * Convert IUnit object to a CSV formatted line
     */
    private static String formatUnitItemLine(IUnit unit, Map<IColumnEnum, Integer> columnMap) {
        return unit.getUserId() + DELIMITER + unit.getHomeId() + DELIMITER + unit.getUnitId()
                + DELIMITER + unit.getItemName() + DELIMITER + unit.getUnitType() + DELIMITER + unit.getRoomType()
                + DELIMITER + unit.getRoomName() + DELIMITER + IUnit.dateToString(unit.getInstallDate())
                + DELIMITER + IUnit.dateToString(unit.getMaintainedDate()) + DELIMITER + unit.getMaintenanceFrequency()
                + DELIMITER + unit.getFrequencyMeasure() + DELIMITER + unit.getIssue() + DELIMITER + unit.getPriority();
    }

    /**
     * Update the user_homes.csv file
     */
    private static void updateUserHomesCsv(List<String> lines, Map<IColumnEnum, Integer> columnMap, User user) {
        boolean updated = false;
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            String[] columns = line.split(DELIMITER);
            if (columns[columnMap.get(ColumnData.UserHomeData.user_id)].equals(user.getUserId())) {
                lines.set(i, formatUserHomesLine(user, columnMap));
                updated = true;
                break;
            }
        }
        if (!updated) {
            lines.add(formatUserHomesLine(user, columnMap));
        }
    }

    /**
     * Convert User object to a CSV formatted line for the user_homes file
     */
    private static String formatUserHomesLine(User user, Map<IColumnEnum, Integer> columnMap) {
        // Assuming user can have multiple homes, you may need to loop through homes here
        StringBuilder line = new StringBuilder();
        for (Home home : user.getHomes()) {
            line.append(user.getUserId()).append(DELIMITER).append(home.getHomeId()).append(System.lineSeparator());
        }
        return line.toString();
    }

}
