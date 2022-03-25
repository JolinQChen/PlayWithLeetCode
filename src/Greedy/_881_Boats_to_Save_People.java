package Greedy;

import java.util.Arrays;

public class _881_Boats_to_Save_People {
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int count = 0;

        // greedy two pointers
        // heavist people sit with lightest people
        int p1 = people.length-1;
        int p2 = 0;
        while(p1>=p2) {
            if(p1!=p2 && people[p1]+people[p2]<=limit) {
                p1--;
                p2++;
            } else {
                p1--;
            }
            count++;
        }
        return count;
    }
}
