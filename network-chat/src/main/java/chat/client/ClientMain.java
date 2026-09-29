package chat.client;

import chat.common.FileLogger;
import chat.common.Settings;

import java.nio.file.Path;
import java.util.Scanner;

public class ClientMain {

    public static void main(String[] args) throws Exception {
        Path settingsPath = Path.of(args.length > 0 ? args[0] : "settings.txt");
        Settings settings = Settings.load(settingsPath);
        FileLogger logger = new FileLogger(settings.getLogFile());

        System.out.print("Введите имя: ");
        String name = new Scanner(System.in).nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("Имя не может быть пустым");
            return;
        }
        System.out.println("Подключение... Для выхода введите " + ChatClient.EXIT_COMMAND);

        new ChatClient(settings.getHost(), settings.getPort(), name, logger, System.in, System.out).run();
    }
}
