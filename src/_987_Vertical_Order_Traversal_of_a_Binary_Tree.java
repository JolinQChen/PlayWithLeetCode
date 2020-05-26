import java.util.*;
/**
 * 和314不同之处在于，遇到相同的位置时，314从左向右，此处要求排序
 * */

public class _987_Vertical_Order_Traversal_of_a_Binary_Tree {
    class Pair{
        public TreeNode node;
        public int index;
        public int level;
        public Pair(TreeNode node, int index, int level){
            this.node = node;
            this.index = index;
            this.level = level;
        }
    }
    // BFS
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        if(root == null) return new ArrayList<>();
        Map<Integer, List<Pair>> map = new HashMap<>();
        int left_index = 0;
        int right_index = 0;
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(root,0,0));
        while(!queue.isEmpty()){
            Pair cur = queue.poll();
            left_index = Math.min(left_index, cur.index);
            right_index = Math.max(right_index, cur.index);
            if(!map.containsKey(cur.index)) {

                map.put(cur.index, new ArrayList<>());
            }

            List<Pair> curList = map.get(cur.index);
            curList.add(cur);
            System.out.println("cur.node.val: "+cur.node.val+"; cur.level: "+cur.level);
            map.put(cur.index, curList);
            if(cur.node.left!=null){
                queue.add(new Pair(cur.node.left,cur.index-1, cur.level+1));
            }
            if(cur.node.right!=null){
                queue.add(new Pair(cur.node.right,cur.index+1, cur.level+1));
            }
        }
        List<List<Integer>> res = new ArrayList<>();


        for(int i=left_index; i<=right_index; i++){
            List<Integer> curList = new ArrayList<>();
            Collections.sort(map.get(i), new Comparator<Pair>() {
                @Override
                public int compare(Pair o1, Pair o2) {
                    if(o1.level - o2.level !=0) return o1.level-o2.level;
                    return o1.node.val - o2.node.val;
                }
            });
            for(Pair pair:map.get(i)){
                curList.add(pair.node.val);
            }
            res.add(curList);
        }
        return res;
    }
}
