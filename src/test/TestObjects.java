
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import maintainhome.model.Home.Home;


/*
 * Class to test the User, Home, unitItems classes.
 * 
 */
public class TestObjects {
    /**
     * Executed before every tests, resets the values of the unit objects.
     * 
     * User: 
     */
    private List<User> user;

    @BeforeEach
    public void setUp() {
        Set<User> user = CsvLoader.loadUserFile("js1");
        user = new ArrayList<>(user);
        System.out.println(userList.get(0).getUserId());
    }
    
    /**
     * Tests to make sure the unit id is returned properly.
     */
    @Test
    public void testGetFunctions() {
    // check IDs
    assertEquals("js1", user.getUserId);
    Home h1 = user.getHomes().get(0);
    assertEquals("1", h1.getHomeId());
    assertEquals("2514 E Harrison St. Carson", h1.getAddress());
    }

    /**
     * Tests to make sure the unit id is returned properly.
     */
    @Test
    public void testSetFunctions() {
        
    }
    
}
