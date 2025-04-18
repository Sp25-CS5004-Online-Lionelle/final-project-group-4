import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import maintainhome.model.User.User;
import maintainhome.model.Home.Home;

public class TestUser {

    @Test
    public void testUserConstructorAndGetters() {
        User user = new User("js1", "John Smith", "john@example.com");

        assertEquals("js1", user.getUserId());
        assertEquals("John Smith", user.getName());
        assertEquals("john@example.com", user.getEmail());
        assertTrue(user.getHomes().isEmpty());
    }

    @Test
    public void testSetNameAndEmail() {
        User user = new User("js2", "Old Name", "old@example.com");

        user.setName("New Name");
        user.setEmail("new@example.com");

        assertEquals("New Name", user.getName());
        assertEquals("new@example.com", user.getEmail());
    }

    @Test
    public void testAddSingleHome() {
        User user = new User("js3", "User A", "a@example.com");
        Home home = new Home("H1", 101, "Home A", "123 Street", "90001");

        user.setHome(home);

        assertEquals(1, user.getHomes().size());
        assertEquals("Home A", user.getHomes().get(0).getHomeName());
    }

    @Test
    public void testAddMultipleHomes() {
        User user = new User("js4", "User B", "b@example.com");

        Home home1 = new Home("H2", 102, "Home B", "456 Avenue", "90002");
        Home home2 = new Home("H3", 103, "Home C", "789 Blvd", "90003");

        user.setHomes(List.of(home1, home2));

        assertEquals(2, user.getHomes().size());
        assertEquals("Home C", user.getHomes().get(1).getHomeName());
    }

    @Test
    public void testGetNextHomeNum() {
        User user = new User("js5", "User C", "c@example.com");

        assertEquals(1, user.getNextHomeNum());  // nothing added yet

        Home home1 = new Home("H4", 101, "First Home", "Addr 1", "11111");
        Home home2 = new Home("H5", 202, "Second Home", "Addr 2", "22222");

        user.setHome(home1);
        user.setHome(home2);

        assertEquals(203, user.getNextHomeNum()); // should return max+1
    }

    @Test
    public void testFindHomeByName() {
        User user = new User("js6", "User D", "d@example.com");

        Home home1 = new Home("H6", 301, "Beach House", "Ocean Dr", "33333");
        Home home2 = new Home("H7", 302, "Mountain Cabin", "Hill Rd", "44444");

        user.setHomes(List.of(home1, home2));

        Home found = user.findHomeByName("Beach House");
        assertNotNull(found);
        assertEquals("Ocean Dr", found.getAddress());

        Home notFound = user.findHomeByName("Not Exist");
        assertNull(notFound);
    }
}
