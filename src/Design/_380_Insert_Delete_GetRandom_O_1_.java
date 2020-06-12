package Design;
import java.util.*;
public class _380_Insert_Delete_GetRandom_O_1_ {
    class RandomizedSet {

        Map<Integer,Integer> innerMap;
        List<Integer> list;
        /** Initialize your data structure here. */
        public RandomizedSet() {
            innerMap = new HashMap<>();
            list = new ArrayList<>();
        }

        /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
        public boolean insert(int val) {
            if(innerMap.containsKey(val)) return false;
            innerMap.put(val, list.size());
            list.add(val);
            return true;
        }

        /** Removes a value from the set. Returns true if the set contained the specified element. */
        public boolean remove(int val) {
            if(!innerMap.containsKey(val)) return false;
            else {

                list.set(innerMap.remove(val), Integer.MIN_VALUE);
                return true;
            }
        }

        /** Get a random element from the set. */
        public int getRandom() {
            Random rm = new Random();
            int i = rm.nextInt(list.size());
            while(list.get(i) == Integer.MIN_VALUE){
                i = rm.nextInt(list.size());
            }

            return list.get(i);
        }
    }

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
}
