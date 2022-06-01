package _AAInterviews.Google;
import org.junit.Test;

import java.text.DecimalFormat;
import java.util.*;
public class Rearrange {
    class Node {
        int elementID;
        int stepsToeLiminate; // -1 means no eliminate

        public Node(int elementID, int stepsToeLiminate) {
            this.elementID = elementID;
            this.stepsToeLiminate = stepsToeLiminate;
        }
    }
    public int totalSteps(int[] nums) {
        int N = nums.length;
        Stack<Node> stack = new Stack<>();
        stack.add(new Node(0,-1));
        int res = -1;
        for(int i=1; i<N; i++) {
            int step = 1;
            while (!stack.isEmpty()) {
                if(nums[stack.peek().elementID] <= nums[i]) {
                    step = Math.max(step, stack.peek().stepsToeLiminate+1);
                    stack.pop();
                }
                else {
                    break;
                }
            }
            if(stack.isEmpty()) step = -1;
            res = Math.max(res, step);
            stack.add(new Node(i, step));
        }
        return res<0 ? 0:res;
    }

    @Test
    public void test(){
        int[] nums = new int[]{5,14,15,2,11,5,13,15};
        System.out.println(totalSteps(nums));
    }
}
