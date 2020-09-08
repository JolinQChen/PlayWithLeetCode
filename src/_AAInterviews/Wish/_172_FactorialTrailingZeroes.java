package _AAInterviews.Wish;

/**
Remember we have loads of two multiples in a factorial expansion. The only way to get zero would be
based on the count of five. One pair of (2,5) would lead to one zero. Another one...another zero and so on...
This is called greedy approach.
 */

public class _172_FactorialTrailingZeroes {
/**
 * Time complexity : O(n)O(n), Space complexity : O(1)O(1).
 * */
    public int trailingZeroes1(int n) {

        int zeroCount = 0;
        for (int i = 5; i <= n; i += 5) {
            int currentFactor = i;
            while (currentFactor % 5 == 0) {
                zeroCount++;
                currentFactor /= 5;
            }
        }
        return zeroCount;
    }


/**fives = n/5 + n/25 + n/125 + ...
 * Time complexity : O(logn).
 * */

    public int trailingZeroes2(int n) {
        int zeroCount = 0;
        // We need to use long because currentMultiple can potentially become
        // larger than an int.
        long currentMultiple = 5; /**注意要long*/
        while (n >= currentMultiple) {
            zeroCount += (n / currentMultiple);
            currentMultiple *= 5;
        }
        return zeroCount;
    }

    // 或者：
    public static int trailingZeroes3(int n) {
        int count = 0;
        while (n>0) {
            n/=5;
            count += n;
        }
        return count;
    }
}
