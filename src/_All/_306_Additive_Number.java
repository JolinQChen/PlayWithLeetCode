package _All;

import java.util.*;
/**
 * Additive number is a string whose digits can form additive sequence.
 *
 * A valid additive sequence should contain at least three numbers. Except for the first two numbers, each subsequent
 * number in the sequence must be the sum of the preceding two.
 *
 * Given a string containing only digits '0'-'9', write a function to determine if it's an additive number.
 *
 * Note: Numbers in the additive sequence cannot have leading zeros, so sequence 1, 2, 03 or 1, 02, 3 is invalid.
 * */

/**backtrack就好，但是主要是用Long.parseLong()就会溢出，只能手动相加*/
public class _306_Additive_Number {
    public boolean isAdditiveNumber(String num) {
        return backTracking(num,0,new LinkedList<Long>());
    }
    private boolean backTracking(String num, int pos, List<Long> cur) {
        //pos表示下一数字开始的位置
        if(pos == num.length() && cur.size()>=3) {

            return true;}
        else {
            for(int i=pos; i<num.length(); i++){
                long tmp = fetchCurValue(num, pos, i);
                if(tmp == -1) break;

                if( cur.size()<2 || (cur.size()>=2 && tmp == cur.get(cur.size()-1)+cur.get(cur.size()-2))) {
                    cur.add(tmp);
                    if(backTracking(num, i+1, cur)) return true;
                    cur.remove(cur.size()-1);
                }
            }
        }
        return false;
    }
    private long fetchCurValue(String num, int l, int r) {
        if (l < r && num.charAt(l) == '0') {
            return -1;
        }
        long res = 0;
        while (l <= r) {
            res = res * 10 + num.charAt(l++) - '0';
        }
        return res;
    }





    public static void main(String[] args){
        _306_Additive_Number test = new _306_Additive_Number();
        System.out.println(test.isAdditiveNumber("112358"));
    }
}
