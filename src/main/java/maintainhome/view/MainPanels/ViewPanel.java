package maintainhome.view.MainPanels;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ViewPanel extends JPanel {
        private JLabel listLabel;
        private DefaultListModel<String> listModel = new DefaultListModel<>();
        private JList<String> list;

        private DefaultTableModel tableModel;
        private JTable table;
        private int inset20 = 20;

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

        public JPanel getPanel() {
                return this;
        }

        public DefaultListModel<String> getListModel() {
                return listModel;
        }

        public JList<String> getJList() {
                return list;
        }

        public void clearJList() {
                listModel.clear();
        }

        public void updateJList(String[] updateItems) {
                clearJList();
                addToJList(updateItems);
        }

        public void addToJList(String[] items) {
                for (String item:items) {
                        listModel.addElement(item);
                }
                list.setModel(listModel);
        }

        public void clearTable() {
                tableModel.setRowCount(0);
        }

        public void addTableRow(String[] row) {
                tableModel.addRow(row);
        }

        public void addTableRows(List<String[]> rows) {
                for (String[] row:rows) {
                        addTableRow(row);
                }
        }

        public void updateTableRows(List<String[]> rows) {
                clearTable();
                for (String[] row : rows) {
                        addTableRow(row);
                }
        }

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
