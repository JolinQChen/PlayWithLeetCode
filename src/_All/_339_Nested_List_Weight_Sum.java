package _All;

import java.util.*;
/**
 * Given a nested list of integers, return the sum of all integers in the
 * list weighted by their depth.
 *
 * Each element is either an integer, or a list -- whose elements may also
 * be integers or other lists.
 *
 * Example 1:
 *
 * Input: [[1,1],2,[1,1]]
 * Output: 10
 * Explanation: Four 1's at depth 2, one 2 at depth 1.
 * Example 2:
 *
 * Input: [1,[4,[6]]]
 * Output: 27
 * Explanation: One 1 at depth 1, one 4 at depth 2, and one 6 at depth 3;
 * 1 + 4*2 + 6*3 = 27.
 *
 * */

/*
  This is the interface that allows for creating nested lists.
  You should not implement it, or speculate about its implementation
 public interface _All.NestedInteger {


 // @return true if this _All.NestedInteger holds a single integer, rather than a nested list.
 public boolean isInteger();

 // @return the single integer that this _All.NestedInteger holds, if it holds a single integer
 // Return null if this _All.NestedInteger holds a nested list
 public Integer getInteger();

 // Set this _All.NestedInteger to hold a single integer.
 public void setInteger(int value);

 // Set this _All.NestedInteger to hold a nested list and adds a nested integer to it.
 public void add(_All.NestedInteger ni);

 // @return the nested list that this _All.NestedInteger holds, if it holds a nested list
 // Return null if this _All.NestedInteger holds a single integer
 public List<_All.NestedInteger> getList();
 }
 */

public class _339_Nested_List_Weight_Sum {
    private int sum = 0;
    public int depthSum(List<NestedInteger> nestedList) {
        for(NestedInteger ni:nestedList){
            countSum(1,ni);
        }
        return sum;
    }

    private void countSum(int depth, NestedInteger nestedInteger){
        if(nestedInteger.getInteger()!=null) sum+=depth*nestedInteger.getInteger();
        else {
            List<NestedInteger> list = nestedInteger.getList();
            for(NestedInteger ni:list) countSum(depth+1,ni);
        }

    }
}
