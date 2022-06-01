package _AAInterviews.Google;

import java.util.*;

public class _1631_Path_With_Minimum_Effort {
//    class State {
//        int pos;
//        int value;
//        public State(){}
//        public State(int pos, int value) {
//            this.pos = pos;
//            this.value = value;
//        }
//
//        @Override
//        public boolean equals(Object obj) {
//            if(! (obj instanceof  State)) return false;
//            State state = (State) obj;
//            return state.value==this.value && state.pos == this.pos;
//        }
//    }
    int R;
    int C;
    int[] dr = {0,0,1,-1};
    int[] dc = {1,-1,0,0};
    public int minimumEffortPath(int[][] heights) {
        // A route's effort is the maximum absolute difference in heights
        // between two consecutive cells of the route.
        R = heights.length;
        C = heights[0].length;
        int res = Integer.MAX_VALUE;
        if(R==C && R==1) return 0;
//        State start = new State(0,0);
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
//        Set<State> visited = new HashSet<>();
//        visited.add(start);
        int[][] dp = new int[heights.length][heights[0].length];
        boolean[] visitedPos = new boolean[R*C];
        visitedPos[0] = true;

        while (!queue.isEmpty()) {
            int curPos = queue.poll();
            int[] coor = getCoordinate(curPos);
            for(int i=0; i<4; i++) {
                if(coor[0]+dr[i]>=0 && coor[0]+dr[i]<R && coor[1]+dc[i]>=0 && coor[1]+dc[i]<C) {
                    int val =Math.max(Math.abs(heights[coor[0]+dr[i]][coor[1]+dc[i]] - heights[coor[0]][coor[1]]), dp[coor[0]][coor[1]]);
                    if(coor[0]+dr[i]==R-1 && coor[1]+dc[i]==C-1) res = Math.min(res, val);
//                    State newState = new State(getPos(coor[0]+dr[i], coor[1]+dc[i]), val);
//                    if(!visited.contains(newState)) {
//                        visited.add(newState);
//                        if(!visitedPos[newState.pos] || dp[coor[0] + dr[i]][coor[1] + dc[i]] > val) {
//                            dp[coor[0] + dr[i]][coor[1] + dc[i]] = val;
//                            queue.add(getPos(coor[0]+dr[i], coor[1]+dc[i]));
//                            visitedPos[]
//                        }
//                    }
                        if(!visitedPos[getPos(coor[0]+dr[i], coor[1]+dc[i])] || dp[coor[0] + dr[i]][coor[1] + dc[i]] > val) {
                            dp[coor[0] + dr[i]][coor[1] + dc[i]] = val;
                            queue.add(getPos(coor[0]+dr[i], coor[1]+dc[i]));
                            visitedPos[getPos(coor[0]+dr[i], coor[1]+dc[i])] = true;
                        }
                }
            }
        }
        return res;
    }

    private int[] getCoordinate(int pos){
        return new int[]{pos/C, pos%C};
    }
    private int getPos(int x, int y) {
        return x*C+y;
    }
}
