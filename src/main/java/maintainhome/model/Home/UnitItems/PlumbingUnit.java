package maintainhome.model.Home.UnitItems;

import java.time.LocalDate;
import maintainhome.model.Home.Types.PriorityType;
import maintainhome.model.Home.Types.RoomType;
import maintainhome.model.Home.Types.UnitType;

public class PlumbingUnit extends AbstractUnit implements IPlumbingUnit {
    private int plumbingGallon;
    private boolean hasFaucet;
    private boolean hasDrain;
    private int pipeCount;
    private UnitType unitType;

    public PlumbingUnit(String userId, String homeId, String unitId, String itemName, UnitType unitType, RoomType roomType, String roomName, LocalDate installDate, LocalDate maintainedDate,
    int maintenanceFrequency, String frequencyMeasure, String issue, PriorityType priority, int plumbingGallon) {
        super(userId, homeId, unitId, itemName, unitType, roomType, roomName, installDate, maintainedDate, maintenanceFrequency, frequencyMeasure, issue, priority);
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
