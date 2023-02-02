package numbers;

import java.util.ArrayList;

public class HappyDetector {

    Long temp, sum, l;
    private ArrayList<Long> cachedValues;

    HappyDetector(){
        temp = 0L;
        sum = 0L;
        cachedValues = new ArrayList<>();
    }

    public boolean detectHappy(Long num){
        cachedValues.clear();
        temp = num;
        sum = 0L;
        getSum();

        if(sum == 1) { return true; }
        else if(cachedValues.contains(num)) { return false; }
        else {
            cachedValues.add(sum);
            return detectHappy(sum, num);
        }
    }

    private boolean detectHappy(long sum, long num) {
        temp = this.sum;
        this.sum = 0L;
        getSum();

        if(this.sum == 1){ return true; }
        else if(cachedValues.contains(this.sum)){ return false; }
        else{
            cachedValues.add(this.sum);
            return detectHappy(sum, num);
        }
    }

    private void getSum() {
        while(temp > 0){
            l = temp % 10;
            this.sum += l * l;
            temp /= 10;
        }
    }
}
