package formMediator;

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

    }

    private void openFile() {

        JLabel fileLabel = (JLabel) form.getComponentByName("fileLabel");
        JList inputList = (JList) form.getComponentByName("inputList");

        String[] words = null;
        FileManager fm = AnagraFileManager.getInstance();
        String fullName = fm.loadFile();
        try {
            words = fm.readFile(fullName);
        } catch (Exception exc) {}

        fileLabel.setText(fullName);
        inputList.setListData(words);

    }

}
