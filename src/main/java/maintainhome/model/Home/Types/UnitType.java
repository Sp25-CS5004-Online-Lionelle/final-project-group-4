package maintainhome.model.Home.Types;


/** A list of the type of units of an item. */
public enum UnitType {
    /** Different unit type options. */
    ELECTRIC_UNIT("Electric Unit"), PLUMBING_UNIT("Plumbing Unit")
    , APPLIANCE("Appliance"); // , HVAC("HVAC Unit");

    /** The unit type. */
    private String unitType;

    /**
     * Constructor for the unit type.
     * 
     * @param format The unit type.
     */
    UnitType(String unitType) {
        this.unitType = unitType;
    }

    /**
     * Get the unit type.
     * 
     * @return The unit type.
     */
    public String getUnitType() {
        return unitType;
    }

    /**
     * Get the unit type from the string unit type.
     * 
     * @param unitType The unit type.
     * @return The unit type.
     */
    public static UnitType toUnitType(String unitType) {
        for (UnitType type : UnitType.values()) {
            if (type.getUnitType().equalsIgnoreCase(unitType)) {
                return type;
            }
        }
        throw new IllegalArgumentException("No unit type with name " + unitType);
    }

    /**
     * Helper function to check if a value is in the list of unit types.
     * 
     * @param value the value to check
     * @return the unit type if found, null otherwise
     */
    
    public static UnitType containsValues(String value) {
        for (UnitType type : UnitType.values()) {
            if (type.toString().equalsIgnoreCase(value)) {
                return type;
            }
        }
        return null;
    }
    
}
