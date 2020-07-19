package _All;

public class _1060_Missing_Element_in_Sorted_Array {
    public int missingElement(int[] nums, int k)
    {
        int[] missing = new int[nums.length];
        missing[0] = 0;
        for(int i=1; i<nums.length; i++)
        {
            missing[i] = nums[i]-nums[0] - i;
        }
        int left = 0;
        int right = nums.length-1;
        while(left<right)
        {
            int mid = left + (right - left)/2;
            if(k > missing[mid]) left = mid+1;
            else if(k<missing[mid]) right = mid;
            else if(k==missing[mid]) {
                left = mid;
                break;
            }
        }
        int gap = missing[left]-k>=0 ? missing[left]-k+1:missing[left]-k;
        return nums[left]-gap;
    }
}
