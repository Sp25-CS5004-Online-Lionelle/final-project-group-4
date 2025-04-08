package maintainhome.model.Home;

import java.util.List;

/**
 * The Room class to store the user's units for maintenance and categorize by room.
 */
public class Room {
    private String roomName;
    private RoomType roomType;
    private List<IUnit> unitItems; // change datatype

    /**
     * Default Room Constructor.
     * @param roomName name of room input by user
     * @param roomType type of room
     */
    public Room(String roomName, RoomType roomType) {
        
        Room(roomName, roomType);
        
    }

    /**
     * Default Room Constructor.
     * @param roomName name of room input by user
     * @param roomType type of room
     * @param unitItems unit items for maintenance or to keep track of for maintenance
     */
    public Room(String roomName, RoomType roomType, List<IUnit> unitItems) {
        this.roomName = roomName;
        this.roomType = roomType;
        this.unitItems = unitItems;
    }

    /**
     * Adds unit item to the room.
     */
    public void addUnitItem(IUnit unitItem) {
        unitItems.add(unitItem);
    }

    /**
     * Sets the room name that is set by user.
     */
    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    /**
     * Gets the room name.
     * 
     * @return the nickname of the room
     */
    public String getRoomName() {
        return roomName;
    }

    /**
     * Sets the room type.
     */
    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    /**
     * Gets the room type.
     * 
     * @return the type of room
     */
    public String getRoomType() {
        return roomType;
    }



}
