package tree;

// 修剪掉所有子树中不含1的结点
public class _814_Binary_Tree_Pruning {
        public TreeNode pruneTree(TreeNode root) {
            if(!contains1(root)) return null;
            root.left = pruneTree(root.left);
            root.right = pruneTree(root.right);
            return root;
        }
        private boolean contains1(TreeNode root){
            if(root==null) return false;
            if(root.val==1) return true;
            return(contains1(root.left) || contains1(root.right));
        }
}
