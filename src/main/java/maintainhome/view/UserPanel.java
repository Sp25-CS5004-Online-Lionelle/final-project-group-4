package maintainhome.view;

import java.awt.*;
import javax.swing.*;
import maintainhome.model.Utilities.Types.ColumnData;

public class UserPanel extends JPanel {

    private JLabel usernameDisplay;
    private JLabel nameDisplay;
    private JLabel emailDisplay;

    //private JTextField usernameText = new JTextField("", 15);
    private JTextField nameText = new JTextField("", 15);
    private JTextField emailText = new JTextField("", 15);
    private JButton update = new JButton("Update");

    
    public String getEnteredName() {
        return nameText.getText().trim();
    }
    
    public String getEnteredEmail() {
        return emailText.getText().trim();
    }

    public void refreshDisplayLabels() {
        nameDisplay.setText(getEnteredName());
        emailDisplay.setText(getEnteredEmail());
    }

    public JButton getUpdateButton() {
        return update;
    }
    
    
    public UserPanel(String id,String name, String email) {
        setLayout(new BorderLayout());
        
        JPanel topPane = setTopPane(id, name, email);
        JPanel bottomPane = setBottomPane();
        JSplitPane splitPane = new JSplitPane(SwingConstants.HORIZONTAL, topPane, bottomPane);

        add(splitPane, BorderLayout.CENTER);
    }

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
        update.setActionCommand("Update User"); 


        return panel;
    }
}
