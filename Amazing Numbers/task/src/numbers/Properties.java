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
    private long storedValue;
    private StringBuilder msg;

    private OddDetector oddDetective;
    private MagicDetector mageDetective;
    private DuckDetector duckDetective;
    private Palindrome paliDetective;
    private GapDetector gapDetective;
    private int sequenceCounter;

    public void resetSequenceAndPrinter() {
        sequenceCounter = 0;
        msg.setLength(0);
        printer = Printer.SINGLE;
    }

    public void incrementSequence() { ++sequenceCounter; }

    public void setPrinter(int size) {
        if(size > printer.printerType){
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
    }

    private void initDetectives(){
        oddDetective = new OddDetector();
        mageDetective = new MagicDetector();
        duckDetective = new DuckDetector();
        paliDetective = new Palindrome();
        gapDetective = new GapDetector();
    }
    public void analyze(long num){


        for(int i = 0; i < attributes.size(); i++)
            detailedList.set(i, new Property(attributes.get(i),detectives[i].detect()));

        if(printer == Printer.SINGLE)
            saveProperties();
        else
            saveListOfProperties();
        resetList();
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
