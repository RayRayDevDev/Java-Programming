import java.util.Scanner;

class Fibo {
    public static void main(String[] args) {
        Fibo newFibo = new Fibo();
        System.out.print("\nGo ahead and enter the numerical position you want the Fibonacci number for: ");
        Scanner userInput = new Scanner(System.in);
        long n = Long.parseLong(userInput.nextLine());
        System.out.println("\nThe Fibonacci number in position " + n + " is " + newFibo.FiboRecursion(n) + "!");
    }

    public long FiboRecursion(long n) {
        if (n == 0 || n == 1)
            return n;
        return FiboRecursion(n - 1) + FiboRecursion(n - 2);
    }
}