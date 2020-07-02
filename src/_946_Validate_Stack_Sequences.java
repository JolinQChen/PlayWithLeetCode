import java.util.*;
public class _946_Validate_Stack_Sequences {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int pointer_pop = 0;
        int pointer_push = 0;
        Stack<Integer> stack = new Stack<>();
        while (pointer_push!=pushed.length){
            stack.push(pushed[pointer_push]);
            while (!stack.isEmpty() && stack.peek()==popped[pointer_pop]){
                stack.pop();
                pointer_pop++;
            }
            pointer_push++;
        }
        return stack.isEmpty();
    }
}
