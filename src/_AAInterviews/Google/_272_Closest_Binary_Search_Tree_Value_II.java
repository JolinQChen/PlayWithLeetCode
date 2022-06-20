package _AAInterviews.Google;
import _All.TreeNode;

import java.util.*;
public class _272_Closest_Binary_Search_Tree_Value_II {
    List<Integer> nums;
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> list = new ArrayList<>();
        // inorder
        nums = new ArrayList<>();
        inorder(root);
        // System.out.println(nums.size());
        int insertPos = binarySearch(target);
        // System.out.println(insertPos);
        int l = insertPos-1;
        int r = insertPos;
        int count = 0;
        if(insertPos< nums.size() && target == (double) nums.get(insertPos)) {
            list.add(nums.get(insertPos));
            r++;
            count++;
        }
        while(count<k) {
            if(l>=0 && r<nums.size()) {
                if(Math.abs(nums.get(l)-target) <= Math.abs(nums.get(r)-target)) {
                    list.add(nums.get(l));
                    l--;
                    count++;
                } else {
                    list.add(nums.get(r));
                    r++;
                    count++;
                }
            }
            else if(r==nums.size()) {
                // all in l
                list.add(nums.get(l--));
                count++;
            } else {
                list.add(nums.get(r++));
                count++;
            }
        }
        return list;

    }

    private void inorder(TreeNode node){
        if(node==null) return;
        inorder(node.left);
        nums.add(node.val);
        inorder(node.right);
    }
    // find the place should insert into
    private int binarySearch(double target){
        int left = 0;
        int right = nums.size()-1;
        while(left<=right) {
            int mid = left + (right - left)/2;
            int midVal = nums.get(mid);
            // System.out.println("left: "+left+", right: "+right+", mid: "+midVal);
            if(midVal==target) return mid;
            if(midVal>target) right = mid-1;
            else left = mid+1;
        }
        return left;
    }
}
