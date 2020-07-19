package _All;

public class _977_Squares_of_a_Sorted_Array {
    public int[] sortedSquares(int[] A) {
        int len = A.length;
        int[] res = new int[len];

        int p = 0;
        while(p<len && A[p]<0) p++; //find the first position that the element is not negative


        int n = p-1;
        int cur = 0;
        while(p<len && n>=0) {

            if(-A[n]<=A[p]) {
                res[cur++] = A[n] * A[n];
                n--;
            }
            else {
                res[cur++] = A[p] * A[p];
                p++;
            }
        }

        while(n>=0) {
            res[cur++] = A[n] * A[n];
            n--;
        }
        while(p<len) {
            res[cur++] = A[p] * A[p];
            p++;
        }
        return res;
    }
}
