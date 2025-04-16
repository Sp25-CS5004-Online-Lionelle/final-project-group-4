// UnitFilters.java
package maintainhome.model.Utilities.Operations;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import maintainhome.model.Home.Home;
import maintainhome.model.Home.UnitItems.IUnit;

public class Filters {
    public static List<IUnit> filterByType(List<IUnit> units, String type) {
        return units.stream()
                .filter(u -> u.getUnitType().toString().equalsIgnoreCase(type))
                .collect(Collectors.toList());
    }

    public static List<IUnit> filterByRoom(List<IUnit> units, String room) {
        return units.stream()
                .filter(u -> u.getRoomType().toString().equalsIgnoreCase(room))
                .collect(Collectors.toList());
    }

    public static List<IUnit> filterByInstallDateAfter(List<IUnit> units, LocalDate date) {
        return units.stream()
                .filter(u -> u.getInstallDate().isAfter(date))
                .collect(Collectors.toList());
    }
    
    public static List<Home> filterByHomeName(List<Home> homes, String homeName) {
    return homes.stream()
            .filter(home -> home.getHomeName().equalsIgnoreCase(homeName))
            .collect(Collectors.toList());
    }
}
