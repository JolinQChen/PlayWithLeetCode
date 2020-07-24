package _AAInterviews.ByteDance;


import java.util.*;
public class Largest_Number {
//        Input: [3,30,34,5,9]
//        Output: "9534330"
    public String largestNumber(int[] nums) {
        Integer[] nums_ = new Integer[nums.length];
        int idx = 0;
        for(int num:nums) {
            nums_[idx++] = Integer.valueOf(num);
        }
        Arrays.sort(nums_, new Comparator<Integer>() {

            @Override
            public int compare(Integer o1, Integer o2) {
                String str1 = String.valueOf(o1) + String.valueOf(o2);
                String str2 = String.valueOf(o2) + String.valueOf(o1);
                int len = str1.length();
                for(int i=0; i<len; i++) {
                    if(str1.charAt(i)>str2.charAt(i)) return -1;
                    else if(str1.charAt(i)<str2.charAt(i)) return 1;
                }
                return 0;
            }
        });
        StringBuilder sb = new StringBuilder();
        for(Integer num:nums_) sb.append(String.valueOf(num));
        idx = 0;
        String ans = sb.toString();
        int len = ans.length();
        while(idx< len && ans.charAt(idx) == '0') {
            idx++;
        }
        return idx==len?"0":ans.substring(idx,len);
    }
}
