package _All;

import java.util.*;
public interface NestedInteger {

    //public _All.NestedInteger();


    //public _All.NestedInteger(int value);

    // @return true if this _All.NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger();

    // @return the single integer that this _All.NestedInteger holds, if it holds a single integer
    // Return null if this _All.NestedInteger holds a nested list
    public Integer getInteger();

    // Set this _All.NestedInteger to hold a single integer.
    public void setInteger(int value);

    // Set this _All.NestedInteger to hold a nested list and adds a nested integer to it.
    public void add(NestedInteger ni);

    // @return the nested list that this _All.NestedInteger holds, if it holds a nested list
    // Return null if this _All.NestedInteger holds a single integer
    public List<NestedInteger> getList();
}
