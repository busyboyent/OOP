package project;

import java.io.FileWriter;
import java.io.IOException;

class MyFileWriter {

    static void write(String text, String path) {
        try (FileWriter writer = new FileWriter(path, false)) {
            writer.write(text);
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
