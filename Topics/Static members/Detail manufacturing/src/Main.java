import java.util.Scanner;

class ManufacturingController {
    // here you may declare a field
    private static int numberOfProducts;
    public static String requestProduct(String product) {
        // write your code here
        numberOfProducts++;
        return "%d. Requested %s".formatted(numberOfProducts, product);
    }

    public static int getNumberOfProducts() {
        // write your code here
        return numberOfProducts;
    }
}

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String product = scanner.nextLine();
            System.out.println(ManufacturingController.requestProduct(product));
            System.out.println(ManufacturingController.getNumberOfProducts());
        }
    }
}