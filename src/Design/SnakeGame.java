package Design;

import java.util.Deque;
import java.util.LinkedList;

class SnakeGame {

    /** Initialize your data structure here.
     @param width - screen width
     @param height - screen height
     @param food - A list of food positions
     E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */
    private boolean[][] board;
    private int food_pointer;
    private int score;
    private Deque<int[]> deque;
    int[][] foodPosition;
    int width;
    int height;
//    private int dir;

    public SnakeGame(int width, int height, int[][] food) {
        board = new boolean[height][width];
        this.width = width;
        this.height = height;
        board[0][0] = true;
        food_pointer = 0;
        score = 0;
        deque = new LinkedList<>();
        deque.addLast(new int[]{0,0});
        foodPosition = food;
//        dir = 0;
    }

    /** Moves the snake.
     @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
     @return The game's score after the move. Return -1 if game over.
     Game over when snake crosses the screen boundary or bites its body. */
    public int move(String direction) {
        int[] head = new int[]{deque.peekFirst()[0],deque.peekFirst()[1]} ;
        if(direction.equals("U"))head[0]--;
        else if(direction.equals("L")) head[1]--;
        else if(direction.equals("R")) head[1]++;
        else if(direction.equals("D")) head[0]++;
        if(head[0]<0 || head[0]>=height || head[1]<0 || head[1]>=width) return -1;
        deque.addFirst(new int[]{head[0],head[1]});
        //take food

        if(food_pointer<foodPosition.length && head[0]==foodPosition[food_pointer][0] && head[1]==foodPosition[food_pointer][1]){
            score++;
            food_pointer++;
        }
        else {
            int[] last = deque.pollLast();
            board[last[0]][last[1]] = false;
        }
        if(board[head[0]][head[1]]) return -1;
        board[head[0]][head[1]] = true;
        return score;
    }
}

/**
 * Your SnakeGame object will be instantiated and called as such:
 * SnakeGame obj = new SnakeGame(width, height, food);
 * int param_1 = obj.move(direction);
 */
