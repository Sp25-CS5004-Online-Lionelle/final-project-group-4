package maintainhome.model.Utilities.Types;

public enum ColumnData {
    /**
     * Enums matching CODE(cvsname) pattern.
     * 
     * name and id are used for column uniqueness.
     */
    USER_ID("user_id"), HOME_ID("home_id"), UNIT_ID("unit_id")
    // user columns
    , NAME("name"), EMAIL("email")
    // home columns
    , ADDRESS("address"), ZIP("zip")
    // unit item columns
    , ITEM_NAME("item_name")
    , UNIT_TYPE("unit_type"), ROOM_TYPE("room_type")
    , ROOM_NAME("room_name"), INSTALL_DATE("install_date")
    , MAINTAINED_DATE("maintained_date"), MAINTAIN_FREQ("maintenance_freq")
    , FREQ_MEAS("frequency_meas"), ISSUE("issue"), PRIORITY("priority");

    /** stores the original csv name in the enum. */
    private final String columnName;

    /**
     * Constructor for the enum.
     * 
     * @param columnName the name of the column in the CSV file.
     */
    ColumnData(String columnName) {
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
    public static ColumnData fromColumnName(String columnName) {
        for (ColumnData col : ColumnData.values()) {
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
    public static ColumnData fromString(String name) {
        for (ColumnData col : ColumnData.values()) {
            if (col.name().equalsIgnoreCase(name) || col.getColumnName().equalsIgnoreCase(name)) {
                return col;
            }
        }
        throw new IllegalArgumentException("No column with name " + name);
    }
}
