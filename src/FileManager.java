import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yura on 2/23/2015.
 */
public class FileManager {

    private static FileManager instance;
    private int counter;
    private String[] words;

    public static synchronized FileManager getInstance() {
        if (instance == null) {
            instance = new FileManager();
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

    public String[] read(String fullFilename) throws IOException {

            FileReader fileReader = new FileReader(fullFilename);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            List<String> lines = new ArrayList<String>();
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }
            bufferedReader.close();
            return lines.toArray(new String[lines.size()]);

    }

}
