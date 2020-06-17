package Design;
import java.util.*;
public class _384_Shuffle_an_Array {
    class Solution {
        int[] original;
        public Solution(int[] nums) {
            original = nums;
        }

        /** Resets the array to its original configuration and return it. */
        public int[] reset() {
            return original;
        }

        /** Returns a random shuffling of the array. */
        public int[] shuffle() {
            int[] res = new int[original.length];
            List<Integer> list = new LinkedList<>();
            for(int i:original) list.add(i);
            Random rand = new Random();
            int cur = 0;
            while(list.size()>0){
                int index = rand.nextInt(list.size());
                res[cur++] = list.get(index);
                list.remove(index);
            }
            return res;
        }
    }

}
