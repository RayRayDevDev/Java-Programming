import java.util.*;

public class Main {
    public static void main(String[] args) {
        aAndRArrayeyListy();
        aAndRLinkeyListy();
        aAndRHasheyTabley();
    }

    private static void aAndRArrayeyListy() {
        ArrayList<Integer> arrayeyListy = new ArrayList<>();
        Random rando = new Random();


        for (int i = 0; i < 2000000; i++) {
          arrayeyListy.add(rando.nextInt(100 + 1));
        }

        int size = arrayeyListy.size();
        for (int i = 0; i < size; i++) {
            arrayeyListy.remove(0);
            size--;
        }


    }

    private static void aAndRLinkeyListy() {
        LinkedList<Integer> linkeyListy = new LinkedList<>();
        Random rando = new Random();

        for (int i = 0; i < 2000000; i++) {
            linkeyListy.add(rando.nextInt(100) + 1);
            linkeyListy.get(i);
        }

        int size = linkeyListy.size();
        for (int i = 0; i < size; i++) {
            linkeyListy.remove(0);
        }
    }



    private static void aAndRHasheyTabley() {
        Hashtable<Integer, Integer> hasheyTabley = new Hashtable<>();
        Random rando = new Random();

        for (int i = 0; i < 2000000; i++) {
            hasheyTabley.put(i, rando.nextInt(100 + 1));
            for (int j = 0; j < 10; j++) {
                rando.nextInt(101);
            }
        }

        for (int i = 0; i < 2000000; i++) {
            hasheyTabley.remove(1999999 - i);
        }
    }

}