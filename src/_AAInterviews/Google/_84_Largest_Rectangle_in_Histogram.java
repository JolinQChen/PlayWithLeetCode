package _AAInterviews.Google;
import org.junit.Test;

import java.util.*;
public class _84_Largest_Rectangle_in_Histogram {
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int[] dpFront = new int[heights.length];
        int max = heights[0];
        int size = heights.length;
        for(int i=0; i<size; i++) {
            while (stack.size()>0 && heights[stack.peek()] > heights[i]) {
                int pre = stack.pop();
                max = Math.max(max, heights[pre] * (i-pre) + dpFront[pre] - heights[pre]);
            }
            dpFront[i] = stack.size()==0?heights[i]*(i+1) : heights[i] * (i-stack.peek());
            stack.push(i);
        }

        while (stack.size()>0) {
            int pre = stack.pop();
            max = Math.max(max, heights[pre]*(size-pre)+ dpFront[pre] - heights[pre]);
        }

        return max;
    }

    @Test
    public void test(){
        int[] height = {2,1,2};
        System.out.println(largestRectangleArea(height));
    }
}
