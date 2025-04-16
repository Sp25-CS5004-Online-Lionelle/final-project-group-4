package maintainhome.view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.*;
import maintainhome.controller.Commands;
import maintainhome.model.Utilities.Types.ColumnData;
import maintainhome.model.Utilities.Types.IColumnEnum;
import maintainhome.view.MainPanels.AddPanel;
import maintainhome.view.MainPanels.ViewPanel;


/**
 * The view's frame using JFrame.
 */
public class View extends JFrame implements IView {

    /** view's panel to hold the components. */
    private ButtonPanel buttonPanel;
    private UserPanel userPanel;
    private ActionListener actionListener;
    private JPanel container = new JPanel(new CardLayout()); // user info - viewing and modifying will be on same page // user icon, homes list - will have Jlist to select and JTextArea - will incorporate a tab for adding
    private LoginPanel loginPanel;
    private JPanel afterLogin = new JPanel(new BorderLayout());
    private JTabbedPane homesTabPane;
    private JTabbedPane unitsTabPane;
    private ViewPanel homesViewPanel;
    private AddPanel homesAddPanel;
    private ViewPanel unitsViewPanel;
    private AddPanel unitsAddPanel;
    private JPanel rightPanel = new JPanel(new CardLayout());
    private JPanel panel1 = new JPanel(new BorderLayout()); 
    private JPanel panel2 = new JPanel(new BorderLayout()); // when a home is selected
    private JPanel panel3 = new JPanel(new BorderLayout());
    private Commands selectedCard;
    private Map<Commands, String[]> commands = new HashMap<>();

    /** view's GridBagConstraints inset . */
    // private int inset = 10; // padding

    public View(String caption) {
        super(caption);

        commands.put(Commands.userButton,
            new String[] {Commands.userButton.getCommandText(), "User"});
        commands.put(Commands.homesButton, new String[] {Commands.homesButton.getCommandText(),
                ColumnData.HomeData.home_name.getColumnName()});
        commands.put(Commands.unitsButton, new String[] {Commands.unitsButton.getCommandText(),
            ColumnData.UnitItemData.room_type.getColumnName()});
        commands.put(Commands.resetFilter,
            new String[] {Commands.resetFilter.getCommandText(), "Reset Filter"});
            commands.put(Commands.saveFiltered,
            new String[] {Commands.saveFiltered.getCommandText(), "Save Filtered"});
        
        

        setFramePanel();
        addToFrame();
        setBorder();
        display();
    }

    /**
     * Sets the JFrame.
     */
    private void setFramePanel() {
        this.setSize(640, 500);
        this.setLocation(200, 100);
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setLayout(new BorderLayout());
        //gbc.fill = GridBagConstraints.NONE;

    }
    
    /**
     * Sets and adds componenets to the login panel.
     */
    private void setLoginPanel() {
        loginPanel = new LoginPanel();
    }

    @Override
    public String getLoginUser() {
        return loginPanel.getUserInput();
    }

    /**
     * Sets and adds componenets to the left panel.
     */
    private void setLeftPanel() {
        // need to have the splitpane
        buttonPanel = new ButtonPanel(5, new String[] {commands.get(Commands.userButton)[0],
            commands.get(Commands.homesButton)[0], commands.get(Commands.unitsButton)[0], 
            commands.get(Commands.resetFilter)[0], commands.get(Commands.saveFiltered)[0]});
    }

    @Override
    public void setUserPanel(String id, String name, String email) {
        userPanel = new UserPanel(id, name, email);
        panel1.add(userPanel);
        // set listener for user update button
        userPanel.getUpdateButton().addActionListener(actionListener);

    }
    @Override
    public String getUpdatedUserName() {
    return userPanel.getEnteredName();
    }

    @Override
    public String getUpdatedUserEmail() {
    return userPanel.getEnteredEmail();
    }

    @Override
    public void refreshUserPanelLabels() {
    userPanel.refreshDisplayLabels();
}


    
    /**
     * Sets and adds componenets to the add tab for Homes panel.
     */
    private String[] homeAddFields(List<IColumnEnum> exclude) {
        List<String> fields = new ArrayList<>();
        int check = 0;
        for (ColumnData.HomeData col:ColumnData.HomeData.values()) {
            for (IColumnEnum ex: exclude) {
                if (col.equals(ex)) {
                    check = 1;
                }
            }
            if (check == 0) {
                fields.add(col.getColumnName());
            }
            check = 0;
        }
        return fields.toArray(new String[0]);
    }

    
    /**
     * Sets and adds componenets to the add tab for Units panel.
     */
    private String[] unitAddFields(List<IColumnEnum> exclude) {
        List<String> fields = new ArrayList<>();
        int check = 0;
        for (ColumnData.UnitItemData col:ColumnData.UnitItemData.values()) {
            for (IColumnEnum ex: exclude) {
                if (col.equals(ex)) {
                    check = 1;
                }
            }
            if (check == 0) {
                fields.add(col.getColumnName());
            }
            check = 0;
        }
        return fields.toArray(new String[0]);
    }

    /**
     * Sets the add tab for Homes panel.
     */
    private void setHomesAdd() {
        //JPanel panel = new JPanel(new GridBagLayout());
        List<IColumnEnum> ex = new ArrayList<>();
        ex.add(ColumnData.HomeData.home_id);
        ex.add(ColumnData.HomeData.home_num);
        String[] fields = homeAddFields(ex);
        homesAddPanel = new AddPanel(fields, Commands.homesButton);
    }

    /**
     * Sets the view tab for Homes panel.
     */
    private void setHomesView() { // https://www.geeksforgeeks.org/java-swing-jlist-with-examples/
        // VIEW tab
        //constraints1.fill = GridBagConstraints.VERTICAL;
        
        //String[] mylist = new String[] {"hello world", "2", "3", "4"};
        
        //String[][] tableData = new String[][] { {"1", "23", "26"}, {"2", "2", "3"} };
        List<IColumnEnum> ex = new ArrayList<>();
        ex.add(ColumnData.HomeData.home_id);
        String[] tableHeading = homeAddFields(ex);

        homesViewPanel = new ViewPanel(commands.get(Commands.homesButton)[1], tableHeading);
        
        /* END */
    }
    
    @Override
    public void updateHomesList (String[] homeNames) {
        homesViewPanel.updateJList(homeNames);
    }
    @Override
    public void updateHomesTable(List<String[]> row) {
        homesViewPanel.updateTableRows(row);
    }

    
    /**
     * Sets the add tab for Units panel.
     */
    private void setUnitsAdd() {
        List<IColumnEnum> ex = new ArrayList<>();
        ex.add(ColumnData.UnitItemData.user_id);
        ex.add(ColumnData.UnitItemData.home_id);
        ex.add(ColumnData.UnitItemData.unit_id);
        // ex.add(ColumnData.UnitItemData.room_type);
        ex.add(ColumnData.UnitItemData.frequency_meas);

        String[] fields = unitAddFields(ex);
        unitsAddPanel = new AddPanel(fields, Commands.unitsButton);
    }
    
    /**
     * Sets the view tab for Units panel.
     */
    private void setUnitsView() { // https://www.geeksforgeeks.org/java-swing-jlist-with-examples/
        // VIEW tab
        //constraints1.fill = GridBagConstraints.VERTICAL;
        
        //String[] mylist = new String[] {"hello world", "2", "3", "4"};
        
        //String[][] tableData = new String[][] { {"1", "23", "26"}, {"2", "2", "3"} };
        /*
        String[] tableHeading = new String[] {
            ColumnData.UnitItemData.unit_id.getColumnName(),
            ColumnData.UnitItemData.item_name.getColumnName(),
            ColumnData.UnitItemData.unit_type.getColumnName(),
            ColumnData.UnitItemData.room_name.getColumnName(),
            ColumnData.UnitItemData.install_date.getColumnName(),
            ColumnData.UnitItemData.maintained_date.getColumnName(),
            ColumnData.UnitItemData.maintenance_freq.getColumnName(),
            //ColumnData.UnitItemData.issue.getColumnName(),
            ColumnData.UnitItemData.priority.getColumnName()
        };
        */
        List<IColumnEnum> ex = new ArrayList<>();
        ex.add(ColumnData.UnitItemData.user_id);
        ex.add(ColumnData.UnitItemData.home_id);
        ex.add(ColumnData.UnitItemData.unit_id);
        ex.add(ColumnData.UnitItemData.room_type);
        ex.add(ColumnData.UnitItemData.frequency_meas);

        String[] tableHeading = unitAddFields(ex);
        unitsViewPanel = new ViewPanel(commands.get(Commands.unitsButton)[1], tableHeading);
        /* END */
    }

    @Override
    public void updateUnitsList (String[] unitIds) {
        unitsViewPanel.updateJList(unitIds);
    }
    @Override
    public void updateUnitsTable(List<String[]> row) {
        unitsViewPanel.updateTableRows(row);
    }

    /**
     * Adds the componenets to the Homes panel tabs.
     */
    private void setHomesTabs(String tab1, String tab2) {
        setHomesAdd();
        homesTabPane = new JTabbedPane();
        homesTabPane.add(tab1, homesViewPanel);
        homesTabPane.add(tab2, homesAddPanel);

        panel2.add(homesTabPane, BorderLayout.CENTER);
    }

    public JTabbedPane getHomesTab() {
        return homesTabPane;
    }

    public JTabbedPane getUnitsTab() {
        return unitsTabPane;
    }

    /**
     * Adds the componenets to the Units panel tabs.
     */
    private void setUnitsTabs(String tab1, String tab2) {
        setUnitsAdd();
        unitsTabPane = new JTabbedPane();
        unitsTabPane.add(tab1, unitsViewPanel);
        unitsTabPane.add(tab2, unitsAddPanel);

        panel3.add(unitsTabPane, BorderLayout.CENTER);
    }

    
    /**
     * Adds all componenets to right panel and sets the panel ready to be displayed.
     */
    private void setRightPanel() {
        String tab1 = "View";
        String tab2 = "Add";
        setHomesTabs(tab1, tab2);
        setUnitsTabs(tab1, tab2);

        rightPanel.add(panel2, commands.get(Commands.homesButton)[0]); // Homes Button - wanted to display this first
        rightPanel.add(panel1, commands.get(Commands.userButton)[0]);
        rightPanel.add(panel3, commands.get(Commands.unitsButton)[0]);
        afterLogin.add(rightPanel);

    }

    /**
     * Adds all componenets to frame and sets the frame ready to be displayed.
     */
    private void addToFrame() {
        
        setLoginPanel();
        setLeftPanel();
        setHomesView();
        setUnitsView();
        /* RIGHT panel */
        /* ADD tab */
        setRightPanel();
        /* END */
        
        afterLogin.add(buttonPanel, BorderLayout.WEST);
        afterLogin.add(rightPanel, BorderLayout.CENTER);
        container.add(loginPanel.getLoginPanel(), "2");
        container.add(afterLogin, "3");

        this.add(container, BorderLayout.CENTER);
    }

    /**
     * Adds border to the main componenets for display.
     */
    private void setBorder() {
        buttonPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        container.setBorder(BorderFactory.createLineBorder(Color.black));
        homesViewPanel.getJList().setBorder(BorderFactory.createLineBorder(Color.black));
        unitsViewPanel.getJList().setBorder(BorderFactory.createLineBorder(Color.black));
    }

    @Override
    public void switchRightPanel(String cardName) {
        CardLayout card = (CardLayout) rightPanel.getLayout();
        card.show(rightPanel, cardName);
    }
    
    @Override
    public void switchMainPanel(String cardName) {
        CardLayout card = (CardLayout) container.getLayout();
        card.show(container, cardName);
    }

    @Override
    public Commands getSelectedCard() {
        return selectedCard;
    }

    @Override
    public void setSelectedCard(Commands card) {
        selectedCard = card;
    }

    @Override
    public void clearAddFields() {
        switch(selectedCard) {
            case Commands.homesButton:
                homesAddPanel.clearFields(selectedCard);
                break;
            case Commands.unitsButton:
                unitsAddPanel.clearFields(selectedCard);
                break;
            default:
        }
    }

    @Override
    public Map<String, String> getAddValues() {
        switch(selectedCard) {
            case Commands.homesButton:
                return homesAddPanel.getValues(selectedCard);
            case Commands.unitsButton:
                return unitsAddPanel.getValues(selectedCard);
            default:
                return null;
        }
    }

    @Override
    public String getRoomTypeSelected() {
    return unitsViewPanel.getJList().getSelectedValue();
    }


    @Override
    public void setListener(ActionListener listener, KeyListener keys) {
        // Store reference so we can use it in setUserPanel
        this.actionListener = listener;

        loginPanel.getLoginBtn().addActionListener(listener);

        for (int i = 0; i < buttonPanel.getButtons().length; i++) {
            buttonPanel.getButtons()[i].addActionListener(listener);
        }
        // set listener for add panels clear button
        homesAddPanel.getClearButton().addActionListener(listener);
        unitsAddPanel.getClearButton().addActionListener(listener);

        // set listener for add panels add button
        homesAddPanel.getAddButton().addActionListener(listener);
        unitsAddPanel.getAddButton().addActionListener(listener);

        // set listener to look for click on list;
        unitsViewPanel.getJList().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                for (JButton b : buttonPanel.getButtons()) {
                    if (b.getActionCommand().equals(Commands.unitsButton.getCommandText())) {
                        b.doClick(); // Simulate click to notify controller
                        break;
                    }
                }
            }
        });
        
    }

    /*
    private void setListSelectionListener() {
        JList<String> list = homesViewPanel.getJList();
        list.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                        ListSelectionModel lsm = (ListSelectionModel) e.getSource();
                        if (!e.getValueIsAdjusting()) {
                                System.out.println(lsm.getLeadSelectionIndex());
                                *
                                final List<String> selectedValuesList = list.getSelectedValuesList();
                                System.out.println(selectedValuesList);
                                *
                            }
                    }
            });
    }
    */

    /**
     * Displays the frame
     */
    private void display() {
        setVisible(true);
    }

}
