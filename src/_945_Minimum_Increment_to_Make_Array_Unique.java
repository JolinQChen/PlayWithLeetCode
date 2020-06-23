
import java.util.*;
public class _945_Minimum_Increment_to_Make_Array_Unique  {
    public int minIncrementForUnique(int[] A) {
        int res = 0;
        Arrays.sort(A);
        int len = A.length;
        if(len<=1) return res;
        for(int i=1; i<len; i++){
            if(A[i]<=A[i-1]){
                res += A[i-1]-A[i]+1;
                A[i]=A[i-1]+1;
            }
        }
        return res;
    }

}
