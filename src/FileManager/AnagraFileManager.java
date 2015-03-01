package fileManager;

import javax.swing.*;
import java.awt.*;
import java.io.*;
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
        String filename = fd.getFile();
        if (filename != null) {
            String directory = fd.getDirectory();
            return directory + filename;
        } else {
            return "";
        }

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

    public void saveFile(String[] words) {

        JFileChooser fileChooser = new JFileChooser();

        if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            PrintWriter writer;

            try {
                writer = new PrintWriter(file, "UTF-8");

                for (String word : words) {
                    writer.println(word+"\n");
                }

                writer.close();
            } catch (Exception e) {}

        }

    }

}
