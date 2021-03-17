import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTripManager {

    @Test
    void testCase1() {
        String[] rawDatas = new String[] {
                "IN-CID000122-20211401143600-BUS901-T0001A",
                "IN-CID000224-20211401144900-BUS902-T0002A",
                "IN-CID000125-20211401145600-BUS905-T0003A",

                "OUT-CID000122-20211401145000-BUS901-T0001B",
                "OUT-CID000224-20211401150600-BUS902-T0002B",
                "OUT-CID000125-20211401151600-BUS905-T0003B"
        };

        TripManager tripManager = new TripManager();
        tripManager.register("CID000122");
        tripManager.register("CID000224");
        tripManager.register("CID000125");

        tripManager.addTripsFromRawDatas(rawDatas);

        assertEquals(1, tripManager.countTripsByCardId("CID000122"));
        assertEquals(1, tripManager.countTripsByCardId("CID000224"));
        assertEquals(1, tripManager.countTripsByCardId("CID000125"));

        Trip trip = tripManager.getTrip( "CID000122",20211401143600L);
        long expectedTripTime = 20211401145000L - 20211401143600L;
        assertEquals(expectedTripTime, trip.getTripTime());
    }

}
