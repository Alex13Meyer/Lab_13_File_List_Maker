import java.util.Scanner;
public class SafeInput {

    public static String getNonZeroLenString(Scanner pipe, String prompt) {
        // Prompts the user to enter a non-zero string
        String retString = " ";
        do {
            System.out.print("\n" + prompt + ":");
            retString = pipe.nextLine();
        }
        while (retString.length() == 0);

        return retString;
    }

    public static int getInt(Scanner in, String prompt) {
        // Prompts the user to enter an Integer
        int intValue = 0;
        boolean isValidInput = false;

        while (!isValidInput) {
            System.out.print(prompt + " ");
            if (in.hasNextInt()) {
                intValue = in.nextInt();
                isValidInput = true;
            }
        }

        return intValue;
    }

    public static double getDouble(Scanner in, String prompt) {
        //Prompts the user to enter a double
        double doubleValue = 0;
        boolean isValidInput = false;

        while (!isValidInput) {
            System.out.print(prompt + " ");
            if (in.hasNextDouble()) {
                doubleValue = in.nextDouble();
                isValidInput = true;
            } else {
                System.out.println("Invalid input. Try again:");
                in.next();
            }
        }
        return doubleValue;
    }

    public static int getRangedInt(Scanner in, String prompt, int min, int max) {
        //Get an integer within a range
        int intValue = 0;
        boolean isValidInput = false;

        while (!isValidInput) {
            System.out.print(prompt + " ");
            if (in.hasNextInt()) {
                intValue = in.nextInt();
                if (intValue >= min && intValue <= max) {
                    isValidInput = true;
                } else {
                    System.out.println("InvalidInput. Enter a number between " + min + " and " + max + ".");
                }
            } else {
                System.out.println("Invalid input. Please input a valid number. ");
                in.next();
            }
        }
        return intValue;
    }

    public static double getRangedDouble(Scanner in, String prompt, double min, double max) {
        // Get a double within a specified range
        double doubleValue = 0.0;
        boolean isValidInput = false;
        while (!isValidInput) {
            System.out.print(prompt + " ");
            if (in.hasNextDouble()) {
                doubleValue = in.nextDouble();
                if (doubleValue >= min && doubleValue <= max) {
                    isValidInput = true;
                } else {
                    System.out.println("Invalid Input. Please enter a number between " + min + " and " + max + ".");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                in.next();
            }
        }
        return doubleValue;
    }

    public static char getYNConfirm(Scanner in, String prompt) {
        // Gets a prompt y or n for user
        char response;
        boolean isValidInput = false;
        do {
            System.out.print(prompt + " ");
            response = in.next().charAt(0);
            if (response == 'y' || response == 'n') {
                isValidInput = true;
            } else {
                System.out.println("Invalid input. Please type 'y' or 'n'.");
            }
        } while (!isValidInput);
        return response;
    }

    public static String getRegExString(Scanner in, String prompt, String regexPattern) {
        // gets a string that matches an expression specified in the code
        String input = "";
        boolean isValidInput = false;
        while (!isValidInput) {
            System.out.print(prompt + " ");
            input = in.nextLine();
            if (input.matches(regexPattern)) {
                isValidInput = true;
            } else {
                System.out.println("Invalid input. Try following steps to the pattern: ");
            }
        }
        return input;
    }

}
