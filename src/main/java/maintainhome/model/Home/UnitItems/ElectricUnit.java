package maintainhome.model.Home.UnitItems;

import java.time.LocalDate;

import maintainhome.model.Home.Types.PriorityType;
import maintainhome.model.Home.Types.RoomType;
import maintainhome.model.Home.Types.UnitType;

/**
 * Represents an electrical unit in the home, such as a microwave, light fixture, or fan.
 */
public class ElectricUnit extends AbstractUnit implements IElectricUnit {

    /** Wattage consumed by the electric unit. */
    private int electricWatt;

    /** Number of wires used by the electric unit (optional). */
    private int wireCount;

    /** Whether the unit includes a battery (optional). */
    private boolean hasBattery;

    /**
     * Constructs an Electric Unit.
     *
     * @param userId               the user ID who owns this unit
     * @param homeId               the home ID this unit belongs to
     * @param unitId               the unique ID of the electric unit
     * @param itemName             the name of the electric unit
     * @param unitType             the unit type (typically {@code UnitType.ELECTRIC_UNIT})
     * @param roomType             the category of room the unit is in
     * @param roomName             specific name of the room
     * @param installDate          installation date of the unit
     * @param maintainedDate       date the unit was last maintained
     * @param maintenanceFrequency numeric frequency of maintenance
     * @param frequencyMeasure     measurement unit for frequency (e.g., "months")
     * @param issue                a description of any current issue
     * @param priority             the maintenance priority of the unit
     * @param electricWatt         wattage rating of the electric unit
     */
    public ElectricUnit(String userId, String homeId, String unitId, String itemName, UnitType unitType,
                        RoomType roomType, String roomName, LocalDate installDate, LocalDate maintainedDate,
                        int maintenanceFrequency, String frequencyMeasure, String issue, PriorityType priority,
                        int electricWatt) {
        super(userId, homeId, unitId, itemName, unitType, roomType, roomName, installDate,
              maintainedDate, maintenanceFrequency, frequencyMeasure, issue, priority);
        this.electricWatt = electricWatt;
    }

    /**
     * Returns the type of this unit.
     *
     * @return ElectricUnit
     */
    @Override
    public UnitType getUnitType() {
        return UnitType.ELECTRIC_UNIT;
    }

    /**
     * Returns the wattage used by this electric unit.
     *
     * @return the wattage in watts
     */
    @Override
    public int getElectricWatt() {
        return electricWatt;
    }

    /**
     * Sets the wattage of this electric unit.
     *
     * @param electricWatt the new wattage value
     */
    public void setElectricWatt(int electricWatt) {
        this.electricWatt = electricWatt;
    }

    // You can add Javadoc for the wireCount and hasBattery fields/setters/getters
    // if you plan to expose or use them later.
}