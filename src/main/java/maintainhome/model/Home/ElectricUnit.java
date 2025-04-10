package maintainhome.model.Home;

import java.time.LocalDate;

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

    public ElectricUnit(String unitId, String itemName, UnitType unitType, RoomType roomType, String roomName, LocalDate installDate, LocalDate maintainedDate,
    int maintenanceFrequency, String frequencyMeasure, int electricWatt) {
        super(unitId, itemName, unitType, roomType, roomName, installDate, maintainedDate, maintenanceFrequency, frequencyMeasure);
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

