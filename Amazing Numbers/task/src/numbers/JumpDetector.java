package numbers;

/**
 * This class is used to detect if a number is jump or not.
 */
public class JumpDetector {
    private Long prev, curr;

    public JumpDetector(){
        prev = null;
        curr = null;
    }
    public boolean detectJump(long value){
        prev = 0L;
        curr = 0L;

        long prev = value % 10;
        value /= 10;
        while (value > 0) {
            long curr = value % 10;
            if (Math.abs(curr - prev) != 1) {
                return false;
            }
            prev = curr;
            value /= 10;
        }
        return true;
    }
}
