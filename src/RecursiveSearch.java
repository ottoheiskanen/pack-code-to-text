import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class RecursiveSearch {

    private ArrayList<String> givenPaths;
    private String extension;
    private ArrayList<String> allFiles = new ArrayList<String>();
    private ArrayList<String[]> projectFiles = new ArrayList<String[]>();
    private File[] projectPaths;

    private String newFileName;
    private String newFileLocation;


    public RecursiveSearch(String extension, String newFileLocation, String newFileName, ArrayList <String> givenPaths ) {
        this.extension = extension;
        this.newFileLocation = newFileLocation;
        this.newFileName = newFileName;
        this.givenPaths = givenPaths;
    }

    /**
     * Start executing...
     */
    public void execute() throws IOException {
        long startTime = System.nanoTime(); // Start measuring time
        this.convert();
        this.search(this.projectPaths, 0, 0);
        this.separate(this.givenPaths);

        // Measure execution time
        long endTime = System.nanoTime();
        long execTime = endTime - startTime;
        System.out.println((double) execTime / 1_000_000_000);
    }

    /**
     * Convert projectPaths arraylist to File[] paths
     */
    public void convert() {
        this.projectPaths = new File[this.givenPaths.size()];
        for (int i = 0; i < this.givenPaths.size(); i++) {
            this.projectPaths[i] = new File(this.givenPaths.get(i).toLowerCase().replace("\\", "/"));
        }
    }

    /**
     * Search through subfolders
     * @param arr File array to search from
     * @param index index on File array
     * @param level Recursion depth
     */
    public void search(File[] arr, int index, int level) {
        // terminate condition
        if (index == arr.length) {
            return;}
        // for files
        if (arr[index].isFile()) {
            this.allFiles.add(String.valueOf(arr[index]).toLowerCase().replace("\\", "/"));
        } else if (arr[index].isDirectory()) {
            // recursion for sub-directories
            search(arr[index].listFiles(), 0,
                    level + 1);}
        // recursion for main directory
        search(arr, ++index, level);
    }

    /**
     * Separate each corresponding project contents to 2d array elements ([projectIndex][projectFileIndex])
     * @param filePaths Get length of original file path input to determine max length of projectFileIndex
     * (!!! ADD SPECIFIC TEST CASES TO PARSE INPUT/OUTPUT OF THE FINAL PATHS!!! )
     */
    public void separate(ArrayList <String> filePaths) throws IOException {
        String[][] tempFiles = new String[filePaths.size()][this.allFiles.size()];
        for (int i = 0; i < filePaths.size(); i++) {
            for (int j = 0; j < this.allFiles.size(); j++) {
                if (this.allFiles.get(j).toLowerCase().contains( filePaths.get(i).toLowerCase())
                        && this.allFiles.get(j).contains(this.extension)) {
                    tempFiles[i][j] = this.allFiles.get(j);
                }
            }
        }
        removeNulls(tempFiles);
    }

    /**
     * Remove null indexes from 2d array
     * @param tempFiles
     */
    public void removeNulls(String[][] tempFiles) throws IOException {
        for (int i = 0; i < tempFiles.length; i++) {
            String[] current = tempFiles[i];
            current = Arrays.stream(current).filter(s -> (s != null && s.length() > 0)).toArray(String[]::new);
            this.projectFiles.add(current);
        }
        readPaths(this.projectFiles);
    }

    public void readPaths(ArrayList<String[]> files) throws IOException {
        String content = "";
        for (int i = 0; i < files.size(); i++) {

            for (int j = 0; j < files.get(i).length; j++) {
                // Add project name commented to each project
                if (j == 0) {
                    int index = files.get(i)[j].indexOf("/src/");
                    String projectName = files.get(i)[j].substring(0, index);
                    int lastSlash = projectName.lastIndexOf("/");
                    int len = projectName.length();
                    projectName = projectName.substring(lastSlash, len);
                    content += "\n// ***************** " + projectName + " *****************\n\n";
                }
                content += FileCreator.readContentToString(files.get(i)[j]);
            }
        }
        System.out.println(content);
        FileCreator.createFile(this.newFileLocation, this.newFileName);
        FileCreator.writeToFile(content, this.newFileLocation, this.newFileName);
    }
}
