package DFS_BFS_BackTracking;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 有 N 个房间，开始时你位于 0 号房间。每个房间有不同的号码：0，1，2，...，N-1，并且房间里可能有一些钥匙能使你进入下一个房间。
 *
 * 在形式上，对于每个房间 i 都有一个钥匙列表 rooms[i]，每个钥匙 rooms[i][j] 由 [0,1，...，N-1] 中的一个整数表示，
 * 其中 N = rooms.length。 钥匙 rooms[i][j] = v 可以打开编号为 v 的房间。
 *
 * 最初，除 0 号房间外的其余所有房间都被锁住。
 *
 * 你可以自由地在房间之间来回走动。
 *
 * 如果能进入每个房间返回 true，否则返回 false。
 *
 * Note:
 * 1 <= rooms.length <= 1000
 * 0 <= rooms[i].length <= 1000
 *
 * 输入: [[1],[2],[3],[]]
 * 输出: true
 * 解释:
 * 我们从 0 号房间开始，拿到钥匙 1。
 * 之后我们去 1 号房间，拿到钥匙 2。
 * 然后我们去 2 号房间，拿到钥匙 3。
 * 最后我们去了 3 号房间。
 * 由于我们能够进入每个房间，我们返回 true。
 *
 * 输入：[[1,3],[3,0,1],[2],[0]]
 * 输出：false
 * 解释：我们不能进入 2 号房间。
 * */


public class _841_keys_and_rooms {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        //无论如何都是从0号房间开始搜索，0永远是root，不如用BFS吧
        int N = rooms.size();
        if(N==1) return true;
        boolean[] open = new boolean[N];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0); // 将0加入queue，而且0本来也是开的
        //open[0] = true;
        while (!queue.isEmpty()){
            int cur = queue.poll();
            open[cur] = true;
            for(int next:rooms.get(cur)){
                if(!open[next]) queue.add(next);
            }
        }
        for(int i=0; i<N; i++){
            if(!open[i]) return false;
        }
        return true;
    }
}
