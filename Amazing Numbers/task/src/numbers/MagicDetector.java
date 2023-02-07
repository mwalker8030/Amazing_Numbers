package numbers;

public class MagicDetector{
    private boolean[] isMagic;
    private long MAGICNUM;
    private StringBuilder msg;

    MagicDetector(){
        isMagic = new boolean[]{false, false};
        MAGICNUM = 7;
        msg = new StringBuilder();
    }

    public boolean detectMagic(long num){
        resetMagic();
        if(num % MAGICNUM == 0){
            //magic number is detected
            isMagic[0] = true;
        }
        if(num % 10 == MAGICNUM){
            isMagic[1] = true;
        }

        return finalAnalysis(num);
    }

    private void resetMagic() {
        isMagic[0] = false;
        isMagic[1] = false;
    }

    private boolean finalAnalysis(long num) {

        if (isMagic[0] || isMagic[1]){
            return true;
        }

        return false;
    }
}
