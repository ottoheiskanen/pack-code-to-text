import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;

public class HandleFiles {
    private String newFileName;
    private String newFileLocation;
    private ArrayList<String> filePaths;

    public HandleFiles(String newFileName, String newFileLocation, ArrayList<String> filePaths) {
        this.newFileName = newFileName;
        this.newFileLocation = newFileLocation;
        this.filePaths = filePaths;

        this.readFilesFromPath( listFilesFromEach() );
    }

    // Get all .java files inside given file paths and save them to arraylist arrays
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

    public void readFilesFromPath(ArrayList<String[]> files) {
        for (int i = 0; i < this.filePaths.size(); i++) {
            String[] a = files.get(i);
            for (int j = 0; j < a.length; j++) {

                String filePath;

                if (!(this.filePaths.get(i).endsWith("/"))) {
                    filePath = this.filePaths.get(i) + "/" +a[j];
                    System.out.println(filePath);
                } else {
                    filePath = this.filePaths.get(i)+a[j];
                    System.out.println(filePath);
                }

            }
        }
    }

    // Return files inside given
    /*public ArrayList getFilesContained() {
        ArrayList<String> containedFiles = null;
        for (int i = 0; i < this.filePaths.size(); i++) {

            containedFiles.add(this.filePaths.get(i));

        }
        return containedFiles;
    }*/
}
