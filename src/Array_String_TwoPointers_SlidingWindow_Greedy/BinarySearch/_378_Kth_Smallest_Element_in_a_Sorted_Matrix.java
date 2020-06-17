package Array_String_TwoPointers_SlidingWindow_Greedy.BinarySearch;

import java.util.Collections;
import java.util.PriorityQueue;

public class _378_Kth_Smallest_Element_in_a_Sorted_Matrix {
    //法1：优先队列
    public int kthSmallest_1(int[][] matrix, int k) {
        PriorityQueue<Integer> MaxPQ = new PriorityQueue<>(Collections.reverseOrder());
        for (int[] row : matrix) {
            if(MaxPQ.size()==k && row[0]>MaxPQ.peek()) break;
            for (int num : row) {
                if (MaxPQ.size() == k && num > MaxPQ.peek())
                    break;
                MaxPQ.add(num);
                if (MaxPQ.size() > k)
                    MaxPQ.remove();
            }
        }
        return MaxPQ.remove();
    }

    //法2：二分法
    /**
     * 思路分析：
     *
     * 从方法一的做法可以看出，对矩阵的有序性运用不充足，比如就导致了不是最优解。
     *
     * 第k小的数字意味着小于等于它的元素一共有k个，大于它的数字有n*n-k个。假设某个数为mid
     *
     * 如果小于等于mid的元素个数小于k，说明mid不是第k小的数，比mid小的数就更不可能是了。所以第k小的数至少是mid + 1。
     *
     * 如果小于等于mid的元素个数大于等于k，说明mid可能为第k小的数，比它小的数也有可能是答案。
     *
     * 这就是二分法的思路。假定查找的范围为[left, right]，首先计算int mid = left + (right - left) / 2;
     * 然后在矩阵中计数有多少个元素小于等于mid，这个数量为count。
     *
     * 如果count < k，那么第k小的数至少为mid + 1，所以left = mid + 1。
     * 反之，right = mid。
     *
     * 循环结束的条件为left >= right，此时left即为答案。
     *
     * */
    public int kthSmallest_2(int[][] matrix, int k) {
        int n = matrix.length - 1;
        int left = matrix[0][0], right = matrix[n][n];
        while(left < right){
            int mid = left + (right - left) / 2;
            int count = countNotMoreThanMid(matrix, mid, n);
            if(count < k)
                left = mid + 1;
            else
                right = mid;
        }
        return left;
    }

    private int countNotMoreThanMid(int[][] matrix, int mid, int n){
        int count = 0;
        int x = 0, y = n;
        while(x <= n && y >= 0){
            if(matrix[y][x] <= mid){
                count += y + 1;
                x++;
            }else{
                y--;
            }
        }
        return count;
    }

}
