
import com.controller.CommandParser;
import com.controller.ParkingLotManager;
import org.junit.Assert;
import org.junit.Test;

public class ParkingChallengeTest {


    @Test
    public void testNegativeParkingSlotSize() {
        CommandParser commandParserObj = CommandParser.getCommandParserInstance();
       // System.out.println("B4 init:" + ParkingLotManager.getParkingLotinstance().isParkingLotInitialized());
        commandParserObj.processCommand("create_PARKING_LOT -1");
        Assert.assertTrue(!ParkingLotManager.getParkingLotinstance().isParkingLotInitialized());
        //System.out.println("B4 flag:" + ParkingLotManager.getParkingLotinstance().isParkingLotInitialized());

    }

    @Test
    public void testInvalidParkingLotSize() {
        CommandParser commandParserObj = CommandParser.getCommandParserInstance();
       // System.out.println("B4 flag:" + ParkingLotManager.getParkingLotinstance().isParkingLotInitialized());
        commandParserObj.processCommand("create_PARKING_LOT");
        //System.out.println("AFter flag: " + ParkingLotManager.getParkingLotinstance().isParkingLotInitialized());
        Assert.assertTrue(!ParkingLotManager.getParkingLotinstance().isParkingLotInitialized());
    }

    @Test
    public void testParking()
    {
        CommandParser commandParserObj = CommandParser.getCommandParserInstance();
        commandParserObj.processCommand("create_PARKING_LOT 1");
        commandParserObj.processCommand("park KA01-6789 White");
        Assert.assertTrue(ParkingLotManager.getParkingLotinstance().getRegistrationNumbersForColor("White").equals("KA01-6789"));
    }
}
