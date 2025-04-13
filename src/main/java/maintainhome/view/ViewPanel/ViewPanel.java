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
        private JTable table;
        private int inset20 = 20;

        public ViewPanel(String label, String[] list, String[][] tableData, String[] tableHeading) { // need to get the list of home ids, names or other and set here
                super(new BorderLayout());
                listLabel = new JLabel(label + ":");
                listItems = new JList<> (list);
                setListLabel();
                setJList();
                table = new JTable(tableData, tableHeading);
                setTable();
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
                
                gbcTop.gridx = 1;
                gbcTop.gridy = 0;
                gbcTop.insets = new Insets(50, inset20, 0, 0);
                topPanel.add(listItems, gbcTop);
        }

        private void setTable() { // String[][] tableData, String[] tableHeading
                
                table.getTableHeader().setReorderingAllowed(false);
                gbcBttm.gridy = 3;
                bttmPanel.add(table.getTableHeader(), gbcBttm);
                gbcBttm.gridy = 4;
                bttmPanel.add(table, gbcBttm);

        }

        /* // update homes table on selection of list
        private void updateTable() { // https://www.geeksforgeeks.org/java-swing-jtable/
                setValueAt(Object value, int row, int column);
                fireTableDataChanged();
        }
        */
}
