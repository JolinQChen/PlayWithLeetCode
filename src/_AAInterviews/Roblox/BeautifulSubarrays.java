package _AAInterviews.Roblox;
import java.util.*;
/**
 * beautiful is defined as an array of any length having a specific number of odd elements
 *
 * */
public class BeautifulSubarrays {
    public static int countBeautifulSubarrays(int[] arr, int n) {
        if(n>arr.length) return 0;
//        int res = 0;
        int left1 = 0;
        int right1;
        int left2 = 0;
        int right2 = 0;
        int count = 0;

        // initialize
        while (left2<arr.length && arr[left2]%2==0) left2++;
        if(left2==arr.length) return 0;
        count++;
        right1 = left2;
        while (count<n && right1<arr.length-1) {
            right1++;
            if(arr[right1]%2==1) count++;
        }
        if(right1==arr.length-1 && count<n) return 0;
        right2=right1+1;
        while (right2<arr.length && arr[right2]%2==0) right2++;
        int res = (left2-left1+1) * (right2-right1);

        while (right1<arr.length) {

            left1 = left2+1;
            right1 = right2;
            if(right1==arr.length) break;
            left2=left1;
            while (left2<arr.length && arr[left2]%2==0) left2++;
            if(left2==arr.length) break;
            right2=right1+1;
            while (right2<arr.length && arr[right2]%2==0) right2++;
            res+=(left2-left1+1) * (right2-right1);

        }
        return res;
    }
    public static void main(String[] args) {
        int[] test = {2,2,5,6,9,2,11};
        System.out.println(countBeautifulSubarrays(test,2));
    }
}
