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
        

        /*
        String sampleUser = "js1";
        model.setUser(sampleUser);
        User user = model.getUser();
        user.setHomes(CsvLoader.loadHomesFile(user.getUserId()));
        
        try {
            h1 = this.user.getHomes().get(0);
            System.out.println(h1.getAddress());
        } catch(Exception e) {
            // NullPointerException
            System.out.println("User has no homes listed.");
        }
        

        //testing the filter and sort functionality
        //List<IUnit> allUnits = CsvLoader.loadUnitItemsFile(user.getUserId(), user.getHomes().get(0).getHomeId());
        
        List<IUnit> units = CsvLoader.loadUnitItemsFile(user.getUserId(), user.getHomes().get(0).getHomeId());
        
        for (IUnit unit : units) {
            System.out.println(unit.getItemName());
        }
        
        List<IUnit> filtered = UnitFilters.filterByRoom(units, RoomType.KITCHEN.getRoomType());
        filtered.sort(UnitSorters.BY_INSTALL_DATE);

        filtered.forEach(System.out::println);

        // Save the filtered units to a CSV file
        String outputPath = "filtered_units.csv";
        CsvSaver.saveUnitsToCsv(filtered, outputPath);
        */
    }
}
