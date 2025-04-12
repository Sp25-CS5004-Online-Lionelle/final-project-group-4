import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import maintainhome.model.Home.ApplianceUnit;
import maintainhome.model.Home.ElectricUnit;
import maintainhome.model.Home.PlumbingUnit;
import maintainhome.model.Home.Types.PriorityType;
import maintainhome.model.Home.Types.RoomType;
import maintainhome.model.Home.Types.UnitType;

public class TestUnits {

    @Test
    public void testApplianceUnitGettersAndSetters() {
        // Initialize values
        String userId = "user123";
        String homeId = "home001";
        String unitId = "unit001";
        String itemName = "Refrigerator";
        UnitType unitType = UnitType.APPLIANCE;
        RoomType roomType = RoomType.KITCHEN;
        String roomName = "Kitchen";
        LocalDate installDate = LocalDate.of(2022, Month.JUNE, 1);
        LocalDate maintainedDate = LocalDate.of(2023, Month.JUNE, 1);
        int maintenanceFrequency = 12;
        String frequencyMeasure = "months";
        String issue = "None";
        PriorityType priority = PriorityType.IMPORTANT;
        int electricWatt = 800;
        int height = 180;
        int width = 80;
        int depth = 75;

        // Create an ApplianceUnit object
        ApplianceUnit unit = new ApplianceUnit(userId, homeId, unitId, itemName, unitType, roomType,
                                                roomName, installDate, maintainedDate, maintenanceFrequency,
                                                frequencyMeasure, issue, priority, electricWatt, height, width, depth);

        // Test getters
        assertEquals(userId, unit.getUserId());
        assertEquals(homeId, unit.getHomeId());
        assertEquals(unitId, unit.getUnitId());
        assertEquals(itemName, unit.getItemName());
        assertEquals(unitType, unit.getUnitType());
        assertEquals(roomType, unit.getRoomType());
        assertEquals(roomName, unit.getRoomName());
        assertEquals(installDate, unit.getInstallDate());
        assertEquals(maintainedDate, unit.getMaintainedDate());
        assertEquals(maintenanceFrequency, unit.getMaintenanceFrequency());
        assertEquals(frequencyMeasure, unit.getFrequencyMeasure());
        assertEquals(issue, unit.getIssue());
        assertEquals(priority, unit.getPriority());
        assertEquals(electricWatt, unit.getElectricWatt());

        // Test the dimension getter (calculated dynamically, not stored)
        assertEquals("180x80x75", unit.getDimension());

        // Test setters
        unit.setItemName("Washing Machine");
        unit.setRoomName("Laundry Room");
        unit.setPriority(PriorityType.URGENT);
        unit.setElectricWatt(1000);

        // Verify the updated values
        assertEquals("Washing Machine", unit.getItemName());
        assertEquals("Laundry Room", unit.getRoomName());
        assertEquals(PriorityType.URGENT, unit.getPriority());
        assertEquals(1000, unit.getElectricWatt());
        
        // Re-test dimension after changes (should still be based on height, width, depth)
        assertEquals("180x80x75", unit.getDimension());
    }

    @Test
    public void testElectricUnitGettersAndSetters() {
        // Test similar to the ApplianceUnit
        ElectricUnit unit = new ElectricUnit("user123", "home001", "unit002", "Microwave", UnitType.ELECTRIC_UNIT, 
                                             RoomType.KITCHEN, "Kitchen", LocalDate.of(2021, Month.DECEMBER, 1), 
                                             LocalDate.of(2022, Month.MARCH, 1), 6, "months", "None", 
                                             PriorityType.LOW, 1200);

        // Verify getters
        assertEquals("user123", unit.getUserId());
        assertEquals("unit002", unit.getUnitId());
        assertEquals("Microwave", unit.getItemName());
        assertEquals(UnitType.ELECTRIC_UNIT, unit.getUnitType());
        assertEquals(RoomType.KITCHEN, unit.getRoomType());
        assertEquals("Kitchen", unit.getRoomName());
        assertEquals(LocalDate.of(2021, Month.DECEMBER, 1), unit.getInstallDate());
        assertEquals(LocalDate.of(2022, Month.MARCH, 1), unit.getMaintainedDate());
        assertEquals(6, unit.getMaintenanceFrequency());
        assertEquals("months", unit.getFrequencyMeasure());
        assertEquals("None", unit.getIssue());
        assertEquals(PriorityType.LOW, unit.getPriority());
        assertEquals(1200, unit.getElectricWatt());

        // Test setter
        unit.setItemName("Toaster");
        assertEquals("Toaster", unit.getItemName());
    }

    @Test
    public void testPlumbingUnitGettersAndSetters() {
        // Initialize a PlumbingUnit
        PlumbingUnit unit = new PlumbingUnit("user123", "home001", "unit003", "Water Heater", UnitType.PLUMBING_UNIT, 
                                             RoomType.BATHROOM, "Bathroom", LocalDate.of(2020, Month.NOVEMBER, 10), 
                                             LocalDate.of(2021, Month.FEBRUARY, 10), 24, "months", "Leaking", 
                                             PriorityType.URGENT, 50);

        // Test getters
        assertEquals("user123", unit.getUserId());
        assertEquals("unit003", unit.getUnitId());
        assertEquals("Water Heater", unit.getItemName());
        assertEquals(UnitType.PLUMBING_UNIT, unit.getUnitType());
        assertEquals(RoomType.BATHROOM, unit.getRoomType());
        assertEquals("Bathroom", unit.getRoomName());
        assertEquals(LocalDate.of(2020, Month.NOVEMBER, 10), unit.getInstallDate());
        assertEquals(LocalDate.of(2021, Month.FEBRUARY, 10), unit.getMaintainedDate());
        assertEquals(24, unit.getMaintenanceFrequency());
        assertEquals("months", unit.getFrequencyMeasure());
        assertEquals("Leaking", unit.getIssue());
        assertEquals(PriorityType.URGENT, unit.getPriority());
        assertEquals(50, unit.getPlumbingGallon());

        // Set new values and test the setters
        unit.setItemName("Shower");
        unit.setPlumbingGallon(60);
        unit.setRoomName("Master Bathroom");

        assertEquals("Shower", unit.getItemName());
        assertEquals(60, unit.getPlumbingGallon());
        assertEquals("Master Bathroom", unit.getRoomName());
    }

    // Additional getter/setter tests for other properties can be added here.
}