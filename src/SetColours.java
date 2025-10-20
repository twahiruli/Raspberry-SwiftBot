public class SetColours {
public static int[] selectedColor2;
public static int[] selectedColor;


		/*Method for selecting first colour*/
public static void setfirstColour() {
		

		System.out.println("Select a First colour:");
		System.out.println("Press 1: Red");
		System.out.println("Press 2: Green");
		System.out.println("Press 3: Blue");
		System.out.println("Press 4: Yellow");

		int underLightSection1 = Movement.scanner.nextInt();
		try {
			switch (underLightSection1) {
			case 1:
				selectedColor = new int[] { 255, 0, 0 }; // Red
				break;
			case 2:
				selectedColor = new int[] { 0, 0, 255 }; // Green
				break;
			case 3:
				selectedColor = new int[] { 0, 255, 0 }; // Blue
				break;
			case 4:
				selectedColor = new int[] { 200, 0, 150 }; // Yellow
				break;
			default:
				setfirstColour();
				break;
			}
	
		} catch (Exception e) {
			
			Movement.scanner.next();
			setfirstColour(); 
		}
	}
	
	
	
	/*Method for Picking Second Colour */
	public static void setSecondColour() {
		

		System.out.println("Select a Second colour:");
		System.out.println("Press 1: Red");
		System.out.println("Press 2: Green");
		System.out.println("Press 3: Blue");
		System.out.println("Press 4: Yellow");

		int underLightSection2 = Movement.scanner.nextInt();
		try {
			switch (underLightSection2) {
			case 1:
				selectedColor2 = new int[] { 255, 0, 0 }; // Red
				break;
			case 2:
				selectedColor2 = new int[] { 0, 0, 255 }; // Green
				break;
			case 3:
				selectedColor2 = new int[] { 0, 255, 0 }; // Blue
				break;
			case 4:
				selectedColor2 = new int[] { 200, 0, 150 }; // Yellow
				break;
			default:
				setSecondColour();
				break;
			}		
		} 
		catch (Exception e) {
			Movement.scanner.next();
			setSecondColour();    // recursively calling method in Exception
		} 
	}
}
