package numbers;

public class OddDetector{
    private boolean isOdd;

    OddDetector(){
        isOdd = false;
    }

    public boolean evenOrOdd(int num){
        return num % 2 != 0;
    }
}
