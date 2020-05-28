package Citrix;
import java.util.*;

/**
 *
 *
 * */
public class ModifyAnArray {
    public static int minCost(int[] arr){
        return Math.min(costNonDecrease(arr), costNonIncrease(arr));
    }

    private static int costNonDecrease(int[] arr){
        int sum = 0, dif = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int n = arr.length;
        for(int i=0; i<n; i++){
            if(!pq.isEmpty() && pq.peek()>arr[i]){
                dif = pq.poll() - arr[i];
                sum += dif;
                pq.add(arr[i]);
            }
            pq.add(arr[i]);
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
}
