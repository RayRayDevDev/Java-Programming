import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        ArrayList<String> toDo = new ArrayList<>();
        Scanner userChoice = new Scanner(System.in); // For the Menu Selection.
        Scanner userInput = new Scanner(System.in); // For the user's actual input.

        while (userChoice != 4) {
            // TODO: Implement User Selection menu.
            // 1. View todo list.
            ViewToDoList(toDo);
            // 2. Add to todo list.
            // 3. Delete from todo list.
            // 4. Exit.

        }
    }

    public static void ViewToDoList(ArrayList toDoList) {
        for (i = 0; i < toDoList.size(); i++) {
            System.out.println(Arrays.toString(toDoList));
        }
    }

    public static String addToToDoList() {

    }
}