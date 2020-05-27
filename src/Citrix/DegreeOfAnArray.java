package Citrix;
import java.util.*;
/**
 * input [1,2,1,3,2]
 * output [2,3]
 * */
public class DegreeOfAnArray {
    public static int[] degree(int[] arr){
        //返回res[0]是degree, res[1]是shortest subarray长度
        Map<Integer, int[]> map = new HashMap<>();
        for(int i=0; i<arr.length; i++){
            int val = arr[i];
            if(map.containsKey(val)){
                int[] tmp = map.get(val);
                tmp[0] += 1;
                tmp[2] = i;
            }
            else{
                map.put(val,new int[]{1,i,i});
            }
        }
        int[] res = new int[2];
        for(int key:map.keySet()){
            int[] tmp = map.get(key);
            if(tmp[0]>res[0]){
                res[0] = tmp[0];
                res[1] = tmp[2]-tmp[1]+1;
            }
            else if(tmp[0]==res[0]){
                res[1] = Math.min(res[1], tmp[2]-tmp[1]+1);
            }
        }
        return res;
    }
    public static void main(String[] args){
        int[] res = degree(new int[]{1,2,1,3,2});
        System.out.println(res[0] +" "+res[1]);
    }
}
