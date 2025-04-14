package maintainhome.view;

import maintainhome.model.Utilities.Types.ColumnData;

import javax.swing.*;
import java.awt.*;

public class UserPanel extends JPanel {

    private JLabel usernameDisplay;
    private JLabel nameDisplay;
    private JLabel emailDisplay;

    private JTextField usernameText = new JTextField("", 15);;
    private JTextField nameText = new JTextField("", 15);;
    private JTextField emailText = new JTextField("", 15);;
    private JButton update = new JButton("Update");
    
    
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
        // username label
        JLabel usernameLabel = new JLabel(ColumnData.UserData.user_id.getColumnName()+":");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 0, 5, 0);
        panel.add(usernameLabel, gbc);

        usernameDisplay = new JLabel(id, JLabel.LEFT);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(usernameDisplay, gbc);

        // name label
        JLabel nameLabel = new JLabel(ColumnData.UserData.name.getColumnName()+":");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(nameLabel, gbc);

        nameDisplay = new JLabel(name, JLabel.LEFT);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(nameDisplay, gbc);

        // email label
        JLabel emailLabel = new JLabel(ColumnData.UserData.email.getColumnName()+":");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(emailLabel, gbc);

        emailDisplay = new JLabel(email, JLabel.LEFT);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(emailDisplay, gbc);

        return panel;
    }

    public JPanel setBottomPane() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        // username label
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

        // name label
        JLabel nameLabel = new JLabel(ColumnData.UserData.name.getColumnName()+":");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 0, 10, 0);
        panel.add(nameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 10, 10, 0);
        panel.add(nameText, gbc);

        // email label
        JLabel emailLabel = new JLabel(ColumnData.UserData.email.getColumnName()+":");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(10, 0, 10, 0);
        panel.add(emailLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.insets = new Insets(10, 10, 10, 0);
        panel.add(emailText, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.insets = new Insets(10, 0, 10, 0);
        panel.add(update, gbc);

        return panel;
    }
}
