package _AAInterviews.ByteDance;

import java.util.Random;

public class _912_Quick_Sort {
    public int[] sortArray(int[] nums) {
        // 快排
        quickSort(nums, 0, nums.length-1);
        return nums;
    }


    private int partition(int[] arr, int low, int high) {
        //swap(arr, low, getPivot(low,high));
        int border = low+1; // left pointer
        for(int i=border; i<=high; i++) {
            if(arr[i]<arr[low]) swap(arr,border++,i);
        }
        swap(arr,border-1,low);
        return border-1;
    }

    private void swap(int[] arr, int idx1, int idx2) {
        int tmp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = tmp;
    }

    private void quickSort(int[] arr, int low, int high) {
        if(low < high) {
            int p = partition(arr,low,high);
            quickSort(arr, low, p-1);
            quickSort(arr, p+1, high);
        }
    }

    //也可以直接将high或者low作为pivot
    private int getPivot(int low, int high) {
        Random rand = new Random();
        return rand.nextInt((high-low)+1)+low;
    }


}
