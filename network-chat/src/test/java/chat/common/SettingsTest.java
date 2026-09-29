package chat.common;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class SettingsTest {

    @TempDir
    Path tmp;

    @Test
    void readsValuesFromFile() throws IOException {
        Path file = tmp.resolve("settings.txt");
        Files.writeString(file, "host=example.org\nport=9090\nlog=my.log\n");

        Settings s = Settings.load(file);

        assertEquals("example.org", s.getHost());
        assertEquals(9090, s.getPort());
        assertEquals(Path.of("my.log"), s.getLogFile());
    }

    @Test
    void usesDefaultsWhenMissing() {
        Settings s = new Settings(new Properties());

        assertEquals(Settings.DEFAULT_PORT, s.getPort());
        assertEquals(Settings.DEFAULT_HOST, s.getHost());
        assertEquals(Path.of(Settings.DEFAULT_LOG), s.getLogFile());
    }

    @Test
    void invalidPortThrows() {
        Properties p = new Properties();
        p.setProperty("port", "abc");
        assertThrows(IllegalArgumentException.class, () -> new Settings(p).getPort());

        p.setProperty("port", "70000");
        assertThrows(IllegalArgumentException.class, () -> new Settings(p).getPort());
    }

    @Test
    void missingFileThrows() {
        assertThrows(IOException.class, () -> Settings.load(tmp.resolve("nope.txt")));
    }
}
