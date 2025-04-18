package maintainhome.model.Utilities;

import java.io.File;
/**
 * The interface for the CSV loader and updater
 */
public interface ICsvSource {
    /** The filepath to the files */
    static String filePath = new File("").getAbsolutePath()
        .concat("\\src\\main\\resources\\files\\");
        

}
