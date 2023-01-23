import static java.lang.System.out;
import java.util.Random;

//Created by Cole Stanley (RäDev) for COP 3330C
//Created with JDK 18.0.1.1

class Animal implements Runnable {
    private String name = null; // The animal's name and also the thread's name.
    private double position = 0; // The animal's position.
    private double speed = 0; // The animal's speed.
    private int restMax = 0; // The maxium amount (in ms) the animal is ever allowed to rest.
    private static boolean winner = false; // Initial condition; nobody has raced, therefore nobody has won yet. Static
                                           // to prevent changes once a thread changes its value.
    private int randomRest = 0; // Variable to store RNG's result.
    private Food animalFood; // Instance of the "Food" class for the two threads to share.

    Animal(String animalName, Double animalStartPos, Double animalStartSpeed, int animalRestMax, Food animalFood) { // Constructor
                                                                                                                    // for
                                                                                                                    // each
                                                                                                                    // Animal
                                                                                                                    // object.
        name = animalName;
        position = animalStartPos;
        speed = animalStartSpeed;
        restMax = animalRestMax;
        this.animalFood = animalFood;
        out.println(name + " has been created successfully!"); // Debug.
    }

    public void run() {
        while (!winner) { // While winner is NOT true, loop. Plays more nicely with threads as whichever
                          // thread stops first will alter the "winner" variable, causing the other thread
                          // to terminate too.
            try { // Exception handler so the compiler plays nicely.
                if (position == 0) { // Initial condition with a different message because I thought it looked weird
                                     // with the "rest" syntax that shows after every iteration hereafter.
                    out.println(name + " has joined the race!");
                    out.println("\nThe current animal, " + name + ", is beginning the race at position: " + position
                            + "!\n");
                    position += speed; // Increment position by the value of the Animal's speed.
                    out.println(name + " has moved to position: " + position + "!\n");
                } else {
                    position += speed; // Increment the Animal's position by the value of the Animal's speed.
                    Random random = new Random(); // New RNG object for the Thread.Sleep() method.
                    randomRest = random.nextInt(restMax) + 1; // Take the respective Animal's "restMax" and add one to
                                                              // the upper bound provided by that variable to ensure it
                                                              // is inclusive instead of exclusive.
                    out.println("\nThe current animal, " + name + ", is at position: " + position
                            + " and is moving at a speed of " + speed + " and is about to rest for the next "
                            + randomRest
                            + " milliseconds!\n");

                    if (position <= 99.99) { // As the user may enter any value float for speed, position--continue
                                             // eating and sleeping unless the value of position is above 99.99. During
                                             // debugging, the winning thread would complete another loop, and this
                                             // finally fixed it.
                        animalFood.eat(name, restMax); // Pass the Animal's name and the restMax for more compact code.
                        Thread.sleep(randomRest); // Sleep on randomRest.
                    } else { // Winning condition. "Else" statement to ensure that it does not execute until
                             // the above "if" statement returns false.
                        if (position >= 100) {
                            winner = true; // Set winner to true, stopping the loop for all threads.
                            out.println(name + " is the winner!");
                            System.exit(0); // Exit the program cleanly.
                        }
                    }
                }
            } catch (InterruptedException e) {
                out.println(e.getMessage());
            }
        }
    }
}

class Food { // Shared "Food" class per requirements.
    public synchronized void eat(String name, int restMax) { // Synchronized method in order to ensure the method
                                                             // becomes locked to the first thread whom accesses it.
        Random random = new Random(); // Separate RNG object for this method only.
        int randomEat = random.nextInt(restMax); // Utilize the Animal's restMax from the "Animal" class as a shorcut
                                                 // for the upper bound.
        try {
            if (name == "rabbit") {
                randomEat = randomEat * 2; // As the rabbit is supposed to eat for longer than the turtle, but there is
                                           // no direction as to HOW much longer, chose an arbitrary amount to multiply
                                           // the result of "randomEat" by.
                out.println(name + " is currently eating and will be doing so for the next " + randomEat
                        + " milliseconds!\n");
                Thread.sleep(randomEat);
            } else {
                out.println(name + " is currently eating and will be doing so for the next " + randomEat
                        + " milliseconds!\n");
                Thread.sleep(randomEat);
            }
        } catch (InterruptedException e) {
            out.println(e.getMessage());
        }
    }
}

class Main {

    public static void main(String[] args) {
        Food animalFood = new Food(); // New "Food" object.
        Animal firstAnimal = new Animal("rabbit", 0.0, 5.0, 150, animalFood); // New Animal named "Rabbit."
        Animal secondAnimal = new Animal("turtle", 0.0, 3.0, 100, animalFood); // New Animal named "Turtle."
        Thread first = new Thread(firstAnimal); // New Thread containing the firstAnimal Object.
        Thread second = new Thread(secondAnimal); // New Thread containing the secondAnimal Object.
        first.setName("firstAnimal"); // Set both threads' names.
        second.setName("secondAnimal");
        first.start(); // Start both threads, thus invoking the run() method in each respective thread
                       // thewreafter.
        second.start();
    }
}