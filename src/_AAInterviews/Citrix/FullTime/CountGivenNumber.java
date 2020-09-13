package _AAInterviews.Citrix.FullTime;

public class CountGivenNumber {
    /** Count the number of bit sets in an integer：统计一个整数的二进制形式里有多少个‘1’ */

    public static int count(int n) {
        int count = 0;
        while (n>0) {
            count += n&1;
            n >>= 1;
        }
        return count;
    }


}
