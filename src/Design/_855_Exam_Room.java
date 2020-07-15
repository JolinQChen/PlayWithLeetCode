package Design;

import java.util.*;

public class _855_Exam_Room {
    static class ExamRoom {
        private int N;
        private TreeSet<Integer> students;
        public ExamRoom(int N) {
            this.N = N;
            students = new TreeSet<>();
        }

        public int seat() {
            if(students.isEmpty()) {
                students.add(0);
                System.out.println("come in");
                for(int s:students) System.out.print(s+" , ");
                System.out.println();
                return 0;
            }
            int pos = 0;
            int dist = students.first();
            int prev = 0;
            for(int cur:students) {
                if(dist<(cur-prev)/2) {
                    pos = prev + (cur-prev)/2;
                    dist = (cur - prev)/2;
                }
                prev = cur;
            }
            if(N-1 - students.last() > dist) pos = N-1;
            students.add(pos);
            System.out.println("come in");
            for(int s:students) System.out.print(s+" , ");
            System.out.println();
            return pos;
        }

        public void leave(int p) {
            students.remove(p);
            System.out.println("leave");
            for(int s:students) System.out.print(s+" , ");
            System.out.println();
        }
    }
    public static void main(String[] args){
        ExamRoom er = new ExamRoom(10);
        er.seat();
        er.seat();
        er.seat();
        er.leave(0);
        er.leave(4);
        er.seat();
        er.seat();
        er.seat();
        er.seat();
        er.seat();
        er.seat();
        er.seat();
        er.seat();
        er.seat();
        er.leave(0);

    }
// [null,0,9,4,null,null,0,4,2,6,1,3,5,7,8,null]
}
