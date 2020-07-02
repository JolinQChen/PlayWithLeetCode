import java.util.*;
public class _1344_Angle_Between_Hands_of_a_Clock {
    public double angleClock(int hour, int minutes) {
        double hourPoint = (hour % 12) * 30 + (double) 30*minutes/60;
        double minutePoint = (double) minutes/60 * 360;
        double res = Math.abs(minutePoint-hourPoint);
        return res<=180?res:360-res;
    }
    public static void main(String[] args){
        _1344_Angle_Between_Hands_of_a_Clock test = new _1344_Angle_Between_Hands_of_a_Clock();
        System.out.println(test.angleClock(12,29));
    }
}
