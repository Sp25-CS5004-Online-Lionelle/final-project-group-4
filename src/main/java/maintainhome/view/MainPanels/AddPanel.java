package maintainhome.view.MainPanels;

import maintainhome.controller.Commands;
import maintainhome.model.Home.Types.PriorityType;
import maintainhome.model.Home.Types.RoomType;
import maintainhome.model.Home.Types.UnitType;
import maintainhome.model.Utilities.Types.ColumnData;

import javax.swing.*;
import java.awt.*;

import java.util.List;
import java.util.ArrayList;

public class AddPanel extends JPanel {
    private GridBagConstraints gbc = new GridBagConstraints();
    private List<JLabel> labels = new ArrayList<>();
    private List<Object> fields = new ArrayList<>();
    private JButton addButton = new JButton("Add");
    private JButton clearButton = new JButton("Clear");

    public AddPanel(String[] displays, Commands panelType) {
    // need to make this flexible to account for Homes and UnitItems
    // need to display from the Column Data

        setLayout(new GridBagLayout());

        setDisplays(displays);
        setTextFields(panelType);
        gbc.anchor= GridBagConstraints.EAST;
        gbc.gridx = 0;
        gbc.gridy = displays.length + 1; // excluding home_num
        add(addButton, gbc);
        gbc.gridx = 1;
        add(clearButton, gbc);
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

    private void setTextFields(Commands type) {
        gbc.anchor= GridBagConstraints.WEST;
        switch(type) {
            case Commands.homesButton:
                for (int i = 0; i < labels.size(); i++) {
                    fields.add(new JTextField("", 15));
                    gbc.gridy = i;
                    gbc.insets = new Insets(10, 5, 10, 0);
                    add((JTextField)fields.get(i), gbc);
                }
                break;
            case Commands.unitsButton:
                for (int i = 0; i < labels.size(); i++) {
                    String labelText = labels.get(i).getText().replace(":", "");
                    gbc.gridy = i;
                    gbc.insets = new Insets(10, 5, 10, 0);
                    if(labelText.equals(ColumnData.UnitItemData.room_type.getColumnName())){
                        fields.add(setRoomComboBox());
                        add((JComboBox<String>)fields.get(i), gbc);
                    } else if(labelText.equals(ColumnData.UnitItemData.unit_type.getColumnName())){
                        fields.add(setUnitComboBox());
                        add((JComboBox<String>)fields.get(i), gbc);
                    } else if(labelText.equals(ColumnData.UnitItemData.priority.getColumnName())){
                        fields.add(setPriorityComboBox());
                        add((JComboBox<String>)fields.get(i), gbc);
                    } else {
                        fields.add(new JTextField("", 15));
                        add((JTextField)fields.get(i), gbc);
                    }
                }
                break;
            default:
        }
    }

    private JComboBox<String> setRoomComboBox() {
        List<String> options = new ArrayList<>();
        for (RoomType val:RoomType.values()) {
            options.add(val.getRoomType());
        }
        JComboBox<String> dropdown = new JComboBox<>(options.toArray(new String[0]));
        return dropdown;
    }

    private JComboBox<String> setUnitComboBox() {
        List<String> options = new ArrayList<>();
        for (UnitType val:UnitType.values()) {
            options.add(val.getUnitType());
        }
        JComboBox<String> dropdown = new JComboBox<>(options.toArray(new String[0]));
        return dropdown;
    }

    private JComboBox<String> setPriorityComboBox() {
        List<String> options = new ArrayList<>();
        for (PriorityType val:PriorityType.values()) {
            options.add(val.toString());
        }
        JComboBox<String> dropdown = new JComboBox<>(options.toArray(new String[0]));
        return dropdown;
    }
    
}
