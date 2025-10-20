import java.awt.image.BufferedImage;
import swiftbot.SwiftBotAPI;

public class QrCodeHandling {
    public static void QRCodeDetection(SwiftBotAPI swiftbot) {
    	
    
        try {
            System.out.println("Taking a capture in 2 seconds...");  
            Thread.sleep(2000);  // waits 2 seconds before taking a capture
            BufferedImage img = swiftbot.getQRImage();   // image stored in an BufferedImage object
            /* Decoding the image and extract the message then
				finally stored in decodedMessage String*/

            String decodedMessage = swiftbot.decodeQRImage(img); 
            if (decodedMessage.isEmpty()) {
                System.out.println("\u001B[31mNo QR Code was found. Please try again.\u001B[0m");
                qrScan(swiftbot);   // will run qr scan method again and ask the user to scan again if Qr is empty
            } else {
                String[] parts = decodedMessage.split(",");  // splits the decoded message into parts until it finds a comma
                if (parts.length == 2 && isInteger(parts[0].trim()) && isInteger(parts[1].trim())) {
                    Movement.zigLength = Integer.parseInt(parts[0].trim());  // first part is set to an integer after trimming 
                    Movement.zigNumber = Integer.parseInt(parts[1].trim());// Second part is set to an integer after trimming 
                    // validation check for zigLength and zigNumber
                    if (Movement.zigLength < 15 || Movement.zigLength > 85 || Movement.zigNumber < 2 || Movement.zigNumber > 12 || Movement.zigNumber % 2 != 0) {
                        System.out.println("\u001B[31mFirst input must be any number from 15 to 85 and second input must be even and can't be more than 12\u001B[0m");
                        qrScan(swiftbot);  // will be asked to scan QR again if the length or section number is invalid 
                    } else {
                        System.out.println("QR Scan successful ^-^");
                        System.out.println("Zigzag Length: " + Movement.zigLength);         
                        System.out.println("Number of Zigzags: " + Movement.zigNumber);
                        Movement.Calc = Movement.zigNumber / 2;     // formula from Software design
                    }
                } else {
                    // if parts not integers error will be shown
                	 System.out.println("\u001B[31mFirst input must be any number from 15 to 85 and second input must be even and can't be more than 12\u001B[0m");
                    qrScan(swiftbot); // goes to qrScn method if input is invalid 
                }
            }
        } catch (Exception e) {
            qrScan(swiftbot);
        }
    }

    public static void qrScan(SwiftBotAPI swiftbot) {
        System.out.println("Press 'Q' to Scan");
        String input = Movement.scanner.nextLine().trim();
        /*Makes sure both lower case and upper case q is accepted*/
        if (input.equalsIgnoreCase("Q")) {
            QRCodeDetection(swiftbot);  // goes to QRCodeDetection method
        } else {
            qrScan(swiftbot);     // if something else is pressed it will recursively run the same method
        }
    }
  /*method to check if string is integer or not*/
    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
