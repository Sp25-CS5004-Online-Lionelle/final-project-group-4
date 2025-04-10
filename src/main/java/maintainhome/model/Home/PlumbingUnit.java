package maintainhome.model.Home;

import java.time.LocalDate;

public class PlumbingUnit extends AbstractUnit implements IUnit, IPlumbingUnit {
    private int plumbingGallon;
    private boolean hasFaucet;
    private boolean hasDrain;
    private int pipeCount;
    private UnitType unitType;

    public PlumbingUnit(String unitId, String itemName, UnitType unitType, RoomType roomType, String roomName, LocalDate installDate, LocalDate maintainedDate,
    int maintenanceFrequency, String frequencyMeasure, int plumbingGallon) {
        super(unitId, itemName, unitType, roomType, roomName, installDate, maintainedDate, maintenanceFrequency, frequencyMeasure);
        this.plumbingGallon = plumbingGallon;
    }

    @Override
    public UnitType getUnitType() {
        return UnitType.PLUMBING_UNIT;
    }

    @Override
    public int getPlumbingGallon() {
        return plumbingGallon;
    }

    public void setPlumbingGallon(int plumbingGallon) {
        this.plumbingGallon = plumbingGallon;
    }



    
}
