import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import maintainhome.model.User.User;
import maintainhome.model.Home.Home;
import maintainhome.model.Home.UnitItems.IUnit;
import maintainhome.model.Utilities.CsvLoader;
import maintainhome.model.Utilities.CsvUpdater;
import maintainhome.model.Utilities.Types.FileType;

public class TestCsvLoadSave {

    @TempDir
    Path tempDir;

    Path userCsv;
    Path homesCsv;
    Path userHomesCsv;

    @BeforeEach
    public void setup() throws IOException {
        // Write temp user.csv
        userCsv = tempDir.resolve("user.csv");
        Files.write(userCsv, List.of(
            "Username,Name,Email",
            "js1,John Smith,john@example.com"
        ));

        // Write temp homes.csv
        homesCsv = tempDir.resolve("homes.csv");
        Files.write(homesCsv, List.of(
            "Home ID,Home Row,Home Name,Address,Zip",
            "H001,101,Home A,123 Main St,12345"
        ));

        // Write temp user_homes.csv
        userHomesCsv = tempDir.resolve("user_homes.csv");
        Files.write(userHomesCsv, List.of(
            "User ID,Home ID",
            "js1,H001"
        ));

        CsvLoader.overrideFilePath(tempDir.toString() + "/");
        CsvUpdater.overrideFilePath(tempDir.toString() + "/");
    }

    @Test
    public void testLoadUserFile() {
        User user = CsvLoader.loadUserFile("js1");
        assertNotNull(user);
        assertEquals("js1", user.getUserId());
        assertEquals("John Smith", user.getName());
    }

    @Test
    public void testLoadHomesForUser() {
        List<Home> homes = CsvLoader.loadHomesFile("js1");
        assertEquals(1, homes.size());
        assertEquals("Home A", homes.get(0).getHomeName());
    }

    @Test
    public void testLoadUserHomesSet() {
        var homeIds = CsvLoader.loadUserHomesFile("js1");
        assertTrue(homeIds.contains("H001"));
    }

    @Test
    public void testUpdateCsvFileAppends() {
        Map<String, String> newRow = Map.of(
            "Username", "js2",
            "Name", "Alice",
            "Email", "alice@example.com"
        );

        CsvUpdater.writeCsvFile(FileType.USER, newRow);
        List<String> lines = readFile(userCsv);
        assertTrue(lines.get(lines.size() - 1).contains("alice@example.com"));
    }

    private List<String> readFile(Path file) {
        try {
            return Files.readAllLines(file);
        } catch (IOException e) {
            return List.of();
        }
    }
}
