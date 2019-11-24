import java.io.FileWriter;
import java.io.IOException;

class SimpleWriter {

    static void Write(String text, String path) {
        try (FileWriter writer = new FileWriter(path, false)) {
            writer.write(text);
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
