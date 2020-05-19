import java.util.*;
/**
 * 给定两个由一些闭区间组成的列表，每个区间列表都是成对不相交的，并且已经排序。
 *
 * 返回这两个区间列表的交集。
 *
 * （形式上，闭区间 [a, b]（其中 a <= b）表示实数 x 的集合，而 a <= x <= b。两个闭区间的交集是一组实数，
 * 要么为空集，要么为闭区间。例如，[1, 3] 和 [2, 4] 的交集为 [2, 3]。）
 *
 * 输入：A = [[0,2],[5,10],[13,23],[24,25]], B = [[1,5],[8,12],[15,24],[25,26]]
 * 输出：[[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
 * 注意：输入和所需的输出都是区间对象组成的列表，而不是数组或列表。
 *
 * */


public class _986_Interval_List_Intersections {
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        List<int[]> res = new LinkedList<>();
        int pa = 0;
        int pb = 0;
        int len_A = A.length;
        int len_B = B.length;
        while(pa<len_A && pb<len_B){
            if(A[pa][1]>=B[pb][0] && A[pa][0]<=B[pb][0]){
                //交集B前段A后段
                int[] intersection = new int[2];
                intersection[0] = B[pb][0];
                intersection[1] = Math.min(B[pb][1], A[pa][1]);
                res.add(intersection);
            }
            else if(A[pa][0]>=B[pb][0] && A[pa][0]<=B[pb][1]) {
                int[] intersection = new int[2];
                intersection[0] = A[pa][0];
                intersection[1] = Math.min(A[pa][1], B[pb][1]);
                res.add(intersection);
            }
            if(A[pa][1]<B[pb][1]) pa++;
            else if(A[pa][1]>B[pb][1]) pb++;
            else {
                pa++;
                pb++;
            }
        }
        int[][] arr = new int[res.size()][2];
        int index=0;
        for(int[] i:res){
            arr[index++] = i;
        }
        return arr;
    }
}
