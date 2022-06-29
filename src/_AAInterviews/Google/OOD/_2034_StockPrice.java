package _AAInterviews.Google.OOD;
import java.util.*;
public class _2034_StockPrice {
    Map<Integer, Integer> timePrice;
    TreeMap<Integer, Integer> priceCountMap;
    int latestTime;
    public _2034_StockPrice() {
        timePrice = new HashMap<>();
        priceCountMap = new TreeMap<>();
        latestTime = 0;
    }

    public void update(int timestamp, int price) {
        latestTime = Math.max(latestTime, timestamp);
        if(timePrice.containsKey(timestamp)) {
            int prePrice = timePrice.get(timestamp);
            priceCountMap.put(prePrice, priceCountMap.get(prePrice)-1);
            if(priceCountMap.get(prePrice)==0) priceCountMap.remove(prePrice);
        }
        timePrice.put(timestamp, price);
        priceCountMap.put(price, priceCountMap.getOrDefault(price, 0)+1);
    }

    public int current() {
        return timePrice.get(latestTime);
    }

    public int maximum() {
        return priceCountMap.lastKey();
    }

    public int minimum() {
        return priceCountMap.firstKey();
    }
}
