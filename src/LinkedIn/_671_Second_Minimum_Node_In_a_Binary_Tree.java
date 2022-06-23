package LinkedIn;

import _All.TreeNode;

public class _671_Second_Minimum_Node_In_a_Binary_Tree {
    public int findSecondMinimumValue(TreeNode root) {
        if(root.left==null) return -1;
        int min = root.val;
        int secondLeft;
        int secondRight;
        if(root.left.val == min) {
            // find secondMin in left part
            secondLeft = findSecondMinimumValue(root.left);
        }
        else secondLeft = root.left.val;
        if(root.right.val==min) {
            secondRight = findSecondMinimumValue(root.right);
        }
        else secondRight = root.right.val;
        int res;
        if (secondLeft<0 && secondRight<0) return -1;
        else if(secondLeft>0 && secondRight>0) res = Math.min(secondLeft,secondRight);
        else if(secondLeft>0) res = secondLeft;
        else res = secondRight;
        return res;
    }

}
