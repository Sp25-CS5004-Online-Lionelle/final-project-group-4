package maintainhome.model.Home;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

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
    private int unitId;
    private String itemName;
    private String mapKeyword;
    private Date installDate;
    private Date maintainedDate;
    private int maintenanceFrequency;
    private String frequencyMeasure;
    private String roomLocation;

    public AbstractUnit(int unitId, String mapKeyword, String itemName, Date installDate, Date maintainedDate,
            int maintenanceFrequency, String frequencyMeasure, String roomLocation) {
        this.unitId = unitId;
        this.mapKeyword = mapKeyword;
        this.itemName = itemName;
        this.installDate = installDate;
        this.maintainedDate = maintainedDate;
        this.maintenanceFrequency = maintenanceFrequency;
        this.frequencyMeasure = frequencyMeasure;
        this.roomLocation = roomLocation;
    }


    public int getHomeId() {
        return unitId;
    }

    public void setHomeId(int unitId) {
        this.unitId = unitId;
    }

    public String getMapKeyword() {
        return mapKeyword;
    }

    public void setMapKeyword(String mapKeyword) {
        this.mapKeyword = mapKeyword;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Date getInstallDate() {
        return installDate;
    }

    public void setInstallDate(Date installDate) {
        this.installDate = installDate;
    }

    public Date getMaintainedDate() {
        return maintainedDate;
    }

    public void setMaintainedDate(Date maintainedDate) {
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

    public String getRoomLocation() {
        return roomLocation;
    }

    public void setRoomLocation(String roomLocation) {
        this.roomLocation = roomLocation;
    }

    public Date getDateFromString(String strDate) {
        Date date = null;
        try {
            date = new SimpleDateFormat().parse(strDate);
        } catch(ParseException e) {
            System.out.println(e);
        }
        return date;
    }

}