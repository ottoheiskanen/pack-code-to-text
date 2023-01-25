import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {

        ArrayList<String> filePaths = new ArrayList<>();
        filePaths.add("/C:/Projects/School/Ohjelmointi2/viikko2/tehtava1/src/");
        filePaths.add("/C:/Projects/School/Ohjelmointi2/viikko2/tehtava2/src/");
        filePaths.add("/C:/Projects/School/Ohjelmointi2/viikko2/tehtava3/src");
        filePaths.add("/C:/Projects/School/Ohjelmointi2/viikko2/tehtava4/src/");
        filePaths.add("/C:/Projects/School/Ohjelmointi2/viikko2/tehtava7/src");

        // Add test to see if file ends with .txt
        HandleFiles input1 = new HandleFiles("output.txt", "/C:/Projects/School/Ohjelmointi2/", filePaths, ".java");
        //ArrayList<String[]> output = input1.listFilesFromEach();
        //System.out.println(output);

    }
}