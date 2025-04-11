package maintainhome;

import maintainhome.controller.Controller;
import maintainhome.model.Model;
import maintainhome.view.IView;
import maintainhome.view.View;

import maintainhome.model.Home.IUnit;
import maintainhome.model.Utilities.CsvLoader;
import maintainhome.model.User.User;
import maintainhome.model.UnitFilters;
import maintainhome.model.UnitSorters;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

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
        
        Set<User> user = CsvLoader.loadFile("user.csv");
        List<User> userList = new ArrayList<>(user);
        System.out.println(userList.get(0).getuserId());

        //testing the filter and sort functionality
        List<IUnit> allUnits = CsvLoader.loadUnits("/sampleemilio.csv");

        List<IUnit> filtered = UnitFilters.filterByRoom(allUnits, "Kitchen");
        filtered.sort(UnitSorters.BY_INSTALL_DATE);

        filtered.forEach(System.out::println);
        

    }
}
