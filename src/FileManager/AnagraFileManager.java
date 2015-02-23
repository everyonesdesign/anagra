package fileManager;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class AnagraFileManager implements FileManager {

    //singleton class
    private static AnagraFileManager instance;

    public static synchronized AnagraFileManager getInstance() {
        if (instance == null) {
            instance = new AnagraFileManager();
        }
        return instance;
    }

    public String loadFile() {
        FileDialog fd = new FileDialog(new JFrame(), "Выберите файл", FileDialog.LOAD);
        fd.setFile("*.txt");
        fd.setVisible(true);
        String directory = fd.getDirectory();
        String filename = fd.getFile();
        return directory + filename;
    }

    public String[] readFile(String fullFilename) throws IOException {

        FileReader fileReader = new FileReader(fullFilename);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        java.util.List<String> lines = new ArrayList<String>();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            lines.add(line);
        }
        bufferedReader.close();
        return lines.toArray(new String[lines.size()]);

    }

}
