package _All;

public class _670_Maximum_Swap {
    public int maximumSwap(int num) {
        String num_s = String.valueOf(num);
        int len = num_s.length();
        if(len<=1) return num;

        int right=len-1, left = right;
        int res_right = right, res_left = left;
        while(left>=0) {
            if(num_s.charAt(left) <= num_s.charAt(right)) {
                if(num_s.charAt(left) < num_s.charAt(right)) {
                    if(num_s.charAt(res_right) < num_s.charAt(right)) res_right = right;
                    res_left = left;
                }


                left--;
            }

            else {
                right = left;
                left--;
            }
        }
        //System.out.println("left:" + res_left + " right:" + res_right);
        if(res_left >= res_right) return num;
        num_s = num_s.substring(0,res_left) + String.valueOf(num_s.charAt(res_right)) + num_s.substring(res_left+1,res_right) + String.valueOf(num_s.charAt(res_left)) + num_s.substring(res_right+1,len);
        return Integer.parseInt(num_s);
    }
}
