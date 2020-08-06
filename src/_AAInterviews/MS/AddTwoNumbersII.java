package _AAInterviews.MS;
import java.util.*;
/**
 * Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 8 -> 0 -> 7
 * 不允许reverse list
 * 开头没有0
 * */

public class AddTwoNumbersII {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1==null) return l2;
        if(l2==null) return l1;
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        Stack<Integer> stack = new Stack<>();

        while(l1!=null) {
            stack1.push(l1.val);
            l1 = l1.next;
        }
        while (l2!=null){
            stack2.push(l2.val);
            l2 = l2.next;
        }
        int carry = 0;
        while(!stack1.isEmpty() && !stack2.isEmpty()) {
            int cur = stack1.pop() + stack2.pop() + carry;
            carry = cur / 10;
            cur %= 10;
            stack.push(cur);
        }
        while (!stack1.isEmpty()) {
            int cur = stack1.pop() + carry;
            carry = cur / 10;
            cur %= 10;
            stack.push(cur);
        }

        while (!stack2.isEmpty()) {
            int cur = stack2.pop() + carry;
            carry = cur / 10;
            cur %= 10;
            stack.push(cur);
        }

        if(carry!=0) stack.push(carry);
        ListNode res = new ListNode();
        ListNode p = res;
        while(!stack.isEmpty()) {
            p.next = new ListNode(stack.pop());
            p = p.next;
        }
        return res.next;
    }
}
