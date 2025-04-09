package maintainhome.model.Home;


/** A list of room type types allowed for the rooms. */
public enum UnitType {
    /** Different room type options. */
    ELECTRIC_UNIT("Electrical Unit"), PLUMBING_UNIT("Plumbing Unit"), APPLIANCE("Appliance");

    /** The room type. */
    private String unitType;

    /**
     * Constructor for the room type.
     * 
     * @param format The room type.
     */
    UnitType(String unitType) {
        this.unitType = unitType;
    }

    /**
     * Get the room type.
     * 
     * @return The room type.
     */
    public String getUnitType() {
        return unitType;
    }

    /**
     * Get the room type from the string room type.
     * 
     * @param unitType The room type.
     * @return The room type.
     */
    public static UnitType toUnitType(String unitType) {
        for (UnitType type : UnitType.values()) {
            if (type.getUnitType().equalsIgnoreCase(unitType)) {
                return type;
            }
        }
        throw new IllegalArgumentException("No room type with name " + unitType);
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
