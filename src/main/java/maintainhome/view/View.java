package maintainhome.view;

import maintainhome.view.MainPanels.ViewPanel;
import maintainhome.model.Utilities.Types.ColumnData;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

//import javax.swing.event.ListSelectionEvent;
//import javax.swing.event.ListSelectionListener;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * The view's frame using JFrame.
 */
public class View extends JFrame implements IView {

    /** view's panel to hold the components. */
    private ButtonPanel leftPanel;
    private JPanel container = new JPanel(new CardLayout()); // user info - viewing and modifying will be on same page // user icon, homes list - will have Jlist to select and JTextArea - will incorporate a tab for adding
    private LoginPanel loginPanel;
    private JPanel afterLogin = new JPanel(new BorderLayout());
    private ViewPanel hViewPanel;
    private JPanel homesViewPanel;
    private JPanel rightPanel = new JPanel(new CardLayout());
    private JPanel panel2 = new JPanel(new BorderLayout()); 
    private JPanel panel3 = new JPanel(new BorderLayout()); // when a home is selected
    private JPanel panel4 = new JPanel(new BorderLayout());
    GridBagConstraints gbc = new GridBagConstraints();

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
        leftPanel = new ButtonPanel(3);
    }

    private JPanel setHomesAdd() {
        JPanel homesAddPanel = new JPanel(new GridBagLayout());

        return homesAddPanel;
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
    public void updateHomesList (String[] homeNames) {
        hViewPanel.updateJList(homeNames);
    }
    @Override
    public void updateHomesTable(List<String[]> row) {
        hViewPanel.updateTableRows(row);
    }

    private void setRightPanel() {
        
        JTabbedPane tabPane = new JTabbedPane();
        JPanel addPanel = setHomesAdd();
        tabPane.add("View", homesViewPanel);
        tabPane.add("Add", addPanel);

        panel3.add(tabPane, BorderLayout.CENTER);

        rightPanel.add(panel2, "2");
        rightPanel.add(panel3, "3");
        rightPanel.add(panel4, "4");
        afterLogin.add(rightPanel);

    }

    private void addToFrame() {
        
        setLoginPanel();
        setLeftPanel();
        setHomesView();
        /* RIGHT panel */
        /* ADD tab */
        setRightPanel();
        /* END */
        
        afterLogin.add(leftPanel, BorderLayout.WEST);
        afterLogin.add(rightPanel, BorderLayout.CENTER);
        container.add(loginPanel.getLoginPanel(), "2");
        container.add(afterLogin, "3");
        //this.add(rightPanel);
        this.add(container, BorderLayout.CENTER);

    }


    private void setBorder() {
        leftPanel.setBorder(BorderFactory.createLineBorder(Color.black));
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

        for (int i = 0; i < leftPanel.getButtons().length; i++) {
            leftPanel.getButtons()[i].addActionListener(listener);
        }

        //setListSelectionListener();
    }
    
    /*
    public void setListSelectionListener() {
        JList<String> list = hViewPanel.getJList();
        list.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                        ListSelectionModel lsm = (ListSelectionModel) e.getSource();
                        if (!e.getValueIsAdjusting()) {
                                System.out.println(list.getSelectedValue());
                                
                                //final List<String> selectedValuesList = list.getSelectedValuesList();
                                //System.out.println(selectedValuesList);
                                
                            }
                    }
            });
    }
    */

    private void display() {
        setVisible(true);
    }

}
