package maintainhome.model.Utilities;

public enum UnitItemData {
    /**
     * Enums matching CODE(cvsname) pattern.
     * 
     * name and id are used for unit uniqueness.
     */
    NAME("item_name"), ID("id")
    /** Enums that are based on double values in the csv file. */
    , UNIT_TYPE("unit_type"), ROOM_TYPE("room_type")
    , ROOM_NAME("room_name"), INSTALL_DATE("install_date")
    , MAINTAINED_DATE("maintained_date"), MAINTAIN_FREQ("maintenance_frequency")
    , FREQ_MEAS("frequency_meas");

    /** stores the original csv name in the enum. */
    private final String columnName;

    /**
     * Constructor for the enum.
     * 
     * @param columnName the name of the column in the CSV file.
     */
    UnitItemData(String columnName) {
        this.columnName = columnName;
    }

    /**
     * Getter for the column name.
     * 
     * @return the name of the column in the CSV file.
     */
    public String getColumnName() {
        return columnName;
    }

    /**
     * Get the enum from the column name.
     * 
     * @param columnName the name of the column in the CSV file.
     * @return the enum that matches the column name.
     */
    public static UnitItemData fromColumnName(String columnName) {
        for (UnitItemData col : UnitItemData.values()) {
            if (col.getColumnName().equals(columnName)) {
                return col;
            }
        }
        throw new IllegalArgumentException("No column with name " + columnName);
    }

    /**
     * Get the enum from the enum name.
     * 
     * Can use the enum name or the column name. Useful for filters and sorts
     * as they can use both.
     * 
     * @param name the name of the enum.
     * @return the enum that matches the name.
     */
    public static UnitItemData fromString(String name) {
        for (UnitItemData col : UnitItemData.values()) {
            if (col.name().equalsIgnoreCase(name) || col.getColumnName().equalsIgnoreCase(name)) {
                return col;
            }
        }
        throw new IllegalArgumentException("No column with name " + name);
    }
}
