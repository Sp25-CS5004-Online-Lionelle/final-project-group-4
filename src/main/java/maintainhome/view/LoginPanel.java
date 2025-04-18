package maintainhome.view;

import javax.swing.*;
import java.awt.*;

/**
 * The Login Panel to retrieve user login information and allow user to access the application along with their stored information.
 */
public class LoginPanel extends JPanel {
    /** This panel's Grid Bag Layout constraints */
    private GridBagConstraints gbc = new GridBagConstraints();
    /** Username label */
    private JLabel userLabel = new JLabel("Username:");
    /** The text input field for user's username */
    private JTextField userInput = new JTextField("js1", 15);
    /** Password label */
    private JLabel passLabel = new JLabel("Password:");
    /** The text input field for user's password */
    private JPasswordField passInput = new JPasswordField("password", 15);
    /** The login button that when clicked will verify the information and allow user access */
    private JButton loginBtn = new JButton("Login");
    /** General Grid Bag Layout inset of 20 */
    private int inset20 = 20;

    /**
     * Default View Constructor.
     * 
     * @param caption for the Frame title display.
     */
    public LoginPanel() {
        super(new GridBagLayout());
        gbc.anchor = GridBagConstraints.CENTER;
        setUserLabel();
        setUserInput();
        setPassLabel();
        setPassInput();
        setLoginBtn();
    }

    /**
     * Gets the user input text.
     * 
     * @return user input text
     */
    public String getUserInput() {
        return userInput.getText();
    }

    /**
     * Gets the Login button.
     * 
     * @return the Login button
     */
    public JButton getLoginBtn() {
        return loginBtn;
    }
    
    /**
     * Gets this Login panel.
     * 
     * @return this Login panel
     */
    public JPanel getLoginPanel() {
        return this;
    }

    
    /**
     * Places the user label of username on this panel.
     */
    private void setUserLabel() {
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 0, 5, inset20);
        add(userLabel, gbc);
    }

    /**
     * Places the user input field of username on this panel.
     */
    private void setUserInput() {
        userInput.setPreferredSize(new Dimension(15, 22));
        userInput.setMinimumSize(userInput.getPreferredSize());
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 0, 5, 0);
        add(userInput, gbc);
    }

    /**
     * Places the password label of username on this panel.
     */
    private void setPassLabel() {
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(5, 0, 5, inset20);
        add(passLabel, gbc);
    }

    /**
     * Places the password input field of username on this panel.
     */
    private void setPassInput() {
        passInput.setPreferredSize(new Dimension(15, 22));
        passInput.setMinimumSize(passInput.getPreferredSize());
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(5, 0, 5, 0);
        add(passInput, gbc);
    }

    /**
     * Places the login button on this panel.
     */
    private void setLoginBtn() {
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.insets = new Insets(5, 0, 0, 0);
        add(loginBtn, gbc);
    }

}
