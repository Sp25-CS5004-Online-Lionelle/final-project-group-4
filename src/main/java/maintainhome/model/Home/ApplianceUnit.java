package maintainhome.model.Home;

import java.time.LocalDate;

public class ApplianceUnit extends AbstractUnit implements IElectricUnit {
    private int electricWatt;

    public ApplianceUnit(int unitId, String itemName, RoomType roomType, String roomName, LocalDate installDate, LocalDate maintainedDate,
        int maintenanceFrequency, String frequencyMeasure, int electricWatt) {
        super(unitId, itemName, roomType, roomName, installDate, maintainedDate, maintenanceFrequency, frequencyMeasure);
        this.electricWatt = electricWatt;
    }

    @Override
    public UnitType getUnitType() {
        return UnitType.APPLIANCE;
    }

    @Override
    public int getElectricWatt() {
        return electricWatt;
    }

    public void setElectricWatt(int electricWatt) {
        this.electricWatt = electricWatt;
    }

}

