package Array_String_TwoPointers_SlidingWindow_Greedy;

public class _4_Median_of_Two_Sorted_Arrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        //保证nums1比nums2短或者一样
        if(nums1.length>nums2.length) return findMedianSortedArrays(nums2,nums1);
        int m = nums1.length;
        int n = nums2.length;

        //分割线左边所有元素需要满足：个数为（m+n+1）/2
        int leftTotal = (m+n+1)/2;
        //在nums1的区间[0,m]中找合适的分割线
        //使得nums1[i-1]<=nums2[j] && nums2[j-1]<=nums1[i]
        int left = 0;
        int right = m;
        while (left<right){
            int i = left + (right-left+1)/2; // nums1左边的数量
            int j = leftTotal - i; //nums2左边的数量
            if(nums1[i-1]>nums2[j]){
                //搜索的位置在i左边，下一轮搜索区间为[left, i-1]
                right = i-1;
            }
            else left = i;
        }
        int i=left;
        int j = leftTotal-i;
        int nums1LeftMax = i==0? Integer.MIN_VALUE :nums1[i-1];
        int nums1RightMin = i==m? Integer.MAX_VALUE : nums1[i];
        int nums2LeftMax = j==0? Integer.MIN_VALUE :nums2[j-1];
        int nums2RightMin = j==n? Integer.MAX_VALUE :nums2[j];

        if((m+n)%2==1){
            return Math.max(nums1LeftMax,nums2LeftMax);
        }
        else {
            return (double)(Math.max(nums1LeftMax,nums2LeftMax)+Math.min(nums1RightMin,nums2RightMin))/2;
        }
    }
}
