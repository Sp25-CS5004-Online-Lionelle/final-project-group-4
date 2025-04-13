package maintainhome.model.Home.UnitItems;

import java.time.LocalDate;
import maintainhome.model.Home.Types.PriorityType;
import maintainhome.model.Home.Types.RoomType;
import maintainhome.model.Home.Types.UnitType;

/*
 * + electricWatt : int

+ ElectricalUnit()

+ getUnitType() : : String
 * 
 * 
 * 
 * 
 */

public class ElectricUnit extends AbstractUnit implements IElectricUnit {
    
    private int electricWatt;
    private int wireCount;
    private boolean hasBattery;
    private UnitType unitType;

    public ElectricUnit(String userId, String homeId, String unitId, String itemName, UnitType unitType, RoomType roomType, String roomName, LocalDate installDate, LocalDate maintainedDate,
    int maintenanceFrequency, String frequencyMeasure, String issue, PriorityType priority, int electricWatt) {
        super(userId, homeId, unitId, itemName, unitType, roomType, roomName, installDate, maintainedDate, maintenanceFrequency, frequencyMeasure, issue, priority);
        this.electricWatt = electricWatt;
    }

    
    public UnitType getUnitType() {
        return UnitType.ELECTRIC_UNIT;
    }

    @Override
    public int getElectricWatt() {
        return electricWatt;
    }

    public void setElectricWatt(int electricWatt) {
        this.electricWatt = electricWatt;
    }


}

