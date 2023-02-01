import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scan = new Scanner(System.in);
        final int SIZE = scan.nextInt();
        int[] values = scan.tokens().limit(SIZE).mapToInt(Integer::parseInt).toArray();
        int minimum = scan.nextInt();

        int sum = 0;

        for (int value : values) {
            if (value > minimum) {
                sum += value;
            }
        }

        System.out.println(sum);


    }
}