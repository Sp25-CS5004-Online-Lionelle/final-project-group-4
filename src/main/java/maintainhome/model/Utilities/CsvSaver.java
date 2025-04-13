package maintainhome.model.Utilities;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import maintainhome.model.Home.UnitItems.IUnit;

/**
 * Utility class for saving unit data to a CSV file.
 */
public class CsvSaver {

    /**
     * Saves a list of units to a CSV file.
     *
     * @param units    the list of IUnit objects to save
     * @param filePath the path to the output CSV file
     */
    public static void saveUnitsToCsv(List<IUnit> units, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            // Write CSV header
            writer.write("unitId,itemName,roomType,roomName,installDate,maintainedDate,maintenanceFrequency,maintenanceFrequency,frequencyMeasure\n");

            for (IUnit unit : units) {
                writer.write(unit.toCSV() + "\n");
            }

            System.out.println("CSV saved to: " + filePath);
        } catch (IOException e) {
            System.err.println("Error saving CSV: " + e.getMessage());
        }
    }
}

