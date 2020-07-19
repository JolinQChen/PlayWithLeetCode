package _All;

import java.util.*;

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface _All.NestedInteger {
 *     // Constructor initializes an empty nested list.
 *     public _All.NestedInteger();
 *
 *     // Constructor initializes a single integer.
 *     public _All.NestedInteger(int value);
 *
 *     // @return true if this _All.NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this _All.NestedInteger holds, if it holds a single integer
 *     // Return null if this _All.NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // Set this _All.NestedInteger to hold a single integer.
 *     public void setInteger(int value);
 *
 *     // Set this _All.NestedInteger to hold a nested list and adds a nested integer to it.
 *     public void add(_All.NestedInteger ni);
 *
 *     // @return the nested list that this _All.NestedInteger holds, if it holds a nested list
 *     // Return null if this _All.NestedInteger holds a single integer
 *     public List<_All.NestedInteger> getList();
 * }
 */
/**
 * 输入: [1,[4,[6]]]
 * 输出: 17
 * 解释: 一个 1 在深度为 3 的位置， 一个 4 在深度为 2 的位置，一个 6 在深度为 1 的位置。 1*3 + 4*2 + 6*1 = 17。
 *
 *
 * */
public class _364_Nested_List_Weight_Sum_II {
    public int depthSumInverse(List<NestedInteger> nestedList) {
        int res = 0;
        int cur_sum = 0;
        while(nestedList.size()!=0){
            List<NestedInteger> tmp_list = new LinkedList<>();
            for(NestedInteger ni : nestedList){
                if(ni.isInteger()) cur_sum += ni.getInteger();
                else {
                    List<NestedInteger> list = ni.getList();
                    for(NestedInteger cu : list)tmp_list.add(cu);
                }
            }
            nestedList = tmp_list;
            res += cur_sum;

        }
        return res;

    }
}
