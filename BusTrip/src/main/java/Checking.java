
// {IN}-{CARD_UUID}-{TIMESTAMP}-{BUS_ID}-{TERMINAL-ID}.
// IN-CID000125-20211401145600-BUS905-T0003A
// OUT-CID000122-20211401145000-BUS901-T0001B

public class Checking {

    public static Checking createCheckingFromRaw(String rawString) {
        String[] strs = rawString.split("-");

        final String state = strs[0];
        final String cardUUID = strs[1];
        final Long timestamp = Long.parseLong(strs[2]);
        final String busId = strs[3];
        final String terminalId = strs[4];
        return new Checking(state, cardUUID, timestamp, busId, terminalId);
    }

    public final static String STATE_IN = "IN";
    public final static String STATE_OUT = "OUT";

    private final String state;
    private final String cardUUID;
    private final Long timestamp;
    private final String busId;
    private final String terminalId;

    private final boolean checkIn;

    public Checking(String state, String cardUUID, Long timestamp, String busId, String terminalId) {
        this.state = state;
        this.cardUUID = cardUUID;
        this.timestamp = timestamp;
        this.busId = busId;
        this.terminalId = terminalId;

        this.checkIn = STATE_IN.equals(state);
    }

    public String getState() {
        return state;
    }

    public String getCardUUID() {
        return cardUUID;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public String getBusId() {
        return busId;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public boolean isCheckIn() {
        return checkIn;
    }

    public String getDescription() {
        return String.format("%s-%s-%s-%s-%s", state, cardUUID, timestamp, busId, terminalId);
    }
}
