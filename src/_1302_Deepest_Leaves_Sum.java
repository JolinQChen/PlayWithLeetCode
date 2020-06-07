import java.util.*;
public class _1302_Deepest_Leaves_Sum {
    public int deepestLeavesSum(TreeNode root) {
        //用bfs记录level，每层level都作和但是只保留最后一层
        int res = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            res = 0;
            int count = queue.size();
            while(count>0){
                count--;
                TreeNode cur = queue.poll();
                res += cur.val;
                if(cur.left!=null) queue.add(cur.left);
                if(cur.right!=null) queue.add(cur.right);
            }
        }
        return res;
    }
}
