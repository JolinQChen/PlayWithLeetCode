package _All;

import java.util.*;
public class _722_Remove_Comments {
    public List<String> removeComments(String[] source) {
        boolean block = false;

        List<String> res = new LinkedList<>();
        for (String s : source) {
            block = findMatch(s, block, res);

        }

        return res;
    }

    private boolean findMatch(String s, boolean block, List<String> res) {
        StringBuilder sb = new StringBuilder();
        if (block) {
            sb.append(res.get(res.size() - 1));
            res.remove(res.size() - 1);
        }
        int i = 0;

        for (; i + 1 < s.length(); i++) {
            if (block) {
                if (s.charAt(i) == '*' && s.charAt(i + 1) == '/') {
                    i++;
                    block = false;
                }
            }
            else {
                if (s.charAt(i) == '/') {
                    if (s.charAt(i + 1) == '/') {
                        if (sb.length() > 0) {
                            res.add(sb.toString());
                        }
                        return false;
                    }
                    else if (s.charAt(i + 1) == '*') {
                        block = true;
                        i++;
                    }
                    else {
                        sb.append('/');
                    }
                }
                else {
                    sb.append(s.charAt(i));
                }
            }
        }

        if (i < s.length() && !block) {
            sb.append(s.charAt(i));
        }
        if (sb.length() > 0) {
            res.add(sb.toString());
            System.out.println(sb.toString());
        }

        return block;
    }
}
