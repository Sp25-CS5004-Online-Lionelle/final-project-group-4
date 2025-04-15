package maintainhome.model.Home.UnitItems;

import java.time.LocalDate;

import maintainhome.model.Home.Types.PriorityType;
import maintainhome.model.Home.Types.RoomType;
import maintainhome.model.Home.Types.UnitType;

/**
 * Represents a plumbing unit in the home, such as a toilet, sink, or shower.
 */
public class PlumbingUnit extends AbstractUnit implements IPlumbingUnit {

    /** The water capacity of the plumbing unit in gallons. */
    private int plumbingGallon;

    /** Whether the unit has a faucet (optional). */
    private boolean hasFaucet;

    /** Whether the unit has a drain (optional). */
    private boolean hasDrain;

    /** Number of pipes connected to the unit (optional). */
    private int pipeCount;

    /**
     * Constructs a Plumbing Unit with core unit and plumbing-specific data.
     *
     * @param userId               the user ID who owns this plumbing unit
     * @param homeId               the home ID this unit belongs to
     * @param unitId               the unique identifier of the unit
     * @param itemName             the name of the plumbing item (e.g., "Toilet")
     * @param unitType             the unit type (typically {@link UnitType#PLUMBING_UNIT})
     * @param roomType             the type of room the unit is located in
     * @param roomName             the specific room name
     * @param installDate          date the unit was installed
     * @param maintainedDate       date the unit was last maintained
     * @param maintenanceFrequency frequency of maintenance (e.g., every 6 months)
     * @param frequencyMeasure     measurement unit for frequency (e.g., "months")
     * @param issue                description of any issues with the unit
     * @param priority             priority of maintenance
     * @param plumbingGallon       water capacity of the unit in gallons
     */
    public PlumbingUnit(String userId, String homeId, String unitId, String itemName, UnitType unitType,
                        RoomType roomType, String roomName, LocalDate installDate, LocalDate maintainedDate,
                        int maintenanceFrequency, String frequencyMeasure, String issue, PriorityType priority,
                        int plumbingGallon) {
        super(userId, homeId, unitId, itemName, unitType, roomType, roomName, installDate,
              maintainedDate, maintenanceFrequency, frequencyMeasure, issue, priority);
        this.plumbingGallon = plumbingGallon;
    }

    /**
     * Returns the type of this unit.
     *
     * @return PlumbingUnit
     */
    @Override
    public UnitType getUnitType() {
        return UnitType.PLUMBING_UNIT;
    }

    /**
     * Returns the water capacity of the unit in gallons.
     *
     * @return the capacity in gallons
     */
    @Override
    public int getPlumbingGallon() {
        return plumbingGallon;
    }

    /**
     * Sets the water capacity of the plumbing unit.
     *
     * @param plumbingGallon the new capacity in gallons
     */
    public void setPlumbingGallon(int plumbingGallon) {
        this.plumbingGallon = plumbingGallon;
    }
}
