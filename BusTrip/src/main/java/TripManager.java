import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class TripManager {

    private final Map<String, Map<Long, Trip>> tripsByCardId;

    private Comparator<Checking> checkingTimeComparator = new Comparator<Checking>() {
        @Override
        public int compare(Checking o1, Checking o2) {
            if (o1.getTimestamp() == o2.getTimestamp()) {
                return 0;
            } else if (o1.getTimestamp() < o2.getTimestamp()) {
                return -1;
            } else {
                return 1;
            }
        }
    };

    public TripManager() {
        tripsByCardId = new ConcurrentHashMap<>();
    }

    public void register(String cardId) {
        tripsByCardId.putIfAbsent(cardId, new ConcurrentHashMap<>());
    }

    public void addTrip(Trip trip) {
        Map<Long, Trip> userTrips = tripsByCardId.get(trip.getCardUUID());
        userTrips.put(trip.getCheckIn().getTimestamp(), trip);
    }

    public void addTrips(List<Trip> trips) {
        for (Trip trip : trips) {
            addTrip(trip);
        }
    }

    public void addTripsByCheckings (List<Checking> checkings) {
        // Do not trust clients, server need to sort the checking set again.
        Collections.sort(checkings, checkingTimeComparator);

        List<Trip> trips = new ArrayList<>();
        for (int i = 0; i < checkings.size(); i += 2) {
            Trip trip = new Trip(checkings.get(i), checkings.get(i + 1));
            trips.add(trip);
        }

        addTrips(trips);
    }

    public void addTripsByUserCheckingsMap(Map<String, List<Checking>> userCheckingsMap) {
        userCheckingsMap.forEach( (k, v) -> {
            addTripsByCheckings(v);
        });
    }

    public void addTripsFromRawDatas(String[] rawDatas) {
        Map<String, List<Checking>> userCheckingsMap = new HashMap<>();

        Checking checking = null;
        List<Checking> userCheckings = null;
        for (int i = 0; i < rawDatas.length; i++) {
            checking = Checking.createCheckingFromRaw(rawDatas[i]);
             userCheckings = userCheckingsMap.get(checking.getCardUUID());
             if (userCheckings == null) {
                 userCheckings = new ArrayList<>();
                 userCheckingsMap.put(checking.getCardUUID(), userCheckings);
             }
             userCheckings.add(checking);
        }

        addTripsByUserCheckingsMap(userCheckingsMap);
    }

    public Map<Long, Trip> getTripsByCardId(String cardId) {
        return tripsByCardId.get(cardId);
    }

    public Trip getTrip(String cardId, Long tripCheckInTimestamp) {
        return tripsByCardId.get(cardId).get(tripCheckInTimestamp);
    }

    public int countTripsByCardId(String cardId) {
        return  tripsByCardId.get(cardId).size();
    }
}
