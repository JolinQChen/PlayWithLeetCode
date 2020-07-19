package _All;

import java.util.*;
/**
 * Given a non-negative integer n, count all numbers with unique digits, x, where 0 ≤ x < 10n.
 *
 * Example:
 *
 * Input: 2
 * Output: 91
 * Explanation: The answer should be the total numbers in the range of 0 ≤ x < 100,
 *              excluding 11,22,33,44,55,66,77,88,99
 * */


public class _357_Count_Numbers_with_Unique_Digits {
    //数学方法即可
    public int countNumbersWithUniqueDigits(int n) {
        int res = 0;
        for(int i=1; i<=n; i++){
            res += countDP(i);
        }
        return res+1;
    }

    private int countDP(int n){
        if(n>10) return 0;
        int count = 1;
        for(int i=0; i<n-1; i++){
            count *= (9-i);
        }
        return count * 9;
    }
}
