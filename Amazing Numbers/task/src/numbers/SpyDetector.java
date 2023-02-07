package numbers;
import java.util.ArrayList;

/**
 * This class is used to detect if a number is spy or not.
 */
public class SpyDetector {
    Long temp, sum, product, tempInt;
    ArrayList<Long> intArr;
    SpyDetector(){
        temp = 0L;
        sum = 0L;
        product = 0L;
        intArr = new ArrayList<Long>();
        tempInt = 0L;
    }

    public boolean detectSpy(Long num){
        //initialize important values;
        temp = num;
        sum = 0L;
        product = 1L;
        intArr.clear();

        //for all the digits in the whole value
        while(temp > 0){
            //separate the last digit from the whole num
            tempInt = temp % 10;
            temp /= 10;

            //add the number to the list of products
            intArr.add(tempInt);

            //add the number value to the sum
            sum += tempInt;
        }

        for(Long v : intArr){
            product *= v;
        }

        //return the comparison of Long values
        return sum.equals(product);
    }
}
