package welcomeProjects;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BitMapImg {

    public static void main(String[] args) throws IOException {
        String message = "I remember as a child, and as a young budding naturalist, spending all my time observing and testing the world around me moving pieces, altering the flow of things, and documenting ways the world responded to me. Now, as an adult and a professional naturalist, I’ve approached language in the same way, not from an academic point of view but as a curious child still building little mud dams in creeks and chasing after frogs. So this book is an odd thing: it is a naturalist’s walk through the language-making landscape of the English language, and following in the naturalist’s tradition it combines observation, experimentation, speculation, and documentation activities we don’t normally associate with language. This book is about testing, experimenting, and playing with language.";
        String binaryMessage = toBinary(message);

        //File originalImage = new File("C:/Users/Admin/eclipse-workspace/Welcome/src/welcomeProjects/img"); // Replace with your image path
        File file = new File("C:/Users/Admin/eclipse-workspace/Welcome/src/welcomeProjects/img/bitmap.bmp"); 
        if (!file.exists()) {
            System.out.println("File not found: " + file.getAbsolutePath());
            return;
        }
       // BufferedImage img=null ;
        BufferedImage image;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        embedMessage(image, binaryMessage);

        File outputfile = new File("C:/Users/Admin/eclipse-workspace/Welcome/src/welcomeProjects/img/encryptedBITMapImage.bmp"); // Replace with your desired output path
        ImageIO.write(image, "bmp", outputfile);
    }

    private static String toBinary(String message) {
        byte[] bytes = message.getBytes();
        StringBuilder binary = new StringBuilder();
        for (byte b : bytes) {
            int val = b;
            for (int i = 0; i < 8; i++) {
                binary.append((val & 128) == 0 ? 0 : 1);
                val <<= 1;
            }
        }
        return binary.toString();
    }	

    private static void embedMessage(BufferedImage img, String binaryMessage) {
        int messageIndex = 0;
        int messageLength = binaryMessage.length();

        for (int y = 0; y < img.getHeight() && messageIndex < messageLength; y++) {
            for (int x = 0; x < img.getWidth() && messageIndex < messageLength; x++) {
                int pixel = img.getRGB(x, y);

                int alpha = (pixel >> 24) & 0xff;
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = pixel & 0xff;

                // Modify the blue component's LSB
                blue = (blue & 0xfe) | Character.getNumericValue(binaryMessage.charAt(messageIndex));

                int newPixel = (alpha << 24) | (red << 16) | (green << 8) | blue;
                img.setRGB(x, y, newPixel);

                messageIndex++;
            }
        }
    }
}
