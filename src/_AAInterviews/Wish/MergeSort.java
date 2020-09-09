package _AAInterviews.Wish;

public class MergeSort {

    //时间复杂度 O( nlogn )；空间复杂度O(N) （快排空间复杂度O(logn)）
    public static void mergeSort(int[] arr) {
        sortHelper(arr,new int[arr.length], 0, arr.length-1);
    }

    private static void sortHelper(int[] arr, int[] tmp, int start, int end) {
        if(start>=end) return;
        int mid = start + (end-start)/2;
        sortHelper(arr, tmp, start, mid);
        sortHelper(arr, tmp,mid+1, end);
        mergeHalves(arr, tmp, start, end);
    }

    private static void mergeHalves(int[] arr,int[] tmp, int leftStart, int rightEnd) {
        int leftEnd = (rightEnd+leftStart)/2;
        int rightStart = leftEnd+1;
        int size = rightEnd - leftStart + 1;

        int left = leftStart;
        int right = rightStart;
        int idx = leftStart;

        while (left<=leftEnd && right<=rightEnd) {
            if(arr[left]<=arr[right]) {
                tmp[idx++] = arr[left++];
            }
            else tmp[idx++] = arr[right++];
        }
        System.arraycopy(arr, left, tmp, idx,leftEnd-left+1);
        System.arraycopy(arr, right, tmp, idx,rightEnd-right+1);
        System.arraycopy(tmp, leftStart, arr, leftStart,size);
    }



    public static void main(String[] args) {
        int[] arr = {2,9,4,3,12,6,5,7,8,2};
        mergeSort(arr);
        for(int num:arr)
            System.out.println(num);
    }
}
