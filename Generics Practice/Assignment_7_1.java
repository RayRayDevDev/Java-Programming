import java.util.Random;

//Created by Cole Stanley (RäDev) for COP 3330C
//Created with JDK 18.0.1.1

interface interfaceValue {
    int intValue();
}

class maxMinGenericsPractice {
public static <P extends interfaceValue> Integer maxMin (Integer one, Integer two) {
    
    if(one.intValue() > two.intValue()) {
        return one;
    }
    else return two;
}
}
class Main {
public static void main(String[] args) {
    Random random = new Random();
    int RNGOne = random.nextInt(100);
    int RNGTwo = random.nextInt(100);
    System.out.println(RNGOne);
    System.out.println(RNGTwo);
    System.out.println(maxMinGenericsPractice.maxMin(RNGOne, RNGTwo)); 
}
}