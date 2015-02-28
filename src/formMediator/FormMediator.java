package formMediator;

import anagramFinder.DefaultAnagramFinder;
import fileManager.AnagraFileManager;
import fileManager.FileManager;
import mainForm.MainForm;
import javax.swing.*;

public class FormMediator {

    private MainForm form;

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
                getAnagrams();
            }
        }

    }

    private void openFile() {

        JLabel fileLabel = (JLabel) form.getComponentByName("fileLabel");
        JList inputList = (JList) form.getComponentByName("inputList");

        String[] words = null;
        FileManager fm = AnagraFileManager.getInstance();
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
        //generationProgress.repaint();

    }

    public void setOutputListData(String[] outputWords) {
        JList outputList = (JList) form.getComponentByName("outputList");
        outputList.setListData(outputWords);
    }

    private void getAnagrams() {

        JList inputList = (JList) form.getComponentByName("inputList");
        JList outputList = (JList) form.getComponentByName("outputList");


        DefaultAnagramFinder af = new DefaultAnagramFinder();

        ListModel model = inputList.getModel();
        int modelSize = model.getSize();
        String[] inputWords = new String[modelSize];
        for (int i=0; i<modelSize; i++) {
            inputWords[i] = model.getElementAt(i).toString();
        }

        af.generateAnagrams(inputWords, this);
    }

}
