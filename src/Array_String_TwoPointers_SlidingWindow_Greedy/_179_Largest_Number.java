package Array_String_TwoPointers_SlidingWindow_Greedy;

import java.util.Arrays;
import java.util.Comparator;

public class _179_Largest_Number {
    public String largestNumber(int[] nums) {
        Integer[] i = new Integer[nums.length];
        for(int ii=0; ii<nums.length; ii++){
            i[ii] = Integer.valueOf(nums[ii]);
        }
        Arrays.sort(
                i,
                new Comparator<Integer>() {
                    @Override
                    public int compare(Integer i1, Integer i2) {
                        String str1 = String.valueOf(i1) + String.valueOf(i2);
                        String str2 = String.valueOf(i2) + String.valueOf(i1);
                        int len =  str1.length();
                        for(int i=0; i<len; i++){
                            if(str1.charAt(i)>str2.charAt(i)) return -1;
                            else if(str1.charAt(i)<str2.charAt(i)) return 1;
                        }
                        return 0;
                    }
                }
                );
        StringBuilder res = new StringBuilder();
        for(Integer ii:i){
            res.append(String.valueOf(ii));
        }
        int index = 0;
        String ans = res.toString();
        while(index<res.length() && ans.charAt(index)=='0'){
            index++;
        }

        return ans.substring(index).length()==0?"0":ans.substring(index);
    }
}
