import java.util.*;
/**
 * Given a string containing only digits, restore it by returning all possible
 * valid IP address combinations.
 *
 * A valid IP address consists of exactly four integers (each integer is between 0 and 255)
 * separated by single points.
 *
 * Input: "25525511135"
 * Output: ["255.255.11.135", "255.255.111.35"]
 * */

public class _93_Restore_IP_Addresses {
    //初步感觉是回溯剪枝吧
    private List<String> res;
    public List<String> restoreIpAddresses(String s) {
        res = new LinkedList<>();
        if(s == null || s.length() == 0) return res;
        backTracking(s, 0, new LinkedList<String>());
        return res;
    }
    private void backTracking(String s, int pos, List<String> cur){
        if(cur.size() == 4){
            if(pos == s.length()){
                //遍历结束
                res.add(String.join(".",cur));
            }
            return;
        }
        // ip地址每段最多3个数字
        for(int i=1; i<=3; i++){
            // 如果超出范围 就不用再分段了，直接跳出循环即可。
            if(pos + i > s.length()) break;
            //将s的子串开始分段
            String segment = s.substring(pos, pos+i);
            // 剪枝条件：段的起始位置不能为 0，段拆箱成 int 类型的长度不能大于 255
            if(valid(segment)){
                cur.add(segment);
                backTracking(s, pos+i, cur);
                cur.remove(cur.size()-1); //回退
            }
        }
    }

    private boolean valid(String segment) {
    /*
    Check if the current segment is valid :
    1. less or equal to 255
    2. the first character could be '0'
    only if the segment is equal to '0'
    */
        int m = segment.length();
        if (m > 3)
            return false;
        return (segment.charAt(0) != '0') ? (Integer.parseInt(segment) <= 255) : (m == 1);
    }



}
