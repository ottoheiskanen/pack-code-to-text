import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class HandleFiles {
    private String newFileName;
    private String newFileLocation;
    private ArrayList<String> filePaths;
    private String extension;

    public HandleFiles(String newFileName, String newFileLocation, ArrayList<String> filePaths, String extension) throws IOException {
        this.newFileName = newFileName;
        this.newFileLocation = newFileLocation;
        this.filePaths = filePaths;
        this.extension = extension;

        // Check if location path ends with slash or not
        if (!(this.newFileLocation.endsWith("/"))) {
            this.newFileLocation += "/";
        }

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

    // Read given .extension file
    public String readContentToString(String path) throws IOException {
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

    public void createFile() throws IOException {
        File fileObj = new File(this.newFileLocation, this.newFileName);
        if (fileObj.createNewFile()) {
            System.out.println("File created: " + fileObj.getName());
        } else {
            System.out.println("File with the given name already exists...");
        }
    }

    public void writeToFile(String content) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(this.newFileLocation + this.newFileName));
        writer.write(content);
        writer.close();
        System.out.println("Successfully wrote to the file.");
    }

    // Read all files that are marked with right file extension and saved in their corresponding project folders
    // after that save them into an output .txt file in the right order
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

                if (filePath.endsWith( this.extension )) {
                    // Identify each project at the start of this loop
                    if (j == 0) {
                        String pathStr = this.filePaths.get(i);
                        String removeSrc = pathStr.substring(0,pathStr.length()-4);
                        if (removeSrc.endsWith("/")) {
                            removeSrc = removeSrc.substring(0,removeSrc.length()-1);
                        }
                        int lastToRemove = removeSrc.lastIndexOf("/") + 1;
                        String projectName = removeSrc.substring(lastToRemove);
                        content += "\n******************** "+projectName.toUpperCase()+" ********************\n";
                    }
                    content += readContentToString(filePath);
                }

            }
        }
        createFile();
        writeToFile(content);
    }

}
