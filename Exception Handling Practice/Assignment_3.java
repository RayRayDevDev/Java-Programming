import static java.lang.System.out;
import java.util.InputMismatchException;
import java.util.Scanner;

//Code by Cole Stanley (RäDev)
//Created with JDK 18.0.1.1

class RadioStationException extends Exception { //Custom exception handler.


    public RadioStationException(String message) { //Only exzception type needed was a message, I think. 
        super(message);
    }

}

class RadioStation {
     private String radioCallSign = null;
     private double radioStationFrequency = 0;
     private static int i = 0;
     private static int k = 0;

     public RadioStation() {

     }
    public RadioStation(String rCS, double rSF) {  //Constructor for validated inputs to use.
        radioCallSign = rCS.toUpperCase();
        radioStationFrequency = rSF;
    }

     public void validateCallSign (String radioCallsign) throws RadioStationException {  //Validate call sign method.
        for(int j = 0; j < radioCallSign.length(); j++){ 
            if(Character.isDigit(radioCallSign.charAt(j))) {  //Count each character in the string entered. Return true if a digit is encountered.

                throw new RadioStationException("\nEXCEPTION in RadioStation: Station call signs must only consist of letters. Unable to create radio station object!");  //New exception object.
            }
        }
        int radioCallSignLength = radioCallsign.length();  //Variable for the callsign's length since Java hates me and wouldn't work with an "if" statement.

        if(radioCallSignLength < 4 || radioCallSignLength > 4) {  //If the string passes previous step, check the length of the entered string. If </> 4, throw exception.
                
            throw new RadioStationException("\nEXCEPTION in RadioStation: Station call signs must contain exactly four (4) letters. Detected more or less than four (4) letters. Unable to create radio station object!");
        }
        
        
    }

     public void validateStationFrequency (double radioStationFrequency) throws RadioStationException {  //Validate radio station's frequency method.
        if(radioStationFrequency < 88.0 || radioStationFrequency > 108.0) {  //Conditional for assignment parameters. 

            throw new RadioStationException("\nEXCEPTION in RadioStation: The station frequency must be within 88.0 and 108.0, inclusive. The inputted frequency is not within this range. Unable to create radio station object!");
        }

    }

    public String toString() {
        String radioStationInfo = "\nRadio Station's Call Sign: " + radioCallSign + "\nRadio Station's Frequency: " + radioStationFrequency;
        return radioStationInfo;
    }

    public static void main(String[] args) {    
        RadioStation[] stationArray = new RadioStation[6];  //New station array of six station objects.
        Scanner userInput = new Scanner(System.in);
        do {
            try {
                RadioStation newRadioStation = new RadioStation();
                out.print("\nWelcome! Please enter the callsign of the radio station you'd like to listen to. Please enter only letters and ensure that the callsign is no more than four (4) letters in length:  ");
                newRadioStation.radioCallSign = userInput.next(); 
                newRadioStation.validateCallSign(newRadioStation.radioCallSign);
                out.print("\nNext, please enter the carrier frequency of the desired radio station. Please note that the frequency cannot be below 88 or above 108: ");
                try {
                    newRadioStation.radioStationFrequency = userInput.nextDouble();
                } catch (InputMismatchException e) {
                    out.println("\n\nYou did not enter a number. Please try again.");
                    break;
                } 

                newRadioStation.validateStationFrequency(newRadioStation.radioStationFrequency);
                stationArray[i] = new RadioStation(newRadioStation.radioCallSign, newRadioStation.radioStationFrequency); 
                i++;
                
            } catch(RadioStationException e) {
                out.println(e.getMessage());
            }

            //if(radioStationFrequency == 0) break; Debug. 
                

            } while(i < stationArray.length);

            for(k = 0; k < stationArray.length; k++) {
                out.println(stationArray[k]);
            }
    }
}