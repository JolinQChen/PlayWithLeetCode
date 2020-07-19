package _All;

import java.util.*;


public class _1197_Minimum_Knight_Moves {
    private int[] dx = {1,1,-1,-1,2,2,-2,-2};
    private int[] dy = {2,-2,2,-2,1,-1,1,-1};
    private class Point {
        public int x;
        public int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Point point = (Point) o;
            if (x != point.x) return false;
            return y == point.y;
        }
    }
    public int minKnightMoves_bfs_brute(int x, int y) {
        //好像只有BFS可以，DFS不太行
        Point target = new Point(x,y);
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(0,0));
        int step = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            while(size>0){
                Point cur = queue.poll();
                for(int i=0; i<8; i++){
                    Point next = new Point(cur.x+dx[i], cur.y+dy[i]);
                    if(next.equals(target)) return step+1;
                    queue.add(next);
                }
                size--;
            }
            step++;
        }
        return -1;
    }


    // 对称递归
    HashMap<String, Integer> map = new HashMap<String, Integer>();
    public int minKnightMoves(int x, int y) {
        x = Math.abs(x);
        y = Math.abs(y);
        if(x + y == 0) return 0;
        if(x + y == 2) return 2;
        if(x + y == 1) return 3;
        String key = x + "#" + y;
        if(map.containsKey(key)) return map.get(key);
        int count = Math.min(minKnightMoves(x - 2, y - 1), minKnightMoves(x-1, y-2)) + 1;
        map.put(key, count);
        return count;
    }
    
}
