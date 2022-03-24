package _All;
import java.util.*;
public class _2034_Stock_Price_Fluctuation {

    private int latestTime;
    private HashMap<Integer, Integer> timeToPrice; //timestamp to price
    private TreeMap<Integer, Integer> priceToCount;
    public _2034_Stock_Price_Fluctuation() {
        timeToPrice = new HashMap<>();
        priceToCount = new TreeMap<>();
        latestTime = 0;

    }

    public void update(int timestamp, int price) {
        latestTime = Math.max(latestTime, timestamp);
        if(timeToPrice.containsKey(timestamp)) {
            // update an existing price
            int prev_price = timeToPrice.get(timestamp);
            if (priceToCount.get(prev_price) - 1 == 0) {
                priceToCount.remove(prev_price);
            } else {
                priceToCount.put(prev_price, priceToCount.get(prev_price) - 1);
            }
        }
        timeToPrice.put(timestamp, price);
        priceToCount.put(price, priceToCount.getOrDefault(price,0)+1);
    }

    public int current() {
        return timeToPrice.get(latestTime);
    }

    public int maximum() {
        return priceToCount.lastKey();
    }

    public int minimum() {
        return priceToCount.firstKey();
    }
}
