package maintainhome;

import maintainhome.controller.Controller;
import maintainhome.model.Model;
import maintainhome.model.Home.IUnit;
import maintainhome.view.IView;
import maintainhome.view.View;
import maintainhome.model.Home.IUnit;
import maintainhome.model.UnitFilters;
import maintainhome.model.UnitSorters;
import maintainhome.model.CsvLoader;
import java.util.List;

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

        //testing the filter and sort functionality
        List<IUnit> allUnits = CsvLoader.loadUnits("/sampleemilio.csv");

        List<IUnit> filtered = UnitFilters.filterByRoom(allUnits, "Kitchen");
        filtered.sort(UnitSorters.BY_INSTALL_DATE);

        filtered.forEach(System.out::println);
    }
}
