package _AAInterviews.Amazon;

import java.util.*;
public class GenerateParentheses {
    List<String> list;
    public List<String> generateParenthesis(int n) {
        list = new ArrayList<>();
        helper(n,n,"");
        return list;
    }

    private void helper(int l, int r, String cur) {
        if(l==0 && r==0) {
            list.add(cur);
            return;
        }
        if(l>0) {
            helper(l-1,r,cur+"(");
        }
        if(l<r) {
            helper(l,r-1,cur+")");
        }

    }
}
