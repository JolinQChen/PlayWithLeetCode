import java.util.*;

/**
 * Given the root of a binary tree with N nodes, each node in the tree has node.val coins,
 * and there are N coins total.
 *
 * In one move, we may choose two adjacent nodes and move one coin from one node to another.
 * (The move may be from parent to child, or from child to parent.)
 *
 * Return the number of moves required to make every node have exactly one coin.
 *
 * Input: [3,0,0]
 * Output: 2
 * Explanation: From the root of the tree, we move one coin to its left child, and one coin to its right child.
 *
 *
 * 如果树的叶子仅包含 0 枚金币（与它所需相比，它的 过载量 为 -1），那么我们需要从它的父亲节点移动一枚金币到这个叶子节点上。
 * 如果说，一个叶子节点包含 4 枚金币（它的 过载量 为 3），那么我们需要将这个叶子节点中的 3 枚金币移动到别的地方去。总的来说，
 * 对于一个叶子节点，需要移动到它中或需要从它移动到它的父亲中的金币数量为 过载量 = Math.abs(num_coins - 1)。然后，在接下来的计算中，
 * 我们就再也不需要考虑这些已经考虑过的叶子节点了。
 *
 * 算法
 *
 * 我们可以用上述的方法来逐步构建我们的最终答案。定义 dfs(node) 为这个节点所在的子树中金币的 过载量，
 * 也就是这个子树中金币的数量减去这个子树中节点的数量。接着，我们可以计算出这个节点与它的子节点之间需要移动金币的数量为：
 * abs(dfs(node.left)) + abs(dfs(node.right))
 * 这个节点金币的过载量为：
 * node.val + dfs(node.left) + dfs(node.right) - 1。
 *
 * */

public class _979_Distribute_Coins_in_Binary_Tree {
    private int ans;
    public int distributeCoins(TreeNode root) {
        ans = 0;
        dfs(root);
        return ans;
    }
    private int dfs(TreeNode root){
        if(root==null) return 0;
        int left = dfs(root.left);
        int right = dfs(root.right);
        ans = ans + Math.abs(left) + Math.abs(right);
        return root.val + left + right -1;
    }
}
