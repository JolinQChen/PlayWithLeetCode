package DFS_BFS_BackTracking;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class _752_Open_the_Lock {
    public int openLock(String[] deadends, String target) {

        int steps = 0;
        Set<String> set = new HashSet<>();
        Set<String> visited = new HashSet<>();
        for(String deadend:deadends) set.add(deadend);
        if(set.contains(target)|| set.contains("0000")) return -1;
        if(target.equals("0000")) return 0;

        //bfs
        Queue<String> queue = new LinkedList<>();
        queue.add("0000");
        visited.add("0000");
        while (!queue.isEmpty()) {

            int size = queue.size();
            System.out.println("size :" + size + " , steps : " + steps);
            while (size > 0) {
                size--;
                String cur = queue.poll();

                String[] cur_next = getNext(cur);
                for(String next:cur_next) {
                    if(target.equals(next)) {
                        return steps+1;
                    }
                    else if(!set.contains(next) && !visited.contains(next)) {
                        queue.add(next);
                        visited.add(next);
                        //System.out.println(next);
                    }
                }
            }
            System.out.println();
            steps++;
        }
        return -1;
    }

    private String[] getNext(String str) {
        String[] res = new String[8];
        int p = 0;
        for(int i=0; i<4; i++) {
            char tmp = str.charAt(i);
            if(tmp == '9') tmp = '0';
            else tmp += 1;
            String cur = str.substring(0,i) + String.valueOf(tmp) + str.substring(i+1,4);
            res[p++] = cur;
            char tmp2 = str.charAt(i);
            if(tmp2 == '0') tmp2 = '9';
            else tmp2 -= 1;
            res[p++] = str.substring(0,i) + String.valueOf(tmp2) + str.substring(i+1,4);
        }
        return res;
    }

    public static void main(String[] args) {
        _752_Open_the_Lock test = new _752_Open_the_Lock();
//        String[] res = test.getNext("0090");
//        for(String str:res) System.out.println(str);
        String[] deadends = {"0201","0101","0102","1212","2002"};
        String target = "0202";
        int res = test.openLock(deadends,target);
        System.out.println(res);
    }
}
