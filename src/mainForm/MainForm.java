package mainForm;

import fileManager.AnagraFileManager;
import fileManager.FileManager;
import formMediator.FormMediator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

public class MainForm extends JFrame {
    private JScrollPane JScrollPane1;
    private JScrollPane JScrollPane2;
    private JButton openFileButton;
    private JList inputList;
    private JPanel rootPanel;
    private JButton generateButton;
    private JTextField minLettersField;
    private JLabel fileLabel;
    private JLabel minLettersLabel;
    private JProgressBar generationProgress;
    private JList outputList;
    private JButton exportButton;
    private JLabel statusLabel;

    private HashMap<String,Component> componentMap;
    private FormMediator mediator;

    public MainForm() {
        super();
        setContentPane(rootPanel);
        setVisible(true);
        pack();
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //centering window
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

        //configuring progress bar
        generationProgress.setStringPainted(true);

        //configuring mediator
        mediator = new FormMediator(this);
        createComponentMap();

        openFileButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mediator.trigger("openFileButton", "click");
            }
        });

        exportButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mediator.trigger("exportButton", "click");
            }
        });

        generateButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mediator.trigger("generateButton", "click");
            }
        });

    }

    private void createComponentMap() {
        componentMap = new HashMap<String,Component>();
        componentMap.put("inputList", (JComponent) inputList);
        componentMap.put("outputList", (JComponent) outputList);
        componentMap.put("fileLabel", (JComponent) fileLabel);
        componentMap.put("minLettersField", (JComponent) minLettersField);
        componentMap.put("openFileButton", (JComponent) openFileButton);
        componentMap.put("generationProgress", (JComponent) generationProgress);
        componentMap.put("generateButton", (JComponent) generateButton);
        componentMap.put("statusLabel", (JComponent) statusLabel);
        componentMap.put("exportButton", (JComponent) exportButton);
    }

    public JComponent getComponentByName(String name) {
        if (componentMap.containsKey(name)) {
            return (JComponent) componentMap.get(name);
        }
        else return null;
    }

}
