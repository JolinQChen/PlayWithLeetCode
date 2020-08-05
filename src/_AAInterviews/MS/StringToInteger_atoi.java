package _AAInterviews.MS;

public class StringToInteger_atoi {
    public static int myAtoi(String str) {
        // 去掉‘ ’
        final int MIN = Integer.MIN_VALUE;
        final int MAX = Integer.MAX_VALUE;
        int idx = 0;
        int len = str.length();
        while(idx<len && str.charAt(idx) == ' ') idx++;
        if(idx == len || (!Character.isDigit(str.charAt(idx)) && str.charAt(idx) != '-' && str.charAt(idx) != '+')) return 0;

        int res = 0;
        int flag = 1;
        if(str.charAt(idx) == '-') {
            flag = -1;
            idx++;
        }
        else if(str.charAt(idx) == '+') idx++;
        while(idx<len && Character.isDigit(str.charAt(idx))) {
            int cur = (int)(str.charAt(idx) - '0');
            if(flag>0) {
                if((res == MAX / 10 && cur>MAX%10) || res>MAX/10) return MAX;
                res = res * 10 + cur;
            }
            else if(flag<0) {
                if((-res == MIN/10 && -cur < MIN%10) || -res < MIN/10) return MIN;
                res = res * 10 + cur;
            }
            idx++;
        }
        return res*flag;
    }

    public static void main(String[] args) {
        int res = myAtoi("4193 with words");
        System.out.println(res);
    }
}
