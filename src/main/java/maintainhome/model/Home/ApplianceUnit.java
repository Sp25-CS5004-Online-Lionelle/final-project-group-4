package maintainhome.model.Home;

import java.time.LocalDate;

public class ApplianceUnit extends AbstractUnit implements IElectricUnit {
    private int electricWatt;
    private Integer height;
    private Integer width;
    private Integer depth;

    public ApplianceUnit(String unitId, String itemName, UnitType unitType, RoomType roomType, String roomName, LocalDate installDate, LocalDate maintainedDate,
        int maintenanceFrequency, String frequencyMeasure, int electricWatt, int height, int width, int depth) {
        super(unitId, itemName, unitType, roomType, roomName, installDate, maintainedDate, maintenanceFrequency, frequencyMeasure);
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

