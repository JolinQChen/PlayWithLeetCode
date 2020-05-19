import java.util.*;
/**
 * Given a list of 24-hour clock time points in "Hour:Minutes" format,
 * find the minimum minutes difference between any two time points in the list.
 *
 * Example 1:
 * Input: ["23:59","00:00"]
 * Output: 1
 *
 * Note:
 * The number of time points in the given list is at least 2 and won't exceed 20000.
 * The input time is legal and ranges from 00:00 to 23:59.
 *
 * */

/** 桶排序，搞一个长度为60*24的list，把每一分钟都放进去*/

public class _539_Minimum_Time_Difference {
    public int findMinDifference(List<String> timePoints) {
        int minDifference = Integer.MAX_VALUE;
        List<Integer> minutes = new LinkedList<>();
        for(String hm:timePoints){
            //构建
            String[] tmp = hm.split(":");
            int hour = Integer.parseInt(tmp[0]);
            int minute = Integer.parseInt(tmp[1]);
            minutes.add(hour*60+minute);
        }

        int[] bucksSort = new int[60*24];
        int interval = 0, count = 0, last = 0, start = 0;
        // bucksSort中储存着每一分钟的情况，一旦出现一次就+1
        for(Integer min:minutes) bucksSort[min]++;
        for(int i=0; i<bucksSort.length; i++){
            if(bucksSort[i]>1) return 0;
            else if(bucksSort[i] == 1){
                interval++;
                if(interval<minDifference && count>=1) minDifference = interval;
                if(count==0) start = i;
                interval = 0;
                count++;
                last = i;
            }
            else interval++;
        }
        int reverse = 24*60+start-last;
        return Math.min(reverse,minDifference);
    }
    public static void main(String[] args){

    }
}
