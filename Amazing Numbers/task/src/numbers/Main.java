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

            analysis.setPrinter(user.getValueList().size());
            for(Long val : user.getValueList()){
                if(user.isValid(val)){
                    analysis.storeUserInput(val);
                    analysis.analyze(val);
                    analysis.incrementSequence();
                }
            }
            //print findings of all numbers
            analysis.displayProperties();
            //reset the counter of properties analyzed
            analysis.resetSequenceAndPrinter();
        }
    }
}
