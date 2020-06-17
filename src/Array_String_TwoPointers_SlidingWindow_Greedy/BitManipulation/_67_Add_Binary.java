package Array_String_TwoPointers_SlidingWindow_Greedy.BitManipulation;

import java.math.BigInteger;

/**
 * 相加两个二进制数
 * */


public class _67_Add_Binary {
    //法一：逐位计算，最原始的方法
    public String addBinary_1(String a, String b) {
        //保证a永远不比b短
        int n = a.length(), m = b.length();
        if (n < m) return addBinary_1(b, a);

        StringBuilder sb = new StringBuilder();
        int carry = 0, j = m - 1;
        for(int i = n - 1; i >= 0; --i) {
            if (a.charAt(i) == '1') ++carry;
            if (j > -1 && b.charAt(j--) == '1') ++carry;

            if (carry % 2 == 1) sb.append('1');
            else sb.append('0');

            carry /= 2;
        }
        if (carry == 1) sb.append('1');
        sb.reverse();

        return sb.toString();

    }

    //法二：位操作
    //XOR 操作得到两个数字无进位相加的结果。
    // 无聊，不想学
    public String addBinary(String a, String b) {
        BigInteger x = new BigInteger(a, 2);
        BigInteger y = new BigInteger(b, 2);
        BigInteger zero = new BigInteger("0", 2);
        BigInteger carry, answer;
        while (y.compareTo(zero) != 0) {
            answer = x.xor(y);
            carry = x.and(y).shiftLeft(1);
            x = answer;
            y = carry;
        }
        return x.toString(2);
    }
}
