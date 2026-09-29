package chat.common;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;


public final class Settings {

    public static final int DEFAULT_PORT = 8080;
    public static final String DEFAULT_HOST = "localhost";
    public static final String DEFAULT_LOG = "file.log";

    private final Properties properties;

    public Settings(Properties properties) {
        this.properties = properties;
    }

    public static Settings load(Path path) throws IOException {
        Properties props = new Properties();
        try (Reader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            props.load(reader);
        }
        return new Settings(props);
    }

    public int getPort() {
        String value = properties.getProperty("port");
        if (value == null || value.isBlank()) {
            return DEFAULT_PORT;
        }
        int port;
        try {
            port = Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Некорректный порт: " + value, e);
        }
        if (port < 0 || port > 65535) {
            throw new IllegalArgumentException("Порт вне диапазона 0..65535: " + port);
        }
        return port;
    }

    public String getHost() {
        return properties.getProperty("host", DEFAULT_HOST).trim();
    }

    public Path getLogFile() {
        return Path.of(properties.getProperty("log", DEFAULT_LOG).trim());
    }
}
