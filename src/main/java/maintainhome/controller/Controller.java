package maintainhome.controller;
import maintainhome.view.IView;
import maintainhome.model.Model;
import maintainhome.model.Home.Home;
import maintainhome.model.User.User;
import maintainhome.model.Utilities.CsvLoader;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

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

    private void loginClicked() {
        User user = model.getUser();
        //try {
            List<Home> homes = CsvLoader.loadHomesFile(user.getUserId());
            model.getUser().setHomes(homes);
            // need to see if setting the JList here works otherwise need to figure out how to update the JList data
            view.setUserPanel(user.getUserId(), user.getName(), user.getEmail());
            view.updateHomesList(user.getHomeNames());
            view.updateHomesTable(user.getHomeRows());
            view.switchMainPanel("3");
        /*
        } catch(NullPointerException err) {
            throw new NullPointerException("No User Found");
        }
        */
    }

    // need to set 
    
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (Commands.toCommand(e.getActionCommand())) {
            case Commands.loginButton:

                String userString = view.getLoginUser();
                model.setUser(userString);
                loginClicked();
                break;
                
            case Commands.userButton:
                view.switchRightPanel("3");
                break;
            case Commands.homesButton:
                view.switchRightPanel("2");
                
                break;
            case Commands.unitsButton:
                break;
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
