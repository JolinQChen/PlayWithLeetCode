package Citrix;
import java.util.*;

/**
 * 贪心算法？？
 * input 01011
 * output 3
 *
 * 观察到顺序不重要
 *
 * Pseudocode:
 *
 * result = 0
 * // most to least significant
 * for bit in bits:
 *     if result%2 == 0:
 *         if bit != 0: result += 1
 *     else:
 *         if not bit != 0: result += 1
 * print(result)
 *
 *
 * And to be more succinct:
 *
 * bits = [0, 0, 1, 0, 1]
 * result = 0
 * for bit in bits: result += (result%2)^bit
 * print(result)
 * */
public class TheFinalProblem {
    public static int countFlips(String target){
        char[] targetBits = target.toCharArray();
        int len = targetBits.length;
        int times = 0;
        for(char cur:targetBits){
            if(cur=='0'){
                //应该是反转偶数次
                if(times%2!=0) times++;
            }
            else {
                //应该反转奇数次
                if(times % 2 == 0) times++;
            }
        }
        return times;
    }

    public static void main(String[] args){
        System.out.println(countFlips("01011"));
    }
}
