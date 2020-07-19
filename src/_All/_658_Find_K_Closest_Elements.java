package _All;

import java.util.*;
public class _658_Find_K_Closest_Elements {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int left = 0, right = arr.length-1;
        int index = -1;
        while(left<=right){
            if(x<=arr[left]) {
                index = left-1;
                break;
            }
            else if(x>=arr[right]){
                index = right;
                break;
            }
            int mid = left + (right-left)/2;
            if(arr[mid]==x) {
                index = mid;
                break;
            }
            else if(arr[mid]>x) right = mid-1;
            else left = mid+1;
        }
        List<Integer> res = new ArrayList<>();

        if(index>=0 && index<arr.length-1) index = x-arr[index]<=arr[index+1]-x?index:index+1;
        if(index<=0){
            for(int i=0; i<k; i++){
                res.add(arr[i]);
            }
        }
        else {
            int l=index, r=index;
            k--;
            while(k>0){
                if(l>0 && r<arr.length-1){
                    if(x - arr[l-1]<=arr[r+1]-x) l--;
                    else r++;
                }
                else if(l>0) l--;
                else r++;
                k--;
            }
            for(int i=l; i<=r; i++) res.add(arr[i]);
        }
        return res;

    }
}
