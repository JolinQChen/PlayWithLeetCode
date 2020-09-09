package _AAInterviews.Wish;
import java.util.*;
public class VerticalOrderTraversalOfABinaryTree {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(){}
        TreeNode(int val) {
            this.val = val;
        }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val; this.left = left; this.right = right;
        }
    }

    class Pair {
        TreeNode node;
        int idx;
        int level;
        public Pair(TreeNode node, int idx, int l) {
            this.node = node;
            this.idx = idx;
            this.level = l;
        }
    }

    // BFS
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Map<Integer, List<Pair>> map = new TreeMap<>(); // column index -> list
        Queue<Pair> queue = new LinkedList<>();
        if(root==null) return res;
        queue.add(new Pair(root,0,0));
        while (!queue.isEmpty()) {
            Pair curr = queue.poll();
            if(!map.containsKey(curr.idx)) map.put(curr.idx, new ArrayList<>());
            map.get(curr.idx).add(curr);
            if(curr.node.left!=null) queue.add(new Pair(curr.node.left, curr.idx-1, curr.level+1));
            if(curr.node.right!=null) queue.add(new Pair(curr.node.right, curr.idx+1, curr.level+1));
        }
        for(int k:map.keySet()) {
            Collections.sort(map.get(k), new Comparator<Pair>() {
                @Override
                public int compare(Pair o1, Pair o2) {
                    return o1.level==o2.level?o1.node.val-o2.node.val:o1.level-o2.level;
                }
            });
            List<Integer> tmp = new ArrayList<>();
            for(Pair p:map.get(k)) tmp.add(p.node.val);
            res.add(tmp);
        }
        return res;
    }
}
