package maintainhome.model.Utilities.Types;

public enum HomeData {
    /**
     * Enums matching CODE(cvsname) pattern.
     * 
     * name and id are used for user uniqueness.
     */
    ADDRESS("address"), ID("user_id"),
    /** Enums that are based on double values in the csv file. */
    ZIP("zip");

    /** stores the original csv name in the enum. */
    private final String columnName;

    /**
     * Constructor for the enum.
     * 
     * @param columnName the name of the column in the CSV file.
     */
    HomeData(String columnName) {
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
    public static HomeData fromColumnName(String columnName) {
        for (HomeData col : HomeData.values()) {
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
    
    public static HomeData fromString(String name) {
        for (HomeData col : HomeData.values()) {
            if (col.name().equalsIgnoreCase(name) || col.getColumnName().equalsIgnoreCase(name)) {
                return col;
            }
        }
        throw new IllegalArgumentException("No column with name " + name);
    }
}
