package maintainhome.view;

import maintainhome.controller.Commands;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.*;
import java.awt.*;

/**
 * The view's frame using JFrame.
 */
public class View extends JFrame implements IView {

    /** view's panel to hold the components. */
    private JPanel leftPanel = new JPanel(new GridBagLayout());
    GridBagConstraints constraints = new GridBagConstraints();
    private JPanel rightPanel = new JPanel();
    private JPanel viewPanel = new JPanel(new GridBagLayout());
    private JPanel addPanel = new JPanel(new GridBagLayout());
    private GridBagConstraints constraints2 = new GridBagConstraints();
    private JPanel container = new JPanel(new CardLayout());
    private JPanel panel1 = new JPanel(new GridBagLayout()); // login button?
    private JButton loginBtn = new JButton("Login");
    private JPanel panel2 = new JPanel(new GridBagLayout()); // user info - viewing and modifying will be on same page // user icon, homes list - will have Jlist to select and JTextArea - will incorporate a tab for adding
    private JPanel panel3 = new JPanel(); // when a home is selected
    private JPanel panel4 = new JPanel(); // when a home is selected
    GridBagConstraints gbc = new GridBagConstraints();
    private JButton sideBtns[] = new JButton[3];

    /** view's GridBagConstraints inset . */
    // private int inset = 10; // padding

    public View(String caption) {
        super(caption);

        setFramePanels();
        setLeftPanel();
        setLoginPanel();
        setHomesPanel();
        addToFrame();
        setBorder();
        display();
    }

    /**
     * Sets the JFrame.
     */
    private void setFramePanels() {
        this.setSize(640, 500);
        this.setLocation(200, 100);
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setLayout(new BorderLayout());
        //gbc.fill = GridBagConstraints.NONE;

    }

    public void setLeftPanel() {
        /* LEFT panel */
        constraints.insets = new Insets(10, 10, 20, 10);
        constraints.anchor = GridBagConstraints.WEST;
        
        //this.add(mainPanel);
        constraints.gridx = 0;
        constraints.gridy = 0;
        
        //gbc.gridheight = 3;
        //gbc.fill = GridBagConstraints.VERTICAL;
        //sidePanel.setBounds(50,50, 500, 500);
        constraints.anchor = GridBagConstraints.CENTER;
        setSideBtns();
        /* END */
    }

    public void setLoginPanel() {
        GridBagConstraints loggbc = new GridBagConstraints();
        loggbc.anchor = GridBagConstraints.CENTER;
        loggbc.gridx = 0;
        panel1.add(loginBtn, loggbc);
    }

    public void setHomesPanel() {
        GridBagConstraints constraints1 = new GridBagConstraints();
        // VIEW tab
        constraints1.anchor = GridBagConstraints.NORTH;
        constraints1.insets = new Insets(50,0, 10, 0);
        constraints1.gridx = 0;
        constraints1.gridy = 0;
        String labelTxt = "Homes" + ":";
        JLabel listLabel = new JLabel(labelTxt);
        viewPanel.add(listLabel, constraints1);

        constraints1.insets = new Insets(50,50, 50, 50);
        String[] mylist = new String[] {"hello world", "2", "3", "4"};
        JList<String> listDisplay = new JList<String>(mylist);
        constraints1.gridy = 1;
        viewPanel.add(listDisplay, constraints1);

        String[][] tableData = new String[][] { {"1", "23", "26"}, {"2", "2", "3"} };
        String[] tableHeading = new String[] {"home", "Address", "Zip"};
        JTable homeTable = new JTable(tableData, tableHeading);
        homeTable.getTableHeader().setReorderingAllowed(false);
        constraints1.gridy = 3;
        viewPanel.add(homeTable.getTableHeader(), constraints1);
        viewPanel.add(homeTable, constraints1);
        /* END */
    }

    public void addToFrame() {

        /* RIGHT panel */
        /* ADD tab */

        /* END */
        
        JTabbedPane tabPane = new JTabbedPane();
        tabPane.add("View", viewPanel);
        tabPane.add("Add", addPanel);

        panel2.add(tabPane);

        container.add(panel1, "2");
        container.add(panel2, "3");
        container.add(panel3, "4");
        container.add(panel4, "5");
        //this.add(card);
        this.add(leftPanel, BorderLayout.WEST);
        //this.add(rightPanel);
        this.add(container);


    }

    public void setSideBtns() {
        String[] btnText = {"User", "Homes", "Units"};
        for (int i = 0; i < sideBtns.length; i++) {
            sideBtns[i] = new JButton(btnText[i]);
            constraints.gridy = i + 1;
            leftPanel.add(sideBtns[i], constraints);
        }
    }

    public void setBorder() {
        leftPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        viewPanel.setBorder(BorderFactory.createLineBorder(Color.black));
    }
    @Override
    public void switchCard(String cardNum) {
        CardLayout card = (CardLayout) container.getLayout();
        
        card.show(container, cardNum);
    }

    @Override
    public void setListener(ActionListener listener, KeyListener keys) {
        loginBtn.addActionListener(listener);
        for (int i = 0; i < sideBtns.length; i++) {
            sideBtns[i].addActionListener(listener);
        }
    }

    public void display() {
        setVisible(true);
    }

}
