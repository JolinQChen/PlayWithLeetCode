package tree;

/**
 * Given n, how many structurally unique BST's (binary search trees)
 * that store values 1 ... n?
 *
 * Input: 3
 * Output: 5
 * Explanation:
 * Given n = 3, there are a total of 5 unique BST's:
 *
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 * */
public class _96_Unique_Binary_Search_Trees {
    public int numTrees(int n) {
        if(n==0 || n==1) return 1;
        int res = 0;
        for(int i=1; i<=n; i++){
            res += numTrees(i-1) + numTrees(n - i);
        }
        return res;
    }
}
