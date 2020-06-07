import java.util.*;

public class _1471_The_k_Strongest_Values_in_an_Array {
    public int[] getStrongest(int[] arr, int k) {
        Arrays.sort(arr);
        int[] res = new int[k];
        int left = 0, right = arr.length-1;
        int m = arr[right/2];
        int index = 0;
        while(index<k){
            if(Math.abs(arr[right]-m)>=Math.abs(arr[left]-m)){
                res[index] = arr[right];
                right--;
            }
            else{
                res[index] = arr[left];
                left++;
            }
            index++;
        }
        return res;
    }
}
