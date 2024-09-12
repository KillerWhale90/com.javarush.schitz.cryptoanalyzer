import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Menu {
    static Scanner scanner = new Scanner(System.in);
    static boolean exit = false;
    public static void start() throws IOException {
        while (!exit) {
            System.out.println("""
                    Выберете действие, и укажите его номер:\s
                    1. Шифрование
                    2. Расшифровка с ключом
                    3. Расшифровка без ключа (Метод BrutForce)
                    4. Выход""");

            String choice = scanner.nextLine();
            int choiceToInt = 0;

            if (UtilMethods.isDigit(choice)) {
                choiceToInt = Integer.parseInt(choice);
            } else {
                System.out.println("Необходимо указать номер действия в числовом виде!");
                System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-");
            }

            String pathToFile = "";
            String pathToEncryptFile;
            String pathToDecryptFile;
            int validKey = 0;

            boolean truePathToFile = false;
            boolean trueKey = false;

            switch (choiceToInt) {
                case (1):
                    while (!truePathToFile){
                        System.out.println("Укажите путь к текстовому файлу:");
                        pathToFile = scanner.nextLine();

                        if(Files.isRegularFile(Path.of(pathToFile))){
                            truePathToFile = true;
                        }else {
                            System.out.println("Файл не найден");
                        }
                    }

                    while (!trueKey){
                        System.out.println("Задайте ключ от 0 до 43:");
                        String key = scanner.nextLine();

                        if(UtilMethods.isDigit(key) &&
                                Integer.parseInt(key) > 0 &&
                                     Integer.parseInt(key) < Alphabet.RUSSIAN_SMALL.length){
                            validKey = Integer.parseInt(key);
                            trueKey = true;
                        }else {
                            System.out.println("Указанный ключ не является числом или выходит за заданный диапазон");
                        }
                    }

                    System.out.println("Укажите путь, куда следует записать зашифрованный файл:");
                    pathToEncryptFile = scanner.nextLine();

                    FileManager.writeFile(CeasarCihper.encrypt(FileManager.readFile(pathToFile), validKey), pathToEncryptFile);

                    System.out.println("Текст успешно зашифрован!");
                    System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-");
                    break;

                case (2):
                    while (!truePathToFile){
                        System.out.println("Укажите путь к текстовому файлу:");
                        pathToFile = scanner.nextLine();

                        if(Files.isRegularFile(Path.of(pathToFile))){
                            truePathToFile = true;
                        }else {
                            System.out.println("Файл не найден");
                        }
                    }

                    while (!trueKey){
                        System.out.println("Задайте ключ от 0 до 43:");
                        String key = scanner.nextLine();

                        if(UtilMethods.isDigit(key) &&
                                Integer.parseInt(key) > 0 &&
                                Integer.parseInt(key) < Alphabet.RUSSIAN_SMALL.length){
                            validKey = Integer.parseInt(key);
                            trueKey = true;
                        }else {
                            System.out.println("Указанный ключ не является числом или выходит за заданный диапазон");
                        }
                    }

                    System.out.println("Укажите путь, куда следует записать зашифрованный файл:");
                    pathToDecryptFile = scanner.nextLine();

                    FileManager.writeFile(CeasarCihper.decrypt(FileManager.readFile(pathToFile), validKey), pathToDecryptFile);

                    System.out.println("Текст успешно расшифрован!");
                    System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-");
                    break;

                case (3):
                    while (!truePathToFile){
                        System.out.println("Укажите путь к зашифрованному файлу:");
                        pathToFile = scanner.nextLine();

                        if(Files.isRegularFile(Path.of(pathToFile))){
                            truePathToFile = true;
                        }else {
                            System.out.println("Файл не найден");
                        }
                    }

                    System.out.println("Укажите путь, куда следует записать расшифрованный файл:");
                    pathToDecryptFile = scanner.nextLine();

                    FileManager.writeFile(CeasarCihper.bruteForce(pathToFile), pathToDecryptFile);

                    System.out.println("Текст успешно расшифрован!");
                    System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-");
                    break;

                case (4):
                    exit = true;
                    break;
            }
        }
    }
}
