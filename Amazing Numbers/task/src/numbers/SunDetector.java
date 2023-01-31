package numbers;

public class SunDetector extends SquareDetector {
    SunDetector(){ }

    public boolean detectSun(Long val){
        return detectSquare(val + 1);
    }
}
