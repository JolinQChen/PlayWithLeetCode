import java.util.*;
import java.util.stream.Collectors;
/**!!!!!!*/
public class _1110_Delete_Nodes_And_Return_Forest {
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {

        Set<Integer> deleteSet = new HashSet<>();
        deleteSet.addAll(Arrays.stream(to_delete).boxed().collect(Collectors.toList()));

        List<TreeNode> res = new ArrayList<>();
        TreeNode node = delete(root, deleteSet, res);
        if(node != null)
            res.add(node);

        return res;
    }

    public TreeNode delete(TreeNode node, Set<Integer> deleteSet, List<TreeNode> res){

        if(node == null) return null;

        node.left = delete(node.left, deleteSet, res);
        node.right = delete(node.right, deleteSet, res);

        if(deleteSet.contains(node.val)){

            if(node.left != null)
                res.add(node.left);

            if(node.right != null)
                res.add(node.right);

            return null;
        }

        return node;
    }
}
