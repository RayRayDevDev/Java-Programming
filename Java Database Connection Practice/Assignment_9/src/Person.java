import java.sql.*;
import java.util.ArrayList;
import java.util.InputMismatchException;

//Created with JDK-18.0.1.1
//Created by Cole Stanley (RäDev) for COP 3330C
//Database logic.

import static java.lang.System.out;

public class Person {

    @Override
    public String toString() {
        return "\nPerson:" + "\nFirst Name: " +  getFirstName() + "\nLast Name: " + getLastName() + "\nAge: " + getAge() + "\nSocial Security Number: " + getSsn() + "\nCredit Card Number: " + getCreditCard();
    }

    private String firstName;

    protected void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    protected String getFirstName() {
        return firstName;
    }

    private String lastName;

    protected void setLastName(String lastName) {
        this.lastName = lastName;
    }

    protected String getLastName() {
        return lastName;
    }

    private int age;

    protected void setAge(int age) {
        this.age = age;
    }

    protected int getAge() {
        return age;
    }

    private long ssn;

    protected void setSsn(long ssn) {
        this.ssn = ssn;
    }

    protected long getSsn() {
        return ssn;
    }

    private long creditCard;

    protected void setCreditCard(long creditCard) {
        this.creditCard = creditCard;
    }

    protected long getCreditCard() {
        return creditCard;
    }

    Person() {
    }

    Person(String firstName, String lastName, int age, long ssn, long creditCard) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.ssn = ssn;
        this.creditCard = creditCard;
    }

    private static final String DB_URL = "jdbc:mysql://localhost/Person";
    private static final String USER = "root";
    private static final String PASS = "root";

    protected static void insertPerson(Person person) {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement statement = connection.createStatement();
            String insertStatement = "INSERT INTO personalInformation (first, last, age, ssn, creditCard) VALUES (" + person.firstName + "," + person.lastName + "," + person.age + "," + person.ssn + "," + person.creditCard + ")";
            statement.executeUpdate(insertStatement);
            out.println("Insert into table completed successfully!");
            statement.close();
            connection.close();
        } catch (SQLException | InputMismatchException e) {
            out.println("Uh oh! Something went wrong!\n" + e.getMessage());
            e.printStackTrace();
        }
    }
    protected Person selectPerson(String firstName, String lastName) {
        Person person = new Person();
        try {
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery("SELECT * FROM personalInformation WHERE first= " + firstName + "AND last=" + lastName + ";");
            if (set.next()) {
                person.setFirstName(set.getString("first"));
                person.setLastName(set.getString("last"));
                person.setAge(set.getInt("age"));
                person.setSsn(set.getLong("ssn"));
                person.setCreditCard(set.getLong("creditCard"));
                set.close();
                statement.close();
                connection.close();
                out.println("\nSuccessfully retrieved the person!");
            } else {
                set.close();
                statement.close();
                connection.close();
                out.println("\nNo records matching first name " + firstName + " and last name " + lastName + " were found! Please check your spelling and try again!\n\nNo records were affected.");
                Main.userSelectionScreen();
            }
        }catch (SQLException e) {
            out.println("Uh oh! Something went wrong!\n" + e.getMessage());
            e.printStackTrace();
            person = null;
        }
        return person;
    }
    protected static ArrayList<Person> findAllPeople() {
        ArrayList<Person> personArrayList = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery("SELECT * FROM personalInformation;");
            while (set.next()) {
                personArrayList.add(new Person(set.getString("first"), set.getString("last"), set.getInt("age"), set.getLong("ssn"), set.getLong("creditCard")));
            }
            set.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            out.println("Uh oh! Something went wrong!\n" + e.getMessage());
            e.printStackTrace();
            personArrayList = null;
        }
        return personArrayList;
    }
    protected String deletePerson(String firstName, String lastName) throws SQLException {
        String deleteSuccessful = "\nSuccessfully deleted the person with the first name " + firstName + " and the last name " + lastName + "!";
        Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
        Statement statement = connection.createStatement();
        String deleteStatement = "DELETE FROM personalInformation WHERE first=" + firstName + "AND last=" + lastName + ";";
        ResultSet set = statement.executeQuery("SELECT * FROM personalInformation WHERE first=" + firstName + "AND last=" + lastName);
        if(set.next()){
            statement.executeUpdate(deleteStatement);
            set.close();
            statement.close();
            connection.close();
            return deleteSuccessful;
        } else {
            set.close();
            statement.close();
            connection.close();
            return "\nNo records matching first name " + firstName + " and last name " + lastName + " were found! Please check your spelling and try again!\n\nNo records were affected.";
        }
    }
}
