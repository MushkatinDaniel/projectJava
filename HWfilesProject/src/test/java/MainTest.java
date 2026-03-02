import org.junit.jupiter.api.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    private File tempDir;

    @BeforeEach
    public void setUp() throws IOException {
        tempDir = Files.createTempDirectory("games_test").toFile();
    }

    @AfterEach
    public void tearDown() {
        deleteRecursively(tempDir);
    }

    @Test
    public void testCreateDirectory_newDirectory_success() {
        // given:
        File dir = new File(tempDir, "src");
        StringBuilder log = new StringBuilder();

        // when:
        createDirectory(dir, log);

        // then:
        assertTrue(dir.exists(), "Директория должна существовать");
        assertTrue(dir.isDirectory(), "Созданный объект должен быть директорией");
        assertTrue(log.toString().contains("Директория создана"), "Лог должен содержать сообщение об успехе");
    }

    @Test
    public void testCreateDirectory_alreadyExists_logsFailure() {
        // given:
        File dir = new File(tempDir, "existing");
        dir.mkdir();
        StringBuilder log = new StringBuilder();

        // when:
        createDirectory(dir, log);

        // then:
        assertTrue(dir.exists(), "Директория должна по-прежнему существовать");
        assertTrue(log.toString().contains("Не удалось создать директорию"),
                "Лог должен сообщать о невозможности создания");
    }

    @Test
    public void testCreateFile_newFile_success() throws IOException {
        // given:
        File file = new File(tempDir, "Main.java");
        StringBuilder log = new StringBuilder();

        // when:
        createFile(file, log);

        // then:
        assertTrue(file.exists(), "Файл должен существовать");
        assertTrue(file.isFile(), "Созданный объект должен быть файлом");
        assertTrue(log.toString().contains("Файл создан"), "Лог должен содержать сообщение об успехе");
    }

    private static void createDirectory(File dir, StringBuilder log) {
        if (dir.mkdir()) {
            log.append("Директория создана: ").append(dir.getPath()).append("\n");
        } else {
            log.append("Не удалось создать директорию: ").append(dir.getPath()).append("\n");
        }
    }

    private static void createFile(File file, StringBuilder log) {
        try {
            if (file.createNewFile()) {
                log.append("Файл создан: ").append(file.getPath()).append("\n");
            } else {
                log.append("Не удалось создать файл: ").append(file.getPath()).append("\n");
            }
        } catch (IOException e) {
            log.append("Ошибка при создании файла ").append(file.getPath())
                    .append(": ").append(e.getMessage()).append("\n");
        }
    }

    private void deleteRecursively(File file) {
        if (file.isDirectory()) {
            for (File child : file.listFiles()) {
                deleteRecursively(child);
            }
        }
        file.delete();
    }
}
