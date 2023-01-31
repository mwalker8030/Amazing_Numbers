package numbers;

public class SpecificNums {
    public enum NumType{
        BUZZ(0, "buzz"),
        DUCK(1, "duck", "spy"),
        PALINDROMIC(2, "palindromic"),
        GAPFUL(3, "gapful"),
        SPY(4, "spy", "duck"),
        SQUARE(5, "square", "sunny"),
        SUNNY(6, "sunny", "square"),
        EVEN(7, "even", "odd"),
        ODD(8, "odd", "even"),
        DEFAULT(9, "default", "buzz");

        private int index;
        private String title;
        private String error;
        private NumType(int num, String str){
            index = num;
            title = str;
            error = "default";
        }

        NumType(int num, String str, String err) {
            index = num;
            title = str;
            error = err;
        }

        public static NumType getType(String s) {
            return SpecificNums.NumType.valueOf(s.toUpperCase());
        }

        public int numType(){
            return index;
        }

        public static int numType(String str){
            for(SpecificNums.NumType t : SpecificNums.NumType.values()){
                if(t.toString().equalsIgnoreCase(str)){
                    return t.numType();
                }
            }
            return NumType.DEFAULT.numType();
        }

        public String getConflictingProperty(){
            return error;
        }

    }
}
