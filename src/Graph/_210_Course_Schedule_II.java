package Graph;

import java.util.*;

/**
 * 和I比起来，这里需要返回修课的顺序
 * There are a total of n courses you have to take, labeled from 0 to n-1.
 *
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1,
 * which is expressed as a pair: [0,1]
 *
 * Given the total number of courses and a list of prerequisite pairs, return the ordering of courses
 * you should take to finish all courses.
 *
 * There may be multiple correct orders, you just need to return one of them. If it is impossible to
 * finish all courses, return an empty array.
 * */


public class _210_Course_Schedule_II {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] res = new int[numCourses];
        Queue<Integer> queue = new LinkedList<>();
        int[] inDegree = new int[numCourses];
        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int[] couple:prerequisites){
            inDegree[couple[0]]++;
            List<Integer> list = map.getOrDefault(couple[1],new LinkedList<>());
            list.add(couple[0]);
            map.put(couple[1],list);
        }
        for(int i=0; i<numCourses; i++){
            if(inDegree[i]==0) queue.add(i);
        }
        int index = 0;
        while (!queue.isEmpty()){
            int cur = queue.poll();
            res[index++] = cur;
            numCourses--;
            if(!map.containsKey(cur)) continue;
            for(int next:map.get(cur)){
                inDegree[next]--;
                if(inDegree[next]==0) queue.add(next);
            }
        }
        if(numCourses==0) return res;
        else return new int[0];
    }
}
