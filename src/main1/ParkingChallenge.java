package main1;

import com.util.FileParser;

import java.io.InputStreamReader;
import java.util.Scanner;
import com.controller.CommandParser;

public class ParkingChallenge
{
    public static void main(String args[])
    {
        //reading commands via inputfile
       // System.out.println("Input args" + args);
       // System.out.println("Inside main program");
        if(args.length > 0)
        {
           FileParser.readFile(args[0]);
        }
        // reading commands from shell
        else {
            System.out.println("Enter command to process, Enter exit to quit");
            Scanner commandScanner = new Scanner(new InputStreamReader(System.in));
            String inputCmd;
            while (true) {
                inputCmd = commandScanner.nextLine();
                CommandParser commandParserObj = CommandParser.getCommandParserInstance();
                if (inputCmd.equalsIgnoreCase("exit")) {
                    System.out.println("Exiting ParkingLot Challenge");
                    break;
                }
                commandParserObj.processCommand(inputCmd);

            }
        }
    }
}