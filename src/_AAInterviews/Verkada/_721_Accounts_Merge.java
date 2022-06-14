package _AAInterviews.Verkada;
import java.util.*;
public class _721_Accounts_Merge {
    Map<Integer, Set<String>> map = new HashMap<>();
    int[] parent;

    private int find(int x) {
        if(parent[x]!=x) parent[x] = find(parent[x]);
        return parent[x];
    }

    private boolean union(int x, int y) {
        int root_x = find(x);
        int root_y = find(y);
        parent[root_x] = root_y;
        map.get(root_y).addAll(map.get(root_x));
        return true;
    }

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        // union find
        // initialize map
        for(int i=0; i<accounts.size(); i++) {
            List<String> account = accounts.get(i);
            String accountName = account.get(0);
            map.put(i, new HashSet<>());
            for(int j = 1; j<account.size(); j++) map.get(i).add(account.get(j));
        }
        parent = new int[accounts.size()];
        for(int i=0; i<accounts.size(); i++) parent[i] = i;
        for(int i=0; i<accounts.size(); i++) {
            for(int j=i+1; j<accounts.size(); j++) {
                if(accounts.get(i).get(0).equals(accounts.get(j).get(0)) && canUnion(i,j)) {
                    union(i,j);
                }
            }
        }
        List<List<String>> res = new ArrayList<>();
        for(int i=0; i<accounts.size(); i++)  {
            if(parent[i] == i) {
                List<String> list = new ArrayList<>();
                list.addAll(map.get(i));
                Collections.sort(list);
                List<String> list1 = new ArrayList<>();
                list1.add(accounts.get(i).get(0));
                list1.addAll(list);
                res.add(list1);
            }
        }
        return res;
    }

    private boolean canUnion(int i, int j){
        int root_i = find(i);
        int root_j = find(j);
        if(root_i==root_j) return false;
        Set<String> seti = map.get(find(root_i));
        Set<String> setj = map.get(find(root_j));
        for(String s:seti) {
            if(setj.contains(s)) return true;
        }
        return false;
    }
}
