package welcomeProjects;

import java.util.Scanner;

public class PolyalphabeticSubstitutionCipher {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the plaintext: ");
        String plaintext = scanner.nextLine().toUpperCase();

        System.out.print("Enter the key: ");
        String key = scanner.nextLine().toUpperCase();

        String ciphertext = encrypt(plaintext, key);
        System.out.println("Ciphertext: " + ciphertext);

        String decryptedText = decryptVigenere(ciphertext, key);
        System.out.println("Decrypted Text: " + decryptedText);
    }

    private static String encrypt(String plaintext, String key) {
        StringBuilder ciphertext = new StringBuilder();
        int keyLength = key.length();

        for (int i = 0; i < plaintext.length(); i++) {
            char plainChar = plaintext.charAt(i);
            char keyChar = key.charAt(i % keyLength);

            if (Character.isLetter(plainChar)) {
                char encryptedChar = (char) ((plainChar + keyChar - 2 * 'A') % 26 + 'A');
                ciphertext.append(encryptedChar);
            } else {
                // Non-alphabetic characters are not encrypted
                ciphertext.append(plainChar);
            }
        }

        return ciphertext.toString();
    }

    private static String decryptVigenere(String ciphertext, String key) {
        StringBuilder decryptedText = new StringBuilder();
        int keyLength = key.length();

        for (int i = 0; i < ciphertext.length(); i++) {
            char encryptedChar = ciphertext.charAt(i);
            char keyChar = key.charAt(i % keyLength);

            if (Character.isLetter(encryptedChar)) {
                char decryptedChar = (char) ((encryptedChar - keyChar + 26) % 26 + 'A');
                decryptedText.append(decryptedChar);
            } else {
                
                decryptedText.append(encryptedChar);
            }
        }

        return decryptedText.toString();
    }
}
