import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import maintainhome.model.Home.Home;
import maintainhome.model.User.User;
import maintainhome.model.Utilities.Operations.Filters;
import maintainhome.model.Utilities.Operations.Sorters;

public class TestEdgeCases {

    private User testUser;

    @BeforeEach
    public void setup() {
        testUser = new User("js1", "Fake", "test@example.com");
    }

    @Test
    public void testHomesWithSameHomeNum() {
        Home home1 = new Home("1", 101, "Home A", "123 Main St", "12345");
        Home home2 = new Home("2", 101, "Home B", "456 Oak St", "12345");
        testUser.setHomes(List.of(home1, home2));
        testUser.getHomes().sort(Sorters.BY_HOME_NUM);
        assertEquals(101, testUser.getHomes().get(0).getHomeNum());
        assertEquals(101, testUser.getHomes().get(1).getHomeNum());
    }

    @Test
    public void testHomesWithMissingFields() {
        Home home1 = new Home("1", 101, "", "123 Main St", "");
        Home home2 = new Home("2", 3, "Home B", "", "54321");
        testUser.setHomes(List.of(home1, home2));
        assertNotNull(testUser.getHomes());
        assertEquals("", testUser.getHomes().get(0).getHomeName());
        assertEquals("2", testUser.getHomes().get(1).getHomeId());
    }

    @Test
    public void testHomesWithSpecialCharacters() {
        Home home1 = new Home("1", 102, "Home@#1", "123 Main St", "12345");
        Home home2 = new Home("2", 103, "Home$%2", "456 Oak St", "12345");
        testUser.setHomes(List.of(home1, home2));
        assertTrue(testUser.getHomes().get(0).getHomeName().contains("@#"));
        assertTrue(testUser.getHomes().get(1).getHomeName().contains("$%"));
    }

    @Test
    public void testEmptyHomeList() {
        testUser.setHomes(List.of());
        assertTrue(testUser.getHomes().isEmpty());
    }

    @Test
    public void testFilterHomesByNonExistName() {
        List<Home> filteredHomes = Filters.filterByHomeName(testUser.getHomes(), "NonExistent Home");
        assertTrue(filteredHomes.isEmpty());
    }

    @Test
    public void testFilterHomesByValidName() {
        Home home1 = new Home("1", 101, "Home A", "123 Main St", "12345");
        Home home2 = new Home("2", 102, "Home B", "456 Oak St", "54321");
        testUser.setHomes(List.of(home1, home2));
        List<Home> filteredHomes = Filters.filterByHomeName(testUser.getHomes(), "Home A");
        assertFalse(filteredHomes.isEmpty());
        assertEquals("Home A", filteredHomes.get(0).getHomeName());
    }

    @Test
    public void testSortHomesByHomeNum() {
        Home home1 = new Home("1", 102, "Home A", "123 Main St", "12345");
        Home home2 = new Home("2", 101, "Home B", "456 Oak St", "54321");
        testUser.setHomes(List.of(home1, home2));
        testUser.getHomes().sort(Sorters.BY_HOME_NUM);
        assertEquals(101, testUser.getHomes().get(0).getHomeNum());
        assertEquals(102, testUser.getHomes().get(1).getHomeNum());
    }

    @Test
    public void testFilterHomesByHomeName() {
        Home home1 = new Home("1", 101, "Home A", "123 Main St", "12345");
        Home home2 = new Home("2", 102, "Home B", "456 Oak St", "54321");
        testUser.setHomes(List.of(home1, home2));
        List<Home> filteredHomes = Filters.filterByHomeName(testUser.getHomes(), "Home A");
        assertFalse(filteredHomes.isEmpty());
        assertEquals("Home A", filteredHomes.get(0).getHomeName());
    }

    @Test
    public void testFilterByHomeNameCaseInsensitive() {
        // Add homes with mixed-case names
        Home home1 = new Home("1", 100, "Home A", "123 Main", "99999");
        Home home2 = new Home("2", 101, "home a", "456 Main", "99999");

        testUser.setHomes(List.of(home1, home2));

        List<Home> result = Filters.filterByHomeName(testUser.getHomes(), "HOME A");
        assertEquals(2, result.size(), "Expected both homes to be matched case-insensitively");
    }

}