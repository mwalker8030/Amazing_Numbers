package numbers;

/**
 * This class is used to detect if a number is sun or not.
 */
public class SunDetector extends SquareDetector {
    SunDetector(){ }

    public boolean detectSun(Long val){
        return detectSquare(val + 1);
    }
}
