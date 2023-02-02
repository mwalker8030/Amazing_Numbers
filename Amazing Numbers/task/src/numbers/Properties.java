package numbers;

import java.util.ArrayList;
import java.util.Arrays;

public class Properties {

    public enum Printer{
        SINGLE(1),
        MULTI(2);

        public final int printerType;

        private Printer(int printerType){
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
    private int sequenceCounter;
    public void resetSequenceAndPrinters() {
        sequenceCounter = 0;
        msg.setLength(0);
        printer = Printer.SINGLE;
    }
    public void incrementSequence() { ++sequenceCounter; }

    public void setPrinter(int size) {
        if(size > 0){
            printer = Printer.MULTI;
        }
    }

    public void setPrinter(long size) {
        if(size > 0){
            printer = Printer.MULTI;
        }
    }



    interface Detective{

        boolean detect();
    }
    private Detective[] detectives = new Detective[]{
            new Detective(){ public boolean detect(){return mageDetective.detectMagic(storedValue);}},
            new Detective(){ public boolean detect(){return duckDetective.detectDucking(storedValue);}},
            new Detective(){ public boolean detect(){return paliDetective.detectPalindrome(storedValue);}},
            new Detective(){ public boolean detect(){return gapDetective.detectGap(storedValue);}},
            new Detective(){ public boolean detect(){return spyDetective.detectSpy(storedValue);}},
            new Detective(){ public boolean detect(){return squareDetective.detectSquare(storedValue);}},
            new Detective(){ public boolean detect(){return sunDetective.detectSun(storedValue);}},
            new Detective(){ public boolean detect(){return jumpDetective.detectJump(storedValue);}},
            new Detective(){ public boolean detect(){return !oddDetective.evenOrOdd(storedValue);}},
            new Detective(){ public boolean detect(){return oddDetective.evenOrOdd(storedValue);}}

    };
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
                        "buzz",
                        "duck",
                        "palindromic",
                        "gapful",
                        "spy",
                        "square",
                        "sunny",
                        "jumping",
                        "even",
                        "odd"
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
    }

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
    public void analyze(Long val, ArrayList<SpecificNums.NumType> numTypes, long quantity) {
        nsnt.clear();
        storedValue = val;
        for(SpecificNums.NumType t : SpecificNums.NumType.values()){
            if(!numTypes.contains(t) && t != SpecificNums.NumType.DEFAULT){
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

    public void analyze(long userEntries, ArrayList<SpecificNums.NumType> userNumTypes, long val, ArrayList<Integer> exclusions) {
        nsnt.clear();
        storedValue = userEntries;

        for(SpecificNums.NumType t : SpecificNums.NumType.values()){
            if(!userNumTypes.contains(t) && !exclusions.contains(t.ordinal()) && t != SpecificNums.NumType.DEFAULT && !exclusions.contains(t.ordinal())){
                nsnt.add(t.ordinal());
            }
        }

        for(long i = 0; i < val;){

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

    private boolean detectSpecifics(ArrayList<SpecificNums.NumType> specifics) {
        for(SpecificNums.NumType t : specifics){
            if(!detectives[t.ordinal()].detect()){
                return false;
            }
            detailedList.set(t.ordinal(), new Property(attributes.get(t.ordinal()), true));
        }
        return true;
    }


    private boolean detectSpecifics(ArrayList<SpecificNums.NumType> userNumTypes, ArrayList<Integer> exclusions) {
        for(SpecificNums.NumType t : userNumTypes){
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

    private void setAllFalse(ArrayList<Integer> exclusions) {
        for(int i : exclusions){
            detailedList.set(i, new Property(attributes.get(i), false));
        }
    }

    public void analyzeList(long num){

        msg = new StringBuilder();

        for(int i = 0; i < attributes.size(); i++)
            detailedList.set(i, new Property(attributes.get(i),detectives[i].detect()));
        saveListOfProperties();
    }

    private void saveProperties() {
        msg.append("\nProperties of %,d\n" .formatted(storedValue));
        for(Property prop : detailedList){
            msg.append("%16.16s: %b\n" .formatted(prop.getCategory(), prop.getData()));
        }
        msg.setLength(msg.length() - 1);
    }

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

    public void displayProperties() {
        System.out.println(msg.toString() + "\n");
    }

    private void resetList(){
        for(int i = 0; i < attributes.size(); i++)
            detailedList.get(i).setData(false);
    }
    public void storeUserInput(long storedValue) { this.storedValue = storedValue; }
}
