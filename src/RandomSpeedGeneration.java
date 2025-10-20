import java.util.Random;
			/*Method for generating random speed between 40 to 100 ( all multiples of 10)*/
public class RandomSpeedGeneration {
    public static int randomSpeed() {
        Random random = new Random();
        // Generate a number from 0 to 6, then add 4 to it, resulting in a range from 4 to 10
        int randomNumber = (random.nextInt(7) + 4) * 10; 
        return randomNumber;
    }
}
