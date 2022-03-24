package _All;

import java.util.Arrays;

public class _31_Next_Permutation {
    public void nextPermutation(int[] nums) {
        // 检查从右往左递增
        int len = nums.length;
        if(len == 1) return;
        boolean maxPermutation = true;
        for(int i =len-1; i>0; i--) {
            if(nums[i]>nums[i-1]) {
                // i is the peek point
                // binary search find the first one that's larger than nums[i-1] in nums[i] - nums[len-1]
                maxPermutation = false;
                int idx = binarySearch(nums[i-1], nums, i, len-1);
                System.out.println("idx: "+idx + ", pre peek: "+(i-1));
                swap(nums, i-1, idx);
                // sort from i to len-1
                partialSort(nums, i, len);
                return;
            }
        }
        if (maxPermutation) reverse(nums);
    }

    private int binarySearch(int val, int[] nums, int start, int end) {
        if(start>=end) return start;
        if(start==end-1) {
            if(nums[end]>val) return end;
            return start;
        }
        int mid = (start+end)/2;
        if (nums[mid]>=val) {
            return binarySearch(val, nums, mid, end);
        } else {
            return binarySearch(val, nums, start, mid-1);
        }
    }

    private void swap(int[] nums, int idx1, int idx2) {
        int tmp = nums[idx1];
        nums[idx1] = nums[idx2];
        nums[idx2] = tmp;
    }

    private void reverse(int[] nums) {
        int i=0,j=nums.length-1;
        while (i<j) {
            swap(nums,i,j);
            i++;
            j--;
        }
    }

    private void partialSort(int[] nums, int start, int end) {
        int[] tmp = Arrays.copyOfRange(nums, start, end);
        Arrays.sort(tmp);
        int idx = 0;
        for(int i=start; i<end; i++) {
            nums[i] = tmp[idx++];
        }
    }
}
