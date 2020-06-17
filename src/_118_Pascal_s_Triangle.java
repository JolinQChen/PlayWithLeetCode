import java.util.*;
public class _118_Pascal_s_Triangle {
    public List<List<Integer>> generate(int numRows) {
        if(numRows<1) return new LinkedList<>();

        List<List<Integer>> res = new ArrayList<>();
        List<Integer> l1 = new ArrayList<>();
        l1.add(1);
        res.add(l1);
        int curLevel = 1; // next level index
        while(curLevel<numRows){
            List<Integer> cur = new ArrayList<>();
            int count = 0;
            List<Integer> tmp = res.get(curLevel-1);
            while(count<=curLevel){
                if(count==0 || count==curLevel) cur.add(1);
                else {
                    cur.add(tmp.get(count-1)+tmp.get(count));
                }
                count++;
            }
            res.add(cur);
            curLevel++;
        }

        return res;
    }

}
