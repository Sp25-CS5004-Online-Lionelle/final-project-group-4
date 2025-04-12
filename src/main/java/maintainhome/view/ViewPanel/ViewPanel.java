package maintainhome.view.ViewPanel;


import javax.swing.*;
import java.awt.*;

public class ViewPanel extends JPanel {
        private JPanel topPanel = new JPanel(new GridBagLayout());
        private GridBagConstraints gbcTop = new GridBagConstraints();
        private JLabel listLabel;
        private JList<String> listItems;
        
        private JPanel bttmPanel = new JPanel(new GridBagLayout());
        private GridBagConstraints gbcBttm = new GridBagConstraints();
        private int inset20 = 20;

        public ViewPanel(String label, JList<String>list) {
                super(new BorderLayout());
                listLabel = new JLabel(label + ":");
                listItems = list;
                setListLabel();
                setJList();
                add(topPanel, BorderLayout.NORTH);
                add(bttmPanel, BorderLayout.CENTER);
        }

        private void setListLabel() {
                gbcTop.gridx = 0;
                gbcTop.gridy = 0;
                gbcTop.insets = new Insets(50, 0, 0, 0);
                
                topPanel.add(listLabel, gbcTop);
        }
        
        public void setJList() {
                String[] mylist = new String[] {"hello world", "2", "3", "4"};
                gbcTop.gridx = 1;
                gbcTop.gridy = 0;
                gbcTop.insets = new Insets(50, inset20, 0, 0);
                topPanel.add(listItems, gbcTop);
        }

        private void setTable(String[][] tableData, String[] tableHeading) {
                //String[][] tableData = new String[][] { {"1", "23", "26"}, {"2", "2", "3"} };
                //String[] tableHeading = new String[] {"home", "Address", "Zip"};
                
                JTable homeTable = new JTable(tableData, tableHeading);
                homeTable.getTableHeader().setReorderingAllowed(false);
                gbcBttm.gridy = 3;
                bttmPanel.add(homeTable.getTableHeader(), gbcBttm);
                gbcBttm.gridy = 4;
                bttmPanel.add(homeTable, gbcBttm);

        }
}
