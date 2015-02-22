import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

/**
 * Created by Yura on 2/21/2015.
 */
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
                FileManager fm = FileManager.getInstance();
                String fullName = fm.loadFile();
                try {
                    words = fm.read(fullName);
                } catch (Exception exc) {}
                System.out.println(Arrays.toString(words));

            }
        });

    }
}
