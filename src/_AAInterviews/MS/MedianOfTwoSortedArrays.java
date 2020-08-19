package _AAInterviews.MS;
// 找分割线
public class MedianOfTwoSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        if(m>n) return findMedianSortedArrays(nums2,nums1);
        // ensure that nums1.length<=nums2.length
        // 分割线左边的元素
        int leftTotal = (m+n+1)/2;
        int left = 0, right = m;
        // 开始二分法查找在nums1里面的划分位置
        while (left<right) {
            int leftCount = left + (right-left+1)/2;
            int  j = leftTotal - leftCount; // nums2左边的数量，标记点在nums2分界线右边
            // 检查是否符合划分条件
            if(nums1[leftCount-1]>nums2[j]) {
                // 划分位置在当前以左
                right = leftCount-1;
            }
            else if(leftCount==m || nums1[leftCount]>=nums2[j-1]) {
                left = leftCount;
                break; //找到了断点

            }
            else {
                left = leftCount;
            }
        }

        int line1 = left;
        int line2 = leftTotal - line1;
        System.out.println("line1: "+line1 +" , line2: "+line2);
        if((m+n)%2==1) {
            if(line1 == 0 ) return (double) nums2[line2-1];
            else return (double) Math.max(nums2[line2-1], nums1[line1-1]);
        }
        else {
            int leftMax1 = line1==0?Integer.MIN_VALUE:nums1[line1-1];
            int rightMin1 = line1==m?Integer.MAX_VALUE:nums1[line1];
            int leftMax2 = line2==0?Integer.MIN_VALUE:nums2[line2-1];
            int rightMin2 = line2==n?Integer.MAX_VALUE:nums2[line2];
            return (double) (Math.max(leftMax1,leftMax2) + Math.min(rightMin1,rightMin2))/2;
        }

    }
}
