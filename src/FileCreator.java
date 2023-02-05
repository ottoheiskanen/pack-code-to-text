import java.io.*;

public class FileCreator {

    public static String readContentToString(String path) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader( path ));
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        String ls = System.getProperty("line.separator");
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append(ls);
        }

        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        reader.close();

        String content = stringBuilder.toString();

        return content;
    }
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
