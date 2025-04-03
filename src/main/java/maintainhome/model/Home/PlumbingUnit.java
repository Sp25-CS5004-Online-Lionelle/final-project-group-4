package maintainhome.model.Home;

public class PlumbingUnit extends AbstractUnit{
    private int plumbingGallon;

    String unitType = "PlumbingUnit";

    public String getUnitType() {
        return unitType;
    }

    public PlumbingUnit(int homeId, String category, String itemName, String installDate, String maintainedDate,
    int standardLifeSpan, String lifeSpanMeasure, String roomLocation, int plumbingGallon) {
    super(homeId, category, itemName, installDate, maintainedDate, standardLifeSpan, lifeSpanMeasure, roomLocation);
    this.plumbingGallon = plumbingGallon;
}
    
    public int getPlumbingGallon() {
        return plumbingGallon;
    }
    
}
