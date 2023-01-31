package numbers;

public class Main {
    public static void main(String[] args) {
//        write your code here
        User user = new User();
        Properties analysis = new Properties();

        while(true){
            //ask the user to enter a value
            user.getUserInput();
            //for each input from user
                //get stored value
            if( user.getValueList().size() == 1 && user.getStoredValue(0) == 0){
                System.out.print("\nGoodbye!");
                break;}

            if(user.getUserNumTypes().get(0).toString().equals(SpecificNums.NumType.DEFAULT.toString())){
                analysis.setPrinter(user.getValueList().size());
                for(Long val : user.getValueList()){
                    if(user.isValid(val)){
                        analysis.analyze(val);
                        analysis.incrementSequence();
                    }
                }

            } else {
                analysis.setPrinter(user.getUserEntries(1));
                analysis.analyze(user.getUserEntries(0), user.getUserNumTypes(), user.getUserEntries(1));
            }

            //print findings of all numbers
            analysis.displayProperties();
            //reset the counter of properties analyzed
            analysis.resetSequenceAndPrinter();
            user.resetNumTypes();
        }
    }
}
