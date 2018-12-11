package com.controller;

import com.model.Car;
import com.model.Vehicle;

public class CommandParser {
    private static CommandParser commandParserInstance = null;
    ParkingLotManager parkingLotManager = null;

   public CommandParser()
   {
       super();
       parkingLotManager = new ParkingLotManager();
   }
    public ParkingLotManager getParkingLotManager()
    {
        return parkingLotManager;
    }
    public void processCommand(String command)
    {

        String[] commandTokens = command.split(" ");
        switch(commandTokens[0].toLowerCase())
        {
            case "create_parking_lot":
                if(commandTokens.length <=1 || commandTokens[1] == null || commandTokens[1].trim().equals("") || !commandTokens[1].matches("^(?!0+$)\\d+$")) {
                System.out.println("Invalid Number to init parking lot");
                break;
                 }
                // System.out.println("Initializing parking lot ");
                 this.parkingLotManager.initParkingLot(Integer.parseInt(commandTokens[1].trim()));
                 break;
            case "park":
               // System.out.println("Command received to park "+commandTokens[1].trim());
                if(commandTokens.length <=1 ||  commandTokens[1] == null ||commandTokens[1].trim().equals("")) {
                    System.out.println("No or invalid Vehicle details");
                    break;
                }

                Vehicle v = new Car(commandTokens[1].trim(),commandTokens[2].trim());
                this.parkingLotManager.park(v);
                break;
            case "status":
                this.parkingLotManager.printParkingLotStatus();
                break;
            case "leave":
                if(commandTokens[1].trim().equals("") || (commandTokens[1] == null) || !commandTokens[1].matches("^(?!0+$)\\d+$")) {
                    System.out.println("Invalid park slot number for unparking");
                    break;
                }
                this.parkingLotManager.leave(Integer.parseInt(commandTokens[1].trim()));
                break;
            case "slot_numbers_for_cars_with_colour":
                if(commandTokens.length <=1 ||  commandTokens[1] == null ||commandTokens[1].trim().equals("")) {
                    System.out.println("No or invalid Vehicle details");
                    break;
                }
                this.parkingLotManager.getSlotNumbersForColor(commandTokens[1].trim());
                break;
            case "slot_number_for_registration_number":
                this.parkingLotManager.getSlotNumberForReg(commandTokens[1].trim());
                break;
            case "registration_numbers_for_cars_with_colour":
                this.parkingLotManager.getRegistrationNumbersForColor(commandTokens[1].trim());
                break;
            case "history":
                this.parkingLotManager.printHistory();
                break;
            case "exit":
                System.out.println("Exiting program");
                break;

        }
    }
}
