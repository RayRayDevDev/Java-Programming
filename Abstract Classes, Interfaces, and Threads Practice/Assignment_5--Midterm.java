import static java.lang.System.out;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

//Created by Cole Stanley (RäDev) for COP 3330C
//Created with JDK 18.0.1.1

abstract class Vehicle implements Runnable {
    protected String vehicleName = null;
    protected Double position = 0.0;
    protected Double speed;
    protected int fuelOrElectricityCapacity;
    protected int currentFuelLevel;
    protected int refuelTime; // random amount of time to refuel.
    protected boolean winner = false;
    protected static int i = 0;
    static ArrayList<String> raceResults = new ArrayList<>();

    Vehicle(String name, Double position, Double speed, int capacity) {

        vehicleName = name;
        this.position = position;
        this.speed = speed;
        fuelOrElectricityCapacity = capacity;
        currentFuelLevel = capacity;

    }

    @Override
    public void run() {
        do {
            try {
                if (position == 0) {
                    out.println("A " + vehicleName + " has joined the race!");
                    out.println("The " + vehicleName + " is at the starting line!");
                    out.println("\n" + vehicleName + ": Go!\n");
                    position += speed; // Easier way than doing complex math.
                    out.println("The " + vehicleName + " is now at position " + position + "!");
                } else if (position <= 99.99 && currentFuelLevel != 0 && winner != true) { // Normal race loop.
                    position += speed;
                    currentFuelLevel -= 1;
                    out.println("The " + vehicleName + " is currently at position " + position
                            + " and is moving at a speed of " + speed + " and currently has "
                            + currentFuelLevel + " gallons/gal-e of fuel left!");
                } else if (position <= 99.99 && currentFuelLevel == 0 && winner != true) { // Condition for refueling.
                    refuel();
                } else if (position >= 100 && winner != true) { // Win condition.
                    winner = true;
                    i++;
                    out.println("\nThe " + vehicleName + " has just crossed the finish line in position: " + i + "!\n");
                    WriteResultsToFile.createFile();
                    raceResults.add("The " + vehicleName + " has finished the race in position: " + i + ".");
                    WriteResultsToFile.writeToFile(raceResults, vehicleName);
                }
            } catch (Exception e) {
                out.println(e.getMessage());
            }
        } while (!winner);
    }

    protected synchronized void refuel() {
        out.println("\nThe " + vehicleName + " is out of fuel and is currently refueling!\n");
        Random r = new Random();
        refuelTime = r.nextInt(fuelOrElectricityCapacity * 5);
        try {
            while (currentFuelLevel < fuelOrElectricityCapacity) {
                Thread.sleep(refuelTime);
                currentFuelLevel += 1;
            }
            out.println("\nThe " + vehicleName + " has been successfully refueled by " + currentFuelLevel + " gallons!\n");
        } catch (Exception e) {
            out.println(e.getMessage());
        }
    }
}
class ToyotaCorollaCross extends Vehicle {

    ToyotaCorollaCross(String name, Double position, Double speed, int capacity) {
        super(name, position, speed, capacity);

    }
    // First Slowest of 2022.
}

class VolkswagenTiguan extends Vehicle {

    VolkswagenTiguan(String name, Double position, Double speed,
            int capacity) {
        super(name, position, speed, capacity);

    }
    // Second Slowest of 2022.

}

class NissanSentra extends Vehicle {

    NissanSentra(String name, Double position, Double speed, int capacity) {
        super(name, position, speed, capacity);

    }
    // Third Slowest of 2022.

}

class BMWM8CompetitionGranCoupe extends Vehicle {

    BMWM8CompetitionGranCoupe(String name, Double position, Double speed, int capacity) {
        super(name, position, speed, capacity);

    }
    // Fourth Fastest of 2022.
}

class LamborghiniHuracanSTO extends Vehicle {

    LamborghiniHuracanSTO(String name, Double position, Double speed, int capacity) {
        super(name, position, speed, capacity);

    }
    // Third Fastest of 2022.

}

class Porsche911TurboSCabriolet extends Vehicle {

    Porsche911TurboSCabriolet(String name, Double position, Double speed, int capacity) {
        super(name, position, speed, capacity);

    }
    // Second Fastest of 2022.

}

class TeslaModelSPlaid extends Vehicle {

    TeslaModelSPlaid(String name, Double position, Double speed, int capacity) {
        super(name, position, speed, capacity);

    }
    // First Fastest of 2022.

}

class WriteResultsToFile {
    public static void createFile() {
        try {
            File raceResults = new File("raceResults.txt");

            if (raceResults.createNewFile()) {
                out.println("\n\nRace results file successfully created!\n\n");
            }

        } catch (IOException e) {
            out.print("\n\nAn error occured: \n\n");
            e.printStackTrace();
        }
   }

    public static void writeToFile(ArrayList<String> raceResults, String name) {
        try{
        FileWriter writeRaceResults = new FileWriter("raceResults.txt");
        for (String str : raceResults) {
            writeRaceResults.write(str + System.lineSeparator());
        }
        writeRaceResults.close();
        out.println("The " + name + "'s race results have been successfully saved!\n");
    } catch(IOException e) {
        e.printStackTrace();
    }
  }
}

class Main {
    public static void main(String[] args) {
        ToyotaCorollaCross toyoCross = new ToyotaCorollaCross("2022 Toyota Corolla Cross", 0.0, 2.0, 12);
        VolkswagenTiguan volkoTiguan = new VolkswagenTiguan("2022 Vokswagen Tiguan", 0.0, 2.5, 15);
        NissanSentra nissaSentra = new NissanSentra("2022 Nissan Sentra", 0.0, 3.0, 12);
        BMWM8CompetitionGranCoupe bMWM8CompoGranCoupe = new BMWM8CompetitionGranCoupe("2022 BMW M8 Competition Gran Coupe", 0.0, 3.5, 20);
        LamborghiniHuracanSTO lamboHuracanSTO = new LamborghiniHuracanSTO("2022 Lamborghini Huracan STO", 0.0, 4.0, 21);
        Porsche911TurboSCabriolet porscho911TurboSCabriolet = new Porsche911TurboSCabriolet("2022 Porsche 911 Turbo S Cabriolet", 0.0, 4.5, 18);
        TeslaModelSPlaid tesloModelSPlaid = new TeslaModelSPlaid("2022 Tesla Model S Plaid", 0.0, 5.0, 15); //Tank capacity is extrapolated based upon 33.7kWh in a average gallon of fuel over its range of 390 miles/charge.
        Thread first = new Thread(toyoCross);
        Thread second = new Thread(volkoTiguan);
        Thread third = new Thread(nissaSentra);
        Thread fourth = new Thread(bMWM8CompoGranCoupe);
        Thread fifth = new Thread(lamboHuracanSTO);
        Thread sixth = new Thread(porscho911TurboSCabriolet);
        Thread seventh = new Thread(tesloModelSPlaid);
        first.setName("Toyota Cross");
        second.setName("Volkswagen Tiguan");
        third.setName("Nissan Sentra");
        fourth.setName("BMW M8");
        fifth.setName("Lamborghini Huracan");
        sixth.setName("Porsche 911");
        seventh.setName("Tesla Model-S");
        first.start();
        second.start();
        third.start();
        fourth.start();
        fifth.start();
        sixth.start();
        seventh.start();

    }
}