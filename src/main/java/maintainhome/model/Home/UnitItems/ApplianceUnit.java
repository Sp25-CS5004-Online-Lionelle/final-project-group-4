package maintainhome.model.Home.UnitItems;

import java.time.LocalDate;
import maintainhome.model.Home.Types.PriorityType;
import maintainhome.model.Home.Types.RoomType;
import maintainhome.model.Home.Types.UnitType;

public class ApplianceUnit extends AbstractUnit implements IElectricUnit {
    private int electricWatt;
    private Integer height;
    private Integer width;
    private Integer depth;

    public ApplianceUnit(String userId, String homeId, String unitId, String itemName, UnitType unitType, RoomType roomType, String roomName, LocalDate installDate, LocalDate maintainedDate,
        int maintenanceFrequency, String frequencyMeasure, String issue, PriorityType priority, int electricWatt, int height, int width, int depth) {
        super(userId, homeId, unitId, itemName, unitType, roomType, roomName, installDate, maintainedDate, maintenanceFrequency, frequencyMeasure, issue, priority);
        this.electricWatt = electricWatt;
        this.height = height;
        this.width = width;
        this.depth = depth;
    }

    @Override
    public UnitType getUnitType() {
        return UnitType.APPLIANCE;
    }

    public String getDimension() {
        return height.toString() + "x" + width.toString() + "x" + depth.toString();
    }

    @Override
    public int getElectricWatt() {
        return electricWatt;
    }

    public void setElectricWatt(int electricWatt) {
        this.electricWatt = electricWatt;
    }

}

