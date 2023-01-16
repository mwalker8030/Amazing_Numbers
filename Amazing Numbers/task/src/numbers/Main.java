package numbers;

public class Main {
    public static void main(String[] args) {
//        write your code here
        User user = new User();
        MagicDetector detector = new MagicDetector();
        //ask the user to enter a value
        user.getUserInput();
        //find the magic number
        detector.detectMagic(user.getStoredInput());

    }
}
