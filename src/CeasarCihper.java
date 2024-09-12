import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class CeasarCihper {
    public static List<String> text;
    private static final char[] ENCRYPT_ALPHABET = new char[Alphabet.RUSSIAN_SMALL.length];
    private static final char[] ENCRYPT_ALPHABET_BIG = new char[Alphabet.RUSSIAN_SMALL.length];
    public static String encrypt(String text, int shift) {
        // Логика шифрования
        char[] textAsCharArray = text.toCharArray();

        UtilMethods.copyAlphabet(Alphabet.RUSSIAN_SMALL, ENCRYPT_ALPHABET);
        UtilMethods.copyAlphabet(Alphabet.RUSSIAN_BIG, ENCRYPT_ALPHABET_BIG);

        System.out.println(Arrays.toString(Alphabet.RUSSIAN_BIG));

        UtilMethods.shiftArray(ENCRYPT_ALPHABET, shift);
        UtilMethods.shiftArray(ENCRYPT_ALPHABET_BIG, shift);

        System.out.println(Arrays.toString(ENCRYPT_ALPHABET_BIG));

        for (int i = 0; i < text.length(); i++) {
            for (int j = 0; j < Alphabet.RUSSIAN_SMALL.length; j++) {
                if(textAsCharArray[i] == Alphabet.RUSSIAN_SMALL[j]){
                    textAsCharArray[i] = ENCRYPT_ALPHABET[j];
                    break;
                } else if(textAsCharArray[i] == Alphabet.RUSSIAN_BIG[j]){
                    textAsCharArray[i] = ENCRYPT_ALPHABET_BIG[j];
                    break;
                }
            }
        }

        System.out.println("Текст успешно зашифрован!");
        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-");

        return String.valueOf(textAsCharArray);
    }
    public static String decrypt(String encryptedText, int shift) {
        // Логика расшифровки
        char[] textAsCharArray = encryptedText.toCharArray();

        UtilMethods.copyAlphabet(Alphabet.RUSSIAN_SMALL, ENCRYPT_ALPHABET);
        UtilMethods.copyAlphabet(Alphabet.RUSSIAN_BIG, ENCRYPT_ALPHABET_BIG);

        System.out.println(Arrays.toString(Alphabet.RUSSIAN_BIG));

        UtilMethods.shiftArray(ENCRYPT_ALPHABET, shift);
        UtilMethods.shiftArray(ENCRYPT_ALPHABET_BIG, shift);

        System.out.println(Arrays.toString(ENCRYPT_ALPHABET_BIG));

        for (int i = 0; i < encryptedText.length(); i++) {
            for (int j = 0; j < Alphabet.RUSSIAN_SMALL.length; j++) {
                if(textAsCharArray[i] == ENCRYPT_ALPHABET[j]){
                    textAsCharArray[i] = Alphabet.RUSSIAN_SMALL[j];
                    break;
                } else if(textAsCharArray[i] == ENCRYPT_ALPHABET_BIG[j]){
                    textAsCharArray[i] = Alphabet.RUSSIAN_BIG[j];
                    break;
                }
            }
        }

        System.out.println("Текст успешно расшифрован!");
        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-");

        return String.valueOf(textAsCharArray);
    }
    public static String bruteForce(String pathToFile) throws IOException {
        List<String> listOfMostPopularWords = Files.readAllLines(Path.of("res/most_popular_words.txt"));
        text = Files.readAllLines(Path.of(pathToFile));
        String encryptText = text.getFirst();

        for (int i = 0; i < Alphabet.RUSSIAN_SMALL.length; i++) {
            String[] words = decrypt(encryptText, i).split(" ");
            for (String word : words) {
                for (String checkString : listOfMostPopularWords) {
                    if (word.equalsIgnoreCase(checkString)) {
                        return decrypt(FileManager.readFile(pathToFile), i);
                    }
                }
            }
        }
        System.out.println("Расшифровка не удалась");
        return "";
    }
}
