import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Scanner;

public class SimpleReader {
    public static String Read(String path){
        try {
            Scanner scanner = new Scanner(Paths.get(path),
                    StandardCharsets.UTF_8.name());
            String text = scanner.useDelimiter("\n").next();

            scanner.close();

            return text;
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
