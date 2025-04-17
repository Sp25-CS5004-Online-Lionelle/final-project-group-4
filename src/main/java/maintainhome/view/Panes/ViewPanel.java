package maintainhome.view.Panes;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * The panel for the Viwe tab.
 */
public class ViewPanel extends JPanel {
        /** Display label for the JList */
        private JLabel listLabel;
        /** The list model user selection for the JList */
        private DefaultListModel<String> listModel = new DefaultListModel<>();
        /** The JList container */
        private JList<String> list;
        /** The table model for the JTable */
        private DefaultTableModel tableModel;
        /** The Jtable container */
        private JTable table;
        //private int inset20 = 20;

        /**
         * Default View panel Constructor.
         * @param label a String array text for the label displays.
         * @param tableHeading The String array for the column names of the JTable
         */
        public ViewPanel(String label, String[] tableHeading) { // need to get the list of home ids, names or other and set here
                super(new BorderLayout());
                // top panel
                listLabel = new JLabel(label + ":");
                list = new JList<> (listModel);
                JPanel topPanel = setTopPanel();
                // bottom panel
                tableModel = new DefaultTableModel(tableHeading, 0);
                table = new JTable(tableModel);
                JPanel bttmPanel = setBottomPanel();
                
                JSplitPane splitPane = new JSplitPane(SwingConstants.HORIZONTAL, topPanel, bttmPanel);
                add(splitPane, BorderLayout.CENTER);
        }

        /**
         * Gets this panel.
         * @return this panel
         */
        public JPanel getPanel() {
                return this;
        }

        /**
         * List Model for the JList.
         * @return the list model for updating
         */
        public DefaultListModel<String> getListModel() {
                return listModel;
        }

        /**
         * Gets the JList object.
         * @return the list of the JList list
         */
        public JList<String> getJList() {
                return list;
        }

        /**
         * Clears the list model of the JList.
         */
        public void clearJList() {
                listModel.clear();
        }

        /**
         * Updates the JList with the new items.
         * @param updateItems the String array of items to update the JList
         */
        public void updateJList(String[] updateItems) {
                clearJList();
                addToJList(updateItems);
        }

        /**
         * Adds items to the List Model of the JList.
         * @param items the items to add to the JList
         */
        public void addToJList(String[] items) {
                for (String item:items) {
                        listModel.addElement(item);
                }
                list.setModel(listModel);
        }

        /**
         * Clears the table model of the JTable
         */
        public void clearTable() {
                tableModel.setRowCount(0);
        }

        /**
         * Adds a table row to the table model of the JTable.
         * @param row the row to update to the JTable
         */
        public void addTableRow(String[] row) {
                tableModel.addRow(row);
        }

        /**
         * Adds the table rows to the table model of the JTable.
         * @param rows the rows to add to the JTable
         */
        public void addTableRows(List<String[]> rows) {
                for (String[] row:rows) {
                        addTableRow(row);
                }
        }

        /**
         * Updates the table rows to the table model of the JTable.
         * @param rows the rows to update the JTable with
         */
        public void updateTableRows(List<String[]> rows) {
                clearTable();
                for (String[] row : rows) {
                        addTableRow(row);
                }
        }

        /**
         * Sets by adding the objects to the top panel.
         * @return the top panel of the View panel
         */
        private JPanel setTopPanel() {
                JPanel panel = new JPanel(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();
                // JList Label
                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.insets = new Insets(25, 0, 25, 0);
                panel.add(listLabel, gbc);
                // JList
                list.setFixedCellWidth(100);
                list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                //list.add(new JScrollPane(list));
                gbc.gridx = 1;
                gbc.gridy = 0;
                gbc.insets = new Insets(25, 10, 25, 0);
                panel.add(list, gbc);
                return panel;
        }

        /**
         * Sets by adding the objects to the bottom panel.
         * @return the bottom panel of the View panel
         */
        private JPanel setBottomPanel() { // String[][] tableData, String[] tableHeading
                JPanel panel = new JPanel(new BorderLayout());
                panel.setBorder(new EmptyBorder(20, 20, 20, 20));
                //GridBagConstraints gbc = new GridBagConstraints();
                //table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                // Wrap in a JScrollPane for scrolling functionality
                table.getTableHeader().setReorderingAllowed(false);
                //gbc.gridy = 3;
                // panel.add(table.getTableHeader(), gbc);
                //gbc.gridy = 4;
                // panel.add(table, gbc);
                
                JScrollPane scrollPane = new JScrollPane(table);
                scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                panel.add(scrollPane, BorderLayout.CENTER);
                return panel;
        }
}
