
public class Trip {
    private final Checking checkIn;
    private final Checking checkOut;

    public Trip(Checking checkIn, Checking checkOut) {
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public Checking getCheckIn() {
        return checkIn;
    }

    public Checking getCheckOut() {
        return checkOut;
    }

    public boolean validate() {
        return checkIn.isCheckIn() && !checkOut.isCheckIn() //
                && checkOut.getTimestamp() > checkIn.getTimestamp() //
                && checkIn.getCardUUID().equals(checkOut.getCardUUID()) //
                && checkIn.getBusId().equals(checkOut.getBusId()) //
                ;
    }
}
