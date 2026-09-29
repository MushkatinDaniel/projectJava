package chat.common;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileLogger {

    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final Path file;
    private final Clock clock;

    public FileLogger(Path file) {
        this(file, Clock.systemDefaultZone());
    }

    public FileLogger(Path file, Clock clock) {
        this.file = file;
        this.clock = clock;
    }

    public synchronized void log(String message) {
        String line = "[" + FORMAT.format(LocalDateTime.now(clock)) + "] " + message + System.lineSeparator();
        try {
            Files.writeString(file, line, StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println("Не удалось записать в лог " + file + ": " + e.getMessage());
        }
    }

    public Path getFile() {
        return file;
    }
}
