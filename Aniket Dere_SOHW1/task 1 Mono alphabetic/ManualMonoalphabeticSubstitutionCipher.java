package welcomeProjects;import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ManualMonoalphabeticSubstitutionCipher {
    private static final Map<Character, Character> encryptionKey = new HashMap<>();
    private static final Map<Character, Character> decryptionKey = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(" Alice please enter text to be send to Bob: ");
        String key = "RSTUVWXYZABCDEFGHIJKLMNOPQ";// Bob received this key in secured manner
//                    ABCDEFGHIJKLMNOPQRSTUVWXYZ
        

        initializeKeys(key);

        
        String plaintext = scanner.nextLine();
        String encrypted = encrypt(plaintext);
        String decrypted = decrypt(encrypted);
        
        System.out.println("Original text: " + plaintext);
        System.out.println();
        System.out.println("Encrypted text which Oscar got from database: ");
        
        System.out.println("Encrypted: " + encrypted);
        System.out.println();
        System.out.println("Message decrypted by Bob: ");
        System.out.println("Decrypted: " + decrypted);

        scanner.close();
    }

    ///                        QRSTUVWXYZABCDEFGHIJKLMNOP
    public static void initializeKeys(String key) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";//QRSTUVWXYZABCDEFGHIJKLMNOP
        				// QRSTUVWXYZABCDEFGHIJKLMNOP
        for (int i = 0; i < alphabet.length(); i++) {
            char plain = alphabet.charAt(i);
            char cipher = key.charAt(i);

            encryptionKey.put(plain, cipher);
            decryptionKey.put(cipher, plain);
        }
    }

    private static String encrypt(String plaintext) {
        StringBuilder ciphertext = new StringBuilder();

        for (char character : plaintext.toUpperCase().toCharArray()) {
            if (encryptionKey.containsKey(character)) {
                ciphertext.append(encryptionKey.get(character));
            } else {
                ciphertext.append(character);
            }
        }

        return ciphertext.toString();
    }

    public static String decrypt(String ciphertext) {
        StringBuilder plaintext = new StringBuilder();

        for (char character : ciphertext.toUpperCase().toCharArray()) {
            if (decryptionKey.containsKey(character)) {
                plaintext.append(decryptionKey.get(character));
            } else {
                plaintext.append(character);
            }
        }

        return plaintext.toString();
    }
}
