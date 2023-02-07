package numbers;

/**
 * This class is used to detect if a number is square or not.
 */
public class SquareDetector {

    double sqrt;
    SquareDetector(){
        sqrt = 0.0;
    }
    //find numbers that are perfect squares
    public boolean detectSquare(long num){
        sqrt = Math.sqrt(num);
        return sqrt == Math.floor(sqrt);
    }

}
