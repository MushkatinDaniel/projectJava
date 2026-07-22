import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        StringBuilder log = new StringBuilder();
        String gamePath = "/Users/daniel/Desktop/Games";

        File src = new File(gamePath + "/src");
        log.append(dirMaker(src) + "\n");
        File res = new File(gamePath + "/res");
        log.append(dirMaker(res) + "\n");
        File savegames = new File(gamePath + "/savegames");
        log.append(dirMaker(savegames) + "\n");
        File temp = new File(gamePath + "/temp");
        log.append(dirMaker(temp) + "\n");
        File main = new File(gamePath + "/src/main");
        log.append(dirMaker(main) + "\n");
        File test = new File(gamePath + "/src/test");
        log.append(dirMaker(test) + "\n");
        File drawables = new File(gamePath + "/res/drawables");
        log.append(dirMaker(drawables) + "\n");
        File vectors = new File(gamePath + "/res/vectors");
        log.append(dirMaker(vectors) + "\n");
        File icons = new File(gamePath + "/res/icons");
        log.append(dirMaker(icons) + "\n");



        try {
            File mainFile = new File(gamePath + "/src/main/Main.java");
            Boolean createMain = mainFile.createNewFile();
            String createDone = (createMain)
                    ? "Файл " + gamePath + "\"/src/main/Main.java\"" + " создан"
                    : "ошибка создания файла " + gamePath + "\"/src/main/Main.java\"";
            log.append(createDone + "\n");
        } catch (IOException e) {
            log.append(e.getMessage());
        }

        try {
            File utilsFile = new File(gamePath + "/src/main/Utils.java");
            Boolean createMain = utilsFile.createNewFile();
            String createDone = (createMain)
                    ? "Файл " + gamePath + "\"/src/main/Utils.java\"" + " создан"
                    : "ошибка создания файла " + gamePath + "\"/src/main/Utils.java\"";
            log.append(createDone + "\n");
        } catch (IOException e) {
            log.append(e.getMessage());
        }

        try {
            File tempFile = new File(gamePath + "/temp/temp.txt");
            Boolean createMain = tempFile.createNewFile();
            String createDone = (createMain)
                    ? "Файл " + gamePath + "\"/temp/temp.txt\"" + " создан"
                    : "ошибка создания файла " + gamePath + "\"/temp/temp.txt\"";
            log.append(createDone + "\n");

            FileWriter logToFile = new FileWriter(tempFile.getPath());
            logToFile.write(log.toString());
            logToFile.close();
        } catch (IOException e) {
            log.append(e.getMessage());
        }


    }

    public static String dirMaker (File newDir) {
        boolean isDone = newDir.mkdir();
        String properDone = isDone
                ? "Директория " + newDir.getPath() + " создана"
                : "Директория " + newDir.getPath() + " не создана";
        System.out.println(properDone);
        return properDone;
    }
}
