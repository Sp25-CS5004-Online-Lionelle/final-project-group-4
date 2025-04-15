package maintainhome.controller;
import maintainhome.view.IView;
import maintainhome.model.Model;
import maintainhome.model.Home.Home;
import maintainhome.model.Home.Types.RoomType;
import maintainhome.model.User.User;
import maintainhome.model.Home.UnitItems.IUnit;
import maintainhome.model.Utilities.CsvLoader;
import maintainhome.model.Utilities.CsvUpdater;
import maintainhome.model.Utilities.Types.ColumnData;
import maintainhome.model.Utilities.Types.FileType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

/**
 * A controller to manage incoming requests from the view and processed outgoing data from the model.
 */
public class Controller implements ActionListener, KeyListener {
    /** Model of the application. */
    private Model model;
    /** View of the application. */
    private IView view;

    /**
     * Default arg controller Constructor.
     * @param m the Model - default DomainNameModel object.
     * @param v the GUI view object.
     */
    public Controller(Model m, IView v) {
        view = v;
        model = m;
        v.setListener(this, this);
    }

    private void setUserHomes() {
        model.setUserHomes();
        User user = model.getUser();
        // User Panel
        view.setUserPanel(user.getUserId(), user.getName(), user.getEmail());
        // Homes Panel
        view.updateHomesList(user.getHomeJList());
        view.updateHomesTable(user.getHomeRows());
    }

    private void setViewUnits() {
        
        model.setUserItems();
        String[] jList = model.getUnitsJList();

        view.updateUnitsList(jList);
        view.updateUnitsTable(model.getUnitRows());
    }


    private void loginClicked() {
        //try {
        setUserHomes();
        setViewUnits();
        /*
        } catch(NullPointerException err) {
            throw new NullPointerException("No User Found");
        }
        */
    }

    // need to set 
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String commandText = e.getActionCommand();
        Commands command = Commands.toCommand(commandText);
        switch (command) {
            case Commands.loginButton:

                String userString = view.getLoginUser();
                model.setUser(userString);
                loginClicked();

                view.switchMainPanel("3");
                break;
                
            case Commands.userButton:
                
                view.setSelectedCard(command);
                view.switchRightPanel(commandText);
                break;
            case Commands.homesButton:
                
                view.setSelectedCard(command);
                view.switchRightPanel(commandText);
                
                break;
            case Commands.unitsButton:

                view.setSelectedCard(command);
                view.switchRightPanel(commandText);
                break;
            case Commands.clearButton:
                view.clearAddFields();
            case Commands.insertButton:
                model.setData(view.getAddValues());
                Commands panel = view.getSelectedCard();
                switch(panel) {
                    case Commands.homesButton:
                        model.setNewHome();
                        model.addHome(model.getNewHome());
                        model.saveHome();
                        view.updateHomesList(model.getUser().getHomeJList());
                        view.updateHomesTable(model.getUser().getHomeRows());
                        view.getHomesTab().setSelectedIndex(0);
                        break;
                    case Commands.unitsButton:
                        // need to implement saving out of units
                        
                        view.updateUnitsList(model.getUser().getHomeJList());
                        view.updateUnitsTable(model.getUser().getHomeRows());
                        view.getUnitsTab().setSelectedIndex(0);
                        break;
                    default:
                    }
                
                view.clearAddFields();
            default:
        }
        //view.resetFocus();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_ENTER:
                
                break;
            }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_ENTER:
                
                break;
            }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_ENTER:
                
                break;
            }
    }
    
}
