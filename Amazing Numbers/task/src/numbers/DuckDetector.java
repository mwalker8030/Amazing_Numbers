package numbers;

/**
 * This class is used to detect if a number is duck or not.
 */
public class DuckDetector {
    private boolean isDuck;
    private long temp;
    private long duck;

    DuckDetector(){temp = 0; duck = 0;}

    /**
     * This method is used to detect if a number is duck or not.
     * @param num
     *  The number to be checked.
     * @return
     *  True if the number is duck, false otherwise.
     */
    public boolean detectDucking(long num) {
        temp = num;
        while(temp > 0){
            duck = temp % 10;
            temp /= 10;
            if(duck == 0){return true;}
        }
        return false;
    }
}
