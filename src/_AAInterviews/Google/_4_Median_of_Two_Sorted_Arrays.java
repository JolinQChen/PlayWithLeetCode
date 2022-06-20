package _AAInterviews.Google;

public class _4_Median_of_Two_Sorted_Arrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }
        int x = nums1.length, y = nums2.length;
        int low = 0, high = x;
        while (low <= high) {
            int partition1 = (low + high) / 2;
            int partition2 = (x + y + 1) / 2 - partition1;

            int maxLeftX = partition1 == 0 ? Integer.MIN_VALUE : nums1[partition1 - 1];
            int minRightX = partition1 == x ? Integer.MAX_VALUE : nums1[partition1];
            int maxLeftY = partition2 == 0 ? Integer.MIN_VALUE : nums2[partition2 - 1];
            int minRightY = partition2 == y ? Integer.MAX_VALUE : nums2[partition2];

            if (maxLeftX <= minRightY && maxLeftY <= minRightX) {
                if ((x + y) % 2 == 0) {
                    return (Math.max(maxLeftX * 1.0, maxLeftY * 1.0) + Math.min(minRightX * 1.0, minRightY * 1.0)) / 2.0;
                } else {
                    return Math.max(maxLeftX, maxLeftY);
                }
            } else if (maxLeftX > minRightY) {
                high = partition1 - 1;
            } else {
                low = partition1 + 1;
            }
        }
        return 0.0;
    }

    public double findMedianSortedArrays_2(int[] nums1, int[] nums2) {
        // make sure nums1 is longer than nums2
        if(nums1.length<nums2.length) return findMedianSortedArrays_2(nums2, nums1);
        // rule out edge case when mid is within nums1
        int len = nums1.length+nums2.length;
        int half = (len+1)/2;

        int left = 0;
        int right = nums2.length-1;
        while (true) {
            int partition2 = left +(right-left)/2;
            int partition1 = half-partition2;
            // is this the right position?
            int ALeft = partition1 == 0 ? Integer.MIN_VALUE: nums1[partition1-1];
            int ARight = partition1 == nums1.length ? Integer.MAX_VALUE: nums1[partition1];
            int BLeft = partition2==0 ? Integer.MIN_VALUE : nums2[partition2-1];
            int BRight = partition2==nums2.length ? Integer.MAX_VALUE: nums2[partition2];
            if(ALeft<=BRight && BLeft<=ARight) {
                // yes
                if(len%2==1) {
                    return Math.max(ALeft, BLeft);
                } else {
                    return (double) (Math.max(ALeft, BLeft) + Math.min(ARight, BRight) )/2.0;
                }
            }

            if(BLeft>ARight) right = partition2-1;
            else left = partition2+1;
        }
    }
}
