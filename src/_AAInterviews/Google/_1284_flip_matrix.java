package _AAInterviews.Google;
import org.junit.Test;

import java.util.*;

public class _1284_flip_matrix {
    class MatState {
        int[][] val;

        public MatState(int[][] input) {
            this.val = input;
        }

        @Override
        public int hashCode() {
            int res = 0;
            for(int i=0 ; i<val.length; i++) {
                for(int j=0; j<val[0].length; j++) {
                    if(val[i][j] == 0) res += 13 * (i*val[0].length + j);
                    if(val[i][j] == 1) res += 107 * (i*val[0].length + j);
                }

            }
            return res;
        }

        @Override
        public boolean equals(Object other) {
            if(! (other instanceof MatState)) return false;
            MatState ms = (MatState) other;
            if(ms.val.length != this.val.length || ms.val[0].length != this.val[0].length) return false;
            for(int i=0; i<ms.val.length; i++) {
                for(int j=0; j<ms.val[0].length; j++) {
                    if(ms.val[i][j] != this.val[i][j]) {
                        return false;
                    }
                }
            }
            return true;

        }

    }


    // private Map<MatState> dpMap;
    private int[] dc = {1,-1,0,0};
    private int[] dr = {0,0,1,-1};
    private Set<MatState> visited;

    public int minFlips(int[][] mat) {
        MatState origin = new MatState(mat);
        if(isFinal(origin)) return 0;
        // dpMap = new HashMap<>();
        visited = new HashSet<>();
        Queue<MatState> queue = new LinkedList<>();
        queue.add(origin);
        visited.add(origin);
        int count = 0;
        while(!queue.isEmpty()) {
            count++;
            int size = queue.size();
            while(size>0) {
                size--;
                MatState curState = queue.poll();
                for(int i=0; i<mat.length; i++) {
                    for(int j=0; j<mat[0].length; j++) {
                        MatState nextState = nextState(curState, new int[]{i,j});
                        if(isFinal(nextState)) return count;
                        if(!visited.contains(nextState)) {
                            visited.add(nextState);
                            queue.add(nextState);
                        }
                    }
                }

            }
        }
        return -1;

    }

    private boolean isFinal(MatState state){
        int R = state.val.length;
        int C = state.val[0].length;
        for(int i=0; i<R; i++) {
            for(int j=0; j<C; j++) {
                if(state.val[i][j] != 0) return false;
            }
        }
        return true;
    }

    private MatState nextState(MatState state, int[] pos) {
        int[][] next = new int[state.val.length][state.val[0].length];
        for(int i=0; i<state.val.length; i++) {
            for(int j=0; j<state.val[0].length; j++) next[i][j] = state.val[i][j];
        }
        next[pos[0]][pos[1]] = 1 - state.val[pos[0]][pos[1]];
        for(int i=0; i<4; i++) {
            if(pos[0]+dr[i]>=0 && pos[0]+dr[i]<state.val.length && pos[1]+dc[i]>=0 && pos[1]+dc[i]<state.val[0].length) {
                next[pos[0]+dr[i]][pos[1]+dc[i]] = 1 - state.val[pos[0]+dr[i]][pos[1]+dc[i]];
            }
        }
        return new MatState(next);

    }

    @Test
    public void test(){
        int[][] testMat = new int[2][2];
        testMat[0] = new int[]{0,0};
        testMat[1] = new int[]{0,1};
        System.out.println(minFlips(testMat));
    }
}
