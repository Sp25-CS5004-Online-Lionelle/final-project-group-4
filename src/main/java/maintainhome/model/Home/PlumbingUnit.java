package maintainhome.model.Home;

import java.time.LocalDate;

public class PlumbingUnit extends AbstractUnit implements IUnit {
    private int plumbingGallon;

    String unitType = "PlumbingUnit";

    public String getUnitType() {
        return unitType;
    }

    public PlumbingUnit(int unitId, String category, String itemName, LocalDate installDate, LocalDate maintainedDate,
    int standardLifeSpan, String lifeSpanMeasure, String roomLocation, int plumbingGallon) {
    super(unitId, category, itemName, installDate, maintainedDate, standardLifeSpan, lifeSpanMeasure, roomLocation);
    this.plumbingGallon = plumbingGallon;
}
    
    public int getPlumbingGallon() {
        return plumbingGallon;
    }

    public void setPlumbingGallon(int plumbingGallon) {
        this.plumbingGallon = plumbingGallon;
    }

    public String getRoomLocation() {
        return super.getRoomLocation();
    }

    public LocalDate getInstallDate() {
        return super.getInstallDate();
    }


    
}
