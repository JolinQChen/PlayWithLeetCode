package _AAInterviews.Google.others;
import java.util.*;
public class _1610_Maximum_Number_of_Visible_Points {
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
            pointAngle += 360;
            pointsWithAngles.add(pointAngle);

        }


        Collections.sort(pointsWithAngles);

        int maxCount = coundMax(pointsWithAngles,angle);


        return maxCount + pointsOnLocation;
    }

    private int coundMax(List<Double> pointsWithAngles,int angle){
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
}
