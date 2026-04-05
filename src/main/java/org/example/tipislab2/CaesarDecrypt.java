package org.example.tipislab2;

public class CaesarDecrypt {

    private static final String ALPHABET = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
    private static final int ALPHABET_SIZE = ALPHABET.length();

    private static final char MOST_FREQUENT_CHAR = 'о';


    public static int determineShift(String text) {
        int[] freq = new int[ALPHABET_SIZE];

        String lowerText = text.toLowerCase();
        for (char c : lowerText.toCharArray()) {
            int index = ALPHABET.indexOf(c);
            if (index != -1) {
                freq[index]++;
            }
        }

        int maxCount = -1;
        int mostFrequentIndex = -1;

        for (int i = 0; i < ALPHABET_SIZE; i++) {
            if (freq[i] > maxCount) {
                maxCount = freq[i];
                mostFrequentIndex = i;
            }
        }

        if (mostFrequentIndex == -1) {
            throw new IllegalArgumentException("Текст не содержит букв русского алфавита");
        }

        int indexO = ALPHABET.indexOf(MOST_FREQUENT_CHAR);

        int shift = (mostFrequentIndex - indexO + ALPHABET_SIZE) % ALPHABET_SIZE;

        return shift;
    }

    public static String decrypt(String text, int shift) {
        StringBuilder result = new StringBuilder();
        String lowerText = text.toLowerCase();

        for (char c : lowerText.toCharArray()) {
            int index = ALPHABET.indexOf(c);
            if (index != -1) {
                int newIndex = (index - shift + ALPHABET_SIZE) % ALPHABET_SIZE;
                result.append(ALPHABET.charAt(newIndex));
            } else {
                result.append(c);
            }
        }

        return result.toString();
    }
}