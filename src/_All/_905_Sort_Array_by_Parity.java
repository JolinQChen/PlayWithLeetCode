package _All;

//有点像quick sort的partition思路
public class _905_Sort_Array_by_Parity {
    public int[] sortArrayByParity(int[] A) {
        int b=0;
        int len = A.length;
        while(b<len && A[b]%2==0) b++;
        if(b>=len-1) return A;
        int p = b+1;
        while(p<len) {
            if(A[p]%2==0) {
                swap(A, p, b);
                b++;
            }
            p++;
        }
        return A;
    }
    private void swap(int[] A, int a, int b) {
        int tmp = A[a];
        A[a] = A[b];
        A[b] = tmp;
    }
}
