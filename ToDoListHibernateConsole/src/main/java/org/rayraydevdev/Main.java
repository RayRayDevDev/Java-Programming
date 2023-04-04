package org.rayraydevdev;

import jakarta.persistence.*;

import java.util.List;
import java.util.Scanner;

@Entity
@Table(name = "toDoBasic")
class ToDo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private String Note;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String task) {
        this.Note = task;
    }
}

public class Main {

    private static EntityManagerFactory entityManagerFactory;

    public static void main(String[] args) {
        entityManagerFactory = Persistence.createEntityManagerFactory("ToDoList");

        Scanner userChoice = new Scanner(System.in);
        int userInput = 0;

        while (userInput != 4) {
            // TODO: Implement User Selection menu.
            System.out.println(
                    "1. View your To-Do List\n2. Add to your To-Do List\n3. Delete items within your To-Do List\n4. Exit");
            userInput = userChoice.nextInt();
            switch (userInput) {
                case 1:
                    viewToDoList();
                    break;
                case 2:
                    addToToDoList();
                    break;
                case 3:
                    deleteOnToDoList();
                    break;
                case 4:
                    System.exit(0);
                    break;
            }

        }

        entityManagerFactory.close();
    }

    public static void viewToDoList() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        TypedQuery<ToDo> query = entityManager.createQuery("SELECT t FROM ToDo t", ToDo.class);
        List<ToDo> toDoList = query.getResultList();
        for (ToDo item : toDoList) {
            System.out.println("\n" + item.getId() + ": " + item.getNote() + "\n");
        }
        entityManager.close();
    }

    public static void addToToDoList() {
        Scanner userInput = new Scanner(System.in); // For the user's actual input.
        System.out.print("Enter note: ");
        String userChoice = userInput.nextLine();
        ToDo item = new ToDo();
        item.setNote(userChoice);

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(item);
        transaction.commit();
        entityManager.close();

        System.out.println("Your note has been successfully added to the To-Do list!");
    }




    public static void deleteOnToDoList() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        TypedQuery<ToDo> query = entityManager.createQuery("SELECT t FROM ToDo t", ToDo.class);
        List<ToDo> toDoList = query.getResultList();
        for (ToDo item : toDoList) {
            System.out.println(item.getId() + ": " + item.getNote());
        }
        System.out.print("Enter the number corresponding to the task you wish to delete: ");
        Scanner userInput = new Scanner(System.in);
        int id = userInput.nextInt();
        ToDo itemToRemove = entityManager.find(ToDo.class, id);
        if (itemToRemove != null) {
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.remove(itemToRemove);
            transaction.commit();
            System.out.println("Your task has been removed from the To-Do list!");
        } else {
            System.out.println("You entered the ID number " + id + ", which was unable to be found");
        }
        entityManager.close();
    }
}
