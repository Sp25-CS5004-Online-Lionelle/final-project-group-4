package maintainhome.controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import maintainhome.model.Model;
import maintainhome.model.Utilities.CsvUpdater;
import maintainhome.model.Utilities.Types.FileType;
import maintainhome.view.IView;

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
        // User Panel
        view.setUserPanel(model.getUser().getUserId(), model.getUser().getName(), model.getUser().getEmail());
        // Homes Panel
        view.updateHomesList(model.getHomeJList());
        view.updateHomesTable(model.getHomeRows());
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
            
                String selectedRoom = view.getRoomTypeSelected();
                if (selectedRoom != null && !selectedRoom.isEmpty()) {
                    model.filterUnitsByRoomType(selectedRoom);
                    view.updateUnitsTable(model.getUnitRows(model.getFilteredUnits()));
                } else {
                    // If no room is selected, show all
                    model.setFilteredUnits(new ArrayList<>());
                    view.updateUnitsTable(model.getUnitRows());
                }
                break;
            
            case Commands.resetFilter:

                model.setFilteredUnits(new ArrayList<>());  
                view.updateUnitsTable(model.getUnitRows()); 
            break;
            
            case Commands.updateUser:
                System.out.println("Update User command received.");

                String newName = view.getUpdatedUserName();
                String newEmail = view.getUpdatedUserEmail();

                if (!newName.isEmpty() && !newEmail.isEmpty()) {
                    model.getUser().setName(newName);
                    model.getUser().setEmail(newEmail);
                    CsvUpdater.updateRewriteCsvFile(FileType.USER, model.getUser());
                    view.refreshUserPanelLabels();  // ← this will call the method above from the view
                    JOptionPane.showMessageDialog(null, "User updated and saved to CSV.");
                } else {
                    JOptionPane.showMessageDialog(null, "Name or email cannot be empty.");
                }
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
                        view.updateHomesList(model.getHomeJList());
                        view.updateHomesTable(model.getHomeRows());
                        view.getHomesTab().setSelectedIndex(0);
                        break;
                    case Commands.unitsButton:
                        // need to implement saving out of units
                        
                        view.updateUnitsList(model.getHomeJList());
                        view.updateUnitsTable(model.getUnitRows());
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
