package _All;

import java.util.*;
public class _969_Pancake_Sorting {
    private Map<Integer, Integer> map;
    public List<Integer> pancakeSort(int[] A)
    {
        map = new HashMap<>();
        for(int i=0; i<A.length; i++)
        {
            map.put(A[i],i);
        }
        List<Integer> path = new ArrayList<>();
        for(int r=A.length-1; r>0; r--)
        {
            int maxPos = map.get(r+1);
            if(maxPos<r)
            {
                if(maxPos>0)
                {
                    path.add(maxPos+1);
                    // flip 1
                    int left = 0;
                    int right = maxPos;
                    while(left<right)
                    {
                        int tmp = A[left];
                        A[left] = A[right];
                        A[right] = tmp;
                        map.put(A[left],left);
                        map.put(A[right],right);
                        left++;
                        right--;
                    }
                }
                path.add(r+1);
                // flip 2
                int left = 0;
                int right = r;
                while(left<right)
                {
                    int tmp = A[left];
                    A[left] = A[right];
                    A[right] = tmp;
                    map.put(A[left],left);
                    map.put(A[right],right);
                    left++;
                    right--;
                }
            }
        }
        return path;
    }
}
