package Design;
import java.util.*;
import javafx.util.Pair;
/**
 * Implement the class UndergroundSystem that supports three methods:
 *
 * 1. checkIn(int id, string stationName, int t)
 * A customer with id card equal to id, gets in the station stationName at time t.
 * A customer can only be checked into one place at a time.
 *
 * 2. checkOut(int id, string stationName, int t)
 * A customer with id card equal to id, gets out from the station stationName at time t.
 *
 * 3. getAverageTime(string startStation, string endStation)
 * Returns the average time to travel between the startStation and the endStation.
 * The average time is computed from all the previous traveling from startStation
 * to endStation that happened directly.
 *
 * Call to getAverageTime is always valid.
 *
 * You can assume all calls to checkIn and checkOut methods are consistent. That is, if a customer
 * gets in at time t1 at some station, then it gets out at time t2 with t2 > t1. All events happen
 * in chronological order.
 *
 * Input
 * ["UndergroundSystem","checkIn","checkIn","checkIn","checkOut","checkOut","checkOut","getAverageTime","getAverageTime","checkIn","getAverageTime","checkOut","getAverageTime"]
 * [[],[45,"Leyton",3],[32,"Paradise",8],[27,"Leyton",10],[45,"Waterloo",15],[27,"Waterloo",20],[32,"Cambridge",22],["Paradise","Cambridge"],["Leyton","Waterloo"],[10,"Leyton",24],["Leyton","Waterloo"],[10,"Waterloo",38],["Leyton","Waterloo"]]
 *
 * Output
 * [null,null,null,null,null,null,null,14.00000,11.00000,null,11.00000,null,12.00000]
 * */

public class _1396_Design_Underground_System {

    class UndergroundSystem {
        /**
         * two HashMaps:
         * checkInData = a new HashMap (id -> startStation, checkInTime)
         * journeyData = a new HashMap (startStation, endStation -> total, count)
         * */
        private Map<Integer, Pair<String, Integer>> checkInData;
        private Map<String, Pair<Double, Double>> journeyData;

        public UndergroundSystem() {
            checkInData = new HashMap<>();
            journeyData = new HashMap<>();
        }

        public void checkIn(int id, String stationName, int t) {
            checkInData.put(id, new Pair<>(stationName, t));
        }

        public void checkOut(int id, String stationName, int t) {
            Pair<String, Integer> checkInInfo = checkInData.get(id);
            String primKey = checkInInfo.getKey() + stationName;
            double total = (double) t - (double) checkInInfo.getValue();
            double count = 1.0;
            if(journeyData.containsKey(primKey)) {
                total += journeyData.get(primKey).getKey();
                count += journeyData.get(primKey).getValue();
            }
            journeyData.put(primKey, new Pair<>(total, count));
        }

        public double getAverageTime(String startStation, String endStation) {
            String primKey = startStation + endStation;
            double total = journeyData.get(primKey).getKey();
            double count = journeyData.get(primKey).getValue();
            return total / count;
        }
    }
}
