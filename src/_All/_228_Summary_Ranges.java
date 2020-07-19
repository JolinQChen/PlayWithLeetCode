package _All;

import java.util.*;
public class _228_Summary_Ranges {
    public List<String> summaryRanges(int[] nums) {
        int startIdx = 0;
        int endIdx = 0;
        List<String> res = new ArrayList<>();
        String tmp = "";
        //sb.append(nums[startIdx]);
        while(startIdx<nums.length && endIdx<=nums.length){
            if(endIdx==startIdx) {
                tmp += String.valueOf(nums[endIdx]);
                endIdx++;
            }
            else if(endIdx<nums.length && nums[endIdx]-nums[endIdx-1]==1){
                if(endIdx-startIdx==1) tmp += "->";
                endIdx++;
            }
            else {
                if(endIdx-startIdx>1) tmp += String.valueOf(nums[endIdx-1]);
                res.add(tmp);
                startIdx = endIdx;
                endIdx = startIdx;
                tmp = "";
            }
        }
        return res;
    }
    public static void main(String[] args){
        int[] nums = new int[]{0,1,2,4,5,7};
        _228_Summary_Ranges test = new _228_Summary_Ranges();
        List<String> res = test.summaryRanges(nums);
        for(String str:res) System.out.println(str);
    }
}
