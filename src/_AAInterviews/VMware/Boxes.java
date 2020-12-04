package _AAInterviews.VMware;

import java.util.Arrays;

public class Boxes {
    public static int finalBoxes(int[] boxes) {
        if(boxes.length<=1) return boxes.length;
        Arrays.sort(boxes);
        boolean[] visited = new boolean[boxes.length];
        int right = visited.length-1;
        int res = boxes.length;
        for(int i=boxes.length-1; i>=0; i--) {
            if(!visited[i]){
                int idx = bs(boxes, 0, right, boxes[i]/2);
                while (idx>=0 && visited[idx]) idx--;
                if(idx<0) break;
                visited[idx]=true;
                visited[i] = true;
                right = idx-1;
                res--;
            }
        }
        return res;

    }

    private static int bs(int[] boxes, int left, int right, int largest) {
        if(left>right) return -1;

        while (left<=right) {
            if(boxes[left]>largest) return -1;
            if(boxes[right]<=largest) return right;

            int mid = left+(right-left)/2;
            if(boxes[mid]==largest) {
                while (mid+1<boxes.length && boxes[mid+1]==boxes[mid]) mid++;
                return mid;
            }
            if(boxes[mid]>largest) right = mid-1;
            if(boxes[mid]<largest) {
                if(mid+1<boxes.length && boxes[mid+1]>largest) return mid;
                left = mid+1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] boxes = {2,5,7,6,9,8,4,2};
        System.out.println(finalBoxes(boxes));
    }
}
