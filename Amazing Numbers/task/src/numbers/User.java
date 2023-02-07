package numbers;
import java.util.ArrayList;
import java.util.Scanner;

public class User {
    private Scanner scan;
    private StringBuilder input;
    private StringBuilder problemTypes;
    private ArrayList<String> strArr;
    private long value;
    private long[] values;
    private ArrayList<Long> valueList;
    private ArrayList<SNT.NumType> userNumTypes;
    private StringBuilder errNumTypes;
    private ArrayList<Integer> exclusions;
    User(){
        scan = new Scanner(System.in);
        input = new StringBuilder();
        problemTypes = new StringBuilder();
        strArr = new ArrayList<String>();
        value = 0;
        values = new long[]{0,0};
        valueList = new ArrayList<Long>();
        userNumTypes = new ArrayList<>();

        errNumTypes = new StringBuilder();
        exclusions = new ArrayList<>();

        initWelcome();
    }

    public void getUserInput(){
        try {
            input.setLength(0);
            valueList.clear();
            exclusions.clear();
            System.out.print("Enter a request:");
            input.append(scan.nextLine());
            //separate the string into two parts
            String[] temp = input.toString().split(" ");

            //assign value to index regardless of result
            if(isNotNumber(temp[0])){
                throw new UserInputException
                        ("\nThe first parameter should be a natural number or zero.");
            }
            values[0] = Long.parseLong(temp[0]);
            values[1] = 0;
            //if there are two parts
            if(temp.length >= 2){
                //get the sequence amount
                if(isNotNumber(temp[1])){
                    throw new UserInputException("\nThe second parameter should be a natural number.");
                }
                values[1] = Long.parseLong(temp[1]);

            }

            if(temp.length >= 3){
                //prints the rest of the String array starting from an index
                //insert commas between each token and trim off the last comma
                StringBuilder str = new StringBuilder();
                for(int i = 2; i < temp.length; i++){
                    str.append(temp[i].toUpperCase()).append(" ");
                }
                str.setLength(str.length() - 1);

                if (!isValid(str.toString().split(" "))) {
                    throw new UserInputException("""

                                The %s [%s] %s wrong.
                                Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING, HAPPY, SAD]""".formatted(
                            (errNumTypes.toString().contains(",") ? "properties" : "property") , errNumTypes.toString().toUpperCase(), (errNumTypes.toString().contains(",") ? "are" : "is")));
                } else {
                    for(int i = 2; i < temp.length; i++) {
                        //get the specified type of number to look for
                        userNumTypes.add(SNT.NumType.valueOf(exclude('-', temp[i]).toUpperCase()));
                    }
                }

                addNumTypeExclusions('-', temp, userNumTypes);

                if(containsParadox(str)){
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

    private void addNumTypeExclusions(char c, String[] temp, ArrayList<SNT.NumType> userNumTypes) {
        for(String str : temp){
            if(isNotNumber(str) && str.contains("-")){
                for(SNT.NumType t : userNumTypes){
                    if(t.toString().equalsIgnoreCase(exclude(c, str))){
                        exclusions.add(t.ordinal());
                    }
                }
            }
        }
    }


    private boolean containsParadox(StringBuilder str) {

        String[] temp = str.toString().split(" ");
        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp.length; j++) {

                if (i != j) {

                    SNT.NumType tempType = SNT.NumType.valueOf(exclude('-', temp[i].toUpperCase()));
                    SNT.NumType tempConflict = SNT.NumType.valueOf(exclude('-', temp[j].toUpperCase()));

                    if (checkTwinException(tempType, tempConflict)) {
                        problemTypes.append(temp[i]).append(" ").append(temp[j]);
                        return true;
                    } else if (checkForConflict(tempType, tempConflict)){
                        problemTypes.append(temp[i]).append(" ").append(temp[j]);
                        return true;
                    }

                }
            }
        }
        return false;
    }

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

    private boolean checkTwinException(SNT.NumType numType, SNT.NumType numType1) {
        if(numType == numType1 && exclusionAmongGroup(numType, numType1)){
            return true;
        }
        return false;
    }

    private boolean exclusionAmongGroup(SNT.NumType numType, SNT.NumType numType1) {
        return isExcluded(numType).equals("-") || isExcluded(numType1).equals("-");
    }

    private String isExcluded(SNT.NumType numType) {
        if(exclusions.contains(numType.ordinal())){
            return "-";
        }
        return "";
    }

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
     * Checks if the user input is valid
     * @param temp the user input
     * @return
     *  true if the user input is valid
     */
    private boolean isValid(String[] temp) {
        boolean flag = false;

        for(String str : temp){
            for (SNT.NumType t : SNT.NumType.values()){
                if(t.contains(exclude('-', str.toUpperCase()))){
                    flag = true;
                    break;
                }
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

    public boolean isValid(Long v) { return v >= 0; }
    public Long getStoredValue(int index){ return this.valueList.get(index);}
    public long getUserEntries(int index){ return this.values[index];}
    public ArrayList<Long> getValueList(){ return this.valueList;}

    public ArrayList<SNT.NumType> getUserNumTypes(){
        return userNumTypes;
    }

    public ArrayList<Integer> getExclusions() {
        return exclusions;
    }

    public void resetNumTypes() {
        userNumTypes.clear();
    }

}
