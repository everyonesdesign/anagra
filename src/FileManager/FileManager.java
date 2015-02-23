package fileManager;

import java.io.IOException;

public interface FileManager {

    public String loadFile();
    public String[] readFile(String fullFilename) throws IOException;

}


