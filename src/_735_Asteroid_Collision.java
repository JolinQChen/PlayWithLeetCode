import sun.awt.image.ImageWatched;

import java.util.*;

/**
 * We are given an array asteroids of integers representing asteroids in a row.
 *
 * For each asteroid, the absolute value represents its size, and the sign
 * represents its direction (positive meaning right, negative meaning left).
 * Each asteroid moves at the same speed.
 *
 * Find out the state of the asteroids after all collisions. If two asteroids
 * meet, the smaller one will explode. If both are the same size, both will
 * explode. Two asteroids moving in the same direction will never meet.
 *
 * Input:
 * asteroids = [5, 10, -5]
 * Output: [5, 10]
 * Explanation:
 * The 10 and -5 collide resulting in 10.  The 5 and 10 never collide.
 * */

public class _735_Asteroid_Collision {
    public int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> deque = new LinkedList<>();
        for(int cur:asteroids){
            if(deque.isEmpty()) deque.addLast(cur);
            else{
                if(!isCollide(deque.getLast(), cur)) {
                    //不会撞
                    deque.addLast(cur);
                }
                else{
                    //撞
                    while(!deque.isEmpty() && isCollide(deque.getLast(), cur) && Math.abs(cur)>Math.abs(deque.getLast())){
                        deque.pollLast();
                    }
                    if(deque.isEmpty()||(!deque.isEmpty() && !isCollide(deque.getLast(), cur))) deque.addLast(cur);
                    else if(Math.abs(cur)==Math.abs(deque.getLast())&& isCollide(deque.getLast(), cur)) deque.pollLast();
                }
            }
        }
        if (deque.isEmpty()) return new int[0];
        int[] res = new int[deque.size()];
        int index = 0;
        while(!deque.isEmpty()) res[index++] = deque.pollFirst();
        return res;

    }
    private boolean isCollide(int a, int b){
        if(a>0 && b<0) return true;
        return false;
    }
}
