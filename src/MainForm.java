import fileManager.AnagraFileManager;
import fileManager.FileManager;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainForm extends JFrame {
    private JButton button1;
    private JList list1;
    private JPanel rootPanel;

    public MainForm() {
        super();
        setContentPane(rootPanel);
        setVisible(true);
        pack();
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                String[] words = null;
                super.mouseClicked(e);
                FileManager fm = AnagraFileManager.getInstance();
                String fullName = fm.loadFile();
                try {
                    words = fm.readFile(fullName);
                } catch (Exception exc) {}

                list1.setListData(words);

            }
        });

    }
}
