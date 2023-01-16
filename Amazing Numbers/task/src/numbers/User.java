package numbers;
import java.util.Scanner;

public class User {
    private Scanner scan;
    private StringBuilder input;
    private int value;
    User(){
        scan = new Scanner(System.in);
        input = new StringBuilder();
        value = 0;
    }

    public void getUserInput(){
        try {
            System.out.println("Enter a natural number:");
            input.append(scan.nextLine());

            for (char d : input.toString().toCharArray()) {
                if (d < '0' || d > '9') {
                    //throw some error
                    throw new UserInputException("This number is not natural!");
                }
            }

            value = Integer.parseInt(input.toString());
            if(!isValid()){
                throw new UserInputException("This number is not natural!");
            }
        }catch (UserInputException ex){
            System.out.println(ex.getMessage());
        }



    }

    private boolean isValid() {
        if(value > 0){
            return true;
        }
        return false;
    }


    public int getStoredValue(){ return value;}

}
