package maintainhome.controller;
import maintainhome.view.IView;
import maintainhome.model.Model;
import maintainhome.model.Home.Home;
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

    // need to set 
    
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (Commands.toCommand(e.getActionCommand())) {
            case Commands.loginButton:
                String sampleUser = "js1";
                model.setUser(sampleUser);
                
                try {
                    List<Home> homes = CsvLoader.loadHomesFile(model.getUser().getUserId());
                    model.getUser().setHomes(homes);
                } catch(Exception err) {
                    // throw new NullPointerException("User has no homes");
                    System.out.println("User has no homes listed yet.");
                }
                view.switchMainPanel("3");
                break;
            case Commands.homesButton:
                view.switchRightPanel("3");
                
                break;
            case Commands.fileOpen:
                break;
            case Commands.fileSave:
                break;
            case Commands.fileExit:
                System.exit(0);
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
