import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestChecking {

    @Test
    void testParseCheckIn() {
        String rawDataString = "IN-CID000122-20211401143600-BUS901-T0001A";
        Checking checkIn = Checking.createCheckingFromRaw(rawDataString);

        assertEquals(Checking.GATE_IN, checkIn.getGate());
        assertEquals("CID000122", checkIn.getCardUUID());
        assertEquals(20211401143600L, checkIn.getTimestamp());
        assertEquals("BUS901", checkIn.getBusId());
        assertEquals("T0001A", checkIn.getTerminalId());
        assertEquals(true, checkIn.isCheckIn());
    }

    @Test
    void testParseCheckOut() {
        String rawDataString = "OUT-CID000122-20211401145000-BUS901-T0001B";
        Checking checkOut = Checking.createCheckingFromRaw(rawDataString);

        assertEquals(Checking.GATE_OUT, checkOut.getGate());
        assertEquals("CID000122", checkOut.getCardUUID());
        assertEquals(20211401145000L, checkOut.getTimestamp());
        assertEquals("BUS901", checkOut.getBusId());
        assertEquals("T0001B", checkOut.getTerminalId());
        assertEquals(false, checkOut.isCheckIn());
    }
}
