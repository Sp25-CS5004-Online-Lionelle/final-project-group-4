package maintainhome.view.ViewPanel;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ViewPanel extends JPanel {
        private JPanel topPanel = new JPanel(new GridBagLayout());
        private GridBagConstraints gbcTop = new GridBagConstraints();
        private JLabel listLabel;
        private DefaultListModel<String> listModel = new DefaultListModel<>();
        private JList<String> list;
        
        private JPanel bttmPanel = new JPanel(new GridBagLayout());
        private GridBagConstraints gbcBttm = new GridBagConstraints();
        private DefaultTableModel tableModel;
        private JTable table;
        private int inset20 = 20;

        public ViewPanel(String label, String[] tableHeading) { // need to get the list of home ids, names or other and set here
                super(new BorderLayout());
                // top panel
                listLabel = new JLabel(label + ":");
                list = new JList<> (listModel);
                setListLabel();
                setJList();
                // bottom panel
                tableModel = new DefaultTableModel(tableHeading, 0);
                table = new JTable(tableModel);
                setTable();

                add(topPanel, BorderLayout.NORTH);
                add(bttmPanel, BorderLayout.CENTER);
        }

        public JPanel getPanel() {
                return this;
        }

        public JList<String> getJList() {
                return list;
        }

        public void clearJList() {
                listModel.clear();
        }

        public void updateJList(String[] updateItems) {
                clearJList();
                for (String items:updateItems) {
                        listModel.addElement(items);
                }
                list.setModel(listModel);
        }

        public void clearTable() {
                tableModel.setRowCount(0);
        }

        public void addTableRow(String[] row) {
                tableModel.addRow(row);
        }

        public void updateTableRows(List<String[]> rows) {
                clearTable();
                for (String[] row : rows) {
                        addTableRow(row);
                }
        }

        private void setListLabel() {
                gbcTop.gridx = 0;
                gbcTop.gridy = 0;
                gbcTop.insets = new Insets(50, 0, 0, 0);
                topPanel.add(listLabel, gbcTop);
        }
        
        public void setJList() {
                list.setFixedCellWidth(100);
                gbcTop.gridx = 1;
                gbcTop.gridy = 0;
                gbcTop.insets = new Insets(50, inset20, 0, 0);
                topPanel.add(list, gbcTop);
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
