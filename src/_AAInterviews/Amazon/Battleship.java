package _AAInterviews.Amazon;

import java.util.*;
/***
 given the size of the map N and two strings S, T that describe the positions of ships and hits
 respectively, returns a string with two numbers: the count of sunken ships and the count of ships that
 have been hit but not sunk, separated with a comma.

 For instance, given N = 4, S = "1B 2C,2D 4D" and T = "2B 2D 3D 4D 4A", your function should return
 "1,1", as explained above.
 */
public class Battleship {

    public static String solution(int N, String S, String T){
        int countDamage = 0;
        int countSink = 0;
        int[][] graph = new int[N][N]; // 0-not occupied; 1-occupied, intact; 2- hit
        String[] t = T.split(" ");
        for(String attack:t) {
//            System.out.println(attack);
            int r = Integer.parseInt(attack.substring(0,attack.length()-1))-1;
//            System.out.println(r);
            int c = (int) (attack.charAt(attack.length()-1) - 'A');
            graph[r][c] = 2;
        }
        String[] boats = S.split(",");
        for(String boat:boats) {
            String[] cells = boat.split(" ");
            int r1 = Integer.parseInt(cells[0].substring(0,cells[0].length()-1))-1;
            int c1 = (int) (cells[0].charAt(cells[0].length()-1) - 'A');

            int r2 = Integer.parseInt(cells[1].substring(0,cells[1].length()-1))-1;
            int c2 = (int) (cells[1].charAt(cells[1].length()-1) - 'A');
            int countCell = 0;
            int hit = 0;
            for(int r=r1; r<=r2; r++) {
                for(int c=c1; c<=c2; c++) {
                    if(graph[r][c]==2) hit++;
                    countCell++;
                }
            }
            if(hit == countCell) countSink++;
            else if(hit!=0) countDamage++;
        }
        String res = countSink + "," + countDamage;
        return res;

    }
    public static void main(String[] args) {
        String res = solution(4,"1B 2C,2D 4D","2B 2D 3D 4D 4A");
        System.out.println(res);
    }
}
