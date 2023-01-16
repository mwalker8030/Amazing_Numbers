package numbers;

public class MagicDetector{
    private boolean[] isMagic;
    private int MAGICNUM;
    private StringBuilder msg;

    MagicDetector(){
        isMagic = new boolean[]{false, false};
        MAGICNUM = 7;
        msg = new StringBuilder();
    }

    public void detectMagic(int num){
        detectEvenOrOdd(num);
        if(num % MAGICNUM == 0){
            //magic number is detected
            isMagic[0] = true;
        }else if(magicCalculated(num)){
            isMagic[0] = true;
        }

        if(num % 10 == MAGICNUM){
            isMagic[1] = true;
        }

        finalAnalysis(num);
    }

    private void finalAnalysis(int num) {
        msg.append("It is ");
        if(!isMagic[0] && !isMagic[1]){
            msg.append("not ");
        }
        msg.append("a Buzz number.\nExplanation:\n");

        if(isMagic[0] && isMagic[1]){ msg.append("%d is divisible by 7 and ends with 7." .formatted(num));}
        else if (isMagic[0]) { msg.append("%d is divisible by 7." .formatted(num));}
        else if (isMagic[1]) { msg.append("%d ends with 7." .formatted(num));}
        else{ msg.append("%d is neither divisible by 7 nor does it end with 7." .formatted(num));}

        System.out.println(msg.toString());
    }

    private void detectEvenOrOdd(int num) {
        msg.append("This number is ");
        if(num % 2 == 0){
            msg.append("Even.\n");
        }else{
            msg.append("Odd.\n");
        }
    }

    private boolean magicCalculated(int num) {
        int remainder = 0, diff = 0, temp = num;
        do{
            //take last number
            remainder = temp % 10;
            temp /= 10;
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
