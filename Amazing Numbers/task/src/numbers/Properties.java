package numbers;

import java.util.ArrayList;
import java.util.Arrays;

public class Properties {
    private ArrayList<String> attributes;
    private ArrayList<Property> detailedList;
    private long storedValue;
    private StringBuilder msg;

    private OddDetector oddDetective;
    private MagicDetector mageDetective;
    private DuckDetector duckDetective;
    private Palindrome paliDetective;
    private GapDetector gapDetective;

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
        initAttributes();
        initDetectives();
        initList();
        this.storedValue = 0;
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

        msg = new StringBuilder("Properties of %,d\n" .formatted(num));

        for(int i = 0; i < attributes.size(); i++)
            detailedList.set(i, new Property(attributes.get(i),detectives[i].detect()));
        printProperties();
        resetList();
    }

    private void printProperties() {
        for(Property prop : detailedList){
            msg.append("%16.16s: %b\n" .formatted(prop.getCategory(), prop.getData()));
        }
        System.out.println(msg.toString());
    }

    private void resetList(){
        for(int i = 0; i < attributes.size(); i++)
            detailedList.get(i).setData(false);
    }
    public void storeUserInput(long storedValue) { this.storedValue = storedValue; }
}
