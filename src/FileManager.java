import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileManager {
    public static String readFile(String filePath) throws IOException {
        // Логика чтения файла
        return Files.readString(Path.of(filePath));
    }
    public static void writeFile(String content, String filePath) throws IOException {
        // Логика записи файла
        Files.writeString(Path.of(filePath), content);
    }
}
