import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import maintainhome.model.Home.Home;
import maintainhome.model.Filters;
import maintainhome.model.Sorters;
import maintainhome.model.User.User;
import maintainhome.model.Utilities.CsvLoader;

public class EdgeCaseTesting {
    
    private User testUser;
    
    @BeforeEach
    public void setup() {
        // Setup test user and homes for the edge case tests
        String userId = "js1";
        testUser = CsvLoader.loadUserFile(userId);
        //testUser.setHomes(CsvLoader.loadHomesFile(userId));
    }
    
    @Test
    public void testHomesWithSameHomeNum() {
        // Assuming homes have the same home number for testing sorting and filtering
        Home home1 = new Home("1", 101, "Home A", "123 Main St", "12345");
        Home home2 = new Home("2", 101, "Home B", "456 Oak St", "12345");
        testUser.setHomes(List.of(home1, home2));

        // Sorting by homeNum using UnitSorters
        testUser.getHomes().sort(Sorters.BY_HOME_NUM);

        // Check if homes are sorted by homeNum (should be in ascending order)
        assertEquals(101, testUser.getHomes().get(0).getHomeNum());
        assertEquals(101, testUser.getHomes().get(1).getHomeNum());
    }
    
    @Test
    public void testHomesWithMissingFields() {
        // Test with homes having missing fields
        Home home1 = new Home("1", 101, "", "123 Main St", "");
        Home home2 = new Home("2", 3, "Home B", "", "54321");

        testUser.setHomes(List.of(home1, home2));

        // Check that homes with missing fields are handled
        assertNotNull(testUser.getHomes());
        assertEquals("", testUser.getHomes().get(0).getHomeName());
        assertEquals("2", testUser.getHomes().get(1).getHomeId());
    }
    
    @Test
    public void testHomesWithSpecialCharacters() {
        // Test with homes having special characters in the home name
        Home home1 = new Home("1", 102, "Home@#1", "123 Main St", "12345");
        Home home2 = new Home("2", 103, "Home$%2", "456 Oak St", "12345");

        testUser.setHomes(List.of(home1, home2));

        // Check that special characters in the home name are handled
        assertTrue(testUser.getHomes().get(0).getHomeName().contains("@#"));
        assertTrue(testUser.getHomes().get(1).getHomeName().contains("$%"));
    }

    @Test
    public void testEmptyHomeList() {
        // Test if there are no homes loaded
        testUser.setHomes(List.of());

        // Check that the home list is empty
        assertTrue(testUser.getHomes().isEmpty());
    }
    
    @Test
    public void testFilterHomesByNonExistName() {
        // Test filtering by a home name that doesn't exist using Filters
        String nonExistName = "NonExistent Home";
        List<Home> filteredHomes = Filters.filterByHomeName(testUser.getHomes(), nonExistName);

        // Check that the filtered list is empty
        assertTrue(filteredHomes.isEmpty());
    }


    @Test
    public void testFilterHomesByValidName() {
        // Test filtering by a valid home name using Filters
        String validHomeName = "Home A";
        List<Home> filteredHomes = Filters.filterByHomeName(testUser.getHomes(), validHomeName);

        // Check that the filtered list contains the home
        assertFalse(filteredHomes.isEmpty());
        assertEquals("Home A", filteredHomes.get(0).getHomeName());
    }

    // Test sorting by HOME_NUM using UnitSorters
    @Test
    public void testSortHomesByHomeNum() {
        // Create test homes
        Home home1 = new Home("1", 102, "Home A", "123 Main St", "12345");
        Home home2 = new Home("2", 101, "Home B", "456 Oak St", "54321");

        testUser.setHomes(List.of(home1, home2));

        // Sort by homeNum using UnitSorters
        testUser.getHomes().sort(Sorters.BY_HOME_NUM);

        // Check if the homes are sorted by homeNum in ascending order
        assertEquals(101, testUser.getHomes().get(0).getHomeNum());
        assertEquals(102, testUser.getHomes().get(1).getHomeNum());
    }

    // Test filtering by HOME_NAME using Filters
    @Test
    public void testFilterHomesByHomeName() {
        // Create test homes
        Home home1 = new Home("1", 101, "Home A", "123 Main St", "12345");
        Home home2 = new Home("2", 102, "Home B", "456 Oak St", "54321");

        testUser.setHomes(List.of(home1, home2));

        // Filter by a specific home name using Filters
        List<Home> filteredHomes = Filters.filterByHomeName(testUser.getHomes(), "Home A");

        // Check that only the home with "Home A" is returned
        assertFalse(filteredHomes.isEmpty());
        assertEquals("Home A", filteredHomes.get(0).getHomeName());
    }

}
