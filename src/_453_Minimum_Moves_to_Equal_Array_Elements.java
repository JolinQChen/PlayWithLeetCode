import java.util.*;
public class _453_Minimum_Moves_to_Equal_Array_Elements
{
    private int res = 0;
    public int minMoves(int[] nums) {
        if(nums.length == 1) return 0;
//        for(int i:nums) System.out.print(i + " ");
//        System.out.println();
        Arrays.sort(nums);


        if(nums[0] == nums[nums.length-1]) return res;
        else {
            int count = nums[nums.length-1] - nums[nums.length-2]==0?1:nums[nums.length-1] - nums[nums.length-2];
            for(int i=0; i<nums.length-1; i++) {
                nums[i] += count;
            }
            res = res + count + minMoves(nums);
            return res;
        }
    }

    public static void main(String[] args)
    {
        int[] nums = {1,2,3};
        _453_Minimum_Moves_to_Equal_Array_Elements test = new _453_Minimum_Moves_to_Equal_Array_Elements();
        System.out.println(test.minMoves(nums));
    }
}
