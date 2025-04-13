package maintainhome.model.Home.UnitItems;

import java.time.LocalDate;

import maintainhome.model.Home.Types.PriorityType;
import maintainhome.model.Home.Types.RoomType;
import maintainhome.model.Home.Types.UnitType;

/**
 * Represents an appliance unit, such as a refrigerator or stove.
 */
public class ApplianceUnit extends AbstractUnit implements IElectricUnit {
    private int electricWatt;
    private Integer height;
    private Integer width;
    private Integer depth;

    /**
     * Constructs an ApplianceUnit with specified metadata and electrical/dimensional details.
     *
     * @param userId               the user ID who owns this appliance
     * @param homeId               the home ID this appliance belongs to
     * @param unitId               the unique identifier of this unit
     * @param itemName             the name of the appliance item
     * @param unitType             the type of unit (should be APPLIANCE)
     * @param roomType             the type of room the appliance is in
     * @param roomName             the specific room name
     * @param installDate          date the appliance was installed
     * @param maintainedDate       date the appliance was last maintained
     * @param maintenanceFrequency maintenance frequency (numeric)
     * @param frequencyMeasure     unit of measurement for frequency (e.g., "months")
     * @param issue                a description of any issue
     * @param priority             the maintenance priority level
     * @param electricWatt         the wattage consumed by the appliance
     * @param height               height of the appliance in inches (nullable)
     * @param width                width of the appliance in inches (nullable)
     * @param depth                depth of the appliance in inches (nullable)
     */
    public ApplianceUnit(String userId, String homeId, String unitId, String itemName, UnitType unitType,
                         RoomType roomType, String roomName, LocalDate installDate, LocalDate maintainedDate,
                         int maintenanceFrequency, String frequencyMeasure, String issue, PriorityType priority,
                         int electricWatt, int height, int width, int depth) {
        super(userId, homeId, unitId, itemName, unitType, roomType, roomName, installDate, maintainedDate,
              maintenanceFrequency, frequencyMeasure, issue, priority);
        this.electricWatt = electricWatt;
        this.height = height;
        this.width = width;
        this.depth = depth;
    }

    /**
     * Gets the specific {@link UnitType} of this unit.
     *
     * @return {@link UnitType#APPLIANCE}, always
     */
    @Override
    public UnitType getUnitType() {
        return UnitType.APPLIANCE;
    }

    /**
     * Returns a string representing the dimensions of the appliance.
     * Format: "heightxwidthxdepth".
     *
     * @return the formatted dimension string
     */
    public String getDimension() {
        return height.toString() + "x" + width.toString() + "x" + depth.toString();
    }

    /**
     * Gets the electric wattage of the appliance.
     *
     * @return the wattage in watts
     */
    @Override
    public int getElectricWatt() {
        return electricWatt;
    }

    /**
     * Sets the electric wattage of the appliance.
     *
     * @param electricWatt the new wattage value
     */
    public void setElectricWatt(int electricWatt) {
        this.electricWatt = electricWatt;
    }
}
