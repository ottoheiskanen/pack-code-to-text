import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class Window extends JFrame {
    JLabel fileExtensionLabel;
    JComboBox fileExtensionBox;
    JLabel fileNameLabel;
    JTextField fileNameInputField;
    JLabel fileLocationLabel;
    JTextField fileLocationField;
    JTextArea inputPane;
    JButton createFileButton;
    JLabel inputLabel;
    JOptionPane messageBox ;

    JLabel searchBoxLabel;
    JCheckBox searchBox;

    public Window() {

        setSize(640,480);
        setTitle("Code Packer");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(Color.getHSBColor(150, 150, 220));

        // Extension label
        fileExtensionLabel = new JLabel("Valitse haettavan koodin tiedostomuoto: ");
        fileExtensionLabel.setBounds(16,16,304,16);

        // Extension combo box
        String[] extensionStrings = {".java", ".js"};
        fileExtensionBox = new JComboBox(extensionStrings);
        fileExtensionBox.setBounds(16,32,304, 32);

        // File name label
        fileNameLabel = new JLabel("Lisää UUDEN tekstitiedoston nimi");
        fileNameLabel.setBounds(16+304+16,80,272,16);

        // File name input field
        fileNameInputField = new JTextField();
        fileNameInputField.setBounds(16+304+16,96,272,32);

        // File location label
        fileLocationLabel = new JLabel("Lisää polku johon tekstitiedosto tallennetaan");
        fileLocationLabel.setBounds(16,80,304,16);

        // File location field
        fileLocationField = new JTextField();
        fileLocationField.setBounds(16,96,304,32);

        // Input label
        inputLabel = new JLabel("Lisää tehtävien polut uudelle riville. Polun täytyy päättyä muodossa: '.../tehtävän-nimi/src/'");
        inputLabel.setBounds(16,144, 588,16);

        // Input pane
        inputPane = new JTextArea();
        inputPane.setBounds(16,160,588,224);

        // Alert
        messageBox = new JOptionPane();

        // Search checkbox label
        searchBoxLabel = new JLabel("Etsi alihakemistoista");
        searchBoxLabel.setBounds(336,16,272,16);

        // Search checkbox
        searchBox = new JCheckBox();
        searchBox.setBounds(336, 32, 288, 32);

        //Create file button
        createFileButton = new JButton("Luo tiedosto");
        createFileButton.setBounds(16,396,152, 32);
        createFileButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed (ActionEvent e){
                String inputText = inputPane.getText().replace("\\", "/");
                String[] fileStringArray = inputText.split("\n");

                // Save input values into these
                String extension = (String) fileExtensionBox.getSelectedItem();
                String fileName = fileNameInputField.getText();
                String fileLocation = fileLocationField.getText().replace("\\", "/");
                boolean searchSubDirs = false;
                if (searchBox.isSelected()) {
                    searchSubDirs = true;
                }

                ArrayList<String> filePaths = new ArrayList<>();

                // Normal array to arraylist for scalability of the program
                for (int i = 0; i < fileStringArray.length; i++) {
                    // Test case
                    fileStringArray[i] = fileStringArray[i].trim();
                    if (!fileStringArray[i].equals("") ) {
                        //add
                        filePaths.add(fileStringArray[i]);
                    }
                }

                InputData data = new InputData(extension, fileName, fileLocation, filePaths, searchSubDirs);

                // Start execution
                try {
                    System.out.println(searchSubDirs);
                    data.execute();
                    infoBox("Tiedosto luotu onnistuneesti!", "Tiedosto luotu");
                } catch (IOException ex) {
                    infoBox("Tiedoston luominen epäonnistui!", "Virhe");
                    throw new RuntimeException(ex);
                }

            }
        });

        add(fileExtensionLabel);
        add(fileExtensionBox);
        add(fileNameLabel);
        add(fileNameInputField);
        add(fileLocationLabel);
        add(fileLocationField);
        add(createFileButton);
        add(inputLabel);
        add(inputPane);
        add(searchBoxLabel);
        add(searchBox);
        setVisible(true);
    }

    private static void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
}
