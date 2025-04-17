package maintainhome.model.Home.UnitItems;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import maintainhome.model.Home.Types.PriorityType;
import maintainhome.model.Home.Types.RoomType;
import maintainhome.model.Home.Types.UnitType;
// the unit interface that all unit items implement
public interface IUnit {
    static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("M/d/yyyy");

    /**
     * Gets the user ID.
     * 
     * @return the user ID string
     */
    String getUserId();

    /**
     * Gets the home ID.
     * 
     * @return the home ID string
     */
    String getHomeId();

    /**
     * Gets the unit ID.
     * 
     * @return the unit ID string
     */
    String getUnitId();

    /**
     * Gets the unit item name.
     * 
     * @return the unit item name as string
     */
    String getItemName();

    /**
     * Gets the unit type.
     * 
     * @return the unit type as UnitType
     */
    UnitType getUnitType();
    
    /**
     * Gets the unit's room type.
     * 
     * @return the room type as RoomType
     */
    RoomType getRoomType();
    
    /**
     * Gets the unit's room name.
     * 
     * @return the room name as string
     */
    String getRoomName();

    /**
     * Gets the unit's install date.
     * 
     * @return the unit's install date as LocalDate
     */
    LocalDate getInstallDate();
    
    /**
     * Gets the unit's maintenance date.
     * 
     * @return the unit's maintenance date as LocalDate
     */
    LocalDate getMaintainedDate();
    
    /**
     * Gets the unit's maintenance frequency.
     * 
     * @return the unit's maintenance frequency as an integer
     */
    int getMaintenanceFrequency();

    /**
     * Gets the unit's frequency measure.
     * 
     * @return the unit's frequency measure as a String
     */
    String getFrequencyMeasure();
    
    /**
     * Gets the unit's issue.
     * 
     * @return the unit's issue as a String
     */
    String getIssue();

    /**
     * Gets the unit's priority.
     * 
     * @return the unit's priority as a PriorityType
     */
    PriorityType getPriority();

    /**
     * Gets the unit's attributes as a row.
     * 
     * @return the unit's attributes as a row
     */
    String[] getUnitRow();

    /**
     * Converts a string (e.g. "2/2/2024") into a LocalDate.
     *
     * @param strDate a date string in M/d/yyyy format
     * @return LocalDate or null if parsing fails
     */
    static LocalDate parseDate(String strDate) {
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
    static String dateToString(LocalDate date) {
        return date != null ? date.format(DATE_FORMATTER) : "";
    }
    
}
