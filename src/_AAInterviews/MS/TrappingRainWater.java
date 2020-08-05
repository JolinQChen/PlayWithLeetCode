package _AAInterviews.MS;
import java.util.*;
public class TrappingRainWater {
    public int trap(int[] height) {
        int len = height.length;
        if(len<=1) return 0;
        int[] leftMax = new int[len];
        int[] rightMax = new int[len];
        leftMax[0] = height[0];
        rightMax[len-1] = height[len-1];
        for(int i=1; i<len; i++) {
            leftMax[i] = Math.max(height[i],leftMax[i-1]);
        }
        for(int i=len-2; i>=0; i--) {
            rightMax[i] = Math.max(height[i],rightMax[i+1]);
        }
        int res = 0;
        for(int i=0; i<len; i++) {
            res += Math.min(rightMax[i],leftMax[i]) - height[i];
        }
        return res;
    }
}
