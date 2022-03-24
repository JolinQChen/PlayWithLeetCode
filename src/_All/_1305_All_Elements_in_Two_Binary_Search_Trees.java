package _All;
import java.util.*;

public class _1305_All_Elements_in_Two_Binary_Search_Trees {
    private List<Integer> sortedList;
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        sortedList = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        dfsHelper(root1, list1);
        dfsHelper(root2,list2);
        list1.stream().forEach(System.out::println);
        int len1 = list1.size();
        int len2 = list2.size();
        int idx1=0,idx2 = 0;
        while (idx1<len1 && idx2<len2) {
            if(list1.get(idx1)<=list2.get(idx2)) sortedList.add(list1.get(idx1++));
            else sortedList.add(list2.get(idx2++));
        }
        if(idx1==len1) {
            // list1 reached the end
            while (idx2<len2) sortedList.add(list2.get(idx2++));
        } else {
            // list2 reached the end
            while (idx1<len2) sortedList.add(list1.get(idx1++));
        }
        return sortedList;
    }

    private void dfsHelper(TreeNode node, List<Integer> list){
        if (node==null) return;
        if(node.left==null && node.right==null) {
            list.add(node.val);
            return;
        }
        dfsHelper(node.left,list);
        list.add(node.val);
        dfsHelper(node.right, list);
    }
}
