package _AAInterviews.MS;
import java.util.*;
/**
 * Input:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 *
 * Output: [1,2,2,3,5,6]
 * */
public class MergeSortedArray {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] copy1 = new int[m];
        System.arraycopy(nums1,0,copy1,0,m);
        int idx = 0;
        int p1 = 0;
        int p2 = 0;
        while (idx<m+n) {
            if(p1<m && p2<n) {
                if(copy1[p1]<nums2[p2])
                    nums1[idx++] = copy1[p1++];
                else nums1[idx++] = nums2[p2++];
            }
            else if(p1<m) nums1[idx++] = copy1[p1++];
            else nums1[idx++] = nums2[p2++];
        }
    }
}
