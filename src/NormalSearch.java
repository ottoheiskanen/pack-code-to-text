import java.io.*;
import java.util.ArrayList;

public class NormalSearch {
    private String newFileName;
    private String newFileLocation;
    private ArrayList<String> filePaths;
    private String extension;

    public NormalSearch(String newFileName, String newFileLocation, ArrayList<String> filePaths, String extension) throws IOException {
        this.newFileName = newFileName.trim();
        this.newFileLocation = newFileLocation.replace(" ", "");
        this.filePaths = filePaths;
        this.extension = extension.trim();

        // Check if location path ends with slash or not
        if (!(this.newFileLocation.endsWith("/"))) {
            this.newFileLocation += "/";
        }

        // Check if file name ends with right extension or not
        if (!(this.newFileName.endsWith(".txt"))) {
            this.newFileName += ".txt";
        }

        // Check if there is empty line breaks or whitespaces in paths
        for (int i = 0; i < this.filePaths.size(); i++) {
            this.filePaths.set(i, this.filePaths.get(i).trim());
            if (this.filePaths.get(i).equals("")) {
                this.filePaths.remove(i);
            }
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
            System.out.println(files[i]);
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

                        content += "\n//******************** "+projectName.toUpperCase()+" ********************\n\n";
                    }
                    content += readContentToString(filePath);
                }

            }
        }
        FileCreator.createFile(this.newFileLocation, this.newFileName);
        FileCreator.writeToFile(content, this.newFileLocation, this.newFileName);
    }

}
