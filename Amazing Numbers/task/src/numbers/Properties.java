package numbers;

import java.util.ArrayList;

public class Properties {
    private boolean[] analysis;
    private String[] attributes;
    private ArrayList<Property> detailedList;
    private int storedValue;
    private StringBuilder msg;

    private OddDetector oddDetective;
    private MagicDetector mageDetective;
    private DuckDetector duckDetective;

    Properties(int num){

        oddDetective = new OddDetector();
        mageDetective = new MagicDetector();
        duckDetective = new DuckDetector();

        msg = new StringBuilder("Properties of %d\n" .formatted(num));
        this.storedValue = num;

        attributes = new String[]{"even","odd","buzz","duck"};
        analysis = new boolean[]{
                !oddDetective.evenOrOdd(storedValue),
                oddDetective.evenOrOdd(storedValue),
                mageDetective.detectMagic(storedValue),
                duckDetective.detectDucking(storedValue),
        };

        detailedList = new ArrayList<Property>();
        for(int i = 0; i < attributes.length; i++)
            detailedList.add(new Property(attributes[i], analysis[i]));

        printProperties();
    }

    private void printProperties() {
        for(Property prop : detailedList){
            msg.append("%16.16s: %b\n" .formatted(prop.getCategory(), prop.getData()));
        }
        System.out.println(msg.toString());
    }

    protected int getStoredValue(){
        return this.storedValue;
    }
}
