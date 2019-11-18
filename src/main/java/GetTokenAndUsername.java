import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Scanner;

class GetTokenAndUsername {

    static String getBotUsername() {
        return Read("src\\main\\resources\\botusername.txt");
    }

    static String getBotToken() {
        return Read("src\\main\\resources\\token.txt");

    }
    
    private static String Read(String path){
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
