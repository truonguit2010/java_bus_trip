
// {IN}-{CARD_UUID}-{TIMESTAMP}-{BUS_ID}-{TERMINAL-ID}.
// IN-CID000125-20211401145600-BUS905-T0003A
// OUT-CID000122-20211401145000-BUS901-T0001B

public class Checking {

    public static Checking createCheckingFromRaw(String rawString) {
        String[] strs = rawString.split("-");

        final String gate = strs[0];
        final String cardUUID = strs[1];
        final Long timestamp = Long.parseLong(strs[2]);
        final String busId = strs[3];
        final String terminalId = strs[4];
        return new Checking(gate, cardUUID, timestamp, busId, terminalId);
    }

    public final static String GATE_IN = "IN";
    public final static String GATE_OUT = "OUT";

    private final String gate;
    private final String cardUUID;
    private final Long timestamp;
    private final String busId;
    private final String terminalId;

    private final boolean checkIn;

    public Checking(String gate, String cardUUID, Long timestamp, String busId, String terminalId) {
        this.gate = gate;
        this.cardUUID = cardUUID;
        this.timestamp = timestamp;
        this.busId = busId;
        this.terminalId = terminalId;

        this.checkIn = GATE_IN.equals(gate);
    }

    public String getGate() {
        return gate;
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
        return String.format("%s-%s-%s-%s-%s", gate, cardUUID, timestamp, busId, terminalId);
    }
}
