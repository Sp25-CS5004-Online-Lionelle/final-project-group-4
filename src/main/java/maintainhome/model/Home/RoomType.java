package maintainhome.model.Home;


/** A list of room type types allowed for the rooms. */
public enum RoomType {
    /** Different room type options. */
    KITCHEN("Kitchen"), LIVING_ROOM("Living Room"), DINING_ROOM("Dining Room")
    , BEDROOM("Bedroom"), BATHROOM("Bathroom");

    /** The room type. */
    private final String roomType;

    /**
     * Constructor for the room type.
     * 
     * @param format The room type.
     */
    RoomType(String roomType) {
        this.roomType = roomType;
    }

    /**
     * Get the room type.
     * 
     * @return The room type.
     */
    public String getRoomType() {
        return roomType;
    }

    /**
     * Get the room type from the string room type.
     * 
     * @param roomType The room type.
     * @return The room type.
     */
    public static RoomType toRoomType(String roomType) {
        for (RoomType type : RoomType.values()) {
            if (type.getRoomType().equalsIgnoreCase(roomType)) {
                return type;
            }
        }
        throw new IllegalArgumentException("No command with name " + roomType);
    }

    /**
     * Helper function to check if a value is in the list of room types.
     * 
     * @param value the value to check
     * @return the room type if found, null otherwise
     */
    /*
    public static RoomType containsValues(String value) {
        for (RoomType roomType : RoomType.values()) {
            if (roomType.toString().equalsIgnoreCase(value)) {
                return roomType;
            }
        }
        return null;
    }
    */
}
