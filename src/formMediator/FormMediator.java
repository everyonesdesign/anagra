package formMediator;

import anagramFinder.AnagramFinder;
import anagramFinder.DefaultAnagramFinder;
import anagramFinder.QuickAnagramFinder;
import fileManager.AnagraFileManager;
import mainForm.MainForm;
import javax.swing.*;
import java.io.File;
import java.io.PrintWriter;

public class FormMediator {

    private MainForm form;
    private boolean generationInProgress = false;
    private Thread generationThread;

    public FormMediator(MainForm form) {
        this.form = form;
    }

    public void trigger(String componentName, String eventName) {

        if (componentName.equals("openFileButton")) {
            if (eventName.equals("click")) {
                openFile();
            }
        }

        if (componentName.equals("generateButton")) {
            if (eventName.equals("click")) {
                if (generationInProgress) {
                    generationThread.interrupt();
                    afterGetAnagrams();
                } else {
                    getAnagrams();
                }
            }
        }

        if (componentName.equals("exportButton")) {
            if (eventName.equals("click")) {
                saveResults();
            }
        }

    }

    private void openFile() {

        JLabel fileLabel = (JLabel) form.getComponentByName("fileLabel");
        JList inputList = (JList) form.getComponentByName("inputList");

        String[] words = null;
        AnagraFileManager fm = AnagraFileManager.getInstance();
        String fullName = fm.loadFile();

        if (!fullName.equals("")) {

            try {
                words = fm.readFile(fullName);
            } catch (Exception exc) {}

            fileLabel.setText(fullName);
            inputList.setListData(words);

        }

    }

    public int getMinLetters() throws NumberFormatException {
        JTextField minLettersField = (JTextField) form.getComponentByName("minLettersField");
        int minLettersFiendValue;
        minLettersFiendValue = Integer.parseInt(minLettersField.getText());
        return minLettersFiendValue;
    }

    public void setProgress(int value) {

        JProgressBar generationProgress = (JProgressBar) form.getComponentByName("generationProgress");
        generationProgress.setValue(value);
        generationProgress.setString(Integer.toString(value)+"%");

    }

    public void setOutputListData(String[] outputWords) {
        JList outputList = (JList) form.getComponentByName("outputList");
        outputList.setListData(outputWords);
    }

    public void beforeGetAnagrams() {
        JButton generateButton = (JButton) form.getComponentByName("generateButton");
        JLabel statusLabel = (JLabel) form.getComponentByName("statusLabel");
        generateButton.setText("Остановить");
        statusLabel.setText("Генерация...");
        generationInProgress = true;
    }

    public void afterGetAnagrams() {
        JButton generateButton = (JButton) form.getComponentByName("generateButton");
        JLabel statusLabel = (JLabel) form.getComponentByName("statusLabel");
        statusLabel.setText("");
        generationInProgress = false;
        generateButton.setText("Генерировать слова");
    }

    private void getAnagrams() {

        JList inputList = (JList) form.getComponentByName("inputList");

        final AnagramFinder af = new QuickAnagramFinder();
        final FormMediator mediator = this;

        beforeGetAnagrams();

        ListModel model = inputList.getModel();
        int modelSize = model.getSize();
        final String[] inputWords = new String[modelSize];
        for (int i=0; i<modelSize; i++) {
            inputWords[i] = model.getElementAt(i).toString();
        }

        generationThread = new Thread(new Runnable() {

            @Override
            public void run() {
                af.generateAnagrams(inputWords, mediator);
            }

        });
        generationThread.start();

    }

    private void saveResults() {

        AnagraFileManager fm = AnagraFileManager.getInstance();
        JList outputList = (JList) form.getComponentByName("outputList");

        //get generated words
        ListModel model = outputList.getModel();
        int modelSize = model.getSize();
        String[] outputWords = new String[modelSize];
        for (int i=0; i<modelSize; i++) {
            outputWords[i] = model.getElementAt(i).toString();
        }

        fm.saveFile(outputWords);

    }

}
