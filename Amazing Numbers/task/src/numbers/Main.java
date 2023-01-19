package numbers;

public class Main {
    public static void main(String[] args) {
//        write your code here
        User user = new User();
        Properties analysis = new Properties();

        while(true){
            //ask the user to enter a value
            user.getUserInput();
            if(user.getStoredValue() == 0){
                System.out.print("\nGoodbye!");
                break;}
            //find the properties of the number
            if(user.isValid() && user.getStoredValue() > 0){
                analysis.storeUserInput(user.getStoredValue());
                analysis.analyze(user.getStoredValue());
            }
        }
    }
}
