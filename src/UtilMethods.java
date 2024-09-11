public class UtilMethods {
    public static boolean isDigit(String s) throws NumberFormatException {
        // Проверка является ли строка числом
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static void shiftArray(char[] encryptAlphabet, int shift){
        // Сдвиг массива симолов на заданное число значений
        for (int i = 0; i < encryptAlphabet.length / 2; i++) {
            char tmp = encryptAlphabet[i];
            encryptAlphabet[i] = encryptAlphabet[encryptAlphabet.length - i - 1];
            encryptAlphabet[encryptAlphabet.length - i - 1] = tmp;
        }

        for (int i = 0; i < (encryptAlphabet.length - shift) / 2; i++) {
            char tmp = encryptAlphabet[i];
            encryptAlphabet[i] = encryptAlphabet[(encryptAlphabet.length - shift) - i - 1];
            encryptAlphabet[(encryptAlphabet.length - shift) - i - 1] = tmp;
        }

        for (int i = 0; i < shift / 2; i++) {
            char tmp = encryptAlphabet[encryptAlphabet.length - shift + i];
            encryptAlphabet[encryptAlphabet.length - shift + i] = encryptAlphabet[encryptAlphabet.length - i - 1];
            encryptAlphabet[encryptAlphabet.length - i - 1] = tmp;
        }
    }
    public static void copyAlphabet(char[] alphabet, char[] copy){
        // Копирование массива символов
        System.arraycopy(alphabet, 0, copy, 0, alphabet.length);
    }
}
