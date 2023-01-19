package numbers;

public class DuckDetector {
    private boolean isDuck;
    private long temp;
    private long duck;

    DuckDetector(){temp = 0; duck = 0;}

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
