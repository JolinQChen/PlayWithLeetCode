import java.util.*;
/**
 * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。
 *
 * 注意：
 *
 * num1 和num2 的长度都小于 5100.
 * num1 和num2 都只包含数字 0-9.
 * num1 和num2 都不包含任何前导零。
 * 你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式。
 *
 *
 * */

public class _415_Add_Strings {
    public String addStrings(String num1, String num2) {
        char[] num_1 = num1.toCharArray();
        char[] num_2 = num2.toCharArray();
        int len1 = num_1.length;
        int len2 = num_2.length;
        //String res = "";
        int len = Math.max(len1, len2);
        char[] res = new char[len];

        int count = 0;
        int delta_index = 1;
        while(len1-delta_index>=0 && len2-delta_index>=0){
            int cur = (num_1[len1-delta_index] - '0') + (num_2[len2-delta_index] - '0') + count;
            count = cur / 10;
            cur %= 10;
            res[len-delta_index] = (char)('0' + cur);
            //res = cur + res;
            delta_index++;
        }

        while(len1-delta_index>=0){
            int cur = (num_1[len1-delta_index] - '0') + count;
            count = cur / 10;
            cur %= 10;
            res[len-delta_index] = (char)('0' + cur);
            //res = cur+res;
            delta_index++;
        }
        while(len2-delta_index>=0){
            int cur = (num_2[len2-delta_index] - '0') + count;
            count = cur / 10;
            cur %= 10;
            res[len-delta_index] = (char)('0' + cur);
            //res = cur+res;
            delta_index++;
        }
        String res_ = new String(res);
        if(count == 0) return res_;
        else return "1"+res_;
    }
    public static void main(String[] args){

    }
}
