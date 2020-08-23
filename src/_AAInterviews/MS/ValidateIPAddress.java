package _AAInterviews.MS;

public class ValidateIPAddress {
    public String validIPAddress(String IP) {
        String[] res = {"IPv4","IPv6","Neither"};
        if (IP.chars().filter(ch -> ch == '.').count() == 3) {
            String[] strs = IP.split("\\.");
            if(isIpv4(strs)) return res[0];

        }
        else if (IP.chars().filter(ch -> ch == ':').count() == 7) {
            String[] strs = IP.split(":");
            if(isIpv6(strs)) return res[1];

        }
        return res[2];
    }

    private boolean isIpv4(String[] strs) {
        // System.out.println(strs.length);
        if(strs.length!=4) return false;
        for(String str:strs) {
            int len = str.length();
            int val = 0;
            if((len>1 && str.charAt(0)=='0') || len<1 || len>3) return false;
            for(int i=0; i<len; i++) {
                if(!Character.isDigit(str.charAt(i))) return false;
                val = val * 10 + (int)(str.charAt(i)-'0');
            }
            if(val>255) return false;
        }
        return true;
    }

    private boolean isIpv6(String[] strs) {
        if(strs.length!=8) {
            return false;
        }
        for(String str:strs) {
            int len = str.length();

            if(len<1||len>4) return false;
            for(int i=0; i<len; i++) {
                char tmp = str.charAt(i);
                if(!Character.isDigit(tmp) && (tmp>'f'||tmp<'a') && (tmp>'F'||tmp<'A')) return false;

            }

        }
        return true;
    }
    public static void main(String[] args) {
        String str = "2001:0db8:85a3:0:0:8A2E:0370:7334:";
        String[] res = str.split(":");
        System.out.println(res.length);
        System.out.println(res[0]);
    }
}
