import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.Scanner;

class MyFileReader {
    static String read(String path){
        try {
            Scanner scanner = new Scanner(Paths.get(path),
                    StandardCharsets.UTF_8.name());
            String text = scanner.useDelimiter("\n").next();

            scanner.close();

            return text;
        } catch (IOException | NoSuchElementException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
