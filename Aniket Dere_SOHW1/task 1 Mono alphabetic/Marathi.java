package welcomeProjects;
public class Marathi {
    public static String encrypt(String text, int shift) {
    	
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);

            if (Character.isLetter(ch)) {
                char base = (Character.isUpperCase(ch)) ? 'അ' : 'ँ';
                //System.out.println("characters "+base);
                //System.out.println("CH value   "+ch);
               // System.out.println("Operation"+ ((ch - base + shift)%128));
                char encryptedChar = (char) (base + (ch - base + shift) % 128);
                //System.out.println("encrypted value"+encryptedChar);
                result.append(encryptedChar);
                
            } else {
                result.append(ch);
            }
        }

        return result.toString();
    }

    public static String decrypt(String text, int shift) {
        return encrypt(text, 128 - shift);
    }
 
    public static void main(String[] args) {
        String originalText = " नैसर्गिकरित्या आढळणारे मूलद्रव्य आहे. पारा आवर्तसारणीत संक्रामक मूलद्रव्यांमध्ये मोडतो. "
        		+ "पाऱ्याचे वैशिष्ट्य म्हणजे सामान्य तापमानाला द्रवरूपात असणारा पारा हा एकमेव धातू आहे. पारा आणि पाऱ्याची अनेक संयुगे विषारी आहेत.";
        int shift = 3;

        String encryptedText = encrypt(originalText, shift);
        String decryptedText = decrypt(encryptedText, shift);

        System.out.println("Original Text: " + originalText);
        System.out.println("Encrypted Text: " + encryptedText);
        System.out.println("Decrypted Text: " + decryptedText);
    }
}
