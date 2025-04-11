
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

    // check result for when user is not found
    NumberFormatException exceptionH1 = Assertions.assertThrows(NullPointerException.class, () -> {
        user = CsvLoader.loadUserFile("js5");
    });
    Assertions.assertEquals("User Not Found.", exceptionH1.getMessage());
    // check exception for when home is not found in csv - will just not trigger this or load into object
    // check exception for when user has no homes listed
    // check for when no unitItems - will just not trigger this or load into object
    }


    /**
     * Tests to make sure the unit id is returned properly.
     */
    @Test
    public void testSetFunctions() {
        
    }
    
}
