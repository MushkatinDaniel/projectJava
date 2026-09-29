package chat.common;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FileLoggerTest {

    @TempDir
    Path tmp;

    private final Clock clock = Clock.fixed(Instant.parse("2025-01-02T03:04:05Z"), ZoneOffset.UTC);

    @Test
    void writesLineWithTimestamp() throws IOException {
        Path file = tmp.resolve("file.log");
        new FileLogger(file, clock).log("bob: hi");

        assertEquals(List.of("[2025-01-02 03:04:05] bob: hi"), Files.readAllLines(file));
    }

    @Test
    void appendsOnEachStart() throws IOException {
        Path file = tmp.resolve("file.log");
        new FileLogger(file, clock).log("first");
        new FileLogger(file, clock).log("second");

        assertEquals(2, Files.readAllLines(file).size());
    }
}
