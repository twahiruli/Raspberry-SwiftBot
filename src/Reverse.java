import swiftbot.SwiftBotAPI;
public class Reverse {
    	public static void retrace(SwiftBotAPI swiftbot) {
    	System.out.println("Starting to Retrace. . . ");
    	
    	System.out.println("Turning around. . . ");
    	swiftbot.move(100 , -100, 400); // right turn
    	  /*Condition to check if its Normal Mode or not and this will run if its zigzag not squareLoop so
    	   * thats why squareLoopcheck is false*/
    	for (int i = 1; i <= Movement.Calc; i++) {
    	if (Movement.mode==true && Movement.squareloopcheck==false) {
    	swiftbot.fillUnderlights(SetColours.selectedColor2); // fill with second colour
    	Movement.adjustMoveBasedOnWheelSpeed(Movement.wheelSpeed, Movement.zigLength);// moves forward with the adjusted wheel speed 
        swiftbot.move(0, 0, 1000 );//pause for 1 second
        swiftbot.move(100 , -100, 400); // right turn
        swiftbot.fillUnderlights(SetColours.selectedColor);// fill with First colour
        Movement.adjustMoveBasedOnWheelSpeed(Movement.wheelSpeed, Movement.zigLength);// moves forward with the adjusted wheel speed 
        swiftbot.move(0, 0, 1000 );//pause for 1 second	
        swiftbot.move(-100 , 100, 400); // left turn
    	}/* This else will run if its zigzag and Energy saving mode*/
    	else {
    		Movement.adjustMoveBasedOnWheelSpeed(Movement.wheelSpeed, Movement.zigLength);// moves forward with the adjusted wheel speed 
    		swiftbot.move(0, 0, 1000 );//pause for 1 second
    		swiftbot.move(100 , -100, 400); // right turn
            Movement.adjustMoveBasedOnWheelSpeed(Movement.wheelSpeed, Movement.zigLength);// moves forward with the adjusted wheel speed 
            swiftbot.move(0, 0, 1000 );//pause for 1 second	
            swiftbot.move(-100 , 100, 400); // left turn
        	
    	}
    	
    	
        	
    	}
    	System.out.println("Zigzag Completed");
    }
    	
  
    
}

				
