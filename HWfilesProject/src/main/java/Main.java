import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        StringBuilder log = new StringBuilder();

        String homeDir = System.getProperty("user.home");
        String gamesPath = homeDir + "/Desktop/Games";

        File src = new File(gamesPath + "/src");
        File res = new File(gamesPath + "/res");
        File savegames = new File(gamesPath + "/savegames");
        File temp = new File(gamesPath + "/temp");

        File srcMain = new File(src, "main");
        File srcTest = new File(src, "test");

        File mainJava = new File(srcMain, "Main.java");
        File utilsJava = new File(srcMain, "Utils.java");

        File drawables = new File(res, "drawables");
        File vectors = new File(res, "vectors");
        File icons = new File(res, "icons");

        File tempTxt = new File(temp, "temp.txt");

        createDirectory(src, log);
        createDirectory(res, log);
        createDirectory(savegames, log);
        createDirectory(temp, log);
        createDirectory(srcMain, log);
        createDirectory(srcTest, log);
        createDirectory(drawables, log);
        createDirectory(vectors, log);
        createDirectory(icons, log);

        createFile(mainJava, log);
        createFile(utilsJava, log);
        createFile(tempTxt, log);

        try (FileWriter writer = new FileWriter(tempTxt)) {
            writer.write(log.toString());
            System.out.println("Лог записан в temp.txt");
        } catch (IOException e) {
            System.out.println("Ошибка записи лога: " + e.getMessage());
        }

        System.out.println("\n" + log.toString());
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
}
