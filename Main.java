import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Incorrect number of arguments. Must be 2");
            return;
        }
        try (Scanner scanner = new Scanner("%s %s".formatted(args[0], args[1]))) {
            if (!scanner.hasNextInt()) {
                System.out.println("First argument must be integer");
                return;
            }
            int a = scanner.nextInt();
            if (!scanner.hasNextInt()) {
                System.out.println("Second argument must be integer");
                return;
            }
            int b = scanner.nextInt();
            System.out.println("Hello world");
            System.out.println(a + b);
        }
    }
}
