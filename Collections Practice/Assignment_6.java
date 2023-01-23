import static java.lang.System.out;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//Created by Cole Stanley (RäDev) for COP 3330C
//Created with JDK 18.0.1.1

class Main {
    
public static void main(String[] args) {
    ArrayList<String> names = new ArrayList<String>();
    names.add("Cole");
    names.add("Tracy");
    names.add("Winnie");
    names.add("Seth");
    names.add("Ernie");
    names.add("Jack");
    names.add("Zoey");
    names.add("Zach");

    for(String i : names) {
        out.println(i);
    }
    
    out.println("\n");

    Collections.sort(names);

    for(String i : names) {
        out.println(i);
    }
    
    out.println("\n");

    for(String i : names) {
        if(i == "Zoey") out.println("The location of the element 'Zoey' is: " + names.indexOf("Zoey"));
        else continue;
    }
    
    out.println("\n");

    out.println("The location of the element 'Heidi' is: " + names.indexOf("Heidi"));
    
    out.println("\n");

    String namesArray[] = new String[names.size()];
    names.toArray(namesArray);

    int j = 0;
    
    for(String i : namesArray) {
        out.println("The element at index " + j + " is: " + i + ".");
        j++;
    }
    
    out.println("\n");

    List<String> newNames = Arrays.asList(namesArray);
    for(String i : newNames) {
        out.println(i);
    }
    
    }
}
