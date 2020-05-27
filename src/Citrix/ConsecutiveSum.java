package Citrix;
import java.util.*;
/**
 * Let N = a + a+1 + ... + b-1 + b = (a + b)*(b-a)/ 2 , now apply b = a + h
 * => N = a + a+1 + ... + a+h = (a + a+h) * (h+1) / 2
 * => 2N = (h+1)(h+2a), find solutions for (a, h) integer solutions with h,
 * a >= 1
 *
 * Instead of deciding a, we deciding on h first
 * (so we can fast compute a to check our answer)
 * so where is the h space to find?
 * since a >= 1 , so (h+1)(h+2a) >= (h+1)(h+2*1) .
 * So we have (h+1)(h+2) <= s = 2N, and loops from h = 1
 * */
public class ConsecutiveSum {
    public static int consecutiveNumbersSum(int N) {
        return sol1(N);
    }

    // N = a + a+1 + ... + b-1 + b = (a + b)*(b-a)/ 2
    // => N = a + a+1 + ... + a+h = (a + a+h) * (h+1) / 2
    // => 2N = (h+1)(h+2a), find solutions for (a, h) integer solutions
    // with h, a >= 1
    private static int sol1(int n) {
        int ans = 0;
        int s = 2*n;
        // Find s = (h+1)(h+2a)
        //ah.add(Arrays.asList(n, 1));
        ans++;
        for (int h = 1; (h+1)*(h+2) <= s; h++) {
            int z = h+1;
            if (s % z == 0) {
                // y = h + 2a
                int y = s / z;
                int a2 = y - h;
                if ((a2 & 1) == 0) { // a2 % 2 == 0
                    //ah.add(Arrays.asList((int)a2/2, (int)h));
                    ans++;
                }
            }
        }
        //return ah.size();
        return ans-1;
    }
    public static void main(String[] args){
        System.out.println(consecutiveNumbersSum(15));
    }
}
