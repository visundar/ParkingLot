package com.util;

import com.controller.CommandParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class FileParser {

    public static void readFile(String fileName)
    {
        CommandParser commandParser =  new CommandParser();
        try {
          List<String> commands= Files.lines(Paths.get(fileName))
                    .map(String::trim)
                    .filter(line ->line.length()>=5)
                    .collect(Collectors.toList());
          for(String command: commands)
          {
             // System.out.println("Processing "+command);
              commandParser.processCommand(command);
          }

        }
        catch(IOException ioe)
        {
            System.out.println("Exception reading file .. Try again..");
        }
    }

}
