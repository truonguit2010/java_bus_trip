
public class Trip {
    private final Checking checkIn;
    private final Checking checkOut;

    private long tripTime;

    public Trip(Checking checkIn, Checking checkOut) {
        this.checkIn = checkIn;
        this.checkOut = checkOut;

        this.tripTime = checkOut.getTimestamp() - checkIn.getTimestamp();
    }

    public Checking getCheckIn() {
        return checkIn;
    }

    public Checking getCheckOut() {
        return checkOut;
    }

    public String getCardUUID() {
        return checkIn.getCardUUID();
    }

    public long getTripTime() {
        return tripTime;
    }

    public boolean validate() {
        return checkIn.isCheckIn() && !checkOut.isCheckIn() //
                && checkOut.getTimestamp() > checkIn.getTimestamp() //
                && checkIn.getCardUUID().equals(checkOut.getCardUUID()) //
                && checkIn.getBusId().equals(checkOut.getBusId()) //
                ;
    }

    public String getDescription() {
        return checkIn.getDescription() + "\n" + checkOut.getDescription();
    }
}
