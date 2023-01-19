package numbers;
import java.util.Scanner;

public class User {
    private Scanner scan;
    private StringBuilder input;
    private long value;
    User(){
        scan = new Scanner(System.in);
        input = new StringBuilder();
        value = 0;
        initWelcome();
    }

    public void getUserInput(){
        try {
            input.setLength(0);
            System.out.print("Enter a request:");
            input.append(scan.nextLine());

            for (char d : input.toString().toCharArray()) {
                if (d < '0' || d > '9') {
                    //throw some error
                    throw new UserInputException
                            ("\nThe first parameter should be a natural number or zero.\n");
                }
            }
            value = Long.parseLong(input.toString());
            if(!isValid()){
                throw new UserInputException
                        ("\nThe first parameter should be a natural number or zero.\n");
            }
        }catch (UserInputException ex){
            System.out.println(ex.getMessage());
        }finally{
            value = Long.parseLong(input.toString());
        }
    }

    private void initWelcome() {
        System.out.println("""
                Welcome to Amazing Numbers!
                                
                Supported requests:
                - enter a natural number to know its properties;
                - enter 0 to exit.
                """);
    }

    public boolean isValid() { return value >= 0; }

    public long getStoredValue(){ return this.value;}

}
