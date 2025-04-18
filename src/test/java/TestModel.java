import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import maintainhome.model.Home.Home;
import maintainhome.model.Home.Types.PriorityType;
import maintainhome.model.Home.Types.RoomType;
import maintainhome.model.Home.Types.UnitType;
import maintainhome.model.Home.UnitItems.IUnit;
import maintainhome.model.Model;
import maintainhome.model.User.User;
import maintainhome.model.Utilities.Types.ColumnData;

public class TestModel {

    private Model model;
    private Map<String, String> testData;
    private User testUser;

    @BeforeEach
    public void setup() {
        model = new Model();

        testUser = new User("js1", "Jane Smith", "jane@example.com");
        testUser.setHomes(List.of(new Home("H001", 101, "Home A", "123 Main St", "12345")));

        model.setUser("js1"); // stub, won't load from CSV
        model.setFilteredUnits(List.of()); // empty default

        // Manually inject user object since we're bypassing CsvLoader
        try {
            java.lang.reflect.Field userField = Model.class.getDeclaredField("user");
            userField.setAccessible(true);
            userField.set(model, testUser);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        testData = new HashMap<>();
        testData.put(ColumnData.HomeData.home_name.toString(), "Test Home");
        testData.put(ColumnData.HomeData.address.toString(), "456 Oak Ave");
        testData.put(ColumnData.HomeData.zip.toString(), "99999");

        testData.put(ColumnData.UnitItemData.home_id.toString(), "Home A");
        testData.put(ColumnData.UnitItemData.item_name.toString(), "Microwave");
        testData.put(ColumnData.UnitItemData.unit_type.toString(), "APPLIANCE");
        testData.put(ColumnData.UnitItemData.room_type.toString(), "KITCHEN");
        testData.put(ColumnData.UnitItemData.room_name.toString(), "Kitchen");
        testData.put(ColumnData.UnitItemData.install_date.toString(), "2021-01-01");
        testData.put(ColumnData.UnitItemData.maintained_date.toString(), "2022-01-01");
        testData.put(ColumnData.UnitItemData.maintenance_freq.toString(), "12");
        testData.put(ColumnData.UnitItemData.issue.toString(), "None");
        testData.put(ColumnData.UnitItemData.priority.toString(), "LOW");

        model.setData(testData);
    }

    @Test
    public void testSetNewHome() {
        model.setNewHome();
        Home newHome = model.getNewHome();
        assertEquals("Test Home", newHome.getHomeName());
        assertEquals("456 Oak Ave", newHome.getAddress());
        assertEquals("99999", newHome.getZip());
    }

    @Test
    public void testAddHomeIncrementsList() {
        model.setNewHome();
        model.addHome();
        assertEquals(2, model.getUser().getHomes().size());
    }

    @Test
    public void testSetNewUnitCreatesAppliance() {
        model.setNewUnit();
        IUnit unit = model.getNewUnit();
        assertNotNull(unit);
        assertEquals("Microwave", unit.getItemName());
        assertEquals(RoomType.KITCHEN, unit.getRoomType());
    }

    @Test
    public void testAddUnitIncrementsUnitsList() {
        model.setNewUnit();
        model.addUnit();
        List<IUnit> units = model.getUser().getHomes().get(0).getUnitItems();
        assertEquals(1, units.size());
        assertEquals("Microwave", units.get(0).getItemName());
    }

    @Test
    public void testGetHomeRowsReturnsFormattedData() {
        model.setNewHome();
        model.addHome();
        List<String[]> rows = model.getHomeRows();
        assertFalse(rows.isEmpty());
        assertEquals("Test Home", rows.get(1)[1]);
    }

    @Test
    public void testGetUnitsJList() {
        
        String[] jList = model.getUnitsJList();
        assertTrue(jList.length > 0);

    }

    @Test
    public void testFilterUnitsByRoomTypeReturnsCorrectUnits() {
        model.setNewUnit();
        model.addUnit();
        model.filterUnitsByRoomType("KITCHEN");
        List<IUnit> filtered = model.getFilteredUnits();
        assertEquals(1, filtered.size());
        assertEquals(RoomType.KITCHEN, filtered.get(0).getRoomType());
    }
}
