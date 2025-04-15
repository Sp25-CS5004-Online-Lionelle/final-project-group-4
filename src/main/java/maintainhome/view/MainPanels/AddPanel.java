package maintainhome.view.MainPanels;

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

public class AddPanel extends JPanel {
    private GridBagConstraints gbc = new GridBagConstraints();
    private List<JLabel> labels = new ArrayList<>();
    private List<Object> fields = new ArrayList<>();
    private JComboBox<String> roomDropdown;
    private JComboBox<String> unitsDropdown;
    private JComboBox<String> priorityDropdown;
    private JButton addButton = new JButton("Add");
    private JButton clearButton = new JButton(Commands.clearButton.getCommandText());
    private Map<String, String> values = new HashMap<>();

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

    public JPanel getPanel() {
        return this;
    }

    public JButton getClearButton() {
        return clearButton;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public Map<String, String> getValues(Commands panelType) {
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

    private void setDisplays(String[] displays) {
        gbc.anchor= GridBagConstraints.EAST;
        for (int i = 0; i < displays.length; i++) {
        
            labels.add(new JLabel(displays[i] + ":"));
            gbc.gridy = i;
            gbc.insets = new Insets(10, 0, 10, 0);
            add(labels.get(i), gbc);
        }
    }

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
                    gbc.insets = new Insets(10, 5, 10, 0);
                    if(key.equals(ColumnData.UnitItemData.room_type.getColumnName())){

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

    private void setRoomComboBox() {
        List<String> options = new ArrayList<>();
        for (RoomType val:RoomType.values()) {
            options.add(val.getRoomType());
        }
        Collections.sort(options);
        roomDropdown = new JComboBox<>(options.toArray(new String[0]));
    }

    private void setUnitComboBox() {
        List<String> options = new ArrayList<>();
        for (UnitType val:UnitType.values()) {
            options.add(val.getUnitType());
        }
        Collections.sort(options);
        unitsDropdown = new JComboBox<>(options.toArray(new String[0]));
    }

    private void setPriorityComboBox() {
        List<String> options = new ArrayList<>();
        for (PriorityType val:PriorityType.values()) {
            options.add(val.toString());
        }
        priorityDropdown = new JComboBox<>(options.toArray(new String[0]));
    }
    
}
