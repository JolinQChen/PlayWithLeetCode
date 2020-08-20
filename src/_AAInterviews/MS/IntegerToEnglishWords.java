package _AAInterviews.MS;
import java.util.*;

public class IntegerToEnglishWords {
    static Map<Integer, String> map = new HashMap<>();

    public static String numberToWords(int num) {
        if(num==0) return "Zero";
        map.put(1,"One");
        map.put(2,"Two");
        map.put(3,"Three");
        map.put(4,"Four");
        map.put(5,"Five");
        map.put(6,"Six");
        map.put(7,"Seven");
        map.put(8,"Eight");
        map.put(9,"Nine");
        map.put(10,"Ten");
        map.put(11,"Eleven");
        map.put(12,"Twelve");
        map.put(13,"Thirteen");
        map.put(14,"Fourteen");
        map.put(15,"Fifteen");
        map.put(16,"Sixteen");
        map.put(17,"Seventeen");
        map.put(18,"Eighteen");
        map.put(19,"Nineteen");
        map.put(20,"Twenty");
        map.put(30, "Thirty");
        map.put(40, "Forty");
        map.put(50,"Fifty");
        map.put(60,"Sixty");
        map.put(70,"Seventy");
        map.put(80,"Eighty");
        map.put(90,"Ninety");
        String[] units = {"","Thousand","Million","Billion"};
        //char[] digits = String.valueOf(num).toCharArray();
        int pos = 0;
        String res = "";
        while (num>0) {
            int n=num%1000;
            num/=1000;
            if(n!=0) res = perUnit(n) + " " + units[pos] + " "+ res;
            pos++;
        }
        int idx = res.length()-1;
        while (res.charAt(idx)==' ') idx--;
        return res.substring(0,idx+1);
    }

    private static String perUnit(int num) {
        // digits<=3
        if(num==0) return "";
        String res = "";
        if(map.containsKey(num)) return map.get(num);
        int n = num%100;
        num/=100;
        if(n!=0){
            if(map.containsKey(n)) res = map.get(n);
            else {
                int tmp = 0;
                tmp = n%10;
                res += map.get(tmp);
                n-=tmp;
                res = map.get(n)+ " " + res;
            }
        }
        if(num==0) return res;
        if(res=="") return map.get(num) + " Hundred";
        return map.get(num) + " Hundred " + res;

    }
    public static void main(String[] args) {

        System.out.println(numberToWords(230000010));
    }
}
