package numbers;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Michael Walker...don't remember the date
 *
 * This class is responsible for getting user input and storing it in a usable format.
 */
public class User {
    private Scanner scan;
    private StringBuilder input;
    private StringBuilder problemTypes;
    private StringBuilder errNumTypes;
    private long[] values;
    private ArrayList<Long> valueList;
    private ArrayList<SNT.NumType> userNumTypes;
    private ArrayList<Integer> exclusions;

    /**
     * This Constructor initializes the welcome message and the list of user data for the program to function.
     */
    User(){
        scan = new Scanner(System.in);
        input = new StringBuilder();
        problemTypes = new StringBuilder();
        values = new long[]{0,0};
        valueList = new ArrayList<Long>();
        userNumTypes = new ArrayList<>();

        errNumTypes = new StringBuilder();
        exclusions = new ArrayList<>();

        initWelcome();
    }

    /**
     * This method is responsible for getting user input and storing it in a usable format.
     */
    public void getUserInput(){
        try {
            //reset the input for reuse.
            input.setLength(0);
            valueList.clear();
            exclusions.clear();
            values[1] = 0;

            //ask the user for input
            System.out.print("Enter a request:");
            input.append(scan.nextLine());
            String[] temp = input.toString().split(" ");

            //if the user enters something other than a number, throw an exception
            if(isNotNumber(temp[0])){
                throw new UserInputException
                        ("\nThe first parameter should be a natural number or zero.");
            }
            //store the value to be analyzed
            values[0] = Long.parseLong(temp[0]);
            //if there are two parts
            if(temp.length >= 2){
                //if the second part is not a number, throw an exception
                if(isNotNumber(temp[1])){
                    throw new UserInputException("\nThe second parameter should be a natural number.");
                }
                //store the value to use as the number of values to analyze
                values[1] = Long.parseLong(temp[1]);

            }

            //if the user enters more than 2 parts to read
            if(temp.length >= 3){

                //isolate the remaining parts of the input
                StringBuilder str = new StringBuilder();
                for(int i = 2; i < temp.length; i++){
                    str.append(temp[i].toUpperCase()).append(" ");
                }
                str.setLength(str.length() - 1);

                //if the user enters a type that is not a valid number type, throw an exception
                if (!isNumType(str.toString().split(" "))) {
                    throw new UserInputException("""

                                The %s [%s] %s wrong.
                                Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING, HAPPY, SAD]""".formatted(
                            (errNumTypes.toString().contains(",") ? "properties" : "property") , errNumTypes.toString().toUpperCase(), (errNumTypes.toString().contains(",") ? "are" : "is")));
                } else {
                    //for every type requested, add it to the list of types to check
                    for(int i = 2; i < temp.length; i++) {
                        //store the specified type of number to look for
                        userNumTypes.add(SNT.NumType.valueOf(exclude('-', temp[i]).toUpperCase()));
                    }
                }

                //if the requested any types to be excluded, add them to the list of exclusions
                addNumTypeExclusions('-', temp, userNumTypes);

                //if the user requests any types the cause a paradox, throw an exception
                if(containsParadox(temp)){
                    throw new UserInputException("""
                                                    
                            The request contains mutually exclusive properties: [%s]
                            There are no numbers with these properties.""".formatted(problemTypes.toString().toUpperCase()));
                }

            }else{
                userNumTypes.add(SNT.NumType.DEFAULT);
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
                    if(!isNaturalNumber(v)){
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
                userNumTypes.add(SNT.NumType.DEFAULT);
            } else {
                userNumTypes.set(0, SNT.NumType.DEFAULT);
            }
            System.out.print(ex.getMessage());
            if(!errNumTypes.isEmpty()){
                errNumTypes.setLength(0);
            }
        }
    }

    /**
     * This method is responsible for checking if the user input contains any requested exclusions and adds the requested
     * exclusions to the list of exclusions.
     * @param c
     *  the character that determines if the type is an exclusion.
     * @param temp
     *  the array of strings that contains the user input.
     * @param userNumTypes
     *  the list of number types that the user has requested.
     */
    private void addNumTypeExclusions(char c, String[] temp, ArrayList<SNT.NumType> userNumTypes) {

        //for every string in the array
        for(String str : temp){
            //if the string is not a number and contains the exclusion character
            if(isNotNumber(str) && str.contains("-")){
                //for every number type
                for(SNT.NumType t : userNumTypes){
                    //if the string is equal to the number type, add the number type to the list of exclusions
                    if(t.toString().equalsIgnoreCase(exclude(c, str))){
                        exclusions.add(t.ordinal());
                    }
                }
            }
        }
    }

    /**
     * This method is responsible for checking if the user input contains any types that cause a paradox.
     * @param temp
     *  the array of strings that contains the user input.
     * @return
     *  true if the user input contains any types that cause a paradox, false otherwise.
     */
    private boolean containsParadox(String[] temp) {
        SNT.NumType tempType, tempConflict;

        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp.length; j++) {

                //if the strings are not numbers and are not the same string
                if (i != j && isNotNumber(temp[i]) && isNotNumber(temp[j])) {
                    //find the number type of the string
                    tempType = SNT.NumType.valueOf(exclude('-', temp[i].toUpperCase()));
                    tempConflict = SNT.NumType.valueOf(exclude('-', temp[j].toUpperCase()));

                    //if the number type is equal to the other number type, add the number types to the problem types
                    if (checkTwinException(tempType, tempConflict)) {
                        problemTypes.append(temp[i]).append(" ").append(temp[j]);
                        return true;
                    //if the number type conflicts with the other number type, add the number types to the problem types
                    } else if (checkForConflict(tempType, tempConflict)){
                        problemTypes.append(temp[i]).append(" ").append(temp[j]);
                        return true;
                    }

                }
            }
        }

        //if the user input does not contain any types that cause a paradox, return false
        return false;
    }

    /**
     * This method is responsible for checking if the user input contains any types that conflict with each other.
     * @param tempType
     *  the number type of the first string.
     * @param tempConflict
     *  the number type of the second string.
     * @return
     *  true if the user input contains any types that conflict with each other, false otherwise.
     */
    private boolean checkForConflict(SNT.NumType tempType, SNT.NumType tempConflict) {
        if (tempType.getConflict().name().equals(tempConflict.name())){
            if(exclusions.contains(tempType.ordinal())){
                if(!exclusions.contains(SNT.NumType.valueOf(tempConflict.name()).ordinal())){
                    return false;
                }
            }
            if(exclusions.contains(SNT.NumType.valueOf(tempConflict.name()).ordinal())){
                if(!exclusions.contains(tempType.ordinal())){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * This method is responsible for checking if the user input contains any types that are the same.
     * @param numType
     *  the number type of the first string.
     * @param numType1
     *  the number type of the second string.
     * @return
     *  true if the user input contains any types that are the same, false otherwise.
     */
    private boolean checkTwinException(SNT.NumType numType, SNT.NumType numType1) {
        if(numType == numType1 && exclusionAmongGroup(numType, numType1)){
            return true;
        }
        return false;
    }

    /**
     * This method is responsible for checking if the parameters are a part of the list of excluded number types.
     * @param numType
     *  the character that determines if the type is an exclusion.
     * @param numType1
     *  the string that contains the user input.
     * @return
     *  the string without the exclusion character.
     */
    private boolean exclusionAmongGroup(SNT.NumType numType, SNT.NumType numType1) {
        return isExcluded(numType).equals("-") || isExcluded(numType1).equals("-");
    }

    /**
     * This method is responsible for checking if the parameters are a part of the list of excluded number types.
     * @param numType
     *  the number type that is being checked.
     * @return
     *  the string without the exclusion character.
     */
    private String isExcluded(SNT.NumType numType) {
        if(exclusions.contains(numType.ordinal())){
            return "-";
        }
        return "";
    }

    /**
     * This method is responsible for checking if the user input contains any types that are not numbers.
     * @param s
     *  the string that contains the user input.
     * @return
     *  true if the user input contains any types that are not numbers, false otherwise.
     */
    private boolean isNotNumber(String s) {
        for(char d : s.toCharArray()){
            if (d < '0' || d > '9') {
                //throw some error
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the user input is valid. If not, it adds the invalid input to the error message and
     * continues to check the rest of the input.
     * @param temp the user input
     * @return
     *  true if the user input is valid
     */
    private boolean isNumType(String[] temp) {

        //set flag to false for the beginning of the function
        boolean flag = false;

        //check if all tokens are valid number types
        for(String str : temp){
            for (SNT.NumType t : SNT.NumType.values()){
                if(t.contains(exclude('-', str.toUpperCase()))){
                    flag = true;
                    break;
                }
            }

            //if the token is not a valid number type, add it to the error message
            if(!flag){
                errNumTypes.append(str).append(", ");
            }

            //reset flag
            flag = false;
        }

        //if there are any invalid number types, remove the last comma and space, and return false
        if(!errNumTypes.isEmpty()){
            errNumTypes.setLength(errNumTypes.length() - 2);
            return false;
        }

        //if there are no invalid number types, return true
        return true;
    }

    /**
     * Removes a character from a string
     * @param c the character to remove
     * @param str the string to remove the character from
     * @return the string without the character
     */
    private String exclude(char c, String str) {
        StringBuilder sb = new StringBuilder();
        for(char d : str.toCharArray()){
            if(d != c){
                sb.append(d);
            }
        }
        return sb.toString();
    }

    /**
     * This method is responsible for displaying the welcome message.
     */
    private void initWelcome() {
        System.out.println("""
                Welcome to Amazing Numbers!
                                
                Supported requests:
                - enter a natural number to know its properties;
                - enter two natural numbers to obtain the properties of the list:
                  * the first parameter represents a starting number;
                  * the second parameter shows how many consecutive numbers are to be processed;
                - two natural numbers and properties to search for;
                - a property preceded by minus must not be present in numbers;
                - separate the parameters with one space;
                - enter 0 to exit.
                """);
    }

    /**
     * this method determines if the user input is a natural number.
     * @param v
     *  the value to check
     * @return
     *  true if the user input is a natural number, false otherwise.
     */
    public boolean isNaturalNumber(Long v) { return v >= 0; }

    //getters for most lists and variables
    public Long getStoredValue(int index){ return this.valueList.get(index);}
    public long getUserEntries(int index){ return this.values[index];}
    public ArrayList<Long> getValueList(){ return this.valueList;}
    public ArrayList<SNT.NumType> getUserNumTypes(){ return userNumTypes;}
    public ArrayList<Integer> getExclusions() { return exclusions;}

    /**
     * This method is responsible for resetting the user input.
     */
    public void resetNumTypes() { userNumTypes.clear();}

}
