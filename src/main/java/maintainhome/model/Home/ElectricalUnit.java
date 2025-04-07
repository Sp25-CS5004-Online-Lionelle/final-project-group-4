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

public class ElectricalUnit extends AbstractUnit implements IUnit {
    
    private int electricWatt;

    String unitType = "ElectricalUnit";

    public String getUnitType() {
        return unitType;
    }

    public ElectricalUnit(int unitId, String category, String itemName, LocalDate installDate, LocalDate maintainedDate,
        int standardLifeSpan, String lifeSpanMeasure, String roomLocation, int electricWatt) {
        super(unitId, category, itemName, installDate, maintainedDate, standardLifeSpan, lifeSpanMeasure, roomLocation);
        this.electricWatt = electricWatt;
    }

    public int getElectricWatt() {
        return electricWatt;
    }

    public void setElectricWatt(int electricWatt) {
        this.electricWatt = electricWatt;
    }

    public String getRoomLocation() {
        return super.getRoomLocation();
    }

    public LocalDate getInstallDate() {
        return super.getInstallDate();
    }



}

