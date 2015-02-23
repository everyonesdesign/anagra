import fileManager.AnagraFileManager;
import fileManager.FileManager;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainForm extends JFrame {
    private JButton openFileButton;
    private JList inputList;
    private JPanel rootPanel;
    private JButton generateButton;
    private JTextField minLettersField;
    private JLabel fileLabel;
    private JLabel minLettersLabel;
    private JProgressBar progressBar1;
    private JList list1;
    private JButton экспортButton;

    public MainForm() {
        super();
        setContentPane(rootPanel);
        setVisible(true);
        pack();
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        openFileButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                String[] words = null;
                super.mouseClicked(e);
                FileManager fm = AnagraFileManager.getInstance();
                String fullName = fm.loadFile();
                try {
                    words = fm.readFile(fullName);
                } catch (Exception exc) {}

                fileLabel.setText(fullName);
                inputList.setListData(words);

            }
        });

    }
}
