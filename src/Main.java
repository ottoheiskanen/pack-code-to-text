import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        ArrayList<String> filePaths = new ArrayList<>();
        filePaths.add("/C:/Projects/School/Ohjelmointi2/viikko2/tehtava2/src/");
        filePaths.add("/C:/Projects/School/Ohjelmointi2/viikko2/tehtava3/src");

        HandleFiles input1 = new HandleFiles("txt", "asd", filePaths);
        //ArrayList<String[]> output = input1.listFilesFromEach();
        //System.out.println(output);

    }
}