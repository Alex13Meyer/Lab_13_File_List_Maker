import javax.swing.*;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
public class ListMaker {
    //Use myArrList variable to store items inputted
        private static ArrayList<String> myArrList = new ArrayList<>();
        private static boolean needsToBeSaved = false;
        private static File currentFile;

        public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (true) {
            displayMenu();
            //Display the menu, and prompt user for input
            String choice = SafeInput.getRegExString(in,"Enter your choice (A/D/V/O/S/C/Q):", "[AaDdVvOoSsCcQq]");
            switch (choice.toUpperCase()) {
                //Add an item
                case "A":
                    addItem(in);
                    break;
                    //Delete an item
                    case "D":
                        deleteItem(in);
                        //View list items
                    case "V":
                    displayList();
                    break;
                    //Open a list from a disk
                    case "O":
                    openList(in);
                    //Save the current list file to disk
                    case "S":
                    saveList();
                    //Clear the current list
                    case "C":
                        clearList();
                        break;
                    //Get confirmation of quitting
                    case "Q":
                    if (confirmQuit(in)) {
                        if (needsToBeSaved) {
                            if (confirmSaveBeforeQuit(in)) {
                                saveList();
                            }
                        }
                        System.out.println("Goodbye!");
                        in.close();
                        return;
                    }
                    break;
            }
        }
    }
    //Display Menu
    private static void displayMenu() {
        System.out.println("\nMenu:");
        System.out.println("A - Add Item to the list");
        System.out.println("D - Delete Item from the list");
        System.out.println("V - View list");
        System.out.println("O - Open a list file");
        System.out.println("S - Save the current list file");
        System.out.println("C - Clear the current list");
        System.out.println("Q - Quit");
    }
    // Add an item
    private static void addItem(Scanner in) {
        String item = SafeInput.getNonZeroLenString(in, "Enter the item: ");
        myArrList.add(item);
        System.out.println(item + "Item added to the list!");
    }
    //Delete an item
    private static void deleteItem(Scanner in) {
        if (myArrList.isEmpty()) {
            System.out.println("You don't have any items to delete");
            return;
        }
        displayList();
        int itemIndex = SafeInput.getRangedInt(in, "Enter item to delete:", 1, myArrList.size());
        String deletedItem = myArrList.remove(itemIndex - 1);
        System.out.println("Item " + deletedItem + " was deleted from the list");
    }
    //Display the list
    private static void displayList() {
        if (myArrList.isEmpty()) {
            System.out.println("You don't have any items to display");
        }
        else {
            System.out.println("\nCurrent list:");
            for (int  i = 0; i < myArrList.size(); i++) {
                System.out.println((i + 1) + ". " + myArrList.get(i));
            }
        }
    }
    private static void openList(Scanner in) {
        JFileChooser J = new JFileChooser();
        int returnVal = J.showOpenDialog(null);
        if (returnVal == J.APPROVE_OPTION) {
            File selectedFile = J.getSelectedFile();
            try {
                BufferedReader reader = new BufferedReader(new FileReader(selectedFile));
                myArrList.clear();
                String line;
                while ((line = reader.readLine()) != null) {
                    myArrList.add(line);
                }
                reader.close();
                needsToBeSaved = false;
                currentFile = selectedFile;
                System.out.println(selectedFile.getName() + " opened!");
            } catch (IOException e) {
                System.out.println("Error reading file: " + e.getMessage());
            }
        }
    }
    //Saves the current list file to disk
    private static void saveList() {
            JFileChooser J = new JFileChooser();
            int returnVal = J.showSaveDialog(null);
            if (returnVal == J.APPROVE_OPTION) {
                File selectedFile = J.getSelectedFile();
                try {
                    PrintWriter writer = new PrintWriter(selectedFile);
                    for (String item : myArrList) {
                    writer.println(item);
                    }
                    writer.close();
                    needsToBeSaved = false;
                    currentFile = selectedFile;
                    System.out.println("List saved to " + currentFile.getName());
                } catch (IOException e) {
                    System.out.println("Error saving: " + e.getMessage());
                }
            }
    }
    private static void saveListAs() {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showSaveDialog(null);
        if (returnValue == fileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                PrintWriter writer = new PrintWriter(selectedFile);
                for (String item : myArrList) {
                    writer.println(item);
                }
                writer.close();
                needsToBeSaved = false;
                currentFile = selectedFile;
                System.out.println("List saved to " + selectedFile.getName());
            } catch (IOException e) {
                System.out.println("Error saving: " + e.getMessage());
            }
        }
    }
    //Clears the current list
    private static void clearList() {
            myArrList.clear();
            needsToBeSaved = true;
            System.out.println("List cleared! ");
    }
    //Ask user if they would like to continue or quit
    private static boolean confirmQuit(Scanner in) {
        char response = SafeInput.getYNConfirm(in, "Are you sure you want to quit? (yes/no)");
        return response == 'y';
    }
    //Asks the user if they want to save the list before quitting 
    private static boolean confirmSaveBeforeQuit(Scanner in) {
            char response = SafeInput.getYNConfirm(in, "Do you want to save changes before quitting? (yes/no)");
    return response == 'y';
        }
}
