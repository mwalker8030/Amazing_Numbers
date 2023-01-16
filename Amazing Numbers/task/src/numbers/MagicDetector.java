package numbers;

public class MagicDetector{
    private boolean[] isMagic;
    private int MAGICNUM;

    MagicDetector(){
        isMagic = new boolean[]{false, false};
        MAGICNUM = 7;
    }

    public void detectMagic(int num){
        if(num/MAGICNUM == 0){
            //magic number is detected
            isMagic[0] = true;
        }else if(magicCalculated(num)){
            isMagic[0] = true;
        }

        if(num % 10 == MAGICNUM){
            isMagic[1] = true;
        }
    }

    private boolean magicCalculated(int num) {
        int remainder = 0, diff = 0, temp = num;
        do{
            //take last number
            remainder = temp % 10;
            //multiply it by 2
            remainder *= 2;
            //subtract whatever was left over by result
            temp -= remainder;
            //if result is not 7 or less
            if(temp/MAGICNUM == 0){
                return true;
            }

        }while(temp > 10);
        return false;
    }


}
