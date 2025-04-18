import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTabbedPane;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import maintainhome.controller.Commands;
import maintainhome.view.View;

public class TestView {

    private View view;

    @BeforeEach
    public void setup() {
        view = new View("Home Maintenance");
        view.setSelectedCard(Commands.homesButton);
    }

    @Test
    public void testLoginUserRetrieval() {
        assertEquals("js1", view.getLoginUser());
    }

    @Test
    public void testSwitchMainPanel() {
        view.switchMainPanel("3");
    }

    @Test
    public void testSelectedCardSetterGetter() {
        view.setSelectedCard(Commands.unitsButton);
        assertEquals(Commands.unitsButton, view.getSelectedCard());
    }

    @Test
    public void testTabPaneAccessorsReturnTabs() {
        JTabbedPane homesTab = view.getHomesTab();
        JTabbedPane unitsTab = view.getUnitsTab();
        assertEquals(2, homesTab.getTabCount());
        assertEquals(2, unitsTab.getTabCount());
    }

    @Test
    public void testUpdateHomesListDoesNotCrash() {
        view.updateHomesList(new String[] { "Home A", "Home B" });
    }

    @Test
    public void testUpdateUnitsListDoesNotCrash() {
        view.updateUnitsList(new String[] { "Unit X", "Unit Y" });
    }

    @Test
    public void testUpdateHomesTableDoesNotCrash() {
        List<String[]> rows = new ArrayList<>();
        rows.add(new String[] { "101", "Home A", "123 St", "99999" });
        view.updateHomesTable(rows);
    }

    @Test
    public void testUpdateUnitsTableDoesNotCrash() {
        List<String[]> rows = new ArrayList<>();
        rows.add(new String[] { "U1", "Microwave", "Kitchen", "2022-01-01" });
        view.updateUnitsTable(rows);
    }
}
