package maintainhome.model.Home.UnitItems;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import maintainhome.model.Home.Types.PriorityType;
import maintainhome.model.Home.Types.RoomType;
import maintainhome.model.Home.Types.UnitType;

/**
 * Abstract base class for all unit items in a home.
 * <p>
 * Stores common attributes such as unit ID, user and home associations,
 * installation and maintenance data, room information, and priority.
 * Provides getters and setters for these fields, along with CSV and
 * string representations.
 * </p>
 */
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

    /**
     * Constructs an AbstractUnit with all common unit item fields.
     *
     * @param userId               ID of the user who owns the unit
     * @param homeId               ID of the home containing the unit
     * @param unitId               ID of the unit itself
     * @param itemName             Name of the item
     * @param unitType             Type of unit (e.g., Appliance, Plumbing)
     * @param roomType             Type of room (e.g., Kitchen, Bathroom)
     * @param roomName             Specific name of the room
     * @param installDate          Date the unit was installed
     * @param maintainedDate       Date the unit was last maintained
     * @param maintenanceFrequency Frequency of maintenance (numeric)
     * @param frequencyMeasure     Unit of measure for frequency (e.g., "months")
     * @param issue                Description of any current issue
     * @param priority             Priority of maintenance
     */
    public AbstractUnit(String userId, String homeId, String unitId, String itemName, UnitType unitType,
                        RoomType roomType, String roomName, LocalDate installDate, LocalDate maintainedDate,
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

    /** @return the ID of the user who owns this unit */
    public String getUserId() {
        return userId;
    }

    /** @param userId the ID of the user to set */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /** @return the ID of the home this unit belongs to */
    public String getHomeId() {
        return homeId;
    }

    /** @param homeId the ID of the home to set */
    public void setHomeId(String homeId) {
        this.homeId = homeId;
    }

    /** @return the unit's unique identifier */
    @Override
    public String getUnitId() {
        return unitId;
    }

    /** @param unitId the unit ID to set */
    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    /** @return the name of the item */
    @Override
    public String getItemName() {
        return itemName;
    }

    /** @param itemName the name of the item to set */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /** @return the unit type (e.g., Appliance, Electrical) */
    public UnitType getUnitType() {
        return unitType;
    }

    /** @param unitType the unit type to set */
    public void setUnitType(UnitType unitType) {
        this.unitType = unitType;
    }

    /** @return the room type where this unit is located */
    public RoomType getRoomType() {
        return roomType;
    }

    /** @param roomType the room type to set */
    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    /** @return the specific room name */
    public String getRoomName() {
        return roomName;
    }

    /** @param roomName the room name to set */
    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    /** @return the installation date of the unit */
    @Override
    public LocalDate getInstallDate() {
        return installDate;
    }

    /** @param installDate the installation date to set */
    public void setInstallDate(LocalDate installDate) {
        this.installDate = installDate;
    }

    /** @return the date the unit was last maintained */
    public LocalDate getMaintainedDate() {
        return maintainedDate;
    }

    /** @param maintainedDate the last maintenance date to set */
    public void setMaintainedDate(LocalDate maintainedDate) {
        this.maintainedDate = maintainedDate;
    }

    /** @return the numeric maintenance frequency */
    public int getMaintenanceFrequency() {
        return maintenanceFrequency;
    }

    /** @param maintenanceFrequency the frequency value to set */
    public void setMaintenanceFrequency(int maintenanceFrequency) {
        this.maintenanceFrequency = maintenanceFrequency;
    }

    /** @return the frequency measure (e.g., "months", "years") */
    public String getFrequencyMeasure() {
        return frequencyMeasure;
    }

    /** @param frequencyMeasure the frequency measure to set */
    public void setFrequencyMeasure(String frequencyMeasure) {
        this.frequencyMeasure = frequencyMeasure;
    }

    /** @return description of any current issue with the unit */
    public String getIssue() {
        return issue;
    }

    /** @param issue the issue description to set */
    public void setIssue(String issue) {
        this.issue = issue;
    }

    /** @return the priority of maintenance for the unit */
    public PriorityType getPriority() {
        return priority;
    }

    /** @param priority the maintenance priority to set */
    public void setPriority(PriorityType priority) {
        this.priority = priority;
    }

    public String[] getUnitRow() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
        return new String[] {getUnitId(), getItemName(), getUnitType().getUnitType(),
                getRoomName(), getInstallDate().format(formatter), getMaintainedDate().format(formatter),
                Integer.toString(getMaintenanceFrequency()) + " " + getFrequencyMeasure(),
                getIssue(),
                getPriority().toString()};
    }

    /**
     * Returns a formatted string representation of this unit item.
     *
     * @return a human-readable description of the unit
     */
    @Override
    public String toString() {
        return String.format("Unit: %s, Room: %s, Item: %s, Install Date: %s, Maintained Date: %s, Priority: %s",
                             getUnitId(), getRoomType(), getItemName(), getInstallDate(), getMaintainedDate(), getPriority());
    }
} 