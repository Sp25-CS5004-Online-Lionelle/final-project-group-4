package maintainhome.model.Home;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/* 
 * 
 * - homeId : int

- category : String

- itemName : String

- installDate : String

- maintainedDate : String

- standardLifeSpan : int

- lifeSpanMeasure : String

- roomLocation : String

+ AbstractUnit()

+ setRoomLocation() : :void
 * 
*/

public abstract class AbstractUnit implements IUnit {
    private int unitId;
    private String itemName;
    private RoomType roomType;
    private String roomName;
    private LocalDate installDate;
    private LocalDate maintainedDate;
    private int maintenanceFrequency;
    private String frequencyMeasure;
    /*
    private String currentIssue;
    private String issueKeyword;
    */


    public AbstractUnit(int unitId, String itemName, RoomType roomType, String roomName, LocalDate installDate, LocalDate maintainedDate,
            int maintenanceFrequency, String frequencyMeasure) {
        this.unitId = unitId;
        this.itemName = itemName;
        this.roomName = roomName;
        this.roomType = roomType;
        this.installDate = installDate;
        this.maintainedDate = maintainedDate;
        this.maintenanceFrequency = maintenanceFrequency;
        this.frequencyMeasure = frequencyMeasure;
    }

    @Override
    public int getUnitId() {
        return unitId;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }
    @Override
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
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

    /*
    private String issuePriority = ;
    public String getIssueKeyword() {
        return issueKeyword;
    }

    public void setIssueKeyword(String issueKeyword) {
        this.issueKeyword = issueKeyword;
    }
    */  
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
}