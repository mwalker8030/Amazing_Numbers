package numbers;

/**
 * This class is used to detect if a number is odd or not.
 */
public class OddDetector{
    private boolean isOdd;

    OddDetector(){
        isOdd = false;
    }

    public boolean evenOrOdd(long num){
        return num % 2 != 0;
    }
}
