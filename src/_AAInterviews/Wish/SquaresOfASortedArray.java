package _AAInterviews.Wish;

public class SquaresOfASortedArray {
    public int[] sortedSquares(int[] A) {
        int len = A.length;
        int[] res = new int[len];

        int p = 0;
        // find the first place where the element is not negative
        while (p<len && A[p]<0)p++;
        int idx = 0;
        int n = p-1;
        while (n>=0 && p<len) {
            if(-A[n]<A[p]) {
                res[idx++] = A[n]*A[n];
                n--;
            }
            else {
                res[idx++] = A[p]*A[p];
                p++;
            }
        }
        while(n>=0) {
            res[idx++] = A[n] * A[n];
            n--;
        }
        while(p<len) {
            res[idx++] = A[p] * A[p];
            p++;
        }
        return res;
    }
}
