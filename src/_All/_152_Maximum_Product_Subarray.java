package _All;

public class _152_Maximum_Product_Subarray {
    public int maxProduct(int[] nums) {
        int imax = 1;
        int imin = 1;
        int resMax = Integer.MIN_VALUE;
        for(int i=0; i<nums.length; i++) {
            if(nums[i]<0) {
                int tmp = imax;
                imax = imin;
                imin = imax;
            }
            imax = Math.max(imax * nums[i], nums[i]);
            imin = Math.min(imin * nums[i], nums[i]);
            resMax = Math.max(resMax, imax);
        }
        return resMax;
    }
}
