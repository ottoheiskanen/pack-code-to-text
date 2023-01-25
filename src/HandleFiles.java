import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class HandleFiles {
    private String newFileName;
    private String newFileLocation;
    private ArrayList<String> filePaths;

    public HandleFiles(String newFileName, String newFileLocation, ArrayList<String> filePaths) throws IOException {
        this.newFileName = newFileName;
        this.newFileLocation = newFileLocation;
        this.filePaths = filePaths;

        this.readFilesFromPath( listFilesFromEach() );
    }

    // Get all files inside given file paths and save them to arraylist arrays
    public ArrayList<String[]> listFilesFromEach() {
        ArrayList<String[]> filesFromEach = new ArrayList<>();
        for (int i = 0; i < this.filePaths.size(); i++) {
            File file = new File( this.filePaths.get(i) );
            String[] files = file.list(new FilenameFilter() {
                @Override
                public boolean accept(File current, String name) {
                    return new File(current, name).isFile();
                }
            });
            filesFromEach.add(files);
        }

        return filesFromEach;
    }

    //read given .java file
    public String readJavaFile(String path) throws IOException {
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

        String javaCode = stringBuilder.toString();

        return javaCode;
    }

    public void createFile() throws IOException {
        File fileObj = new File(this.newFileName);
        if (fileObj.createNewFile()) {
            System.out.println("File created: " + fileObj.getName());
        } else {
            System.out.println("File with the given name already exists...");
        }
    }

    public void writeToFile(String content) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(this.newFileName));
        writer.write(content);
        writer.close();
        System.out.println("Successfully wrote to the file.");
    }

    // Read all .java files related to each individual project folder and save them into an output .txt file
    public void readFilesFromPath(ArrayList<String[]> files) throws IOException {
        String content = "";
        for (int i = 0; i < this.filePaths.size(); i++) {
            String[] a = files.get(i);
            for (int j = 0; j < a.length; j++) {

                String filePath;

                if (!(this.filePaths.get(i).endsWith("/")))  {
                    filePath = this.filePaths.get(i) + "/" +a[j];
                } else {
                    filePath = this.filePaths.get(i)+a[j];
                }

                if (filePath.endsWith(".java")) {
                    //System.out.println(filePath);
                    //System.out.println( readJavaFile(filePath) );
                    content += readJavaFile(filePath);
                }
            }
        }
        createFile();
        writeToFile(content);
        //System.out.println(content);
    }

}
