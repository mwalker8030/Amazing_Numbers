
package numbers;
public class SNT {
    public enum Conflicts{
        EVEN,
        ODD,
        BUZZ,
        DUCK,
        SPY,
        SUNNY,
        SQUARE,
        HAPPY,
        SAD,
        DEFAULT;
    }

    public enum NumType{
        EVEN(Conflicts.ODD),
        ODD(Conflicts.EVEN),
        BUZZ(),
        DUCK(Conflicts.SPY),
        PALINDROMIC,
        GAPFUL,
        SPY(Conflicts.DUCK),
        SQUARE(Conflicts.SUNNY),
        SUNNY(Conflicts.SQUARE),
        JUMPING,
        HAPPY(Conflicts.SAD),
        SAD(Conflicts.HAPPY),
        DEFAULT(Conflicts.BUZZ);
        private Conflicts error;

        private NumType(){
            error = Conflicts.DEFAULT;
        }

        NumType(Conflicts err) {
            error = err;
        }

        public Conflicts getConflictingProperty(){
            return error;
        }

        public boolean contains(Conflicts req) {
            return this.name().equals(req.name());
        }

        public boolean contains(String req) {
            return this.name().equals(req.toUpperCase());
        }

        public Conflicts getConflict() {
            return this.error;
        }
    }
}
