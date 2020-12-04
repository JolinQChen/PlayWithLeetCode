package _AAInterviews.VMware;
import java.util.*;
public class NextPermutationQuickSort {

    public void nextPermutation(int[] nums) {
        if(nums.length<=1) return;
        int max = nums[nums.length-1];
        // int pos = nums.length-1;
        for(int i=nums.length-2; i>=0; i--) {
            if(nums[i]>=max) {
                max = nums[i];
                // pos = i;
            }
            else {
                int min = Integer.MAX_VALUE;
                int pos = 0;
                for(int j=i+1; j<nums.length; j++) {
                    if(nums[j]>nums[i] && nums[j]<min) {
                        min = nums[j];
                        pos = j;
                    }

                }
                int tmp = nums[pos];
                nums[pos] = nums[i];
                nums[i] = tmp;

                quickSort(nums,i+1, nums.length-1);
                return;

            }

        }

        Arrays.sort(nums);
        return;
    }



    // quick sort method to re-arrange the remaining array
    private void quickSort(int[] arr, int from, int to) {
        if(from<to) {
            int pivot = position(arr, from, to);
            quickSort(arr, from, pivot-1);
            quickSort(arr, pivot+1, to);
        }
    }

    // return the position of pivot
    private int position(int[] arr, int from, int to) {
        int pivot = arr[from]; // set the begin point as pivot
        while(from < to){
            while(from < to && arr[to]>=pivot) to--;
            arr[from] = arr[to];
            while(from<to && arr[from]<=pivot) from++;
            arr[to]=arr[from];
        }
        arr[from] = pivot;
        return from;
    }
}
