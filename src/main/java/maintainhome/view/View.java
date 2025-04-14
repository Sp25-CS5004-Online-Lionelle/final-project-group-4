package maintainhome.view;

import maintainhome.view.MainPanels.ViewPanel;
import maintainhome.model.Utilities.Types.ColumnData;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * The view's frame using JFrame.
 */
public class View extends JFrame implements IView {

    /** view's panel to hold the components. */
    private ButtonPanel buttonPanel;
    private JPanel container = new JPanel(new CardLayout()); // user info - viewing and modifying will be on same page // user icon, homes list - will have Jlist to select and JTextArea - will incorporate a tab for adding
    private LoginPanel loginPanel;
    private JPanel afterLogin = new JPanel(new BorderLayout());
    private ViewPanel hViewPanel;
    private JPanel homesViewPanel;
    private ViewPanel uViewPanel;
    private JPanel unitsViewPanel;
    private JPanel rightPanel = new JPanel(new CardLayout());
    private JPanel panel2 = new JPanel(new BorderLayout()); 
    private JPanel panel3 = new JPanel(new BorderLayout()); // when a home is selected
    private JPanel panel4 = new JPanel(new BorderLayout());

    /** view's GridBagConstraints inset . */
    // private int inset = 10; // padding

    public View(String caption) {
        super(caption);

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

    private void setLoginPanel() {
        loginPanel = new LoginPanel();
    }

    public String getLoginUser() {
        return loginPanel.getUserInput();
    }

    private void setLeftPanel() {
        // need to have the splitpane
        buttonPanel = new ButtonPanel(3);
    }

    @Override
    public void setUserPanel(String id, String name, String email) {
        UserPanel userPanel = new UserPanel(id, name, email);
        panel2.add(userPanel);
    }

    private JPanel setHomesAdd() {
        JPanel panel = new JPanel(new GridBagLayout());

        return panel;
    }

    private void setHomesView() { // https://www.geeksforgeeks.org/java-swing-jlist-with-examples/
        // VIEW tab
        //constraints1.fill = GridBagConstraints.VERTICAL;
        
        //String[] mylist = new String[] {"hello world", "2", "3", "4"};
        
        //String[][] tableData = new String[][] { {"1", "23", "26"}, {"2", "2", "3"} };
        String[] tableHeading = new String[] {
            ColumnData.HomeData.home_num.getColumnName(),
            ColumnData.HomeData.home_name.getColumnName(),
            ColumnData.HomeData.address.getColumnName(),
            ColumnData.HomeData.zip.getColumnName()
        };

        hViewPanel = new ViewPanel("Homes", tableHeading);
        homesViewPanel = hViewPanel.getPanel();
        /* END */
    }
    
    @Override
    public void addHomesList (String[] homeNames) {
        hViewPanel.addToJList(homeNames);
    }
    @Override
    public void addHomesTable(List<String[]> row) {
        hViewPanel.addTableRows(row);
    }

    
    private JPanel setUnitsAdd() {
        JPanel panel = new JPanel(new GridBagLayout());

        return panel;
    }
    
    private void setUnitsView() { // https://www.geeksforgeeks.org/java-swing-jlist-with-examples/
        // VIEW tab
        //constraints1.fill = GridBagConstraints.VERTICAL;
        
        //String[] mylist = new String[] {"hello world", "2", "3", "4"};
        
        //String[][] tableData = new String[][] { {"1", "23", "26"}, {"2", "2", "3"} };
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

        uViewPanel = new ViewPanel("Units", tableHeading);
        unitsViewPanel = uViewPanel.getPanel();
        /* END */
    }

    @Override
    public void addUnitsList (String[] unitIds) {
        uViewPanel.addToJList(unitIds);
    }
    @Override
    public void addUnitsTable(List<String[]> row) {
        uViewPanel.addTableRows(row);
    }

    private void setHomesTabs(String tab1, String tab2) {
        
        JTabbedPane tabPane = new JTabbedPane();
        JPanel addPanel = setHomesAdd();
        tabPane.add(tab1, homesViewPanel);
        tabPane.add(tab2, addPanel);

        panel3.add(tabPane, BorderLayout.CENTER);
    }

    private void setUnitsTabs(String tab1, String tab2) {
        
        JTabbedPane tabPane = new JTabbedPane();
        JPanel addPanel = setUnitsAdd();
        tabPane.add(tab1, unitsViewPanel);
        tabPane.add(tab2, addPanel);

        panel4.add(tabPane, BorderLayout.CENTER);
    }

    private void setRightPanel() {
        String tab1 = "View";
        String tab2 = "Add";
        setHomesTabs(tab1, tab2);
        setUnitsTabs(tab1, tab2);

        rightPanel.add(panel3, "2");
        rightPanel.add(panel2, "3");
        rightPanel.add(panel4, "4");
        afterLogin.add(rightPanel);

    }

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
        //this.add(rightPanel);
        this.add(container, BorderLayout.CENTER);
    }


    private void setBorder() {
        buttonPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        container.setBorder(BorderFactory.createLineBorder(Color.black));
        hViewPanel.getJList().setBorder(BorderFactory.createLineBorder(Color.black));
    }

    @Override
    public void switchRightPanel(String cardNum) {
        CardLayout card = (CardLayout) rightPanel.getLayout();
        card.show(rightPanel, cardNum);
    }
    
    @Override
    public void switchMainPanel(String cardNum) {
        CardLayout card = (CardLayout) container.getLayout();
        card.show(container, cardNum);
    }

    @Override
    public void setListener(ActionListener listener, KeyListener keys) {
        loginPanel.getLoginBtn().addActionListener(listener);

        for (int i = 0; i < buttonPanel.getButtons().length; i++) {
            buttonPanel.getButtons()[i].addActionListener(listener);
        }

        // setListSelectionListener();
    }
    /*
    private void setListSelectionListener() {
        JList<String> list = hViewPanel.getJList();
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

    private void display() {
        setVisible(true);
    }

}
