package numbers;

public class Main {
    public static void main(String[] args) {
//        write your code here
        User user = new User();
        //ask the user to enter a value
        user.getUserInput();
        //find the properties of the number
        Properties analysis = new Properties(user.getStoredValue());
    }
}
