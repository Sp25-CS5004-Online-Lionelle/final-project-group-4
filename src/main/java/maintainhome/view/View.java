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
    private ButtonPanel leftPanel;
    private JPanel container = new JPanel(new CardLayout()); // user info - viewing and modifying will be on same page // user icon, homes list - will have Jlist to select and JTextArea - will incorporate a tab for adding
    private LoginPanel login;
    private JPanel main1; // login panel
    private JPanel main2 = new JPanel(new BorderLayout());
    private JPanel rightPanel = new JPanel(new CardLayout());
    private JPanel panel2 = new JPanel(new BorderLayout()); 
    private JPanel panel3 = new JPanel(new BorderLayout()); // when a home is selected
    private JPanel panel4 = new JPanel(new BorderLayout()); // when a home is selected
    GridBagConstraints gbc = new GridBagConstraints();
    private int inset20 = 20;

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
        login = new LoginPanel();
        main1 = login.getLoginPanel();
    }

    private void setLeftPanel() {
        leftPanel = new ButtonPanel(3);
    }

    public JPanel setHomesAdd() {
        JPanel homesAddPanel = new JPanel(new GridBagLayout());

        return homesAddPanel;
    }

    public JPanel setHomesView() {
        JPanel homeVwPanel = new JPanel(new BorderLayout());
        // VIEW tab
        JPanel topPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints1 = new GridBagConstraints();
        JPanel bttmPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints2 = new GridBagConstraints();
        //constraints1.fill = GridBagConstraints.VERTICAL;
        
        constraints1.gridx = 0;
        constraints1.gridy = 0;
        constraints1.insets = new Insets(50, 0, 0, 0);
        String labelTxt = "Homes" + ":";
        JLabel listLabel = new JLabel(labelTxt);
        topPanel.add(listLabel, constraints1);

        String[] mylist = new String[] {"hello world", "2", "3", "4"};
        JList<String> listDisplay = new JList<String>(mylist);
        constraints1.gridx = 1;
        constraints1.gridy = 0;
        constraints1.insets = new Insets(50, inset20, 0, 0);
        topPanel.add(listDisplay, constraints1);


        String[][] tableData = new String[][] { {"1", "23", "26"}, {"2", "2", "3"} };
        String[] tableHeading = new String[] {"home", "Address", "Zip"};
        JTable homeTable = new JTable(tableData, tableHeading);
        homeTable.getTableHeader().setReorderingAllowed(false);
        constraints2.gridy = 3;
        bttmPanel.add(homeTable.getTableHeader(), constraints2);
        constraints2.gridy = 4;
        bttmPanel.add(homeTable, constraints2);

        homeVwPanel.add(topPanel, BorderLayout.NORTH);
        homeVwPanel.add(bttmPanel, BorderLayout.CENTER);

        return homeVwPanel;
        /* END */
    }

    public void setRightPanel() {
        
        JTabbedPane tabPane = new JTabbedPane();
        JPanel viewPanel = setHomesView();
        JPanel addPanel = setHomesAdd();
        tabPane.add("View", viewPanel);
        tabPane.add("Add", addPanel);

        panel3.add(tabPane, BorderLayout.CENTER);

        rightPanel.add(panel2, "2");
        rightPanel.add(panel3, "3");
        rightPanel.add(panel4, "4");
        main2.add(rightPanel);

    }

    private void addToFrame() {
        
        setLoginPanel();
        setLeftPanel();
        /* RIGHT panel */
        /* ADD tab */
        setRightPanel();
        /* END */
        
        main2.add(leftPanel, BorderLayout.WEST);
        main2.add(rightPanel, BorderLayout.CENTER);
        container.add(main1, "2");
        container.add(main2, "3");
        //this.add(rightPanel);
        this.add(container, BorderLayout.CENTER);

    }


    private void setBorder() {
        leftPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        container.setBorder(BorderFactory.createLineBorder(Color.black));
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
        login.getLoginBtn().addActionListener(listener);

        for (int i = 0; i < leftPanel.getButtons().length; i++) {
            leftPanel.getButtons()[i].addActionListener(listener);
        }
    }

    private void display() {
        setVisible(true);
    }

}
