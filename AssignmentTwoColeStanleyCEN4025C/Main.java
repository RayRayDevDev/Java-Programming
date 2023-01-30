import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        ArrayList<String> toDo = new ArrayList<>();
        Scanner userChoice = new Scanner(System.in); // For the Menu Selection.
        int userInput = 0;

        while (userInput != 4) {
            // TODO: Implement User Selection menu.
            System.out.println(
                    "1. View your To-Do List\n2. Add to your To-Do List\n3. Delete items within your To-Do List\n4. Exit");
            userInput = userChoice.nextInt();
            switch (userInput) {
                case 1:
                    ViewToDoList(toDo);
                    break;
                case 2:
                    addToToDoList(toDo);
                    break;
                case 3:
                    DeleteOnToDoList(toDo);
                    break;
                case 4:
                    System.exit(0);
                    break;
            }

        }
    }

    public static void ViewToDoList(ArrayList toDoList) {
        for (int i = 0; i < toDoList.size(); i++) {
            System.out.println(toDoList.get(i));
        }
    }

    public static ArrayList addToToDoList(ArrayList toDoList) {
        Scanner userInput = new Scanner(System.in); // For the user's actual input.
        String userChoice = " ";
        while (!userChoice.equals("-1")) { // Less likely trigger in a console application.
            userChoice = userInput.nextLine();
            toDoList.add(userChoice);
        }

        return toDoList;

    }

    public static ArrayList DeleteOnToDoList(ArrayList toDoList) {
        Scanner userInput = new Scanner(System.in); // For the user's actual input.
        while (userInput != -1) {
            int i = userInput.nextInt();
            toDoList.remove(i);
        }
    }
}