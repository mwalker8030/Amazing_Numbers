package numbers;

/**
 * This is a custom exception class that is used to handle user input errors.
 */
public class UserInputException extends Exception{
    UserInputException(String e){
        super(e);
    }
}
