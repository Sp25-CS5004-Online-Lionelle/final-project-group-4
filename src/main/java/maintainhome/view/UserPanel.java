package maintainhome.view;

import maintainhome.model.Utilities.Types.ColumnData;

import javax.swing.*;
import java.awt.*;

/**
 * The User Panel to display and update user information.
 */
public class UserPanel extends JPanel {
    /** The user's id display text coming from the file */
    private JLabel usernameDisplay;
    /** The user's name display text coming from the file */
    private JLabel nameDisplay;
    /** The user's email display text coming from the file */
    private JLabel emailDisplay;

    /** The user's name input field for update */
    private JTextField nameText = new JTextField("", 15);
    /** The user's email input field for update */
    private JTextField emailText = new JTextField("", 15);
    /** The update button that will update the user information when clicked */
    private JButton update = new JButton("Update");
    
    /**
     * Default UserPanel Constructor.
     * @param id user's id from file
     * @param name user's name from file
     * @param email user's emailfrom file
     */
    public UserPanel(String id,String name, String email) {
        setLayout(new BorderLayout());
        
        JPanel topPane = setTopPane(id, name, email);
        JPanel bottomPane = setBottomPane();
        JSplitPane splitPane = new JSplitPane(SwingConstants.HORIZONTAL, topPane, bottomPane);

        add(splitPane, BorderLayout.CENTER);
    }

    /**
     * Sets the top pane of the split pane with the display only of the user's information
     * @param id user's id from file
     * @param name user's name from file
     * @param email user's emailfrom file
     */
    private JPanel setTopPane(String id, String name, String email) {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        
        gbc.anchor = GridBagConstraints.EAST;
        // username label
        JLabel usernameLabel = new JLabel(ColumnData.UserData.user_id.getColumnName()+":");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 0, 5, 0);
        panel.add(usernameLabel, gbc);

        // name label
        JLabel nameLabel = new JLabel(ColumnData.UserData.name.getColumnName()+":");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(nameLabel, gbc);

        // email label
        JLabel emailLabel = new JLabel(ColumnData.UserData.email.getColumnName()+":");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(emailLabel, gbc);

        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 0);
        usernameDisplay = new JLabel(id);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(usernameDisplay, gbc);

        nameDisplay = new JLabel(name);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(nameDisplay, gbc);

        emailDisplay = new JLabel(email);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(emailDisplay, gbc);
        

        return panel;
    }

    /**
     * Sets the bottom pane of the split pane to allow the user to update their information.
     */
    public JPanel setBottomPane() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        // username label
        /*
        JLabel usernameLabel = new JLabel(ColumnData.UserData.user_id.getColumnName()+":");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 0, 10, 0);
        panel.add(usernameLabel, gbc);

        //passText.setPreferredSize(new Dimension(15, 22));
        //passInput.setMinimumSize(passInput.getPreferredSize());
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 0);
        panel.add(usernameText, gbc);
        */

        // name label
        JLabel nameLabel = new JLabel(ColumnData.UserData.name.getColumnName()+":");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 0, 10, 0);
        panel.add(nameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 0);
        panel.add(nameText, gbc);

        // email label
        JLabel emailLabel = new JLabel(ColumnData.UserData.email.getColumnName()+":");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 0, 10, 0);
        panel.add(emailLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 10, 10, 0);
        panel.add(emailText, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.insets = new Insets(10, 0, 10, 0);
        panel.add(update, gbc);

        return panel;
    }
}
