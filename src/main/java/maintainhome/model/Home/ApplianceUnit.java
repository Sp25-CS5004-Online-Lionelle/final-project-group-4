package maintainhome.model.Home;

import java.time.LocalDate;

public class ApplianceUnit extends AbstractUnit implements IUnit {
    private int electricWatt;
    private String unitType = "ApplianceUnit";

    public String getUnitType() {
        return unitType;
    }

    public ApplianceUnit(int homeId, String category, String itemName, LocalDate installDate, LocalDate maintainedDate,
            int standardLifeSpan, String lifeSpanMeasure, String roomLocation, int electricWatt) {
            super(homeId, category, itemName, installDate, maintainedDate, standardLifeSpan, lifeSpanMeasure, roomLocation);
            this.electricWatt = electricWatt;
        }

    public int getElectricWatt() {
        return electricWatt;
    }

    public void setElectricWatt(int electricWatt) {
        this.electricWatt = electricWatt;
    }

    public String getRoomLocation() {
        return super.getRoomLocation();
    }

    public LocalDate getInstallDate() {
        return super.getInstallDate();
    }
}

