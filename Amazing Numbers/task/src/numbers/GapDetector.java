package numbers;

/**
 * This class is used to detect if a number is a gapful number or not.
 */
public class GapDetector {
    private long divisor;

    GapDetector(){
        divisor = 10;
    }

    /**
     * This method is used to detect if a number is a gapful number or not.
     * @param num
     *  The number to be checked.
     * @return
     *  True if the number is a gapful number, false otherwise.
     */
    public boolean detectGap(long num){
        if(num >= 100){
            divisor = getDivisor(num);
            return num % divisor == 0;
        }
        return false;
    }

    private long getDivisor(long num) {
        long temp = num % 10;
        long prev = num;
        while(prev >= 10){
            prev /= 10;
        }

        temp += prev * 10;
        return temp;
    }
}
