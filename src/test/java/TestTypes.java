import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import maintainhome.model.Utilities.Types.FileType;

public class TestTypes {

    @Test
    public void testGetFileName() {
        assertEquals("user.csv", FileType.USER.getFileName());
        assertEquals("homes.csv", FileType.HOMES.getFileName());
        assertEquals("user_homes.csv", FileType.USER_HOMES.getFileName());
        assertEquals("unit_items.csv", FileType.UNIT_ITEMS.getFileName());
    }

    @Test
    public void testFromFileNameValid() {
        assertEquals(FileType.USER, FileType.fromFileName("user.csv"));
        assertEquals(FileType.UNIT_ITEMS, FileType.fromFileName("unit_items.csv"));
    }

    @Test
    public void testFromFileNameInvalidThrows() {
        assertThrows(IllegalArgumentException.class, () -> {
            FileType.fromFileName("invalid.csv");
        });
    }

    @Test
    public void testFromStringValid() {
        assertEquals(FileType.USER, FileType.fromString("user"));
        assertEquals(FileType.USER, FileType.fromString("USER"));
        assertEquals(FileType.USER, FileType.fromString("user.csv"));
    }

    @Test
    public void testFromStringInvalidThrows() {
        assertThrows(IllegalArgumentException.class, () -> {
            FileType.fromString("not_a_type");
        });
    }
}
