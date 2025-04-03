package maintainhome.model.Home;

public class ApplianceUnit extends AbstractUnit {
    private int electricWatt;
    private String unitType = "ApplianceUnit";

    public String getUnitType() {
        return unitType;
    }

    public ApplianceUnit(int homeId, String category, String itemName, String installDate, String maintainedDate,
            int standardLifeSpan, String lifeSpanMeasure, String roomLocation, int electricWatt) {
            super(homeId, category, itemName, installDate, maintainedDate, standardLifeSpan, lifeSpanMeasure, roomLocation);
            this.electricWatt = electricWatt;
        }
        
    
    }
    
    