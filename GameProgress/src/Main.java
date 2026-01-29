import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {

        String homeDir = System.getProperty("user.home");
        String savegamesPath = homeDir + "/Desktop/Games/savegames/";

        GameProgress save1 = new GameProgress(100, 5, 1, 150.5);
        GameProgress save2 = new GameProgress(80, 10, 2, 300.8);
        GameProgress save3 = new GameProgress(50, 15, 3, 500.2);

        saveGame(savegamesPath + "save1.dat", save1);
        saveGame(savegamesPath + "save2.dat", save2);
        saveGame(savegamesPath + "save3.dat", save3);

        List<String> filesToZip = new ArrayList<>();
        filesToZip.add(savegamesPath + "save1.dat");
        filesToZip.add(savegamesPath + "save2.dat");
        filesToZip.add(savegamesPath + "save3.dat");

        zipFiles(savegamesPath + "zip.zip", filesToZip);

        deleteFiles(filesToZip);

        System.out.println("Все операции выполнены успешно!");
    }

    public static void saveGame(String path, GameProgress gameProgress) {
        try (FileOutputStream fos = new FileOutputStream(path);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(gameProgress);
            System.out.println("Сохранение создано: " + path);

        } catch (IOException e) {
            System.out.println("Ошибка при создании сохранения: " + e.getMessage());
        }
    }

    public static void zipFiles(String zipPath, List<String> files) {
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipPath))) {

            for (String filePath : files) {
                try (FileInputStream fis = new FileInputStream(filePath)) {

                    File file = new File(filePath);
                    ZipEntry entry = new ZipEntry(file.getName());
                    zos.putNextEntry(entry);

                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = fis.read(buffer)) > 0) {
                        zos.write(buffer, 0, length);
                    }

                    zos.closeEntry();
                    System.out.println("Файл добавлен в архив: " + file.getName());

                } catch (IOException e) {
                    System.out.println("Ошибка при добавлении файла в архив: " + e.getMessage());
                }
            }

            System.out.println("Архив создан: " + zipPath);

        } catch (IOException e) {
            System.out.println("Ошибка при создании архива: " + e.getMessage());
        }
    }
    
    public static void deleteFiles(List<String> files) {
        for (String filePath : files) {
            File file = new File(filePath);
            if (file.delete()) {
                System.out.println("Файл удален: " + filePath);
            } else {
                System.out.println("Не удалось удалить файл: " + filePath);
            }
        }
    }
}
