package chat.server;

import chat.common.FileLogger;
import chat.common.Settings;

import java.nio.file.Path;

public class ServerMain {

    public static void main(String[] args) throws Exception {
        Path settingsPath = Path.of(args.length > 0 ? args[0] : "settings.txt");
        Settings settings = Settings.load(settingsPath);
        FileLogger logger = new FileLogger(settings.getLogFile());

        ChatServer server = new ChatServer(settings.getPort(), logger);
        Runtime.getRuntime().addShutdownHook(new Thread(server::stop));
        server.start();
        System.out.println("Сервер запущен на порту " + server.getPort() + ". Остановка: Ctrl+C");
    }
}
