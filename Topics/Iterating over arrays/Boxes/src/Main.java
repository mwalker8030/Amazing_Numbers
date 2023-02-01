import java.util.*;
import java.util.stream.IntStream;
public class Main {
    public static void main(String[] args) {
        // write your code here
        Scanner scan = new Scanner(System.in);

        int[] box1 = IntStream.range(0, 3).map(i -> scan.nextInt()).toArray();
        int[] box2 = IntStream.range(0, 3).map(i -> scan.nextInt()).toArray();

        Arrays.sort(box1);
        Arrays.sort(box2);

        if (box1GreaterThanBox2(box1, box2)) {
            System.out.println("Box 1 > Box 2");
        } else if (box1LessThanBox2(box1, box2)) {
            System.out.println("Box 1 < Box 2");
        } else {
            System.out.println("Incompatible");
        }
    }

    private static boolean box1GreaterThanBox2(int[] box1, int[] box2) {
        return box1[0] > box2[0] && box1[1] > box2[1] && box1[2] > box2[2];
    }

    private static boolean box1LessThanBox2(int[] box1, int[] box2) {
        return box1[0] < box2[0] && box1[1] < box2[1] && box1[2] < box2[2]
;
    }
}