package maintainhome.model.Home.Types;


/** A list of priorite types allowed for the issues. */
public enum PriorityType {
    /** Different room type options. */
    URGENT(1), IMPORTANT(2)
    , LOW(3);

    /** The priority type. */
    private int type;

    /**
     * Constructor for the priority type.
     * 
     * @param format The priority type.
     */
    PriorityType(int type) {
        this.type = type;
    }

    /**
     * Get the priority type.
     * 
     * @return The priority type.
     */
    public int getPriorityType() {
        return type;
    }

    /**
     * Get the priority type from the int priority.
     * 
     * @param type The priority type.
     * @return The priority type.
     */
    public static PriorityType toPriorityType(int prioriytType) {
        for (PriorityType type : PriorityType.values()) {
            if (type.getPriorityType() == prioriytType) {
                return type;
            }
        }
        throw new IllegalArgumentException("No priority type with name " + prioriytType);
    }

    /**
     * Helper function to check if a value is in the list of priority types.
     * 
     * @param value the value to check
     * @return the priority type if found, null otherwise
     */
    
    public static PriorityType containsValues(String value) {
        for (PriorityType type : PriorityType.values()) {
            if (type.toString().equalsIgnoreCase(value)) {
                return type;
            }
        }
        return null;
    }
    
}
