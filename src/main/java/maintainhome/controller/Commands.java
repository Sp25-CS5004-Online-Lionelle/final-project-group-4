package maintainhome.controller;


/** A list of command types allowed for the contoller. */
public enum Commands {
    /** Different command options. */
    loginButton("Login"), userButton("User"), homesButton("Homes"), unitsButton("Units");

    /** The command. */
    private final String command;

    /**
     * Constructor for the commands.
     * 
     * @param format The command.
     */
    Commands(String command) {
        this.command = command;
    }

    /**
     * Get the command.
     * 
     * @return The command.
     */
    public String getCommand() {
        return command;
    }

    /**
     * Get the command from the string command.
     * 
     * @param command The command.
     * @return The command.
     */
    public static Commands toCommand(String command) {
        for (Commands op : Commands.values()) {
            if (op.getCommand().equalsIgnoreCase(command)) {
                return op;
            }
        }
        throw new IllegalArgumentException("No command with name " + command);
    }

    /**
     * Helper function to check if a value is in the list of commands.
     * 
     * @param value the value to check
     * @return the command if found, null otherwise
     */
    /*
    public static Commands containsValues(String value) {
        for (Commands command : Commands.values()) {
            if (command.toString().equalsIgnoreCase(value)) {
                return command;
            }
        }
        return null;
    }
    */
}
