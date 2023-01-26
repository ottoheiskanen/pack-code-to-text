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
    public Window() {

        setSize(640,480);
        setTitle("Code Packer");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);

        // Extension label
        fileExtensionLabel = new JLabel("Valitse haettavan koodin tiedostomuoto: ");
        fileExtensionLabel.setBounds(16,16,304,16);

        // Extension combo box
        String[] extensionStrings = {".java", ".py", ".js"};
        fileExtensionBox = new JComboBox(extensionStrings);
        fileExtensionBox.setBounds(16,32,304, 32);

        // File name label
        fileNameLabel = new JLabel("Lisää tekstitiedoston nimi .txt muodossa");
        fileNameLabel.setBounds(16+304+16,80,288,16);

        // File name input field
        fileNameInputField = new JTextField();
        fileNameInputField.setBounds(16+304+16,96,288,32);

        // File location label
        fileLocationLabel = new JLabel("Lisää polku johon tallennetaan");
        fileLocationLabel.setBounds(16,80,304,16);

        // File location field
        fileLocationField = new JTextField();
        fileLocationField.setBounds(16,96,304,32);

        // Input pane
        inputPane = new JTextArea();
        inputPane.setBounds(16,144,480,128);

        //Create file button
        createFileButton = new JButton("Luo tiedosto");
        createFileButton.setBounds(16,384,152, 32);
        createFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //String[] fileStringArray;
                String inputText = inputPane.getText().replace("\\", "/");
                String[] fileStringArray = inputText.split("\n");

                // Save input values into these
                String extension = (String) fileExtensionBox.getSelectedItem();
                String fileName = fileNameInputField.getText();
                String fileLocation = fileLocationField.getText().replace("\\", "/");
                ArrayList<String> filePaths = new ArrayList<>();

                // Normal array to arraylist for scalability of the program
                for (int i = 0; i < fileStringArray.length; i++) {
                    filePaths.add(fileStringArray[i]);
                }

                InputData data = new InputData(extension, fileName, fileLocation,filePaths);

                // Here we go
                try {
                    data.execute();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                System.out.println(data.getFilePaths());
            }
        });

        add(fileExtensionLabel);
        add(fileExtensionBox);
        add(fileNameLabel);
        add(fileNameInputField);
        add(fileLocationLabel);
        add(fileLocationField);
        add(createFileButton);
        add(inputPane);

        setVisible(true);

    }
}
