import java.util.*;
public class _1007_Minimum_Domino_Rotations_For_Equal_Row {
    public int minDominoRotations(int[] A, int[] B) {
        int flag1 = A[0], flag2 = B[0];
        int[][] DP = new int[2][2];
        for(int i=0; i<A.length; i++){
            if(flag1>0 && A[i]!=flag1 && B[i]!=flag1) flag1 = -1;
            if(flag2>0 && A[i]!=flag2 && B[i]!=flag2) flag2 = -1;
            if(flag1<0 && flag2<0) return -1;
            if(flag1 > 0){
                if(A[i]!=flag1) DP[0][0] += 1;
                if(B[i]!=flag1) DP[0][1] += 1;
            }
            if(flag2 > 0){
                if(A[i]!=flag2) DP[1][0] += 1;
                if(B[i]!=flag2) DP[1][1] += 1;
            }
        }
        int res = Integer.MAX_VALUE;
        if(flag1>0) {
            res = Math.min(DP[0][0],DP[0][1]);
        }
        if(flag2>0){
            res = Math.min(res, Math.min(DP[1][0],DP[1][1]));
        }
        return res;
    }
}
