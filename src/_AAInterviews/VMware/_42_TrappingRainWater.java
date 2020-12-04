package _AAInterviews.VMware;
// Input: [0,1,0,2,1,0,1,3,2,1,2,1]
// Output: 6
// Buckets effect
public class _42_TrappingRainWater {
    public int trap(int[] height) {
        if(height==null || height.length==0) return 0;
        int[] left = new int[height.length];
        int[] right = new int[height.length];
        left[0] = height[0];
        int res = 0;
        for(int i=1; i<height.length; i++) {
            left[i] = Math.max(height[i],left[i-1]);
        }
        right[height.length-1] = height[height.length-1];
        for(int i=height.length-2; i>=0; i--) {
            right[i] = Math.max(height[i],right[i+1]);
            res += Math.min(right[i],left[i])-height[i];
        }
        return res;


    }
}
