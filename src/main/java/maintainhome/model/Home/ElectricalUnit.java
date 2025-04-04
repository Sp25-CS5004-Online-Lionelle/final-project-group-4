package maintainhome.model.Home;

import java.util.Date;

/*
 * + electricWatt : int

+ ElectricalUnit()

+ getUnitType() : : String
 * 
 * 
 * 
 * 
 */

public class ElectricalUnit extends AbstractUnit {
    
    private int electricWatt;

    String unitType = "ElectricalUnit";

    public String getUnitType() {
        return unitType;
    }

    public ElectricalUnit(int unitId, String category, String itemName, Date installDate, Date maintainedDate,
        int standardLifeSpan, String lifeSpanMeasure, String roomLocation, int electricWatt) {
        super(unitId, category, itemName, installDate, maintainedDate, standardLifeSpan, lifeSpanMeasure, roomLocation);
        this.electricWatt = electricWatt;
    }

    public int getElectricWatt() {
        return electricWatt;
    }

}

