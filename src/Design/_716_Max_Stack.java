package Design;
import java.util.*;
public class _716_Max_Stack {
    class MaxStack {

        /** initialize your data structure here. */
        private Stack<Integer> stack;
        private Stack<Integer> maxStack;
        public MaxStack() {
            stack = new Stack<>();
            maxStack = new Stack<>();
        }

        public void push(int x) {
            if(maxStack.isEmpty()) maxStack.push(x);
            else{
                int max = maxStack.peek();
                if(x>max) maxStack.push(x);
                else maxStack.push(max);
            }
            stack.push(x);
        }

        public int pop() {
            maxStack.pop();
            return stack.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int peekMax() {
            return maxStack.peek();
        }

        public int popMax() {
            int res = peekMax();
            Stack<Integer> store = new Stack<>();
            while(stack.peek()!= res) store.push(pop());
            pop();
            while(!store.isEmpty()) push(store.pop());
            return res;
        }
    }

}
