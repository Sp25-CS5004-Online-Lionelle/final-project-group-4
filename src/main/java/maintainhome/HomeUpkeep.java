package maintainhome;

import maintainhome.controller.Controller;
import maintainhome.model.Model;
import maintainhome.view.IView;
import maintainhome.view.View;

/**
 * Main driver for the program.
 */
public final class HomeUpkeep {

    /** Private constructor to prevent instantiation. */
    private HomeUpkeep() {
        // empty
    }

    /**
     * Main entry point for the program.
     * 
     * @param args the command line arguments.
     */
    public static void main(String[] args) {
        Model model = new Model();
        IView view = new View("Home Maintenance App");
        Controller controller = new Controller(model, view);
        
    }
}
