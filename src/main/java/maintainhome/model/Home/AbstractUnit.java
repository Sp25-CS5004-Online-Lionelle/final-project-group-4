package maintainhome.model.Home;

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

public abstract class AbstractUnit {
    private int homeId;
    private String category;
    private String itemName;
    private String installDate;
    private String maintainedDate;
    private int standardLifeSpan;
    private String lifeSpanMeasure;
    private String roomLocation;

    public AbstractUnit(int homeId, String category, String itemName, String installDate, String maintainedDate,
            int standardLifeSpan, String lifeSpanMeasure, String roomLocation) {
        this.homeId = homeId;
        this.category = category;
        this.itemName = itemName;
        this.installDate = installDate;
        this.maintainedDate = maintainedDate;
        this.standardLifeSpan = standardLifeSpan;
        this.lifeSpanMeasure = lifeSpanMeasure;
        this.roomLocation = roomLocation;
    }


    public int getHomeId() {
        return homeId;
    }

    public void setHomeId(int homeId) {
        this.homeId = homeId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getInstallDate() {
        return installDate;
    }

    public void setInstallDate(String installDate) {
        this.installDate = installDate;
    }

    public String getMaintainedDate() {
        return maintainedDate;
    }

    public void setMaintainedDate(String maintainedDate) {
        this.maintainedDate = maintainedDate;
    }

    public int getStandardLifeSpan() {
        return standardLifeSpan;
    }

    public void setStandardLifeSpan(int standardLifeSpan) {
        this.standardLifeSpan = standardLifeSpan;
    }

    public String getLifeSpanMeasure() {
        return lifeSpanMeasure;
    }
    public void setLifeSpanMeasure(String lifeSpanMeasure) {
        this.lifeSpanMeasure = lifeSpanMeasure;
    }

    public String getRoomLocation() {
        return roomLocation;
    }

    public void setRoomLocation(String roomLocation) {
        this.roomLocation = roomLocation;
    }

}