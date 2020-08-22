package _AAInterviews.MS;

import java.util.Arrays;

public class PlusOne {
    public int[] plusOne(int[] digits) {
        int[] res = new int[digits.length+1];
        int idx = digits.length-1;
        int carry = 1;
        while (idx>=0) {
            res[idx+1] = (digits[idx]+carry)%10;
            carry = (digits[idx]+carry)/10;
            idx--;
        }
        res[0] = carry;
        if(res[0]==0) return Arrays.copyOfRange(res, 1, res.length);
        return res;
    }
}
