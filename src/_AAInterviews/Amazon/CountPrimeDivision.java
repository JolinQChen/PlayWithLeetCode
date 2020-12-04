package _AAInterviews.Amazon; /**
 * Input: str = “3175”
 * Output: 3
 * Explanation:
 * There are 3 ways to split this string into prime numbers which are (31, 7, 5), (3, 17, 5), (317, 5).
 *
 * Input: str = “11373”
 * Output: 6
 * Explanation:
 * There are 6 ways to split this string into prime numbers which are (11, 3, 7, 3), (113, 7, 3), (11, 37, 3), (11, 3, 73), (113, 73) and (11, 373).
 * */
import java.util.Arrays;

public class CountPrimeDivision {
    private static boolean isPrime(String num) {
        int n = Integer.parseInt(num);
        if(n<=1) return false;
        for(int i=2; i*i<=n; i++) {
            if(n % i == 0) return false;
        }
        return true;
    }
    /** 据说prime number都会出现在10^6以内...*/
    // 法一：straight forward：recursive ON^2; ON
    private static int countHelper(String str, int start) {
        if(start == str.length()) return 1;
        if(str.charAt(start) == '0') return 0;
        int count = 0;
        for(int i = start+1; i<=str.length() && i<=start+6; i++) {
            if(isPrime(str.substring(start,i))) {
                count += countHelper(str,i);
            }
        }
        return count;
    }

    private static int countDivisionRecursive(String str) {
        return countHelper(str,0);
    }

    // 法二：DP
    // Function to find the count
    // of ways to split String
    // into prime numbers
    private static int rec(String number, int i,
                   int []dp)
    {
        if (dp[i] != -1)
            return dp[i];
        int cnt = 0;

        for(int j = 1; j <= 6; j++)
        {

            // Number should not have a
            // leading zero and it
            // should be a prime number
            if (i - j >= 0 &&
                    number.charAt(i - j) != '0' &&
                    isPrime(number.substring(i - j, i)))
            {
                cnt += rec(number, i - j, dp);
            }
        }
        return dp[i] = cnt;
    }

    // Function to count the
// number of prime Strings
    private static int countPrimeStrings(String number)
    {
        int n = number.length();
        int []dp = new int[n + 1];

        Arrays.fill(dp, -1);
        dp[0] = 1;

        return rec(number, n, dp);
    }



    public static void main(String[] args) {
        String str = "30115";
        System.out.println(countDivisionRecursive(str));
        System.out.println(isPrime("3011"));
    }
}
