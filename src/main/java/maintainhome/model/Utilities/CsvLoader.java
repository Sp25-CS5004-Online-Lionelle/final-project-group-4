package maintainhome.model.Utilities;

import maintainhome.model.Home.AbstractUnit;
import maintainhome.model.Home.ApplianceUnit;
import maintainhome.model.Home.ElectricUnit;
import maintainhome.model.Home.PlumbingUnit;
import maintainhome.model.Home.IUnit;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Loads IUnit objects from a CSV file.
 * Supports unit types: ElectricalUnit, PlumbingUnit, ApplianceUnit.
 */
public class CsvLoader {
    public static List<IUnit> loadUnits(String csvPath) throws IOException {
        List<IUnit> units = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                CsvLoader.class.getResourceAsStream(csvPath)))) {
            String line;
            reader.readLine(); // Skip header

            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");

                String objectName = fields[0];
                String unitType = fields[1];
                String itemName = fields[2];
                LocalDate installDate = LocalDate.parse(fields[3], formatter);
                LocalDate maintainedDate = LocalDate.parse(fields[4], formatter);
                int lifeSpan = Integer.parseInt(fields[5]);
                String lifeSpanMeasure = fields[6];
                String room = fields[7];
                int electricWatt = Integer.parseInt(fields[8]);
                int plumbingGallon = Integer.parseInt(fields[9]);

                int id = objectName.hashCode();

                if (unitType.equalsIgnoreCase("ElectricalUnit")) {
                    units.add(new ElectricUnit(id, unitType, itemName, installDate,
                            maintainedDate, lifeSpan, lifeSpanMeasure, room, electricWatt));
                } else if (unitType.equalsIgnoreCase("PlumbingUnit")) {
                    units.add(new PlumbingUnit(id, unitType, itemName, installDate,
                            maintainedDate, lifeSpan, lifeSpanMeasure, room, plumbingGallon));
                } else if (unitType.equalsIgnoreCase("ApplianceUnit")) {
                    units.add(new ApplianceUnit(id, unitType, itemName, installDate,
                            maintainedDate, lifeSpan, lifeSpanMeasure, room, electricWatt));
                } else {
                    System.err.println("Unknown unit type: " + unitType);
                }
            }
        }

        return units;
    }
}
