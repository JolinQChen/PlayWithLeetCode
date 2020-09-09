package _AAInterviews.Wish;
import java.util.*;
public class FrogJump {
    public boolean canCross(int[] stones) {
        for(int i=3; i<stones.length; i++) {
            if(stones[i]>stones[i-1]*2) return false;
        }

        Set<Integer> stonePositions = new HashSet<>();
        for(int p:stones) stonePositions.add(p);
        Stack<Integer> positions = new Stack<>();
        Stack<Integer> jumpDist = new Stack<>();
        positions.push(0);
        jumpDist.push(0);
        while(!positions.isEmpty()) {
            int pos = positions.pop();
            int jump = jumpDist.pop();
            for(int i=jump-1; i<=jump+1; i++) {
                if(i<=0) continue;
                int nextPos = pos+i;
                if(nextPos == stones[stones.length-1]) return true;
                if(stonePositions.contains(nextPos)){
                    positions.push(nextPos);
                    jumpDist.push(i);
                }
            }
        }
        return false;

    }
}
