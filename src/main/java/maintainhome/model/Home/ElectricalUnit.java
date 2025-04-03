package maintainhome.model.Home;

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

    public ElectricalUnit(int homeId, String category, String itemName, String installDate, String maintainedDate,
        int standardLifeSpan, String lifeSpanMeasure, String roomLocation, int electricWatt) {
        super(homeId, category, itemName, installDate, maintainedDate, standardLifeSpan, lifeSpanMeasure, roomLocation);
        this.electricWatt = electricWatt;
    }

    public int getElectricWatt() {
        return electricWatt;
    }

}

