import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import maintainhome.model.Home.Home;
import maintainhome.model.Home.Types.PriorityType;
import maintainhome.model.Home.Types.RoomType;
import maintainhome.model.Home.Types.UnitType;
import maintainhome.model.Home.UnitItems.ApplianceUnit;
import maintainhome.model.Home.UnitItems.IUnit;
import maintainhome.model.Utilities.Operations.Filters;
import maintainhome.model.Utilities.Operations.Sorters;

public class TestFiltersAndSorters {

    private List<IUnit> getSampleUnits() {
        List<IUnit> units = new ArrayList<>();

        units.add(new ApplianceUnit("js1", "H001", "U1", "Oven", UnitType.APPLIANCE, RoomType.KITCHEN,
            "Main Kitchen", LocalDate.of(2022, 1, 1), LocalDate.of(2023, 1, 1),
            12, "months", "Hotplate cracked", PriorityType.IMPORTANT,
            1000, 100, 60, 60));

        units.add(new ApplianceUnit("js1", "H001", "U2", "Toaster", UnitType.APPLIANCE, RoomType.KITCHEN,
            "Main Kitchen", LocalDate.of(2021, 5, 5), LocalDate.of(2022, 5, 5),
            6, "months", "None", PriorityType.LOW,
            800, 30, 20, 25));

        units.add(new ApplianceUnit("js1", "H001", "U3", "Lamp", UnitType.APPLIANCE, RoomType.BEDROOM,
            "Master Bedroom", LocalDate.of(2020, 3, 15), LocalDate.of(2021, 3, 15),
            18, "months", "Dim light", PriorityType.URGENT,
            60, 20, 20, 40));

        return units;
    }

    private List<Home> getSampleHomes() {
        List<Home> homes = new ArrayList<>();
        homes.add(new Home("H1", 101, "Home A", "123 Main St", "11111"));
        homes.add(new Home("H2", 102, "Home B", "456 Elm St", "22222"));
        homes.add(new Home("H3", 103, "Beach House", "789 Ocean Ave", "33333"));
        return homes;
    }

    @Test
    public void testFilterByRoom_Kitchen() {
        List<IUnit> filtered = Filters.filterByRoom(getSampleUnits(), "KITCHEN");
        assertEquals(2, filtered.size());
        assertTrue(filtered.stream().allMatch(u -> u.getRoomType().toString().equals("KITCHEN")));
    }

    @Test
    public void testFilterByType_Appliance() {
        List<IUnit> filtered = Filters.filterByType(getSampleUnits(), "APPLIANCE");
        assertEquals(3, filtered.size());
    }

    @Test
    public void testFilterByInstallDateAfter() {
        LocalDate cutoff = LocalDate.of(2021, 1, 1);
        List<IUnit> filtered = Filters.filterByInstallDateAfter(getSampleUnits(), cutoff);
        assertEquals(2, filtered.size());
    }

    @Test
    public void testFilterByHomeNameExactMatch() {
        List<Home> filtered = Filters.filterByHomeName(getSampleHomes(), "Home B");
        assertEquals(1, filtered.size());
        assertEquals("Home B", filtered.get(0).getHomeName());
    }
    @Test
    public void testSortUnitsByRoom() {
        List<IUnit> units = getSampleUnits();
        units.sort(Sorters.BY_ROOM);
    
        // Sort order will follow declaration order of RoomType enum
        assertEquals(RoomType.KITCHEN, units.get(0).getRoomType());
        assertEquals(RoomType.KITCHEN, units.get(1).getRoomType());
        assertEquals(RoomType.BEDROOM, units.get(2).getRoomType());
    }
    
    @Test
    public void testSortHomesByHomeNum() {
        List<Home> homes = getSampleHomes();
        homes.sort(Sorters.BY_HOME_NUM);
        assertEquals(101, homes.get(0).getHomeNum());
        assertEquals(103, homes.get(2).getHomeNum());
    }

    @Test
    public void testSortUnitsByInstallDate() {
        List<IUnit> units = getSampleUnits();
        units.sort(Sorters.BY_INSTALL_DATE);
        assertEquals("Lamp", units.get(0).getItemName());
    }

    @Test
    public void testSortUnitsByMaintainDate() {
        List<IUnit> units = getSampleUnits();
        units.sort(Sorters.BY_MAINTAIN_DATE);
        assertEquals("Lamp", units.get(0).getItemName());
    }
}
