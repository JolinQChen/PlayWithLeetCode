package Array_String_TwoPointers_SlidingWindow_Greedy.slidingWindow;

import java.util.ArrayList;
import java.util.List;

public class _1052_Grumpy_Bookstore_Owner {
    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        List<Integer> grumpyTime = new ArrayList<>();
        int len = grumpy.length;
        int sum = 0;
        //corner case
        if(len<=X){
            for(int i=0; i<len; i++) sum += customers[i];
            return sum;
        }
        for(int i=0; i<len; i++){
            if(grumpy[i]==0) sum += customers[i];
            else grumpyTime.add(i);
        }

        int curAdd=0;
        int maxAdd=0;
        int left = 0;
        int right = 0;
        int grumpyLen = grumpyTime.size();
        while(left<=right && right<grumpyLen){
            while(right<grumpyLen && grumpyTime.get(right)-grumpyTime.get(left)+1<=X) {
                curAdd += customers[grumpyTime.get(right)];
                right++;
            }
            //得到下一个位置
            maxAdd = Math.max(curAdd,maxAdd);
            curAdd -= customers[grumpyTime.get(left)];
            left++;

        }
        return sum + maxAdd;
    }
}
