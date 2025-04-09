// UnitFilters.java
package maintainhome.model;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import maintainhome.model.Home.IUnit;
import maintainhome.model.Home.RoomType;

public class UnitFilters {
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
}
