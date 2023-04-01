import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.stream.IntStream;

public class TaskMain {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int userInput;
        while (true) {
            System.out.print("Enter a number between 1 and 32. Enter -1 to exit: ");
            try {
                if ((userInput = scanner.nextInt()) >= 1 && userInput <= 32) {
                    printTable(userInput);
                    break;
                } else if (userInput == -1) {
                    break;
                }
                System.out.println("Incorrect input: " + userInput + ". Try again!");
            } catch (InputMismatchException e) {
                System.out.println("Incorrect input: " + scanner.nextLine() + ". Try again!");
            }
        }
    }

    public static void printTable(int tableSize) {
        final int columnSize = String.valueOf(tableSize * tableSize).length() + 1;
        final int firstColumnSize = String.valueOf(tableSize).length() + 1;
        final String rowDivider = createRowDivider(columnSize, firstColumnSize, tableSize);

        printHeader(tableSize, firstColumnSize, columnSize);
        System.out.printf("%s%n", rowDivider);

        IntStream.rangeClosed(1, tableSize).forEach(i -> {
            System.out.printf("%" + firstColumnSize + "s", i + "|");
            IntStream.rangeClosed(1, tableSize - 1).forEach(j ->
                    System.out.printf("%" + columnSize + "s", i * j + "|")
            );
            System.out.printf("%" + columnSize + "s", (i * tableSize) + " ");
            System.out.printf("%n%s%n", rowDivider);
        });
    }

    public static void printHeader(int tableSize, int firstColumnSize, int columnSize) {
        System.out.printf("%" + firstColumnSize + "s", "|");
        IntStream.rangeClosed(1, tableSize - 1).forEach(i ->
                System.out.printf("%" + columnSize + "s", i + "|")
        );
        System.out.printf("%" + columnSize + "s", tableSize + " ");
        System.out.printf("%n");
    }

    public static String createRowDivider(int columnSize, int firstColumnSize, int tableSize) {
        final int dividerCapacity = tableSize * (columnSize - 1) + tableSize + firstColumnSize;
        StringBuilder headerPattern = new StringBuilder(dividerCapacity);
        headerPattern.append("-".repeat(firstColumnSize - 1));

        String dash = "-".repeat(columnSize - 1);
        IntStream.rangeClosed(1, tableSize).forEach(i ->
                headerPattern.append("+").append(dash)
        );
        return headerPattern.toString();
    }

}