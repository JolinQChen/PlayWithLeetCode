import java.util.*;
public class _1031_Maximum_Sum_of_Two_Non_Overlapping_Subarrays {
    private int[][] DP;
    public int maxSumTwoNoOverlap(int[] A, int L, int M) {
        // brute force - improved with dp
        int len = A.length;
        int res = 0;
        if(A.length<L || A.length<M) return 0;
        DP = new int[len][len];
        for(int i=0; i+L-1<len; i++){
            int tmpL = 0;
            for(int j=0; j<L; j++){
                tmpL += A[i+j];
            }
            System.out.println("start: "+i);
            System.out.println("tmpL: "+tmpL);
            int tmpM = Math.max(findMax(A,0,i-1,M), findMax(A,i+L,len-1,M));
            System.out.println("tmpM: "+tmpM);
            System.out.println();
            res = Math.max(res, tmpL+tmpM);
        }
        return res;
    }

    private int findMax(int[] nums, int start, int end, int l){
        int len = end-start+1;
        if(len<l) return 0;
        int res = 0;

        for(int i=start; i+l-1<=end; i++){
            int tmp = 0;
            for(int j=0; j<l; j++){
                tmp += nums[i+j];
            }
            res = Math.max(res, tmp);
        }
        return res;
    }

    public static void main(String[] args){
        _1031_Maximum_Sum_of_Two_Non_Overlapping_Subarrays test = new _1031_Maximum_Sum_of_Two_Non_Overlapping_Subarrays();
        int[] A = {3,8,1,3,2,1,8,9,0};
        System.out.println(test.maxSumTwoNoOverlap(A,3,2));
    }
}
