package _AAInterviews.weride;
import org.junit.Test;

import java.util.*;
public class _1610_Maximum_Number_of_Visible_Points {
    // https://leetcode.com/problems/maximum-number-of-visible-points/
    // Shift origin to the location, then get angle (polar coordinates) with respect to nre origin.
    // for -ve angles, add 360, so that we can cover cases like 2nd and 3rd quadrant are together.
    // sort by angles and slide window to get max. visible points
    public int visiblePoints(List<List<Integer>> points, int angle, List<Integer> location) {
        List<Double> pointsWithAngles = new ArrayList<>();
        int pointsOnLocation = 0;
        for (int i = 0; i < points.size(); ++i) {
            int x = points.get(i).get(0) - location.get(0);
            int y = points.get(i).get(1) - location.get(1);
            // they are always visible
            if (x == 0 && y == 0) {
                pointsOnLocation++;
                continue;
            }
            Double pointAngle = Math.toDegrees(Math.atan2(y, x));
            pointsWithAngles.add(pointAngle);
            // if angle is negative, add 360 and add it too.
            pointAngle += 360;
            pointsWithAngles.add(pointAngle);
        }


        Collections.sort(pointsWithAngles);

        int maxCount = countMax(pointsWithAngles,angle);


        return maxCount + pointsOnLocation;
    }

    private int countMax(List<Double> pointsWithAngles,int angle){
        int maxCount = 0, count = 0;
        int startIndex = 0;
        int currIndex = 0;
        while (currIndex < pointsWithAngles.size()) {
            Double startAngle = pointsWithAngles.get(startIndex);
            Double endAngle = startAngle + angle;
            Double currAngle = pointsWithAngles.get(currIndex);

            if (currAngle <= endAngle) {
                count ++;
                ++currIndex;
                maxCount = Math.max(count, maxCount);
            } else {
                count--;
                ++startIndex;
            }
        }
        return maxCount;
    }

    @Test
    public void test(){
        List<Double> pointsWithAngles = new ArrayList<>();
        pointsWithAngles.add(-135.0);
        pointsWithAngles.add(-45.0);
        pointsWithAngles.add(10.0);
        pointsWithAngles.add(145.0);
        pointsWithAngles.add(225.0);
        pointsWithAngles.add(315.0);
        pointsWithAngles.add(370.0);
        pointsWithAngles.add(505.0);
        System.out.println(countMax(pointsWithAngles,227));
    }
}
