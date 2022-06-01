package _AAInterviews.Google;
import org.junit.Test;

import java.util.*;
public class _818_race_car {
    class State {
        int pos;
        int speed;
        public State(int p, int s) {
            this.pos = p;
            this.speed = s;
        }

        public State() {
        }

        @Override
        public boolean equals(Object obj){
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            State state = (State) obj;
            return state.pos == this.pos && state.speed == this.speed;
        }
    }

    public int racecar(int target) {
        // BFS
        int count = 0;
        Queue<State> queue = new LinkedList<>();
        queue.add(new State(0,1));
        Set<State> visited = new HashSet<>();
        while(!queue.isEmpty()) {
            int size = queue.size();
            while(size>0) {
                size--;
                State curState = queue.poll();
                if(curState.pos<100){
                    System.out.println(curState.pos + ", "+curState.speed);
                }

                if(curState.pos == target) return count;
                //A
                State stateA = new State(curState.pos + curState.speed, curState.speed*2);
                if(stateA.pos == target) return count+1;
                if(!visited.contains(stateA)) {
                    visited.add(stateA);
                    queue.add(stateA);
                }
                //R
                if(curState.pos+curState.speed > target && curState.speed>0 || curState.pos+curState.speed<target && curState.speed<0) {
                    // must turn back
                    State stateR = new State();
                    stateR.pos = curState.pos;
                    if(curState.speed>0) {
                        stateR.speed = -1;
                    } else {
                        stateR.speed = 1;
                    }
                    if(!visited.contains(stateR)) {
                        visited.add(stateR);
                        queue.add(stateR);
                    }
                }

            }
            count++;
        }

        return -1;
    }

    @Test
    public void testRaceCar(){
        System.out.println(racecar(6));
    }
}
