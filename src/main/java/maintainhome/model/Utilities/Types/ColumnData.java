package maintainhome.model.Utilities.Types;

public class ColumnData { // https://www.geekster.in/articles/enum-class-in-java/
    
    private ColumnData() {

    }

    public enum UserData implements IColumnEnum {
        // user columns
        user_id("Username"), name("Name"), email("Email");
        
        /** stores the original csv name in the enum. */
        private final String columnName;

        /**
         * Constructor for the enum.
         * 
         * @param columnName the name of the column in the CSV file.
         */
        UserData(String columnName) {
            this.columnName = columnName;
        }

        @Override
        public String getColumnName() {
            return columnName;
        }
    }

    public enum HomeData implements IColumnEnum {
        // home columns
        home_id("Home ID"), home_num("Home Row")
        , home_name("Home Name"), address("Address"), zip("Zip");
        
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

        @Override
        public String getColumnName() {
            return columnName;
        }
    }

    public enum UnitItemData implements IColumnEnum {
        user_id("User ID"), home_id("Home ID")
        // unit item columns
        , unit_id("Unit ID"), item_name("Unit Name")
        , unit_type("Unit Type"), room_type("Room Type")
        , room_name("Room Name"), install_date("Install Date")
        , maintained_date("Last Maintained Date"), maintenance_freq("Maintenance Frequency")
        , frequency_meas("Frequency Measure"), issue("Issue"), priority("Priority");
        
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

        @Override
        public String getColumnName() {
            return columnName;
        }
    }

    public enum UserHomeData implements IColumnEnum {
        // unit item columns
        user_id("User ID"), home_id("Home ID");
        
        /** stores the original csv name in the enum. */
        private final String columnName;

        /**
         * Constructor for the enum.
         * 
         * @param columnName the name of the column in the CSV file.
         */
        UserHomeData(String columnName) {
            this.columnName = columnName;
        }

        @Override
        public String getColumnName() {
            return columnName;
        }
    }

    /**
     * Get the enum from the column name.
     * 
     * @param columnName the name of the column in the CSV file.
     * @return the enum that matches the column name.
     */
    public static IColumnEnum fromColumnName(String columnName, FileType source) {
        
        switch(source) {
            case USER:
                for (IColumnEnum col : ColumnData.UserData.values()) {
                    if (col.getColumnName().equals(columnName)) {
                        return col;
                    }
                }
                fromColumnNameException(columnName);
            case HOMES:
                for (IColumnEnum col : ColumnData.HomeData.values()) {
                    if (col.getColumnName().equals(columnName)) {
                        return col;
                    }
                }
                fromColumnNameException(columnName);
            case UNIT_ITEMS:
                for (IColumnEnum col : ColumnData.UnitItemData.values()) {
                    if (col.getColumnName().equals(columnName)) {
                        return col;
                    }
                }
                fromColumnNameException(columnName);
            case USER_HOMES:
                for (IColumnEnum col : ColumnData.UserHomeData.values()) {
                    if (col.getColumnName().equals(columnName)) {
                        return col;
                    }
                }
                fromColumnNameException(columnName);
            default:
                return null;
        }
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
    public static IColumnEnum fromString(String name, FileType source) {

        switch(source) {
            case USER:
                for (UserData col : UserData.values()) {
                    if (col.name().equalsIgnoreCase(name) || col.getColumnName().equalsIgnoreCase(name)) {
                        return col;
                    }
                }
                fromStringException(name);
            case HOMES:
                for (HomeData col : HomeData.values()) {
                    if (col.name().equalsIgnoreCase(name) || col.getColumnName().equalsIgnoreCase(name)) {
                        return col;
                    }
                }
                fromStringException(name);
            case UNIT_ITEMS:
                for (UnitItemData col : UnitItemData.values()) {
                    if (col.name().equalsIgnoreCase(name) || col.getColumnName().equalsIgnoreCase(name)) {
                        return col;
                    }
                }
                fromStringException(name);
            case USER_HOMES:
                for (UserHomeData col : UserHomeData.values()) {
                    if (col.name().equalsIgnoreCase(name) || col.getColumnName().equalsIgnoreCase(name)) {
                        return col;
                    }
                }
                fromStringException(name);
            default:
                return null;
            }
    }

    private static IllegalArgumentException fromColumnNameException(String columnName) {
        throw new IllegalArgumentException("No column with name " + columnName);
    }

    private static IllegalArgumentException fromStringException(String name) {
        throw new IllegalArgumentException("No column with name " + name);
    }

}
