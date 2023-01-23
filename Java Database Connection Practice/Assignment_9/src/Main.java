import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;
import static java.lang.System.out;

//Created with JDK-18.0.1.1
//Created by Cole Stanley (RäDev) for COP 3330C

public class Main {
//    static final String DB_URL = "jdbc:mysql://localhost/Person";
//    static final String USER = "root";
//    static final String PASS = "root";


    public static void main(String[] args) throws SQLException {
//        Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
//        Statement statement = connection.createStatement();
//                String createDB = "CREATE DATABASE Person";
//                statement.executeUpdate(createDB);
//                String createTable = "CREATE TABLE personalInformation" +
//                        "(id INTEGER not NULL, " +
//                        " first VARCHAR(255), " +
//                        " last VARCHAR(255), " +
//                        " age INTEGER, " +
//                        " ssn BigInt, " +
//                        " creditCard BigInt, " +
//                        " PRIMARY KEY ( id ))";
//                statement.executeUpdate(createTable);
        userSelectionScreen();

    }

    public static void userSelectionScreen() {

        int userSelection = 0;
        while (userSelection != -1) {
            Scanner userInput = new Scanner(System.in);
            Person newPerson = new Person();
            out.println("\nPlease enter the corresponding number to select the desired action (-1 to exit):\n1. Insert a new Person into the database.\n2. Select and display a particular record by first and last names.\n3. Select and display the entire database.\n4. Select and delete a particular record by first and last names.");
            out.print("Please make your selection: ");
            try {
                userSelection = userInput.nextInt();
                switch (userSelection) {
                    case -1:
                        break;
                    case 1:
                        UserInputs.insertPersonUI();
                        break;
                    case 2:
                        UserInputs.selectPersonUI(newPerson);
                        break;
                    case 3:
                        UserInputs.findAllPeopleUI();
                        break;
                    case 4:
                        UserInputs.deletePersonUI(newPerson);
                        break;
                    default:
                        out.println("\nYou did not select a valid option. Please try again!");
                        continue;
                }
            } catch (InputMismatchException | SQLException e) {
                out.println("\nYou did not enter a number. Please try again, using only numbers.");
                userSelection = 0;
                continue;
            }
        }
    }
}
