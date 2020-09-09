package _AAInterviews.Wish;
import java.util.*;
public class RandomPickWithWeight {
    class Solution {

//        int total;
//        int[] weight;
        int[] accumulateWeight;
        public Solution(int[] w) {
            int idx = 0;
            accumulateWeight = new int[w.length];
            accumulateWeight[0] = w[0];
            for(int i=1; i<w.length; i++) {
                accumulateWeight[i]=accumulateWeight[i-1]+w[i];
            }
        }

        public int pickIndex() {
            Random rand = new Random();
            int cur = rand.nextInt(accumulateWeight[accumulateWeight.length-1])+1;

            int pos = 0;
            // binary search find the insert place
            int left = 0;
            int right = accumulateWeight.length;
            while (left<right) {
                int mid = left + (right-left)/2;
                if(accumulateWeight[mid] == cur) return mid;
                if(accumulateWeight[mid]>cur) {
                    if(mid==left || accumulateWeight[mid-1]<cur) return mid;
                    else right = mid-1;
                }
                else {
                    left = mid+1;
                }
            }
            return left;

//            while (cur>0) {
//                if(weight[pos]>=cur) return pos;
//                cur -= weight[pos];
//                pos++;
//            }
        }
    }
}
