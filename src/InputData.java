import java.io.IOException;
import java.util.ArrayList;

public class InputData {
    private String fileExtension;
    private String fileName;
    private String locationPath;
    private ArrayList<String> filePaths;

    public InputData(String fileExtension, String fileName, String locationPath, ArrayList<String> filePaths) {
        this.fileName = fileName;
        this.fileExtension = fileExtension;
        this.filePaths = filePaths;
        this.locationPath = locationPath;
    }

    //String newFileName, String newFileLocation, ArrayList<String> filePaths, String extension
    public void execute() throws IOException {
        new HandleFiles(this.fileName, this.locationPath, this.filePaths, this.fileExtension);
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
