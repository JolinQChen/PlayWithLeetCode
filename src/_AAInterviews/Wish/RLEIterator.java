package _AAInterviews.Wish;
/**
 * Your RLEIterator object will be instantiated and called as such:
 * RLEIterator obj = new RLEIterator(A);
 * int param_1 = obj.next(n);
 */
class RLEIterator {

    int idxA;
    int[] A;
    public RLEIterator(int[] A) {
        this.A = A;
        idxA = 0;
    }

    public int next(int n) {

//        if(A[idxA]>=n) {
//            A[idxA]-=n;
//            if(A[idxA]==0) idxA+=2;
//            return A[idxA];
//        }
        while (n>0 && idxA<A.length-1) {
            if(A[idxA]>=n) {
                A[idxA]-=n;
                n = 0;
                if(A[idxA]==0) {
                    idxA+=2;
                    return A[idxA-1];
                }
                return A[idxA+1];
            }
            else {
                n -= A[idxA];
                A[idxA]=0;
                idxA+=2;
            }
        }
        return -1;

    }
}
