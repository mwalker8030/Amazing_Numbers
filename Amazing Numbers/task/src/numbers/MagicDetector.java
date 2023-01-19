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
        }else if(magicCalculated(num)){
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

    private boolean magicCalculated(long num) {
        long remainder = 0, diff = 0, temp = num;
        do{
            //take last number
            remainder = temp % 10;
            temp /= 10.0;
            //multiply it by 2
            remainder *= 2;
            //subtract whatever was left over by result
            temp -= remainder;
            //if result is not 7 or less
            if(temp%MAGICNUM == 0){
                return true;
            }

        }while(temp > 10);
        return false;
    }
    
    


}
