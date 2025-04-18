import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import maintainhome.model.Home.Home;
import maintainhome.model.Home.UnitItems.IUnit;
import maintainhome.model.Home.UnitItems.ApplianceUnit;
import maintainhome.model.Home.Types.UnitType;
import maintainhome.model.Home.Types.RoomType;
import maintainhome.model.Home.Types.PriorityType;

import java.time.LocalDate;

public class TestHome {

    @Test
    public void testHomeGetters() {
        Home home = new Home("H001", 101, "Test Home", "123 Main St", "99999");

        assertEquals("H001", home.getHomeId());
        assertEquals(101, home.getHomeNum());
        assertEquals("Test Home", home.getHomeName());
        assertEquals("123 Main St", home.getAddress());
        assertEquals("99999", home.getZip());
    }

    @Test
    public void testSetUnitItemAndGetUnitItems() {
        Home home = new Home("H002", 102, "Appliance Home", "456 Elm St", "54321");

        ApplianceUnit unit = new ApplianceUnit(
            "js1", "H002", "A001", "Fridge", UnitType.APPLIANCE, RoomType.KITCHEN,
            "Kitchen", LocalDate.of(2020, 1, 1), LocalDate.of(2023, 1, 1),
            12, "months", "Broken door", PriorityType.IMPORTANT,
            700, 180, 70, 60
        );

        home.setUnitItem(unit);

        List<IUnit> units = home.getUnitItems();
        assertEquals(1, units.size());
        assertEquals("Fridge", units.get(0).getItemName());
    }

    @Test
    public void testSetUnitItemsList() {
        Home home = new Home("H003", 103, "Bulk Home", "789 Oak Ave", "11111");

        ApplianceUnit unit1 = new ApplianceUnit(
            "js1", "H003", "A002", "Oven", UnitType.APPLIANCE, RoomType.KITCHEN,
            "Kitchen", LocalDate.of(2019, 5, 15), LocalDate.of(2022, 5, 15),
            24, "months", "Rusty", PriorityType.LOW,
            1200, 150, 80, 70
        );

        ApplianceUnit unit2 = new ApplianceUnit(
            "js1", "H003", "A003", "Dishwasher", UnitType.APPLIANCE, RoomType.KITCHEN,
            "Kitchen", LocalDate.of(2021, 3, 10), LocalDate.of(2022, 9, 10),
            6, "months", "Leaks", PriorityType.URGENT,
            950, 85, 60, 65
        );

        home.setUnitItems(List.of(unit1, unit2));

        List<IUnit> units = home.getUnitItems();
        assertEquals(2, units.size());
        assertEquals("Dishwasher", units.get(1).getItemName());
    }

    @Test
    public void testHomeRowFormat() {
        Home home = new Home("H004", 104, "Row Home", "321 Pine Rd", "22222");

        String[] row = home.getHomeRow();
        assertEquals("104", row[0]);
        assertEquals("Row Home", row[1]);
        assertEquals("321 Pine Rd", row[2]);
        assertEquals("22222", row[3]);
    }

    @Test
    public void testUnitJListReturnFormat() {
        Home home = new Home("H005", 105, "List Home", "999 Maple St", "33333");

        ApplianceUnit unit = new ApplianceUnit(
            "js1", "H005", "A004", "Toaster", UnitType.APPLIANCE, RoomType.KITCHEN,
            "Kitchen", LocalDate.of(2021, 7, 20), LocalDate.of(2022, 7, 20),
            12, "months", "None", PriorityType.LOW,
            900, 30, 20, 25
        );

        home.setUnitItem(unit);

        String[] unitList = home.getUnitJList();
        assertEquals(1, unitList.length);
        assertEquals("A004", unitList[0]);
    }
}
