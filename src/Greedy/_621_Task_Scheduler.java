package Greedy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Given a char array representing tasks CPU need to do. It contains capital letters A to Z
 * where different letters represent different tasks. Tasks could be done without original order.
 * Each task could be done in one interval. For each interval, CPU could finish one task or just be idle.
 *
 * However, there is a non-negative cooling interval n that means between two same tasks, there must be at
 * least n intervals that CPU are doing different tasks or just be idle.
 *
 * You need to return the least number of intervals the CPU will take to finish all the given tasks.
 *
 * Input: tasks = ["A","A","A","B","B","B"], n = 2
 * Output: 8
 * Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.
 * */

public class _621_Task_Scheduler {
    /**
     * 方法一：排序
     * 由于相同的任务之间必须有 n 的冷却时间，所以我们可以想到按照任务的数量来安排它们，即一种任务的出现次数越多，
     * 我们就越早地安排。例如有 5 种任务 A, B, C, D, E，且它们分别有 6, 1, 1, 1, 1 个时，假设冷却时间 n = 2，
     * 那么我们首先安排任务 A，随后在 2 单位的冷却时间里，我们安排任务 B, C，随后继续安排任务 A，再安排任务 D, E，以此类推。
     *
     * 因此我们得到了一种安排的方法：我们规定 n + 1 个任务为一轮，这样的好处是同一轮中一个任务最多只能被安排一次。
     * 在每一轮中，我们将当前的任务按照它们剩余的次数降序排序，并选择剩余次数最多的 n + 1 个任务依次执行。
     * 如果任务的种类 t 少于 n + 1 个，就只选择全部的 t 种任务，其余的时间空闲。这样做的正确性在于，由于冷却时间的存在，
     * 出现次数较多的那些任务如果不尽早安排，将会导致大量空闲时间的出现，因此贪心地将出现次数较多的任务安排在前面是合理的。
     * 同时我们可以保证，这一轮的第 k 个任务距离上一次执行至少有 n 个单位的冷却时间。我们可以使用逆向思维来证明：
     * 假设第 r 轮中某个任务在第 k 个执行，那么说明它在第 r 轮时为数量第 k 多的任务。在第 r 轮结束后，第 1 多到第 k 多
     * 的任务的数量都会减少 1，因此在第 r + 1 轮，这个任务最多也只能是数量第 k 多，因此它如果被执行，一定满足冷却时间的要求。
     *
     * 根据上面的安排方法，我们每一轮选择不超过 n + 1 个任务执行，直到所有的任务被执行。
     *
     * 可以用优先队列表示
     * */
    public static int leastInterval(char[] tasks, int n) {
        int[] tasks_remain = new int[26];
        for(char t:tasks){
            tasks_remain[(int)t-'A']++;
            System.out.println("tasks[t-'A']: " + tasks_remain[t-'A']);
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>(
                new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        return o2-o1;
                    }
                }
        );
        int res = 0;
        for(int i:tasks_remain){
            if(i>0) queue.add(i);
        }

        while (!queue.isEmpty()) {
            int count = 0;
            List<Integer> tmp = new ArrayList<>();
            while(count<=n){
                //n+1为一组
                if(!queue.isEmpty()) {
                    if(queue.peek()>1) tmp.add(queue.poll()-1);
                    else queue.poll();
                }
                res++;
                if(queue.isEmpty() && tmp.size()==0) break;
                count++;

            }
            for(int t:tmp){
                if(t!=0) queue.add(t);
            }
        }
        return res;
    }
    public static void main(String[] args){
        String str = "AAABBB";
        char[] test = str.toCharArray();
        int res = leastInterval(test,2);
        System.out.println(res);
    }
}
