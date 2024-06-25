package welcomeProjects;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class BitMapImgDecryption {

    public static void main(String[] args) throws IOException {
    	try {
        File steganographedImage = new File("C:/Users/Admin/eclipse-workspace/Welcome/src/welcomeProjects/img/encryptedBITMapImage.bmp");  // Replace with the path to the received image
        BufferedImage img = ImageIO.read(steganographedImage);
        String endMarker = "00000000";
        String binaryMessage = extractHiddenMessage(img,endMarker);
        String textMessage = binaryToString(binaryMessage);

        System.out.println("Decoded Message: " + textMessage);
        //writeToFile(textMessage, "C:/Users/Admin/eclipse-workspace/Welcome/src/welcomeProjects/img/DecryptedImage.bmp");
        File outputfile = new File("C:/Users/Admin/eclipse-workspace/Welcome/src/welcomeProjects/img/DecryptedImage.bmp"); // Replace with your desired output path
        ImageIO.write(img, "bmp", outputfile);
    	}
    	catch(IOException io){
    		 io.printStackTrace();
    	}
    }

     
	private static String extractHiddenMessage(BufferedImage img, String endMarker) {
        StringBuilder binaryMessage = new StringBuilder();
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                int pixel = img.getRGB(x, y);
                int blue = pixel & 0xff;

                binaryMessage.append(blue & 1);

                // Check for end marker
                if (binaryMessage.length() >= endMarker.length() && binaryMessage.substring(binaryMessage.length() - endMarker.length()).equals(endMarker)) {
                    // Remove end marker and break
                    return binaryMessage.substring(0, binaryMessage.length() - endMarker.length());
                }
            }
        }
        return binaryMessage.toString();
    }


    private static String binaryToString(String binary) {
        StringBuilder message = new StringBuilder();
        for (int i = 0; i <= binary.length() -8; i += 8) {
            int charCode = Integer.parseInt(binary.substring(i, i + 8), 2);
            message.append((char) charCode);
        }
        return message.toString();
    }
}
