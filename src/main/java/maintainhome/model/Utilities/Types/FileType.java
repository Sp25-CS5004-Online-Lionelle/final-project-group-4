package maintainhome.model.Utilities.Types;

public enum FileType {
    /**
     * Enums matching CODE(filenames) pattern.
     * 
     */
    USER("user.csv"), HOMES("homes.csv"),
    USER_HOMES("user_homes.csv"),
    /** Enums that are based on double values in the csv file. */
    UNIT_ITEMS("unit_items.csv");

    /** stores the original csv name in the enum. */
    private final String fileName;

    /**
     * Constructor for the enum.
     * 
     * @param fileName the file name of the file type.
     */
    FileType(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Getter for the column name.
     * 
     * @return the file name of the file type.
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Get the enum from the file name.
     * 
     * @param fileName the name of the file type.
     * @return the enum that matches the file name.
     */
    public static FileType fromFileName(String fileName) {
        for (FileType type : FileType.values()) {
            if (type.getFileName().equals(fileName)) {
                return type;
            }
        }
        throw new IllegalArgumentException("No column with name " + fileName);
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
    public static FileType fromString(String name) {
        for (FileType type : FileType.values()) {
            if (type.name().equalsIgnoreCase(name) || type.getFileName().equalsIgnoreCase(name)) {
                return type;
            }
        }
        throw new IllegalArgumentException("No column with name " + name);
    }
}
