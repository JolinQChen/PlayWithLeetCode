import java.util.*;

/**
 * Input:
 * n = 2
 * logs = ["0:start:0","1:start:2","1:end:5","0:end:6"]
 * Output: [3, 4]
 * Explanation:
 * Function 0 starts at the beginning of time 0, then it executes 2 units of time
 * and reaches the end of time 1.
 * Now function 1 starts at the beginning of time 2, executes 4 units of time and
 * ends at time 5.
 * Function 0 is running again at the beginning of time 6, and also ends at the
 * end of time 6, thus executing for 1 unit of time.
 * So function 0 spends 2 + 1 = 3 units of total time executing, and function 1
 * spends 4 units of total time executing.
 * */

public class _636_Exclusive_Time_of_Functions {
/*
我们可以使用栈来模拟函数的调用，即在遇到一条包含 start 的日志时，我们将对应的函数 id 入栈；
在遇到一条包含 end 的日志时，我们将对应的函数 id 出栈。在每一个时刻，栈中的所有函数均为被调
用的函数，而栈顶的函数为正在执行的函数。

在每条日志的时间戳后，栈顶的函数会独占执行，直到下一条日志的时间戳，因此我们可以根据相邻两条
日志的时间戳差值，来计算函数的独占时间。我们依次遍历所有的日志，对于第 i 条日志，如果它包含
start，那么栈顶函数从其时间戳 time[i] 开始运行，即 prev = time[i]；如果它包含 end，
那么栈顶函数从其时间戳 time[i] 的下一个时间开始运行，即 prev = time[i] + 1。对于第 i + 1 条
日志，如果它包含 start，那么在时间戳 time[i + 1] 时，有新的函数被调用，因此原来的栈顶函数的独占
时间为 time[i + 1] - prev；如果它包含 end，那么在时间戳 time[i + 1] 时，原来的栈顶函数执行结束，
独占时间为 time[i + 1] - prev + 1。在这之后，我们更新 prev 并遍历第 i + 2 条日志。在遍历结束后，
我们就可以得到所有函数的独占时间。
* */
    public int[] exclusiveTime(int n, List<String> logs) {
        int[] res = new int[n];
        if(logs == null || logs.size()==0) return res;
        int prev = 0;

        Stack<Integer> stack = new Stack<>();
        for(String str:logs){
            String[] break_str = str.split(":");
            if(break_str[1].equals("start")) {
                if(stack.isEmpty()) {
                    stack.push(Integer.parseInt(break_str[0]));
                    prev = Integer.parseInt(break_str[2]);
                }
                else {
                    int last = stack.peek();
                    int start = Integer.parseInt(break_str[2]);
                    res[last] = res[last] + start-prev;
                    stack.push(Integer.parseInt(break_str[0]));
                    prev = start;
                }
            }
            else {
                // end a func
                int last = stack.pop();
                int start = Integer.parseInt(break_str[2])+1;
                res[last] += (start-prev);
                prev = start;
            }
        }
        return res;
    }

//    public static void main(String[] args){
//        String str = "1:start:0";
//        String[] break_str = str.split(":");
//        for(int i=0; i<break_str.length;i++)
//        System.out.println(break_str[i]);
//    }


}
