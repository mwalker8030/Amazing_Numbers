package numbers;
import java.util.Scanner;

public class User {
    private Scanner scan;
    private StringBuilder input;
    User(){
        scan = new Scanner(System.in);
        input = new StringBuilder();
    }

    public void getUserInput(){
        try {
            input.append(scan.nextLine());

            for (char d : input.toString().toCharArray()) {
                if (d < '0' || d > '9') {
                    //throw some error
                    throw new UserInputException("This number is not natural!");
                }
            }
        }catch (UserInputException ex){
                System.out.println(ex.getMessage());
        }

    }

    public int getStoredInput(){
        return Integer.getInteger(input.toString());
    }

}
