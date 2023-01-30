package numbers;

public class SpecificNums {
    public enum NumType{
        BUZZ(0, "buzz"),
        DUCK(1, "duck"),
        PALINDROMIC(2, "palindromic"),
        GAPFUL(3, "gapful"),
        SPY(4, "spy"),
        EVEN(5, "even"),
        ODD(6, "odd"),
        DEFAULT(7, "default");

        private int index;
        private String title;
        private NumType(int num, String str){
            index = num;
            title = str;
        }

        public static NumType getType(String s) {
            return SpecificNums.NumType.valueOf(s.toUpperCase());
        }

        public int numType(){
            return index;
        }

        public int numType(String str){
            for(SpecificNums.NumType t : SpecificNums.NumType.values()){
                if(t.toString().equals(str)){
                    return t.numType();
                }
            }
            return NumType.DEFAULT.numType();
        }



    }
}
