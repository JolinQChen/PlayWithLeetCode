package _All;

import java.util.*;

public class _1239_Maximum_Length_of_a_Concatenated_String_with_Unique_Characters {
    /**
     * 思路很明显，就是使用深度优先搜索（DFS）。
     * 需要注意的是，如何去判断当前字符串是否与已经组合的字符串是否有重复。
     *
     * 可以使用位掩码来解决。
     * 数字与字母映射('a' -> 1, 'b' -> 2, 'c' -> 4)以此类推，如果已组合的字符位掩码与当前所选择的子字符
     * 的位掩码进行与运算后，得到的结果为0，说明无重复。
     *
     * */
    public int maxLength(List<String> arr) {
        if (arr == null || arr.size() == 0)
            return 0;

        return maxLengthDFS(arr, 0, 0);
    }

    //此处函数的目的是为了返回串联字符的最大长度
    public int maxLengthDFS(List<String> arr, int start, int bitMask) {
        if (start == arr.size())
            return 0;

        int ans = 0;
        for (int i = start; i < arr.size(); i++) {
            int bit = getBitMask(arr.get(i));
            if (bit == 0 || (bitMask & bit) != 0) continue;
            //一。当前子字符位掩码不为0（为0的话证明子字符里面有重复字符）
            //二。当前子字符位掩码与前面字符的位掩码与运算结果为0（如果结果不为0，那么说明与之前的字符串有重复）
            //满足上述两个条件才进入一下层递归。
            ans = Math.max(ans, maxLengthDFS(arr, i + 1, bitMask | bit) + arr.get(i).length());
        }
        return ans;
    }

    public int getBitMask(String s) {
        int bitMask = 0;
        for (char c : s.toCharArray()) {
            int bit = 1 << (c - 'a');
            if ((bit & bitMask) != 0) return 0; //有重复
            bitMask |= bit;
        }
        return bitMask;
    }
    public static void main(String[] args){
        _1239_Maximum_Length_of_a_Concatenated_String_with_Unique_Characters test = new _1239_Maximum_Length_of_a_Concatenated_String_with_Unique_Characters();
        //System.out.println(test.getBitMask("ca"));
        System.out.println(test.getBitMask("cac"));
    }
}
