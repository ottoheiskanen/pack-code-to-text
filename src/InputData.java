import java.io.IOException;
import java.util.ArrayList;

// Middleware class between GUI and file handling
public class InputData {
    private String fileExtension;
    private String fileName;
    private String locationPath;
    private ArrayList<String> filePaths;

    private boolean subDirs;

    public InputData(String fileExtension, String fileName, String locationPath, ArrayList<String> filePaths, boolean subDirs) {
        this.fileName = fileName;
        this.fileExtension = fileExtension;
        this.filePaths = filePaths;
        this.locationPath = locationPath;
        this.subDirs = subDirs;
    }

    public void execute() throws IOException {
        if (this.subDirs) {
            RecursiveSearch rs = new RecursiveSearch(this.fileExtension, this.locationPath, this.fileName,this.filePaths);
            rs.execute();
        } else {
            new HandleFiles(this.fileName, this.locationPath, this.filePaths, this.fileExtension);
        }
    }

    // Getters
    public String getFileExtension() {
        return fileExtension;
    }

    public String getLocationPath() {
        return locationPath;
    }

    public ArrayList<String> getFilePaths() {
        return filePaths;
    }

    public String getFileName() {
        return fileName;
    }

    // Setters
    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setLocationPath(String locationPath) {
        this.locationPath = locationPath;
    }

    public void setFilePaths(ArrayList<String> filePaths) {
        this.filePaths = filePaths;
    }
}
