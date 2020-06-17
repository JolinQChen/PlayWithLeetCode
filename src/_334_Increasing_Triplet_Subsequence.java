public class _334_Increasing_Triplet_Subsequence {
    public boolean increasingTriplet(int[] nums) {
        int len = nums.length;
        if(len<3) return false;
        int i=0;
        while(i<len-2 && nums[i]>=nums[i+1]) i++;
        if(i==len-2) return false;
        int j = i+1;
        int k = j+1;
        while(k<len){
            if(nums[k]>nums[j]) return true;
            if(nums[k]<=nums[i]) i = k;
            else if(nums[k]<=nums[j]) j = k;
            k++;
        }
        return false;

    }
}
