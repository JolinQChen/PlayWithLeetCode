package _AAInterviews.Wish;

import java.util.Stack;

public class MinStack {
    /** initialize your data structure here. */
    private Stack<Integer> min;
    private Stack<Integer> store;
    public MinStack() {
        min = new Stack<>();
        store = new Stack<>();
    }

    public void push(int x) {
        store.push(x);
        if(min.isEmpty()) min.push(x);
        else {
            int tmp = min.peek();
            min.push(Math.min(x,tmp));
        }
    }

    public void pop() {
        store.pop();
        min.pop();
    }

    public int top() {
        return store.peek();
    }

    public int getMin() {
        return min.peek();
    }
}
