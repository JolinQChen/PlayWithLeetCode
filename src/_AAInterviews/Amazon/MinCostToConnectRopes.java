package _AAInterviews.Amazon;

import com.sun.tools.classfile.ConstantPool;
import javafx.scene.layout.Priority;

import java.util.*;
public class MinCostToConnectRopes {
    public static int connectSticks(int[] sticks) {
        // 必须用pq，不能硬乘，可能两个相加结果还没有另一个长，所以必须重新排列
        if(sticks.length<=1) return 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int stick:sticks) pq.add(stick);
        int res = 0;
        while (pq.size()>1) {
            int stick1 = pq.poll();
            int stick2 = pq.poll();
            res += stick1+stick2;
            pq.add(stick1+stick2);

        }
        return res;
    }
    public static void main(String[] args) {
        int[] ropes = {1, 2, 5, 10, 35, 89};
        int res = connectSticks(ropes);
        System.out.println(res);
    }
}
