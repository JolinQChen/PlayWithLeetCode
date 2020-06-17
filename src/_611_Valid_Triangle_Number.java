import java.util.*;
public class _611_Valid_Triangle_Number {
    // 法1：binary search
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int count = 0;
        int len = nums.length;
        if(len<=2) return 0;
        for(int i=0; i<len-2; i++){
            for(int j=i+1; j<len-1; j++){
                int k = binarySearch(nums, j+1, len-1, nums[i]+nums[j]);
                count += k-j-1;
            }
        }
        return count;
    }

    private static int binarySearch(int[] nums, int l, int r, int upperBound){

        while(l<=r){
            int mid = (l+r)/2;
            if(nums[mid]<upperBound) l = mid+1;
            else r = mid-1;
        }
        return l; //upperbound的左边界（不能达到）
    }

    // 法2：双指针，固定i，把j和k作为双指针往两边夹逼
    public static int triangleNumber_2(int[] nums){
        Arrays.sort(nums);
        int count = 0;
        int len = nums.length;
        if(len<=2) return 0;
        for(int i=0; i<len-2; i++){
            if(nums[i]==0) continue;
            int j = i+1;
            int k = binarySearch(nums, j+1, len-1, nums[i]+nums[j]);

            count += k-j-1;
            j++;
            while(j<len-1){
                k = binarySearch(nums, k, len-1, nums[i]+nums[j]);
                count += k-j-1;
                j++;
            }
        }
        return count;
    }

    public static void main(String[] args){
        int[] nums = {2,2,3,4};
        int res = triangleNumber_2(nums);
        System.out.println(res);
    }
}
