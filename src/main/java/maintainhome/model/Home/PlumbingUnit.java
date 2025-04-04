package maintainhome.model.Home;

import java.util.Date;

public class PlumbingUnit extends AbstractUnit{
    private int plumbingGallon;

    String unitType = "PlumbingUnit";

    public String getUnitType() {
        return unitType;
    }

    public PlumbingUnit(int unitId, String category, String itemName, Date installDate, Date maintainedDate,
    int standardLifeSpan, String lifeSpanMeasure, String roomLocation, int plumbingGallon) {
    super(unitId, category, itemName, installDate, maintainedDate, standardLifeSpan, lifeSpanMeasure, roomLocation);
    this.plumbingGallon = plumbingGallon;
}
    
    public int getPlumbingGallon() {
        return plumbingGallon;
    }
    
}
