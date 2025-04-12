package maintainhome.view;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {
    private GridBagConstraints gbc = new GridBagConstraints();
    private JLabel userLabel = new JLabel("Username:");
    private JTextField userInput = new JTextField("", 15);
    private JLabel passLabel = new JLabel("Password:");
    private JPasswordField passInput = new JPasswordField("", 15);
    private JButton loginBtn = new JButton("Login");
    private int inset20 = 20;

    public LoginPanel() {
        super(new GridBagLayout());
        gbc.anchor = GridBagConstraints.CENTER;
        setUserLabel();
        setUserInput();
        setPassLabel();
        setPassInput();
        setLoginBtn();
    }

    public JPanel getLoginPanel() {
        return this;
    }

    public JButton getLoginBtn() {
        return loginBtn;
    }

    private void setUserLabel() {
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 0, 5, inset20);
        add(userLabel, gbc);
    }

    private void setUserInput() {
        userInput.setPreferredSize(new Dimension(15, 22));
        userInput.setMinimumSize(userInput.getPreferredSize());
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 0, 5, 0);
        add(userInput, gbc);
    }

    private void setPassLabel() {
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(5, 0, 5, inset20);
        add(passLabel, gbc);
    }

    private void setPassInput() {
        passInput.setPreferredSize(new Dimension(15, 22));
        passInput.setMinimumSize(passInput.getPreferredSize());
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(5, 0, 5, 0);
        add(passInput, gbc);
    }

    private void setLoginBtn() {
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.insets = new Insets(5, 0, 0, 0);
        add(loginBtn, gbc);
    }

}
