package _AAInterviews.Wish;

public class JumpGame {
    private int canTour(int[] nums, int start, int end) {
        int res = -1;
        for(int i = end-1; i>=start; i--) {
            if(nums[i] + i > end) {
                res = Math.max(res, nums[i] + i);
            }
        }
        return res;
    }

    public boolean canJump(int[] nums) {
        if(nums.length == 1) return true;
        if(nums[0] == 0) return false;
        int index = 0;
        //int start = 0;
        while(index<nums.length-1) {
            if(nums[index]==0) {
                int tmp = canTour(nums, 0, index);
                if(tmp == -1) return false;
                index = tmp;
                //start = tmp;
            }
            else index++;
        }
        return true;

    }
    // 法2：
    public boolean canJump2(int[] nums) {
        int k = 0;
        for (int i = 0; i < nums.length; i++)
        {
            if (i > k) return false;
            k = Math.max(k, i + nums[i]);
        }
        return true;

    }
}
