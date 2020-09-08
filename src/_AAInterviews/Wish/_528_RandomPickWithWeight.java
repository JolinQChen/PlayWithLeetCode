package _AAInterviews.Wish;
import java.util.*;
public class _528_RandomPickWithWeight {
    class Solution {

        int total;
        int[] weight;
        public Solution(int[] w) {
            this.weight = w;
            for(int ww:w) total+=ww;
        }

        public int pickIndex() {
            Random rand = new Random();
            int cur = rand.nextInt(total)+1;

            int pos = 0;
            while (cur>0) {
                if(weight[pos]>=cur) return pos;
                cur -= weight[pos];
                pos++;
            }
            return -1;
        }
    }
}
