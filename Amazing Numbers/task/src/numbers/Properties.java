package numbers;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Michael Elmo Walker
 */
public class Properties {


    public enum Printer{
        SINGLE(1),
        MULTI(2);

        public final int printerType;

        Printer(int printerType){
            this.printerType = printerType;
        }

    }
    private Printer printer;
    private ArrayList<String> attributes;

    private ArrayList<Property> detailedList;
    private ArrayList<Integer> nsnt;
    private long storedValue;
    private StringBuilder msg;
    private OddDetector oddDetective;
    private MagicDetector mageDetective;

    private DuckDetector duckDetective;
    private Palindrome paliDetective;
    private GapDetector gapDetective;
    private SpyDetector spyDetective;
    private SquareDetector squareDetective;
    private SunDetector sunDetective;
    private JumpDetector jumpDetective;
    private HappyDetector happyDetective;

    private int sequenceCounter;
    public void resetSequenceAndPrinters() {
        sequenceCounter = 0;
        msg.setLength(0);
        printer = Printer.SINGLE;
    }
    public void incrementSequence() { ++sequenceCounter; }

    public void setPrinter(long size) {
        if(size > 0){
            printer = Printer.MULTI;
        }
    }


    /**
     * This interface is used to detect properties of a number
     */
    interface Detective {
        boolean detect();
    }

    /**
     * This is a list of all the properties that can be detected in the form of an array of functions.
     */
    private Detective[] detectives = new Detective[]{
            new Detective(){ public boolean detect(){return !oddDetective.evenOrOdd(storedValue);}},
            new Detective(){ public boolean detect(){return oddDetective.evenOrOdd(storedValue);}},
            new Detective(){ public boolean detect(){return mageDetective.detectMagic(storedValue);}},
            new Detective(){ public boolean detect(){return duckDetective.detectDucking(storedValue);}},
            new Detective(){ public boolean detect(){return paliDetective.detectPalindrome(storedValue);}},
            new Detective(){ public boolean detect(){return gapDetective.detectGap(storedValue);}},
            new Detective(){ public boolean detect(){return spyDetective.detectSpy(storedValue);}},
            new Detective(){ public boolean detect(){return squareDetective.detectSquare(storedValue);}},
            new Detective(){ public boolean detect(){return sunDetective.detectSun(storedValue);}},
            new Detective(){ public boolean detect(){return jumpDetective.detectJump(storedValue);}},
            new Detective(){ public boolean detect(){return happyDetective.detectHappy(storedValue);}},
            new Detective(){ public boolean detect(){return !happyDetective.detectHappy(storedValue);}}

    };

    /**
     * This Constructor is used to initialize the attributes and the list of properties
     */
    Properties(){
        msg = new StringBuilder("");
        initAttributes();
        initDetectives();
        initList();
        this.storedValue = 0;
        this.sequenceCounter = 0;
        printer = Printer.SINGLE;
    }
    private void initAttributes() {
        attributes = new ArrayList<String>(
                Arrays.asList(
                        "even",
                        "odd",
                        "buzz",
                        "duck",
                        "palindromic",
                        "gapful",
                        "spy",
                        "square",
                        "sunny",
                        "jumping",
                        "happy",
                        "sad"
                )
        );
    }

    private void initList(){
        detailedList = new ArrayList<Property>();
        while(detailedList.size() < attributes.size()){
            detailedList.add(new Property());
        }
        nsnt = new ArrayList<Integer>();
    }
    private void initDetectives(){
        oddDetective = new OddDetector();
        mageDetective = new MagicDetector();
        duckDetective = new DuckDetector();
        paliDetective = new Palindrome();
        gapDetective = new GapDetector();
        spyDetective = new SpyDetector();
        squareDetective = new SquareDetector();
        sunDetective = new SunDetector();
        jumpDetective = new JumpDetector();
        happyDetective = new HappyDetector();
    }

    /**
     * This method is used to analyze a single number and store the properties of that number
     * @param num
     *  The number to be analyzed
     */
    public void analyze(long num){

        //assign passed value to stored value
        storedValue = num;
        for(int i = 0; i < attributes.size(); i++)
            detailedList.set(i, new Property(attributes.get(i),detectives[i].detect()));

        if(printer == Printer.SINGLE)
            saveProperties();
        else
            saveListOfProperties();
        resetList();
    }

    /**
     * Analyze a range of numbers and store the properties of each number in a list
     * to be printed later.
     * @param val
     *  The starting value of the range
     * @param numTypes
     *  The types of numbers to be analyzed
     * @param quantity
     *  The quantity of numbers to be analyzed
     */
    public void analyze(Long val, ArrayList<SNT.NumType> numTypes, long quantity) {
        nsnt.clear();
        storedValue = val;
        for(SNT.NumType t : SNT.NumType.values()){
            if(!numTypes.contains(t) && t != SNT.NumType.DEFAULT){
                nsnt.add(t.ordinal());
            }
        }

        for(long i = 0; i < quantity;){

            if(detectSpecifics(numTypes)){
                for(int ind : nsnt){
                    detailedList.set(ind, new Property(attributes.get(ind),detectives[ind].detect()));
                }

                if(printer == Printer.SINGLE) {
                    saveProperties();
                }
                else {
                    saveListOfProperties();
                }
                resetList();
                i++;
            }

            storedValue++;
        }
    }

    /**
     * Analyze a range of numbers and store the properties of each number in a list, discard any number that has a property
     *  this is excluded from the list of properties to be analyzed.
     * @param userValue
     *  The starting value of the range
     * @param userNumTypes
     *  The types of properties to be confirmed for the value to be successfully analyzed
     * @param quantity
     *  The quantity of numbers to be analyzed
     * @param exclusions
     *  The types of properties to be excluded from the analysis
     */
    public void analyze(long userValue, ArrayList<SNT.NumType> userNumTypes, long quantity, ArrayList<Integer> exclusions) {
        nsnt.clear();
        storedValue = userValue;

        for(SNT.NumType t : SNT.NumType.values()){
            if(!userNumTypes.contains(t) && !exclusions.contains(t.ordinal()) && t != SNT.NumType.DEFAULT && !exclusions.contains(t.ordinal())){
                nsnt.add(t.ordinal());
            }
        }

        for(long i = 0; i < quantity;){

            if(detectSpecifics(userNumTypes, exclusions)){
                for(int ind : nsnt){
                    detailedList.set(ind, new Property(attributes.get(ind),detectives[ind].detect()));
                }

                if(printer == Printer.SINGLE) {
                    saveProperties();
                }
                else {
                    saveListOfProperties();
                }
                resetList();
                i++;
            }

            storedValue++;
        }
    }

    /**
     * This method is used to detect a specific property of a number
     * @param specifics
     *  The specific property to be detected
     * @return
     *  True if the property is detected, false otherwise
     */
    private boolean detectSpecifics(ArrayList<SNT.NumType> specifics) {
        for(SNT.NumType t : specifics){
            if(!detectives[t.ordinal()].detect()){
                return false;
            }
            detailedList.set(t.ordinal(), new Property(attributes.get(t.ordinal()), true));
        }
        return true;
    }


    /**
     * This method is used to detect a specific property of a number, and fail if any of the properties in the exclusion list
     * are detected.
     * @param userNumTypes
     *  The specific properties to be detected
     * @param exclusions
     *  The specific properties to be excluded from the analysis
     * @return
     *  True if the property is detected, false otherwise
     */
    private boolean detectSpecifics(ArrayList<SNT.NumType> userNumTypes, ArrayList<Integer> exclusions) {
        for(SNT.NumType t : userNumTypes){
            for(int i : exclusions){
                if(detectives[i].detect()){
                    return false;
                }
            }
            setAllFalse(exclusions);
            if(!exclusions.contains(t.ordinal())){
                if(!detectives[t.ordinal()].detect()){
                    return false;
                }
                detailedList.set(t.ordinal(), new Property(attributes.get(t.ordinal()), true));
            }
        }
        return true;
    }

    /**
     * This method is used to set all of the properties in the exclusion list to false
     * @param exclusions
     *  The list of properties to be set to false
     */
    private void setAllFalse(ArrayList<Integer> exclusions) {
        for(int i : exclusions){
            detailedList.set(i, new Property(attributes.get(i), false));
        }
    }

    /**
     * This method is used to save the properties of a number to a string
     */
    private void saveProperties() {
        msg.append("\nProperties of %,d\n" .formatted(storedValue));
        for(Property prop : detailedList){
            msg.append("%16.16s: %b\n" .formatted(prop.getCategory(), prop.getData()));
        }
        msg.setLength(msg.length() - 1);
    }

    /**
     * This method is used to save the properties of a number to a string
     */
    private void saveListOfProperties() {
        msg.append("\n%,16d is ".formatted(storedValue));
        for(Property prop : detailedList){
            if(prop.getData()){
                msg.append("%s, " .formatted(prop.getCategory()));
            }
        }

        //remove the last comma
        msg.setLength(msg.length() - 2);
    }

    /**
     * This method is used to display the properties of a number or numbers
     */
    public void displayProperties() {
        System.out.println(msg.toString() + "\n");
    }

    /**
     * This method is used to reset the list of properties to false
     */
    private void resetList(){
        for(int i = 0; i < attributes.size(); i++)
            detailedList.get(i).setData(false);
    }
}
