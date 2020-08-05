package _All;
import java.util.*;
/**
 * complete at most k transactions.
 * */
public class _188_Best_Time_to_Buy_and_Sell_Stock_IV {
    // Merge
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;

        // solve special cases
        if (n <= 1 || k <= 0) {
            return 0;
        }

        // find all consecutively increasing subsequence

        ArrayList<int[]> transactions = new ArrayList<>();
        int idx = 0;
        while(idx<n) {

            while(idx<n-1 && prices[idx]>=prices[idx+1]) idx++;
            int start = idx;
            while(idx<n-1 && prices[idx]<=prices[idx+1]) idx++;
            int end = idx;
            if(start<end) transactions.add(new int[]{start,end});
            idx++;
        }


        while (transactions.size() > k) {
            // check delete loss
            int delete_index = 0;
            int min_delete_loss = Integer.MAX_VALUE;
            for (int i = 0; i < transactions.size(); i++) {
                int[] t = transactions.get(i);
                int profit_loss = prices[t[1]] - prices[t[0]];
                // find least delete index and loss
                if (profit_loss < min_delete_loss) {
                    min_delete_loss = profit_loss;
                    delete_index = i;
                }
            }

            // check merge loss
            int merge_index = 0;
            int min_merge_loss = Integer.MAX_VALUE;
            for (int i = 1; i < transactions.size(); i++) {
                int[] t1 = transactions.get(i - 1);
                int[] t2 = transactions.get(i);
                int profit_loss = prices[t1[1]] - prices[t2[0]];
                if (profit_loss < min_merge_loss) {
                    min_merge_loss = profit_loss;
                    merge_index = i;
                }
            }

            // delete or merge
            if (min_delete_loss <= min_merge_loss) {
                transactions.remove(delete_index);
            } else {
                int[] t1 = transactions.get(merge_index - 1);
                int[] t2 = transactions.get(merge_index);
                t1[1] = t2[1];
                transactions.remove(merge_index);
            }

        }

        int res = 0;
        for (int[] t : transactions) {
            res += prices[t[1]] - prices[t[0]];
        }

        return res;
    }
}
