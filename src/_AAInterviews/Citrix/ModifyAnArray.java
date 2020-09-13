package _AAInterviews.Citrix;
import java.util.*;

/**
 * 变成non-decreasing或non-increasing的代价（差值）
 *
 * */
public class ModifyAnArray {
    public static int minCost(int[] arr){
        return Math.min(costNonDecrease(arr), costNonIncrease(arr));
    }

    private static int costNonDecrease(int[] arr){
//        int sum = 0, dif = 0;
//        PriorityQueue<Integer> pq = new PriorityQueue<>();
//        int n = arr.length;
//        for(int i=0; i<n; i++){
//            if(!pq.isEmpty() && pq.peek()>arr[i]){
//                dif = pq.poll() - arr[i];
//                sum += dif;
//                pq.add(arr[i]);
//            }
//            pq.add(arr[i]);
//        }
//        return sum;
        int sum = 0;
        int pre = arr[0];
        for(int i=1; i<arr.length; i++) {
            if(arr[i]<pre) {
                sum += (pre-arr[i]);
                arr[i] = pre;
            }
            pre = arr[i];

        }
        return sum;
    }
    private static int costNonIncrease(int[] a){
        //不增
        int sum = 0, dif = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
/** Here in the loop we will check that whether the upcoming element
 * of array is less than top of priority queue.
 * If yes then we calculate the difference.
 * After that we will remove that element and push the current element in queue.
 * And the sum is incremented by the value of difference
 */
        int n = a.length;
        for (int i = 0; i < n; i++)
        {
            if (!pq.isEmpty() && pq.peek() < a[i])
            {
                dif = a[i] - pq.peek();
                sum += dif;
                pq.remove();
                pq.add(a[i]);
            }
            pq.add(a[i]);
        }

        return sum;
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,2};
        System.out.println(minCost(arr));
    }
}
