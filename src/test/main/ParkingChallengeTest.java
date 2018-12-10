
import com.controller.CommandParser;
import com.controller.ParkingLotManager;
import com.model.Parking;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

public class ParkingChallengeTest {

    @Test
    public void testNegativeParkingSlotSize() {
        CommandParser commandParserObj = new CommandParser();
        commandParserObj.processCommand("create_PARKING_LOT -1");
        Assert.assertTrue(!commandParserObj.getParkingLotManager().isParkingLotInitialized());
    }

    @Test
    public void testInvalidParkingLotSize() {
        CommandParser commandParserObj = new CommandParser();
       // System.out.println("B4 flag:" + commandParserObj.getParkingLotManager().isParkingLotInitialized());
        commandParserObj.processCommand("create_PARKING_LOT");
        //System.out.println("AFter flag: " + commandParserObj.getParkingLotManager().isParkingLotInitialized());
        Assert.assertTrue(!commandParserObj.getParkingLotManager().isParkingLotInitialized());
    }

    @Test
    public void testParking()
    {
        CommandParser commandParserObj = new CommandParser();
        commandParserObj.processCommand("create_PARKING_LOT 1");
        commandParserObj.processCommand("park KA01-6789 White");
        Assert.assertTrue(commandParserObj.getParkingLotManager().getSlotNumberForReg("KA01-6789") == 1);
    }

    @Test
    public void testLeave()
    {
        CommandParser commandParserObj = new CommandParser();
        commandParserObj.processCommand("create_PARKING_LOT 2");
        commandParserObj.processCommand("park KA01-6789 White");
        commandParserObj.processCommand("park KA01-0909 Red");
        commandParserObj.processCommand("park KA01-0239 Brown");
        commandParserObj.processCommand("leave 01");
        Assert.assertTrue(commandParserObj.getParkingLotManager().getSlotNumberForReg("KA01-6789") == 0);
        Assert.assertTrue(commandParserObj.getParkingLotManager().getSlotNumberForReg("KA01-0239") == 1);

    }

    @Test
    public void testInvalidLeaveNumber()
    {
        CommandParser commandParserObj = new CommandParser();
        commandParserObj.processCommand("create_PARKING_LOT 2");
        commandParserObj.processCommand("park KA01-6789 White");
        commandParserObj.processCommand("park KA01-0909 Red");
        commandParserObj.processCommand("park KA01-0239 Brown");
        commandParserObj.processCommand("leave -100");
        Assert.assertTrue(commandParserObj.getParkingLotManager().getSlotNumberForReg("KA01-6789") == 1);
        Assert.assertTrue(commandParserObj.getParkingLotManager().getSlotNumberForReg("KA01-0239") == 0);

    }

    @Test
    public void testVehiclesWaiting()
    {
        CommandParser commandParserObj = new CommandParser();
        commandParserObj.processCommand("create_PARKING_LOT 2");
        commandParserObj.processCommand("park KA01-6789 White");
        commandParserObj.processCommand("park KA01-0909 Red");
        commandParserObj.processCommand("park KA01-0239 RED");
        commandParserObj.processCommand("leave 1");
        Assert.assertTrue(commandParserObj.getParkingLotManager().getSlotNumberForReg("KA01-6789") == 0);
        Assert.assertTrue(commandParserObj.getParkingLotManager().getSlotNumberForReg("KA01-0239") == 1);
        Assert.assertTrue(commandParserObj.getParkingLotManager().getSlotNumbersForColor("RED").size() == 2);
        StringBuilder slotNumber = new StringBuilder("");
        commandParserObj.getParkingLotManager().getSlotNumbersForColor("RED").forEach(t ->slotNumber.append(t));
       // System.out.println("****"+slotNumber);
        Assert.assertTrue(slotNumber.toString().equals("21"));
    }

    @Test
    public void testEndTimeStamp()
    {
        CommandParser commandParserObj = new CommandParser();
        commandParserObj.processCommand("create_PARKING_LOT 2");
        commandParserObj.processCommand("park KA01-6789 White");
        commandParserObj.processCommand("park KA01-0909 Red");
        commandParserObj.processCommand("park KA01-0239 RED");
        commandParserObj.processCommand("leave 1");
        List<Parking> parkedVehiclesHistory = commandParserObj.getParkingLotManager().getHistory();
        Date endTimeStamp = null;
        for(Parking p:parkedVehiclesHistory)
        {
            if(p.getVehicle().getRegistrationNum().equalsIgnoreCase("KA01-6789"))
                endTimeStamp = p.getEndTimeStamp();

        }
        Assert.assertNotNull(endTimeStamp);
    }
}
