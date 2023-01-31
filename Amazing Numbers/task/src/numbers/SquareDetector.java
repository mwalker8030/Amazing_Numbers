package numbers;

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
