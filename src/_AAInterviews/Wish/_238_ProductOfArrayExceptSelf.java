package _AAInterviews.Wish;
/**
 * Input:  [1,2,3,4]
 * Output: [24,12,8,6]
 * */
public class _238_ProductOfArrayExceptSelf {
    public int[] productExceptSelf(int[] nums) {

        if(nums.length==1) return new int[]{0};

        int[] res = new int[nums.length];
        int curr = 1;
        for(int i=0; i<nums.length; i++) {
            res[i] = curr;
            curr *= nums[i];
        }
        curr = 1;
        for(int i=nums.length-1; i>=0; i--) {
            res[i] *= curr;
            curr *= nums[i];
        }

        return res;

    }
}
