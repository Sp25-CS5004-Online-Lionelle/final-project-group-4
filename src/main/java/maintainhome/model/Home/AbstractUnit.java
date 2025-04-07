package maintainhome.model.Home;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/* 
 * 
 * - homeId : int

- category : String

- itemName : String

- installDate : String

- maintainedDate : String

- standardLifeSpan : int

- lifeSpanMeasure : String

- roomLocation : String

+ AbstractUnit()

+ setRoomLocation() : :void
 * 
*/

public abstract class AbstractUnit {
    private int unitId;
    private String itemName;
    private String mapKeyword;
    private LocalDate installDate;
    private LocalDate maintainedDate;
    private int maintenanceFrequency;
    private String frequencyMeasure;
    private String roomLocation;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("M/d/yyyy");


    public AbstractUnit(int unitId, String mapKeyword, String itemName, LocalDate installDate, LocalDate maintainedDate,
            int maintenanceFrequency, String frequencyMeasure, String roomLocation) {
        this.unitId = unitId;
        this.mapKeyword = mapKeyword;
        this.itemName = itemName;
        this.installDate = installDate;
        this.maintainedDate = maintainedDate;
        this.maintenanceFrequency = maintenanceFrequency;
        this.frequencyMeasure = frequencyMeasure;
        this.roomLocation = roomLocation;
    }


    public int getHomeId() {
        return unitId;
    }

    public void setHomeId(int unitId) {
        this.unitId = unitId;
    }

    public String getMapKeyword() {
        return mapKeyword;
    }

    public void setMapKeyword(String mapKeyword) {
        this.mapKeyword = mapKeyword;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public LocalDate getInstallDate() {
        return installDate;
    }

    public void setInstallDate(LocalDate installDate) {
        this.installDate = installDate;
    }

    public LocalDate getMaintainedDate() {
        return maintainedDate;
    }

    public void setMaintainedDate(LocalDate maintainedDate) {
        this.maintainedDate = maintainedDate;
    }

    public int getMaintenanceFrequency() {
        return maintenanceFrequency;
    }

    public void setMaintenanceFrequency(int maintenanceFrequency) {
        this.maintenanceFrequency = maintenanceFrequency;
    }

    public String getFrequencyMeasure() {
        return frequencyMeasure;
    }
    public void setFrequencyMeasure(String frequencyMeasure) {
        this.frequencyMeasure = frequencyMeasure;
    }

    public String getRoomLocation() {
        return roomLocation;
    }

    public void setRoomLocation(String roomLocation) {
        this.roomLocation = roomLocation;
    }

/**
     * Converts a string (e.g. "2/2/2024") into a LocalDate.
     *
     * @param strDate a date string in M/d/yyyy format
     * @return LocalDate or null if parsing fails
     */
    public static LocalDate parseDate(String strDate) {
        try {
            return LocalDate.parse(strDate, DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            System.err.println("Error parsing date: " + strDate + " (" + e.getMessage() + ")");
            return null;
        }
    }

/**
     * Converts a LocalDate into a string using M/d/yyyy format.
     *
     * @param date the LocalDate to format
     * @return a formatted date string like "2/2/2024"
     */
    public static String formatDate(LocalDate date) {
        return date != null ? date.format(DATE_FORMATTER) : "";
    }
    

}