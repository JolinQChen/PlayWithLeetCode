package Array_String_TwoPointers_SlidingWindow_Greedy.Greedy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class _1564_Put_Boxes_Into_the_Warehouse_I {
    // 超时
    public static int maxBoxesInWarehouse(int[] boxes, int[] warehouse) {
        Arrays.sort(boxes);
        if(warehouse[0]<boxes[0]) return 0;
        int len = warehouse.length;

        int[] house = new int[len];
        house[0] = warehouse[0];
        for(int i=1; i<len; i++) {
            house[i] = Math.min(house[i-1],warehouse[i]);
        }

        // find house start cell
        int start = 0;
        while(start<len && house[start]>=boxes[0]) start++;
        start = start-1;
//        System.out.println(start);
        Set<Integer> set = new HashSet<>(); // placed boxes idx
        int res = 0;

        for(int i=start; i>=0; i--) {
            int idx = binaryFindMaxMin(boxes, house[i], 0, boxes.length-1);
            if(idx>=0) {
                while (idx>=0 && set.contains(idx)) {
                    idx--;
                }
                if(idx>=0) {
                    set.add(idx);
//                    System.out.println("idx: "+idx +", i: "+i );
                    res++;
                }
            }
        }
        return res;
    }

    private static int binaryFindMaxMin(int[] boxes, int height, int left, int right) {
        if(left>right) return right;
        int mid = left+(right-left)/2;
        if(boxes[mid]==height) {
            while(mid<boxes.length && boxes[mid]==height) mid++;
            return mid-1;
        }
        else {
            if(boxes[mid]>height) return binaryFindMaxMin(boxes, height, left, mid-1);
            else return binaryFindMaxMin(boxes, height, mid+1, right);
        }
    }


    // beat 100%
    public int maxBoxesInWarehouse2(int[] boxes, int[] warehouse) {
        int minHere = Integer.MAX_VALUE;
        int[] realSpace = new int[warehouse.length];
        for (int i = 0; i < warehouse.length; i++) {
            minHere = Math.min(minHere, warehouse[i]);
            realSpace[i] = minHere;
        }
        Arrays.sort(boxes);

        /** 从这里开始不同的，前一种方法太冗余了，根本不需要2分法，只是箱子数量而已，没必要塞满*/
        int i = 0;
        int j = realSpace.length - 1;
        int result = 0;
        while (i < boxes.length && j >= 0) {
            if (realSpace[j--] >= boxes[i]) {
                i++;
                result++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] boxes = {4,3,4,1};
        int[] warehouse = {5,3,3,4,1};
        int res = maxBoxesInWarehouse(boxes, warehouse);
        System.out.println(res);
    }
}
