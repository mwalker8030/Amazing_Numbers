package numbers;

public class GapDetector {
    private long divisor;

    GapDetector(){
        divisor = 10;
    }

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
