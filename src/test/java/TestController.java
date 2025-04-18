import static org.junit.jupiter.api.Assertions.*;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import maintainhome.controller.Commands;
import maintainhome.controller.Controller;
import maintainhome.model.Model;
import maintainhome.view.IView;

public class TestController {

    private Controller controller;
    private MockModel model;
    private MockView view;

    @BeforeEach
    public void setup() {
        model = new MockModel();
        view = new MockView();
        controller = new Controller(model, view);
    }

    @Test
    public void testCommandConversion() {
        assertEquals(Commands.loginButton, Commands.toCommand("Login"));
        assertEquals(Commands.userButton, Commands.toCommand("User"));
        assertEquals(Commands.unitsButton, Commands.toCommand("Units"));
        assertEquals(Commands.insertButton, Commands.toCommand("Add"));
    }

    @Test
    public void testCommandConversionThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Commands.toCommand("invalidCommand");
        });
        assertTrue(exception.getMessage().contains("No command with name"));
    }

    @Test
    public void testLoginButtonAction() {
        ActionEvent loginEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Login");
        controller.actionPerformed(loginEvent);

        assertTrue(view.loginPanelSwitched);
        assertTrue(view.userPanelUpdated);
    }

    @Test
    public void testUserButtonAction() {
        ActionEvent userEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "User");
        controller.actionPerformed(userEvent);

        assertEquals("User", view.lastSwitchedPanel);
        assertEquals(Commands.userButton, view.lastSelectedCard);
    }

    @Test
    public void testInsertButtonDelegatesToCorrectPanel() {
        view.selectedCard = Commands.homesButton;
        ActionEvent insertEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Add");
        controller.actionPerformed(insertEvent);

        assertTrue(model.homeAdded);
        assertTrue(view.homeListUpdated);
    }

    @Test
    public void testUpdateUserAction() {
        ActionEvent updateUser = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Update User");
        controller.actionPerformed(updateUser);
        assertTrue(view.userPanelUpdated);
    }

    @Test
    public void testClearButtonTriggersClearAddFields() {
        ActionEvent clearBtn = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Clear");
        controller.actionPerformed(clearBtn);
        assertTrue(view.clearedFields);
    }
}

class MockModel extends Model {
    public boolean homeAdded = false;

    @Override
    public void setUser(String userId) {}

    @Override
    public maintainhome.model.User.User getUser() {
        return new maintainhome.model.User.User("js1", "Test User", "test@example.com");
    }

    @Override
    public void setUserHomes() {}

    @Override
    public void setUserItems() {}

    @Override
    public String[] getHomeJList() {
        return new String[] { "Home A" };
    }

    @Override
    public List<String[]> getHomeRows() {
        List<String[]> rows = new ArrayList<>();
        rows.add(new String[] { "101", "Home A", "123 St", "99999" });
        return rows;
    }

    @Override
    public List<String[]> getUnitRows() {
        return new ArrayList<>();
    }

    @Override
    public List<String[]> getUnitRows(List list) {
        return new ArrayList<>();
    }

    @Override
    public String[] getUnitsJList() {
        return new String[0];
    }

    @Override
    public void setNewHome() {
        homeAdded = true;
    }

    @Override
    public void addHome() {}

    @Override
    public void saveHome() {}
}

class MockView implements IView {
    public boolean loginPanelSwitched = false;
    public boolean userPanelUpdated = false;
    public boolean homeListUpdated = false;
    public boolean clearedFields = false;
    public Commands selectedCard = Commands.homesButton;
    public String lastSwitchedPanel = null;
    public Commands lastSelectedCard = null;

    @Override
    public void setListener(java.awt.event.ActionListener al, java.awt.event.KeyListener kl) {}

    @Override
    public String getLoginUser() {
        return "js1";
    }

    @Override
    public void setUserPanel(String userId, String name, String email) {
        userPanelUpdated = true;
    }

    @Override
    public void switchMainPanel(String name) {
        loginPanelSwitched = true;
    }

    @Override
    public void setSelectedCard(Commands command) {
        lastSelectedCard = command;
    }

    @Override
    public Commands getSelectedCard() {
        return selectedCard;
    }

    @Override
    public void switchRightPanel(String name) {
        lastSwitchedPanel = name;
    }

    @Override
    public void updateHomesList(String[] values) {
        homeListUpdated = true;
    }

    @Override
    public void updateUnitsList(String[] values) {}

    @Override
    public void updateHomesTable(List<String[]> data) {}

    @Override
    public void updateUnitsTable(List<String[]> data) {}

    @Override
    public void setUnitsAddHomesDropDown(String[] values) {}

    @Override
    public String getRoomTypeSelected() {
        return null;
    }

    @Override
    public void clearAddFields() {
        clearedFields = true;
    }

    @Override
    public Map<String, String> getAddValues() {
        Map<String, String> values = new HashMap<>();
        values.put("itemName", "Microwave");
        values.put("roomName", "Kitchen");
        return values;
    }

    @Override
    public javax.swing.JTabbedPane getHomesTab() {
        javax.swing.JTabbedPane tab = new javax.swing.JTabbedPane();
        tab.addTab("Mock Tab", new javax.swing.JPanel());
        return tab;
    }

    @Override
    public javax.swing.JTabbedPane getUnitsTab() {
        return new javax.swing.JTabbedPane();
    }

    @Override
    public String getUpdatedUserName() {
        return "Updated";
    }

    @Override
    public String getUpdatedUserEmail() {
        return "updated@example.com";
    }

    @Override
    public void refreshUserPanelLabels() {
        userPanelUpdated = true;
    }
}
