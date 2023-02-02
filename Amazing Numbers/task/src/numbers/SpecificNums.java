package numbers;

public class SpecificNums {
    public enum NumType{
        BUZZ(),
        DUCK("spy"),
        PALINDROMIC,
        GAPFUL,
        SPY("duck"),
        SQUARE("sunny"),
        SUNNY("square"),
        JUMPING,
        EVEN("odd"),
        ODD("even"),
        DEFAULT("buzz");

        private String error;
        private NumType(){
            error = "default";
        }

        NumType(String err) {
            error = err;
        }

        public String getConflictingProperty(){
            return error;
        }

        public boolean contains(String req) {
            return this.name().equals(req.toUpperCase());
        }
    }
}
