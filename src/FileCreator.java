import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileCreator {
    public static void createFile(String newFileLocation, String newFileName) throws IOException {
        File fileObj = new File(newFileLocation, newFileName);
        if (fileObj.createNewFile()) {
            System.out.println("File created: " + fileObj.getName());
        } else {
            System.out.println("File with the given name already exists...");
        }
    }
    public static void writeToFile(String content, String newFileLocation, String newFileName) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(newFileLocation + newFileName));
        writer.write(content);
        writer.close();
        System.out.println("Successfully wrote to the file.");
    }

}
