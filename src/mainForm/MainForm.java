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
    private JProgressBar progressBar1;
    private JList outputList;
    private JButton exportButton;


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

        //configuring mediator
        mediator = new FormMediator(this);
        fileLabel.setName("fileLabel");
        minLettersField.setName("minLettersField");
        openFileButton.setName("openFileButton");
        createComponentMap();

        openFileButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mediator.trigger("openFileButton", "click");
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
        Component[] components = this.getContentPane().getComponents();
        for (int i=0; i < components.length; i++) {
            componentMap.put(components[i].getName(), (JComponent) components[i]);
        }
        componentMap.put("inputList", (JComponent) inputList);
        componentMap.put("outputList", (JComponent) outputList);
    }

    public JComponent getComponentByName(String name) {
        if (componentMap.containsKey(name)) {
            return (JComponent) componentMap.get(name);
        }
        else return null;
    }

}
