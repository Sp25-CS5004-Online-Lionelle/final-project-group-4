package maintainhome.model.Home.UnitItems;

import java.time.LocalDate;

import maintainhome.model.Home.Types.PriorityType;
import maintainhome.model.Home.Types.RoomType;
import maintainhome.model.Home.Types.UnitType;


public abstract class AbstractUnit implements IUnit {
    private String userId;
    private String homeId;
    private String unitId;
    private String itemName;
    private UnitType unitType;
    private RoomType roomType;
    private String roomName;
    private LocalDate installDate;
    private LocalDate maintainedDate;
    private int maintenanceFrequency;
    private String frequencyMeasure;
    private String issue;
    private PriorityType priority;


    public AbstractUnit(String userId, String homeId, String unitId, String itemName, UnitType unitType, RoomType roomType, String roomName, LocalDate installDate, LocalDate maintainedDate,
            int maintenanceFrequency, String frequencyMeasure, String issue, PriorityType priority) {
        this.userId = userId;
        this.homeId = homeId;
        this.unitId = unitId;
        this.itemName = itemName;
        this.unitType = unitType;
        this.roomName = roomName;
        this.roomType = roomType;
        this.installDate = installDate;
        this.maintainedDate = maintainedDate;
        this.maintenanceFrequency = maintenanceFrequency;
        this.frequencyMeasure = frequencyMeasure;
        this.issue = issue;
        this.priority = priority;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getHomeId() {
        return homeId;
    }

    public void setHomeId(String homeId) {
        this.homeId = homeId;
    }

    @Override
    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    @Override
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public UnitType getUnitType() {
        return unitType;
    }

    public void setUnitType(UnitType unitType) {
        this.unitType = unitType;
    }
    
    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    @Override
    public LocalDate getInstallDate() {
        return installDate;
    }

    public void setInstallDate(LocalDate installDate) {
        this.installDate = installDate;
    }

    public LocalDate getMaintainedDate() {
        return maintainedDate;
    }

    public void setMaintainedDate(LocalDate maintainedDate) {
        this.maintainedDate = maintainedDate;
    }

    public int getMaintenanceFrequency() {
        return maintenanceFrequency;
    }

    public void setMaintenanceFrequency(int maintenanceFrequency) {
        this.maintenanceFrequency = maintenanceFrequency;
    }

    public String getFrequencyMeasure() {
        return frequencyMeasure;
    }
    public void setFrequencyMeasure(String frequencyMeasure) {
        this.frequencyMeasure = frequencyMeasure;
    }

    public String getIssue() {
        return issue;
    }
    public void setIssue(String issue) {
        this.issue = issue;
    }

    public PriorityType getPriority() {
        return priority;
    }
    public void setPriority(PriorityType priority) {
        this.priority = priority;
    }

    /**
     * Converts the unit item to a CSV string.
     * 
     * @return the unit item as a CSV string
     */
    
    @Override
    public String toCSV() {
        return getUnitId() + "," + getItemName() + "," + getRoomType().toString() + "," + getRoomName()
             + "," + IUnit.dateToString(getInstallDate()) + "," + IUnit.dateToString(getMaintainedDate()) + "," + getMaintenanceFrequency()
             + "," + getMaintenanceFrequency() + "," + getFrequencyMeasure();
    }

    @Override
    public String toString() {
        return String.format("Unit: %s, Room: %s, Item: %s, Install Date: %s, Maintained Date: %s, Priority: %s",
                         getUnitId(), getRoomType(), getItemName(), getInstallDate(), getMaintainedDate(), getPriority());
    }

}