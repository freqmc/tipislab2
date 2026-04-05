package org.example.tipislab2;

public class CaesarCrypt {
    private static final String ALPHABET = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
    public static String crypt(String text, int shift) {
        StringBuilder result = new StringBuilder();
        text = text.toLowerCase();

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            int index = ALPHABET.indexOf(c);

            if (index != -1) {
                int newIndex = (index + shift) % ALPHABET.length();
                if (newIndex < 0) newIndex += ALPHABET.length();
                result.append(ALPHABET.charAt(newIndex));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }
}