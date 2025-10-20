import swiftbot.*;
import java.util.Scanner;

public class Movement {
    
    static int zigLength;
    static int zigNumber;
    static int wheelSpeed;
    static int Calc;    
    public static SwiftBotAPI swiftbot;
    static long duration = 0;
    static final Scanner scanner = new Scanner(System.in);
    static int zigzagJourney = 0;
    static boolean mode = false;
    static boolean squareloopcheck = false;
    
    public static void main(String[] args) {
        swiftbot = new SwiftBotAPI();  
        
        System.out.println("Press any key to start...");
      
        scanner.nextLine();
        userInstructions();  // calling user instructions method
        QrCodeHandling.qrScan(swiftbot); //calling qrScan method from QrCodeHandling Class
       
        modeSelection();   //calling modeSelection method 
       
        ProgramTermination.termination(swiftbot); // calling termination method from ProgramTermination class
    }
    
    /*
     * This method is for mode selection and asks the user to select a mode if 
     * invalid mode is selected say 3 or 4 it will run the same method again so Error handling is done properly
     */
    public static void modeSelection() {
        while (true) { // loop until a valid input is provided
            System.out.println("Select Mode:");
            System.out.println("Press 1: Normal Mode");
            System.out.println("Press 2: Energy Efficiency Mode");
            String ModeSelection = scanner.nextLine(); 

            switch (ModeSelection) { 
                case "1":     
                    normalMode(); // here the switch takes userInput and if its 1 then it will run Normal Mode 
                    return; 
                case "2":
                    energyEfficiencyMode(); // here the switch takes userInput and if its 2 then it will run energyEfficiencyMode Mode 
                    return; 
            }
        }
    }
    
    /*
     * This method is for Pattern selection and asks the user to select a pattern if 
     * invalid pattern is selected say 3 or 4 it will run the same method again so Error handling is done properly
     */
    public static void patternSelection() {
        while (true) { // Loop until a valid input is provided
            System.out.println("Select Pattern:");
            System.out.println("Press 1: ZigZag");
            System.out.println("Press 2: SquareLoop");
            
            String patternSelection = scanner.nextLine(); // asks the user for an input for Pattern Selection and stores it in a string

            switch (patternSelection) {
                case "1":
                    zigZag();
                    return;
                case "2":
                    squareLoop();
                    return; 
            }
        }
    }
    
    /*
     * This method is for the squareLoop pattern and it will run only if it is called as after mode selection 
     * user gets to choose the pattern and
     * if squareLoop is selected it will run this part and the swiftbot will create a squareLoop pattern
     * 
     */
    private static void squareLoop() {
        squareloopcheck = true; // This boolean checks if the swiftbot is running squareLoop and will set it to true
                                // and if its true then it will not retrace and will terminate after a single or double loop
        System.out.println("SquareLoop selected");
        System.out.println("Select Number of Loops");
        System.out.println("Press 1: Single Loop");
        System.out.println("Press 2: Double Loop");
        /*
         * This is a condition set to identify if its Normal Mode or Energy Efficiency mode if its set to true
         * then it will ask the user for colours and if its set to false it wont ask for colours
         */
        if (mode == true) {
            int noLoop = scanner.nextInt(); // takes input from the user, valid inputs are 1 and 2 
            SetColours.setfirstColour(); // method for setting first colour 
            SetColours.setSecondColour(); // method for setting Second colour
            // switch condition is 1 and 2 if its 1 then SquareLoop will run once and if its 2 then it will run twice
            switch (noLoop) {
                case 1:
                    for (int i = 1; i <= 2; i++)// condition to run half of square twice
                    { 
                        swiftbot.fillUnderlights(SetColours.selectedColor); // sets first colour 
                        adjustMoveBasedOnWheelSpeed(wheelSpeed, zigLength); // moves forward with the adjusted wheel speed 
                        swiftbot.move(0, 0, 1000); // stops for 1 second
                        swiftbot.move(-100 , 100, 400); // left turn
                        swiftbot.fillUnderlights(SetColours.selectedColor2);// sets Second colour 
                        adjustMoveBasedOnWheelSpeed(wheelSpeed, zigLength); // moves forward with the adjusted wheel speed 
                        swiftbot.move(0, 0, 1000);// stops for 1 second
                        swiftbot.move(-100 , 100, 400); // left turn
                    }
                    break;
                case 2:
                    for (int i = 1; i <= 4; i++)  // condition to run half of square 4 times
                    { 
                        swiftbot.fillUnderlights(SetColours.selectedColor);// sets first colour 
                        adjustMoveBasedOnWheelSpeed(wheelSpeed, zigLength);// moves forward with the adjusted wheel speed 
                        swiftbot.move(0, 0, 1000);// stops for 1 second
                        swiftbot.move(-100 , 100, 400); // left turn
                        swiftbot.fillUnderlights(SetColours.selectedColor2);// sets Second colour 
                        adjustMoveBasedOnWheelSpeed(wheelSpeed, zigLength); // moves forward with the adjusted wheel speed 
                        swiftbot.move(0, 0, 1000);// stops for 1 second
                        swiftbot.move(-100 , 100, 400); // left turn
                    }
                    break;
            }
        }
        // else run the same thing without colours
        else {   
            int noLoop = scanner.nextInt();
            switch (noLoop) {
                case 1:
                    for (int i = 1; i <= 2; i++)// condition to run half of square twice
                    {
                        adjustMoveBasedOnWheelSpeed(wheelSpeed, zigLength);// moves forward with the adjusted wheel speed 
                        swiftbot.move(0, 0, 1000);// stops for 1 second
                        swiftbot.move(-100 , 100, 400); // left turn              
                        adjustMoveBasedOnWheelSpeed(wheelSpeed, zigLength);// moves forward with the adjusted wheel speed 
                        swiftbot.move(0, 0, 1000);// stops for 1 second
                        swiftbot.move(-100 , 100, 400); // left turn
                    }
                    break;
                case 2:
                    for (int i = 1; i <= 4; i++) // condition to run half of square 4 times
                    {
                        adjustMoveBasedOnWheelSpeed(wheelSpeed, zigLength);// moves forward with the adjusted wheel speed 
                        swiftbot.move(0, 0, 1000);// stops for 1 second
                        swiftbot.move(-100 , 100, 400); // left turn
                        adjustMoveBasedOnWheelSpeed(wheelSpeed, zigLength);// moves forward with the adjusted wheel speed 
                        swiftbot.move(0, 0, 1000);// stops for 1 second
                        swiftbot.move(-100 , 100, 400); // left turn
                    }
                    break;
            }
        }
    }
     
    static void zigZag() {
    	
        long StartTime = 0;
        if (mode == true && squareloopcheck==false) // Here the condition checks if its Normal mode or not followed by squareLoop check
        {
        	System.out.println("Zigzag Selected");
            SetColours.setfirstColour();                // asks the user to input the first colour	
            SetColours.setSecondColour();				 // asks the user to input the Second colour	
            for (int i = 1; i <= Calc; i++) {
            	System.out.println("Clocking current time...");
                StartTime = System.currentTimeMillis();  // records start time 
                swiftbot.fillUnderlights(SetColours.selectedColor);    // sets first colour  
                adjustMoveBasedOnWheelSpeed(wheelSpeed, zigLength);   // moves forward with the adjusted wheel speed 
                swiftbot.move(0, 0, 1000); // pause for 1 second
                swiftbot.move(-100 , 100, 400); // left turn
                swiftbot.fillUnderlights(SetColours.selectedColor2); // sets Second colour  
                adjustMoveBasedOnWheelSpeed(wheelSpeed, zigLength);// moves forward with the adjusted wheel speed 
                swiftbot.move(0, 0, 1000); // pause for 1 second
                swiftbot.move(100 , -100, 400); // right turn
            }
            Reverse.retrace(swiftbot);  // calling retrace method from Reverse class to retrace journey
            swiftbot.disableUnderlights(); // disabling all underLights
            long StopTime = System.currentTimeMillis();  //after retracing it should Stop the timer and calculate duration
            duration = duration + (StopTime - StartTime); //calculating duration
            System.out.println("Duration: " + duration / 1000 + " seconds");
            zigzagJourney = zigzagJourney + 1; // adding +1 to zigzagJourney after a complete journey
        } else {
            for (int i = 1; i <= Calc; i++) {
                StartTime = System.currentTimeMillis(); // records start time 
                adjustMoveBasedOnWheelSpeed(wheelSpeed, zigLength);// moves forward with the adjusted wheel speed 
                swiftbot.move(0, 0, 1000); // pause for 1 second
                swiftbot.move(-100 , 100, 400); // left turn
                adjustMoveBasedOnWheelSpeed(wheelSpeed, zigLength);// moves forward with the adjusted wheel speed 
                swiftbot.move(0, 0, 1000); // pause for 1 second
                swiftbot.move(100 , -100, 400); // right turn
            }
            Reverse.retrace(swiftbot);
            swiftbot.disableUnderlights();// disabling all underLights
            long StopTime = System.currentTimeMillis();//after retracing it should Stop the timer and calculate duration
            duration = duration + (StopTime - StartTime);//calculating duration
            System.out.println("Duration: " + duration / 1000 + " seconds");
            zigzagJourney = zigzagJourney + 1;// adding +1 to zigzagJourney after a complete journey
        }
        }
    
    static void adjustMoveBasedOnWheelSpeed(int wheelSpeed, int zigLength) {  /* this method helps the swiftbot to adjust the
     																		wheel speed as for each wheel speed the 
     																		adjustment is different*/
        switch (wheelSpeed) {
            case 40:
            	swiftbot.move(40-4, 40, 60 * zigLength); 
                break;
            case 50:
            	swiftbot.move(50 - 7, 50, 52 * zigLength);	
                break;
            case 60:
            	 swiftbot.move(60 - 9, 60, 47 * zigLength);
                break;
            case 70:
            	 swiftbot.move(70 - 11, 70, 45 * zigLength);
                break;
            case 80:
            	swiftbot.move(80 - 11, 80, 42 * zigLength);
                break;
            case 90:
            	swiftbot.move(90 - 19, 90, 41 * zigLength);
                break;
            case 100:
            	swiftbot.move(100 - 23, 100, 41 * zigLength);
                break;
        }
    }
    
    public static void energyEfficiencyMode() {
        mode = false;  // setting boolean mode to false will make it run the parts where colour is not included
        System.out.println("Energy Efficiency Mode Selected");
        wheelSpeed = RandomSpeedGeneration.randomSpeed();  //calling randomSpeed method from RandomSpeedGenration class to generate the wheel speed
        System.out.println("Disabling all underlights");
        System.out.println("Random Wheel Speed: " + wheelSpeed);
        
        patternSelection();  // goes to pattern selection  after Energy Efficiency Mode and asks the user to select a pattern
    }
    
    public static void normalMode() {
        mode = true; // setting boolean mode to true will make it run the parts where colour is included
        System.out.println("Normal Mode Selected");
        wheelSpeed = RandomSpeedGeneration.randomSpeed(); /* wheelSpeed is generated from randomSpeed method of RandomSpeedGeneration class */
        System.out.println("Random Wheel Speed: " + wheelSpeed);
        patternSelection();                    // goes to pattern selection  after normal mode and asks the user to select a pattern
    }
    
    /* Method for displaying the userInstructions */
    public static void userInstructions() {
        System.out.println("                                   *****************************");
        System.out.println("                                    Welcome to ZigZag Journey");
        System.out.println("                                   *****************************");
        System.out.println(" User instructions:");
        System.out.println("- The SwiftBot will scan a QR code to begin the journey");
        System.out.println("- The SwiftBot has 2 modes, Normal Mode and Energy Efficiency Mode");
        System.out.println("- The SwiftBot has 2 Patterns (Zigzag and Squareloop)");
        System.out.println("- The SwiftBot has the option to set 4 different colours for the underlight");
        System.out.println("- The SwiftBot can capture photo during the journey if 'C' key is pressed");
        System.out.println("- Use button 'X' to terminate the program anytime");
        System.out.println("- Ensure that the Swiftbot is placed in a location free of obstructions");
        System.out.println("- Confirm that the Swiftbot's battery is fully charged before initiating the program.");
        System.out.println("                                   *****************************");
    }
}
