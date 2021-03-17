import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTrip {

    @Test
    void testTrip() {
        String checkInRawData = "IN-CID000122-20211401143600-BUS901-T0001A";
        Checking checkIn = Checking.createCheckingFromRaw(checkInRawData);

        String checkOutRawData = "OUT-CID000122-20211401145000-BUS901-T0001B";
        Checking checkOut = Checking.createCheckingFromRaw(checkOutRawData);

        Trip trip = new Trip(checkIn, checkOut);

        assertEquals(true, trip.validate());
    }

    @Test
    void testTripWithMismatchCardId() {
        String checkInRawData = "IN-CID000122-20211401143600-BUS901-T0001A";
        Checking checkIn = Checking.createCheckingFromRaw(checkInRawData);

        String checkOutRawData = "OUT-CID000123-20211401145000-BUS901-T0001B";
        Checking checkOut = Checking.createCheckingFromRaw(checkOutRawData);

        Trip trip = new Trip(checkIn, checkOut);
        assertEquals(false, trip.validate());
    }

    @Test
    void testTripWithMismatchTimestamp() {
        String checkInRawData = "IN-CID000122-20211401145000-BUS901-T0001A";
        Checking checkIn = Checking.createCheckingFromRaw(checkInRawData);

        String checkOutRawData = "OUT-CID000122-20211401143600-BUS901-T0001B";
        Checking checkOut = Checking.createCheckingFromRaw(checkOutRawData);

        Trip trip = new Trip(checkIn, checkOut);
        assertEquals(false, trip.validate());
    }

    @Test
    void testTripWithMismatchBusId() {
        String checkInRawData = "IN-CID000122-20211401143600-BUS901-T0001A";
        Checking checkIn = Checking.createCheckingFromRaw(checkInRawData);

        String checkOutRawData = "OUT-CID000122-20211401145000-BUS902-T0001B";
        Checking checkOut = Checking.createCheckingFromRaw(checkOutRawData);

        Trip trip = new Trip(checkIn, checkOut);

        assertEquals(false, trip.validate());
    }
}
