package _AAInterviews.Google;

import java.util.*;

public class _1011_capacoty_to_ship_packages_within_D_days {
    class ShipDay {
        int capacity;
        int day;

        public ShipDay(int capacity, int day) {
            this.capacity = capacity;
            this.day = day;
        }
    }

    public int shipWithinDays(int[] weights, int days) {
        int maxNum = 0;
        int sum = 0;
        for (int weight : weights) {
            sum += weight;
            maxNum = Math.max(maxNum, weight);
        }

        int min = Math.max((sum + days - 1) / days, maxNum);
        int minDay = minDay(weights, min);
        int max = sum;
        int maxDay = minDay(weights, max);
        ShipDay leftNode = new ShipDay(min, minDay);
        ShipDay rightNode = new ShipDay(max, maxDay);
        while (leftNode.capacity <= rightNode.capacity) {
            if (leftNode.day <= days) return leftNode.capacity;
            int mid = leftNode.capacity + (rightNode.capacity - leftNode.capacity) / 2;
            int midDay = minDay(weights, mid);
            if (midDay > days) {
                leftNode.capacity = mid + 1;
                leftNode.day = minDay(weights, leftNode.capacity);
            } else {
                rightNode.capacity = mid - 1;
                rightNode.day = minDay(weights, rightNode.capacity);
            }
        }
        int res = sum;
        if (leftNode.day <= days) res = leftNode.capacity;
        if (rightNode.day <= days) res = Math.min(res, rightNode.capacity);
        return res;

        // greedy
        // int day = minDay(weights, minCapacity);
        // while(day<days) {
        //     minCapacity++;
        //     day = minDay(weights, minCapacity);
        // }

    }

    private int minDay(int[] weights, int capacity) {
        int count = 0;
        int curSum = 0;
        for(int i=0; i<weights.length; i++) {
            curSum += weights[i];
            if(curSum>capacity) {
                count++;
                curSum = weights[i];
            }
        }
        count++;
        return count;
    }


    /**
     * Binary search
     * */

    public int shipWithinDays_1(int[] weights, int days) {
        int sum = 0, maxWeight = 0;
        for (int w : weights) {
            sum += w;
            maxWeight = Math.max(maxWeight, w);
        }
        int lo = maxWeight, hi = sum;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (canShip(weights, mid, days)) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }

    private boolean canShip(int[] weights, int capacity, int days) {
        int i = 0;
        int curSum = 0, daysUsed = 1;
        for (int w : weights) {
            curSum += w;
            if (curSum > capacity) {
                curSum = w;
                daysUsed++;
            }
        }
        return daysUsed <= days;
    }
}
