package numbers;
import java.util.ArrayList;
import java.util.Scanner;

public class User {
    private Scanner scan;
    private StringBuilder input;
    private ArrayList<String> strArr;
    private long value;
    private long[] values;
    User(){
        scan = new Scanner(System.in);
        input = new StringBuilder();
        strArr = new ArrayList<String>();
        value = 0;
        values = new long[]{0,0};
        initWelcome();
    }

    public void getUserInput(){
        try {
            input.setLength(0);
            System.out.print("Enter a request:");
            input.append(scan.nextLine());
            if(input.toString().contains(" ")){
                String[] temp = input.toString().split(" ");
                if(temp.length == 2){
                    values[0] = Long.parseLong(temp[0]);
                    values[1] = Long.parseLong(temp[1]);
                }
            }
            //detect if there are two value inputs in string
            //if there are separate them

            //use loop to make an array of entries for input

            //make sure each entry is valid and there is no more than 2

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
- enter two natural numbers to obtain the properties of the list:
  * the first parameter represents a starting number;
  * the second parameter shows how many consecutive numbers are to be processed;
- separate the parameters with one space;
- enter 0 to exit.
                """);
    }

    public boolean isValid() { return value >= 0; }

    public long getStoredValue(){ return this.value;}

}
