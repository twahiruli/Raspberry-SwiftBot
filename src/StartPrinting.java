import java.io.BufferedWriter;
import java.io.FileWriter;



public class StartPrinting {

    static double updatedzigLength = Movement.zigLength;
    	/*Method for writing text to a text file */
    static void writeToFile() {
    	
    	double straightDis = (Math.sqrt(Math.pow(updatedzigLength, 2) + Math.pow(updatedzigLength, 2)));
    	 straightDis = (updatedzigLength * Math.sqrt(2))*(Movement.zigNumber/2);       // calculating straightDis as per Design
    	 straightDis = Math.round(straightDis);
        try  {
        	FileWriter writehandle = new FileWriter("//home//pi//Documents//log.txt"); // writehandle object created to write in mentioned path
        	BufferedWriter bw = new BufferedWriter(writehandle); 
            
            bw.write("Zigzag length: " + Movement.zigLength + "cm");  
            bw.newLine();                                         
            bw.write("Number of sections: " + Movement.zigNumber);
            bw.newLine();
            bw.write("Random wheel speed: " + Movement.wheelSpeed);
            bw.newLine();
            bw.write("Traversed path Length: " + (Movement.zigLength * Movement.zigNumber) + "cm");
            bw.newLine();
            bw.write("Duration: " + Movement.duration/1000 + " seconds");
            bw.newLine();
            bw.write("Distance travelled(Sraightline):" + straightDis + " cm");
            
            bw.close();  // closing bw
            writehandle.close();  // closing writehandle

        } catch (Exception e) {
        	System.out.println("\u001B[31mError writing data to a text file. Check available storage space or permissions\u001B[0m");

        }
    }
}


