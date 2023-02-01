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
    private ArrayList<SpecificNums.NumType> userNumTypes;
    private StringBuilder errNumTypes;
    User(){
        scan = new Scanner(System.in);
        input = new StringBuilder();
        specifics = new StringBuilder();
        strArr = new ArrayList<String>();
        value = 0;
        values = new long[]{0,0};
        valueList = new ArrayList<Long>();
        userNumTypes = new ArrayList<>();
        errNumTypes = new StringBuilder();

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
                    throw new UserInputException("\nThe second parameter should be a natural number.");
                }
                values[1] = Long.parseLong(temp[1]);

            }

            if(temp.length >= 3){
                //prints the rest of the String array starting from an index
                //insert commas between each token and trim off the last comma
                StringBuilder str = new StringBuilder();
                for(int i = 2; i < temp.length; i++){
                    str.append(temp[i]).append(" ");
                }
                str.setLength(str.length() - 1);

                for(int i = 2; i < temp.length; i++) {
                    //get the specified type of number to look for
                    if (!isValid(str.toString().split(" "))) {
                        throw new UserInputException("""
                                                            
                                The %s [%s] %s wrong.
                                Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY]""".formatted(
                                (errNumTypes.toString().contains(",") ? "properties" : "property") , errNumTypes.toString().toUpperCase(), (errNumTypes.toString().contains(",") ? "are" : "is")));
                    }
                    userNumTypes.add(SpecificNums.NumType.valueOf(temp[i]));
                    specifics.append(temp[i].toLowerCase());
                }

                if(containsParadox()){
                    throw new UserInputException("""
                                                    
                            The request contains mutually exclusive properties: [%s]
                            There are no numbers with these properties.""".formatted(str.toString().toUpperCase()));
                }

            }else{
                userNumTypes.add(SpecificNums.NumType.DEFAULT);
                //assign the values to a temporary storage
                long tempValue = values[0];
                long tempSeq = values[1];
                do{
                    //add each sequence to the end of the list
                    valueList.add(tempValue++);
                    //until number of sequences reaches 0
                } while(values[1]-- > 1);
                values[1] = tempSeq;

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
            if(userNumTypes.isEmpty()){
                userNumTypes.add(SpecificNums.NumType.DEFAULT);
            } else {
                userNumTypes.set(0, SpecificNums.NumType.DEFAULT);
            }
            System.out.print(ex.getMessage());
            if(!errNumTypes.isEmpty()){
                errNumTypes.setLength(0);
            }
        }
    }

    private boolean containsParadox() {
        for(SpecificNums.NumType t : userNumTypes){
            for(SpecificNums.NumType errCheck : userNumTypes){
                if(t.getConflictingProperty().equals(errCheck.toString().toLowerCase())){
                    return true;
                }
            }
        }
        return false;
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

    private boolean isValid(String[] temp) {
        boolean flag = false;

        for(String str : temp){
            /*
            if(isNumber(str)){continue;}
        */
        if(SpecificNums.NumType.valueOf(str) != SpecificNums.NumType.DEFAULT){
                flag = true;
            }

            if(!flag){
                errNumTypes.append(str).append(", ");
            }
            flag = false;
        }


        if(!errNumTypes.isEmpty()){
            errNumTypes.setLength(errNumTypes.length() - 2);
            return false;
        }


        return true;

    }

    private boolean isValid(String s) {
        for(SpecificNums.NumType t : SpecificNums.NumType.values()){
            if(s.equals(t.toString().toLowerCase())){
                return true;
            }
        }
        return false;
    }

    private void initWelcome() {
        System.out.println("""
                Welcome to Amazing Numbers!

                Supported requests:
                - enter a natural number to know its properties;\s
                - enter two natural numbers to obtain the properties of the list:
                  * the first parameter represents a starting number;
                  * the second parameter shows how many consecutive numbers are to be printed;
                - two natural numbers and a property to search for;
                - two natural numbers and two properties to search for;
                - separate the parameters with one space;
                - enter 0 to exit.
                """);
    }

    public boolean isValid(Long v) { return v >= 0; }

    public long getStoredValue(){ return this.value;}
    public Long getStoredValue(int index){ return this.valueList.get(index);}
    public long getUserEntries(int index){ return this.values[index];}

    public ArrayList<Long> getValueList(){ return this.valueList;}

    public ArrayList<SpecificNums.NumType> getUserNumTypes(){
        return userNumTypes;
    }

    public void resetNumTypes() {
        userNumTypes.clear();
    }



}
