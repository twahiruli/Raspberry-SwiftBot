import swiftbot.Button;
import swiftbot.SwiftBotAPI;

public class ProgramTermination {

    private static boolean buttonXPressed = false; // by default buttonXPressed is set to false

    public static void termination(SwiftBotAPI swiftbot) { // passing parameter
        /* This method runs when the pattern is not squareLoop */
        if (Movement.squareloopcheck == false) {
            swiftbot.enableButton(Button.X, () -> {
                buttonXPressed = true; // setting to true means it has been pressed
                System.out.println("Button X Pressed.");
                System.out.println("Number of Zigzag completed: " + Movement.zigzagJourney); // prints out Number of zigzag completed
                System.out.println("Terminating....");
                StartPrinting.writeToFile(); // calling writeToFile method from the StartPrinting Class
                System.out.println("Terminated");
                System.exit(0); // Exits
            });
        } else {
            System.out.println("Squareloop Completed");
            System.exit(0); // Exits
        }
        // Continuous check for the button press
        while (!buttonXPressed) {
            try {
                // Wait for 5 seconds to check if button X is pressed
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // handle interrupted status
                System.out.println("Thread was interrupted, failed to complete operation");
            }

            // if button is not pressed this bit of code will run
            if ((buttonXPressed == false) && ((Movement.mode == false) || (Movement.mode == true))
                    && (Movement.squareloopcheck == false)) {
                System.out.println("Button X was not pressed within 5 seconds. Continuing...");
                swiftbot.move(-100, 100, 400); // taking a left so that it can go in a loop
                Movement.zigZag(); // run zigzag again after taking a turn
            }
            /*
             * This condition checks if its a squareLoop or not and if its a squareLoop it will exist
             * instead of running an endless loop like zigzag
             */
            if ((buttonXPressed == false) && ((Movement.mode == false) || (Movement.mode == true))
                    && (Movement.squareloopcheck == true)) {
                System.out.println("Squareloop Completed");
                System.exit(0);
            }

        }

    }
}

