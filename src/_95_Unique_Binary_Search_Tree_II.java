import com.sun.source.tree.Tree;

import java.util.*;
public class _95_Unique_Binary_Search_Tree_II {
    // 好难哦
    /*
    * 首先来计数需要构建的二叉树数量。可能的二叉搜素数数量是一个 卡特兰数。
    * 我们从序列 1 ..n 中取出数字 i，作为当前树的树根。于是，剩余 i - 1 个元素可用于左子树，n - i 个元素用于右子树。
    * 如前文所述，这样会产生 G(i - 1) 种左子树 和 G(n - i) 种右子树，其中 G 是卡特兰数。
    * 现在，我们对序列 1 ... i - 1 重复上述过程，以构建所有的左子树；然后对 i + 1 ... n 重复，以构建所有的右子树。
    * 这样，我们就有了树根 i 和可能的左子树、右子树的列表。
    * 最后一步，对两个列表循环，将左子树和右子树连接在根上。
    * */



    public List<TreeNode> generateTrees(int n) {
        if(n==0) return new LinkedList<TreeNode>();

        List<TreeNode> res = generate_trees_helper(1,n);
        return res;

    }

    private List<TreeNode> generate_trees_helper(int start, int end){
        List<TreeNode> all_heads = new LinkedList<>();
        if(start>end) {
            all_heads.add(null);
            return all_heads;
        }
        // pick a root
        for(int i=start;i<=end;i++){
            List<TreeNode> left_heads = generate_trees_helper(start,i-1);
            List<TreeNode> right_heads = generate_trees_helper(i+1,end);

            // 合并
            for(TreeNode l:left_heads){
                for(TreeNode r:right_heads){
                    TreeNode cur = new TreeNode(i);
                    cur.left = l;
                    cur.right = r;
                    all_heads.add(cur);
                }
            }

        }
        return all_heads;


    }
}
