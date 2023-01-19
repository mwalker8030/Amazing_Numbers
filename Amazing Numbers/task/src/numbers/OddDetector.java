package numbers;

public class OddDetector{
    private boolean isOdd;

    OddDetector(){
        isOdd = false;
    }

    public boolean evenOrOdd(long num){
        return num % 2 != 0;
    }
}
