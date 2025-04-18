// UnitFilters.java
package maintainhome.model.Utilities.Operations;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import maintainhome.model.Home.Home;
import maintainhome.model.Home.UnitItems.IUnit;

/**
 * The Filters to filter the Unit Items or Homes by
 */
public class Filters {
    /**
     * Filters the unit items collection by the unit type.
     * 
     * @param units the unit items collection to filter
     * @param type the unit type to filter the unit items by
     * @return the filtered collection of unit items by unit type
     */
    public static List<IUnit> filterByType(List<IUnit> units, String type) {
        return units.stream()
                .filter(u -> u.getUnitType().toString().equalsIgnoreCase(type))
                .collect(Collectors.toList());
    }

    /**
     * Filters the unit items collection by the room.
     * 
     * @param units the unit items collection to filter
     * @param room the room to filter the unit items by
     * @return the filtered unit items collection by room
     */
    public static List<IUnit> filterByRoom(List<IUnit> units, String room) {
        return units.stream()
                .filter(u -> u.getRoomType().toString().equalsIgnoreCase(room))
                .collect(Collectors.toList());
    }

    /**
     * Filters the unit items collection by the install date after the specified date.
     * 
     * @param units the unit items collection to filter
     * @param date the date to filter the install date by
     * @return the filtered unit items collection by dates after install date
     */
    public static List<IUnit> filterByInstallDateAfter(List<IUnit> units, LocalDate date) {
        return units.stream()
                .filter(u -> u.getInstallDate().isAfter(date))
                .collect(Collectors.toList());
    }
    
    /**
     * Filters the Homes collection by the home name.
     * 
     * @param homes the collection of homes to filter
     * @param homeName the home name to filter by
     * @return the filtered collection of homes by home name
     */
    public static List<Home> filterByHomeName(List<Home> homes, String homeName) {
    return homes.stream()
            .filter(home -> home.getHomeName().equalsIgnoreCase(homeName))
            .collect(Collectors.toList());
    }
}
