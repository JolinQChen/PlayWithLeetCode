package _AAInterviews.weride;

import java.util.*;

public class _1293_Shortest_Path {
    class State {
        int remainEliminate;
        int step;
        int r;
        int c;
        int manhattan;

        public State(int remainEliminate,int step, int r, int c, int manhattan) {
            this.remainEliminate = remainEliminate;
            this.step = step;
            this.r = r;
            this.c = c;
            this.manhattan = manhattan;
        }

        @Override
        public boolean equals(Object obj){
            if(!(obj instanceof State)) return false;
            State ns = (State) obj;
            if(ns.remainEliminate == this.remainEliminate && ns.r==this.r && ns.c==this.c) return true;
            return false;
        }

        @Override
        public int hashCode() {
            // needed when we put objects into any container class
            return (this.r + 1) * (this.c + 1) * this.remainEliminate;
        }
    }

    int[] dr = {0,0,1,-1};
    int[] dc = {1,-1,0,0};
    int R;
    int C;
    Set<State> visited;
    public int shortestPath(int[][] grid, int k) {
        visited = new HashSet<>();
        R = grid.length;
        C = grid[0].length;
        if(R==1 && C==1) return 0;
        PriorityQueue<State> pq = new PriorityQueue<>((o1,o2)->(o1.manhattan+o1.step-(o2.manhattan+o2.step)));
        State init = new State(k,0,0,0, R+C-2);
        pq.add(init);
        visited.add(init);
        int step = 0;
        // A*

        while(!pq.isEmpty()) {
            State curState = pq.poll();
            // System.out.println("r: "+curState.r+", c"+curState.c+", remainEliminate"+curState.remainEliminate+", manhattan:" +curState.manhattan+", total step:"+curState.step);
            if(curState.r==R-1 && curState.c==C-1) return curState.step;
            if(curState.manhattan<=curState.remainEliminate) return curState.step + curState.manhattan;
            for(int i=0; i<4; i++) {
                if(curState.r+dr[i]>=0 && curState.c+dc[i]>=0 && curState.r+dr[i]<R && curState.c+dc[i]<C) {
                    int nr = curState.r+dr[i];
                    int nc = curState.c+dc[i];
                    State ns;
                    if(grid[nr][nc]==0) {
                        ns = new State(curState.remainEliminate, curState.step+1,  nr, nc, countManhattan(nr,nc));
                    } else {
                        ns = new State(curState.remainEliminate-1, curState.step+1, nr, nc, countManhattan(nr,nc));
                    }
                    if(ns.remainEliminate>=0 && !visited.contains(ns)) {
                        if(ns.manhattan==0) return ns.step;
                        visited.add(ns);
                        pq.add(ns);
                    }
                }
            }
        }
        return -1;
    }

    private int countManhattan(int r, int c){
        return R-r+C-c-2;
    }
}
