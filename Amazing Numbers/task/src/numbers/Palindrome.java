package numbers;

public class Palindrome {

    long temp;
    long palindrome;
    Palindrome(){temp = 0; palindrome = 0;}

    protected boolean detectPalindrome(long num){
        initFields(num);
        while(temp > 0){
            palindrome *= 10;
            palindrome += temp % 10;
            temp /= 10;
        }

        return palindrome == num;
    }

    private void initFields(long num){
        temp = num;
        palindrome = 0;
    }
}
