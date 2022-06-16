package _AAInterviews.Verkada;

import javafx.util.Pair;

import java.util.*;

public class SnapshotArray {

    TreeMap<Integer, Integer>[] arr;
    int curId;
    public SnapshotArray(int length) {
        arr = new TreeMap[length];
        for(int i=0; i<length; i++) arr[i] = new TreeMap<>();
        curId = 0;
    }

    public void set(int index, int val) {
        arr[index].put(curId, val);
    }

    public int snap() {
        return curId++;
    }

    public int get(int index, int snap_id) {
        TreeMap<Integer, Integer> map = arr[index];
        // get the max id that's smaller that snapId
        return map.lowerKey(snap_id);
    }
}
