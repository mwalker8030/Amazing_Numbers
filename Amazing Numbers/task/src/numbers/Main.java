package numbers;

public class Main {
    public static void main(String[] args) {
//        write your code here
        User user = new User();
        Properties analysis = new Properties();

        //for each input from user
        while(true){
            //ask the user to enter a value
            user.getUserInput();

            //if user enters 0, exit the program
            if( user.getValueList().size() == 1 && user.getStoredValue(0) == 0){
                System.out.print("\nGoodbye!");
                break;}

            //set the printer based off of user input
            analysis.setPrinter(user.getUserEntries(1));

            //if user does not specify number type, analyze all properties of any number
            if(user.getUserNumTypes().get(0).toString().equals(SNT.NumType.DEFAULT.toString())){
                //analyze the properties of each number requested
                for(Long val : user.getValueList()){
                    //analyze the properties of the number
                    analysis.analyze(val);
                    //increment the counter of properties analyzed
                    analysis.incrementSequence();
                }
            //else if user specifies number type, analyze only the properties of the specified number type
            } else {

                //if user does not specify exclusions, analyze all properties of the specified number type
                if(user.getExclusions().isEmpty())
                    analysis.analyze(user.getUserEntries(0), user.getUserNumTypes(), user.getUserEntries(1));
                //else if user specifies exclusions, analyze all properties of the specified number type except the exclusions
                else
                    analysis.analyze(user.getUserEntries(0), user.getUserNumTypes(), user.getUserEntries(1), user.getExclusions());
            }

            //print findings of all numbers
            analysis.displayProperties();
            //reset the counter of properties analyzed
            analysis.resetSequenceAndPrinters();
            //reset the number types to be analyzed
            user.resetNumTypes();
        }
    }
}
