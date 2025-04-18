package maintainhome.view.Panes;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;
import maintainhome.controller.Commands;
import maintainhome.model.Home.Types.PriorityType;
import maintainhome.model.Home.Types.RoomType;
import maintainhome.model.Home.Types.UnitType;
import maintainhome.model.Utilities.Types.ColumnData;
import maintainhome.model.Utilities.Types.FileType;
import maintainhome.model.Utilities.Types.IColumnEnum;
import javax.swing.*;
import java.awt.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;

/**
 * The panel for the Add tab.
 */
public class AddPanel extends JPanel {
    /** This panel's Grid Bag Layout constraints */
    private GridBagConstraints gbc = new GridBagConstraints();
    /** The display labels of the input and dropdown fields */
    private List<JLabel> labels = new ArrayList<>();
    /** The list of Objects that consist of the input and dropdown fields */
    private List<Object> fields = new ArrayList<>();
    /** The list model for the Home dropdown field */
    private DefaultComboBoxModel<String> homeList = new DefaultComboBoxModel<>();
    /** The Home dropdown dropdown field */
    private JComboBox<String> homeDropdown;
    /** The Room Type dropdown field */
    private JComboBox<String> roomDropdown;
    /** The Unit Type dropdown field */
    private JComboBox<String> unitsDropdown;
    /** The Priority Type dropdown field */
    private JComboBox<String> priorityDropdown;
    /** The Add button */
    private JButton addButton = new JButton("Add");
    /** The Clear button */
    private JButton clearButton = new JButton(Commands.clearButton.getCommandText());
    /** The map that holds the user input and selection values */
    private Map<String, String> values = new HashMap<>();

    /**
     * Default Add panel Constructor.
     * 
     * @param displays a String array text for the label displays.
     * @param panelType the panel card that is currently visible
     */
    public AddPanel(String[] displays, Commands panelType) {
    // need to make this flexible to account for Homes and UnitItems
    // need to display from the Column Data

        setLayout(new GridBagLayout());

        setDisplays(displays);
        setFields(panelType);
        gbc.anchor= GridBagConstraints.EAST;
        gbc.gridx = 0;
        gbc.gridy = displays.length + 1; // excluding home_num
        add(addButton, gbc);
        gbc.gridx = 1;
        add(clearButton, gbc);
    }

    /**
     * Gets this panel.
     * 
     * @return this panel
     */
    public JPanel getPanel() {
        return this;
    }

    /**
     * Gets the clear button object.
     * 
     * @return the clear button object
     */
    public JButton getClearButton() {
        return clearButton;
    }

    /**
     * Gets the add button object.
     * 
     * @return the add button object
     */
    public JButton getAddButton() {
        return addButton;
    }

    /**
     * Gets the values of the input and dropdown values.
     * 
     * @param panelType the panel that is currently visible
     */
    public Map<String, String> getValues(Commands panelType) {
        System.out.println("[DEBUG] extracting value for key: ");

        switch(panelType) {
        case Commands.homesButton:
            for (int i = 0; i < labels.size(); i++) {
                String claenLabel = labels.get(i).getText().replace(":","");
                String key = ColumnData.fromColumnName(claenLabel, FileType.HOMES).toString();
                String value = ((JTextField)fields.get(i)).getText().trim();
                values.put(key, value);
            }
            break;
        case Commands.unitsButton:
            for (int i = 0; i < labels.size(); i++) {
                if (fields.get(i) instanceof JTextField) {
                    String claenLabel = labels.get(i).getText().replace(":","");
                    String key = ColumnData.fromColumnName(claenLabel, FileType.UNIT_ITEMS).toString();
                    String value = ((JTextField)fields.get(i)).getText().trim();
                    values.put(key, value);
                } else if (fields.get(i) instanceof JComboBox) {
                    String claenLabel = labels.get(i).getText().replace(":","");
                    String key = ColumnData.fromColumnName(claenLabel, FileType.UNIT_ITEMS).toString();
                    String value = ((JComboBox<String>)fields.get(i)).getSelectedItem().toString();
                    values.put(key, value);
                }
            }
            break;
        default:

        }
        return values;
    }

    /**
     * Clears the input and dropdown values.
     * 
     * @param panelType the panel card that is currently visible
     */
    public void clearFields(Commands panelType) {
        switch(panelType) {
            case Commands.homesButton:
                for (Object field : fields) {
                    ((JTextField)field).setText("");
                }
                break;
            case Commands.unitsButton:
                for (Object field : fields) {
                    if (field instanceof JTextField) {
                        ((JTextField)field).setText("");
                    } else if (field instanceof JComboBox) {
                        ((JComboBox<String>)field).setSelectedIndex(0);
                    }
                }
                break;
            default:

        }
    }

    /**
     * Sets the display labels to describe the input and dropdown fields.
     * 
     * @param displays the String array of the display labels
     */
    private void setDisplays(String[] displays) {
        gbc.anchor= GridBagConstraints.EAST;
        for (int i = 0; i < displays.length; i++) {
        
            labels.add(new JLabel(displays[i] + ":"));
            gbc.gridy = i;
            gbc.insets = new Insets(10, 0, 10, 0);
            add(labels.get(i), gbc);
        }
    }

    /**
     * Sets by adding the input and dropdown fields for this panel.
     * 
     * @param panelType the panel card that is currently visible
     */
    private void setFields(Commands panelType) {
        gbc.anchor= GridBagConstraints.WEST;
        switch(panelType) {
            case Commands.homesButton:
                for (int i = 0; i < labels.size(); i++) {
                    String key = labels.get(i).getText().replace(":", "");
                    fields.add(new JTextField("", 15));
                    gbc.gridy = i;
                    gbc.insets = new Insets(10, 5, 10, 0);
                    ((JTextField)fields.get(i)).setActionCommand(key);
                    add((JTextField)fields.get(i), gbc);
                }
                break;
            case Commands.unitsButton:
                for (int i = 0; i < labels.size(); i++) {
                    String key = labels.get(i).getText().replace(":", "");
                    gbc.gridy = i;
                    gbc.insets = new Insets(5, 5, 5, 0);
                    if(key.equals(ColumnData.UnitItemData.home_id.getColumnName())){

                        setHomeComboBox();
                        fields.add(homeDropdown);
                        ((JComboBox<String>)fields.get(i)).setActionCommand(key);
                        add((JComboBox<String>)fields.get(i), gbc);
                    } else if(key.equals(ColumnData.UnitItemData.room_type.getColumnName())){

                        setRoomComboBox();
                        fields.add(roomDropdown);
                        ((JComboBox<String>)fields.get(i)).setActionCommand(key);
                        add((JComboBox<String>)fields.get(i), gbc);

                    } else if(key.equals(ColumnData.UnitItemData.unit_type.getColumnName())){

                        setUnitComboBox();
                        fields.add(unitsDropdown);
                        ((JComboBox<String>)fields.get(i)).setActionCommand(key);
                        add((JComboBox<String>)fields.get(i), gbc);

                    } else if(key.equals(ColumnData.UnitItemData.priority.getColumnName())){

                        setPriorityComboBox();
                        fields.add(priorityDropdown);
                        ((JComboBox<String>)fields.get(i)).setActionCommand(key);
                        add((JComboBox<String>)fields.get(i), gbc);
                        
                    } else {
                        fields.add(new JTextField("", 15));
                        ((JTextField)fields.get(i)).setActionCommand(key);
                        add((JTextField)fields.get(i), gbc);
                    }
                }
                break;
            default:
        }
    }

    /**
     * Gets the Home dropdown as a list model.
     * 
     * @return the dropdown list model
     */
    public DefaultComboBoxModel<String> getHomeList(){
        return homeList;
    }

    /**
     * Sets the dropdown for Home Names mapped as ID for saving the unitItems.
     */
    private void setHomeComboBox() {
        homeDropdown = new JComboBox<>(homeList);
    }

    /**
     * Sets the dropdown for Room Type.
     */
    private void setRoomComboBox() {
        List<String> options = new ArrayList<>();
        for (RoomType val:RoomType.values()) {
            options.add(val.getRoomType());
        }
        Collections.sort(options);
        roomDropdown = new JComboBox<>(options.toArray(new String[0]));
    }

    /**
     * Sets the dropdown for Unit Type.
     */
    private void setUnitComboBox() {
        List<String> options = new ArrayList<>();
        for (UnitType val:UnitType.values()) {
            options.add(val.getUnitType());
        }
        Collections.sort(options);
        unitsDropdown = new JComboBox<>(options.toArray(new String[0]));
    }
    
    /**
     * Sets the dropdown for Priority.
     */
    private void setPriorityComboBox() {
        List<String> options = new ArrayList<>();
        for (PriorityType val:PriorityType.values()) {
            options.add(val.toString());
        }
        priorityDropdown = new JComboBox<>(options.toArray(new String[0]));
    }
    
}
