import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        StringBuilder log = new StringBuilder();
        String gamePath = "/Users/daniel/Desktop/Games";

        List<String> directories = new ArrayList<>(List.of(
                gamePath + "/src",
                gamePath + "/res",
                gamePath + "/savegames",
                gamePath + "/temp",
                gamePath + "/src/main",
                gamePath + "/src/test",
                gamePath + "/res/drawables",
                gamePath + "/res/vectors",
                gamePath + "/res/icons"
        ));

        for (String path : directories) {
            File dir = new File(path);
            dirMaker(dir, log);
        }


        File mainFile = new File(gamePath + "/src/main/Main.java");
        fileMaker(mainFile, log);

        File utilsFile = new File(gamePath + "/src/main/Utils.java");
        fileMaker(utilsFile, log);

        File tempFile = new File(gamePath + "/temp/temp.txt");
        fileMaker(tempFile, log);


        try (FileWriter logToFile = new FileWriter(tempFile.getPath())) {
            logToFile.write(log.toString());
        } catch (IOException e) {
            System.out.println("Ошибка записи лога: " + e.getMessage());
        }
    }

    public static void dirMaker(File newDir, StringBuilder log) {
        boolean isDone = newDir.mkdir();
        String properDone = isDone
                ? "Директория " + newDir.getPath() + " создана"
                : "Директория " + newDir.getPath() + " не создана";
        System.out.println(properDone);
        log.append(properDone).append("\n");
    }

    public static void fileMaker(File newFile, StringBuilder log) {
        try {
            boolean isDone = newFile.createNewFile();
            String properDone = isDone
                    ? "Файл " + newFile.getPath() + " создан"
                    : "Файл " + newFile.getPath() + " не создан";
            System.out.println(properDone);
            log.append(properDone).append("\n");
        } catch (IOException e) {
            log.append(e.getMessage()).append("\n");
        }
    }
}
