package numbers;
import java.util.ArrayList;
import java.util.Scanner;

public class User {
    private Scanner scan;
    private StringBuilder input;
    private StringBuilder specifics;
    private ArrayList<String> strArr;
    private long value;
    private long[] values;
    private ArrayList<Long> valueList;
    private SpecificNums.NumType userNumType;
    User(){
        scan = new Scanner(System.in);
        input = new StringBuilder();
        specifics = new StringBuilder();
        strArr = new ArrayList<String>();
        value = 0;
        values = new long[]{0,0};
        valueList = new ArrayList<Long>();
        userNumType = SpecificNums.NumType.DEFAULT;
        initWelcome();
    }

    public void getUserInput(){
        try {
            input.setLength(0);
            valueList.clear();
            System.out.print("Enter a request:");
            input.append(scan.nextLine());
            //separate the string into two parts
            String[] temp = input.toString().split(" ");

            //assign value to index regardless of result
            if(!isNumber(temp[0])){
                throw new UserInputException
                        ("\nThe first parameter should be a natural number or zero.");
            }
            values[0] = Long.parseLong(temp[0]);
            values[1] = 0;
            //if there are two parts
            if(temp.length >= 2){
                //get the sequence amount
                if(!isNumber(temp[1])){
                    throw new UserInputException("\nsecond parameter should be a natural number");
                }
                values[1] = Long.parseLong(temp[1]);

            }

            if(temp.length == 3){
                //get the specified type of number to look for
                if(!isValid(temp[2].toLowerCase())){
                    throw new UserInputException("""
                            
                            The property [%s] is wrong.
                            Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY]""".formatted(temp[2].toUpperCase()));
                }
                userNumType = SpecificNums.NumType.getType(temp[2]);
                specifics.append(temp[2].toLowerCase());
            }else{
                //assign the values to a temporary storage
                long tempValue = values[0];
                do{
                    //add each sequence to the end of the list
                    valueList.add(tempValue++);
                    //until number of sequences reaches 0
                } while(values[1]-- > 1);

                for(Long v : valueList){
                    if(!isValid(v)){
                        throw new UserInputException
                                ("\nThe first parameter should be a natural number or zero.\n");
                    }
                }

                for (char d : input.toString().toCharArray()) {
                    if (d < '0' || d > '9') {
                        //throw some error
                        if(d != ' ') {
                            throw new UserInputException
                                    ("\nThe first parameter should be a natural number or zero.\n");
                        }
                    }
                }
            }


            //for(Long v : valueList){ }

        }catch (UserInputException ex){
            System.out.print(ex.getMessage());
        }
    }

    private boolean isNumber(String s) {
        for(char d : s.toCharArray()){
            if (d < '0' || d > '9') {
                //throw some error
                return false;
            }
        }
        return true;
    }

    private boolean isValid(String s) {
        for(SpecificNums.NumType t : SpecificNums.NumType.values()){
            if(s.equals(t.toString().toLowerCase())){
                userNumType = t;
                return true;
            }
        }
        return false;
    }

    private void initWelcome() {
        System.out.println("""
Welcome to Amazing Numbers!

Supported requests:
- enter a natural number to know its properties;
- enter two natural numbers to obtain the properties of the list:
  * the first parameter represents a starting number;
  * the second parameters show how many consecutive numbers are to be processed;
- two natural numbers and a property to search for;
- separate the parameters with one space;
- enter 0 to exit.
                """);
    }

    public boolean isValid(Long v) { return v >= 0; }

    public long getStoredValue(){ return this.value;}
    public Long getStoredValue(int index){ return this.valueList.get(index);}
    public long getUserEntries(int index){ return this.values[index];}

    public ArrayList<Long> getValueList(){ return this.valueList;}

    public SpecificNums.NumType getUserNumType(){
        return userNumType;
    }

    public void resetNumType() {
        userNumType = SpecificNums.NumType.DEFAULT;
    }
}
