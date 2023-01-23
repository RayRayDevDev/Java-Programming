import static java.lang.System.out; //Got tired of writing the entire "Println" or "Print" statements out.
import java.util.Scanner; //Importing for userInput.

//Created with JDK-18.0.1.1
//Created by Cole Stanley (RäDev) for COP 3330C

class Main {
    
    public static void main(String[] args) {
        
        try {  //Catch all exceptions, most likely InputFormatExceptions. This is the only way I could figure it out without doing weird conversion things that I don't yet understand in Java, unfortunately.
            double userLength; //Fields for User inputted length, width, radius, and the final area depending on the control statement executed.
            double userWidth;
            double userRadius;
            double finalArea;
            Rectangle zeroArgRect =  new Rectangle(); //Call first 0 arg constructor for class Rectangle and initialize all values to "0." 
            Circle zeroArgCircle = new Circle();  //Call first 0 arg constructor for class Circle and initialize all values to "0."
            out.println("The current length of a Rectangle stored in memory is: " + zeroArgRect.getLength() + ".");  //Show all values are, indeed, initialized to 0 per the first constructor. 
            out.println("The current width of a Rectangle stored in memory is: " + zeroArgRect.getWidth() + ".");
            out.println("The current radius of a Circle stored in memory is: " + zeroArgCircle.getRadius() + "."); 
            Scanner userInput = new Scanner(System.in); //New scanner object for user input.
            out.println("Please type a number to select an option:\n1. Compute the area of a Rectangle\n2. Compute the area of a Circle");  //Menu for the user's menu choice.
            int userChoice = userInput.nextInt();  //Variable to store the user's menu selection.

        if(userChoice == 1) {  //Logic for if the user wants to compute the area of a rectangle. 
            out.print("Please enter the length of the rectangle: ");
            userLength = userInput.nextDouble();  //Variable for storing the inputted length of the rectangle to allow for passing into a new constructor after the user makes all inputs. 
            Rectangle.setLength(userLength);  //Setting length per requirements. 
            out.print("Please enter the width of the rectangle: ");
            userWidth = userInput.nextDouble();  //Variable for storing the inputted width of the rectangle to allow for passing into a new constructor after the user makes all inputs.
            Rectangle.setWidth(userWidth);  //Setting width per requirements.
            Rectangle twoArgRect = new Rectangle(userLength, userWidth);  //Pass the two variables storing the user's input into the overloaded constructor.
            finalArea = twoArgRect.computeArea();  //Compute the area using the stored variables from the new constructor which took in the previous two arguments and store it in a variable.
            out.println("The area of a Rectangle with length " + twoArgRect.getLength() + " and width " + twoArgRect.getWidth() + " is: " + finalArea + "." );  //Print the result of the calculation.
        }

        else if(userChoice == 2) {  //Logic for if a user wants to compute the area of a circle. 
            out.print("Please enter the radius of the Circle: ");
            userRadius = userInput.nextDouble();  //Variable for storing the inputted radius of the circle to allow for passing into a new constructor after the user makes all inputs.
            Circle.setRadius(userRadius);  //Setting radius per requirements.
            Circle oneArgCircle = new Circle(userRadius);  //Pass the variable into the overloaded constructor.
            finalArea = oneArgCircle.computeArea();  //Compute the area using the stored variables from the new constructor which took in the previous argument and store it in a variable.
            out.println("The area of a Circle with radius " + oneArgCircle.getRadius() + " is: " + finalArea + ".");  //Print the result of the calculation.
        }
        else out.println("You did not make a valid selection. Please try again."); //Catch an invalid menu choice.

        }
        catch (Exception e) {  //Catch the aforementioned exceptions.
            out.println("You did not enter a valid input. Please try again.");  //Catch all other exceptions. 
        }
    } 
}

class Rectangle {
    private static double length;
    private static double width;

    public Rectangle() {  //Initial constructor setting values to "0".
        length = 0;
        width = 0;
    }

    public Rectangle(double userLength, double userWidth) {  //Overloaded constructor per requirements.
        length = userLength;
        width = userWidth;
    }

    public static void setLength(double userLength) {
        length = userLength;
    }

    public double getLength() {
        return length;
    }

    public static void setWidth(double userWidth) {
         width = userWidth;
    }

    public double getWidth() {
        return width;
    }

    public double computeArea() {  //Compute the area of the rectangle and return the result to the calling function.
        return length * width;
    }

}

class Circle {
    private static double radius;

    public Circle() {  //Initial constructor setting the value to "0".
        radius = 0;
    }

    public Circle(double userRadius) {  //Overloaded constructor per requirements. 
        radius = userRadius;
    }

    public static void setRadius(double userRadius) {
        radius = userRadius;
    }

    public double getRadius() {
        return radius;
    }

    public double computeArea() {  //Compute the area of the circle and return the result to the calling function. 
        return Math.PI * Math.pow(radius, 2);

    } 

    
}
