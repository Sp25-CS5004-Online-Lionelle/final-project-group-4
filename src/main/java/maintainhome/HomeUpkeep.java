package maintainhome;

import maintainhome.controller.Controller;
import maintainhome.model.Model;
import maintainhome.view.IView;
import maintainhome.view.View;
import maintainhome.model.Home.Home;
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
        
        User user = null;
        try {
            user = CsvLoader.loadUserFile("js5");
            System.out.println(user.getUserId());
        } catch(Exception e) {
            System.out.println(e);
            System.out.println("No User Found.");
        }
        
         try {
             List<Home> homes = CsvLoader.loadHomesFile(user.getUserId());
             user.setHomes(homes);
         } catch(Exception e) {
             // throw new NullPointerException("User has no homes");
             System.out.println(e);
         }
        
        try {
            Home h1 = user.getHomes().get(0);
            System.out.println(h1.getAddress());
        } catch(Exception e) {
            System.out.println("User has no homes listed.");
        }

        /*
        List<IUnit> units = CsvLoader.loadUnitItemsFile();
        System.out.println(units.get(1).getInstallDate());

        
        //testing the filter and sort functionality
        //List<IUnit> allUnits = CsvLoader.loadUnits("/sampleemilio.csv");

        List<IUnit> filtered = UnitFilters.filterByRoom(units, "Kitchen");
        filtered.sort(UnitSorters.BY_INSTALL_DATE);

        filtered.forEach(System.out::println);
        */

    }
}
