package maintainhome.model.Home;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import maintainhome.model.Home.Types.RoomType;
import maintainhome.model.Home.Types.UnitType;

public interface IUnit {
    static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("M/d/yyyy");

    String getUserId();

    String getHomeId();

    String getUnitId();

    String getItemName();

    UnitType getUnitType();

    RoomType getRoomType();
    
    LocalDate getInstallDate();

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

    String toCSV();
    
}
