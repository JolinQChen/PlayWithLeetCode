package _math;

public class _1041_Robot_Bounded_In_Circle {
    /**
     * hint1:
     * Calculate the final vector of how the robot travels after executing all instructions once
     * it consists of a change in position plus a change in direction.
     *
     * hint2:
     * The robot stays in the circle iff (looking at the final vector) it changes direction
     * (ie. doesn't stay pointing north), or it moves 0.
     * */
    public boolean isRobotBounded(String instructions) {
        int x = 0, y=0;
        int direction = 0;

        char[] _instructions = instructions.toCharArray();
        for(char instruction:_instructions){
            if(instruction=='G'){
                if(direction == 0) y++;
                else if(direction==1)x++;
                else if(direction == 2) y--;
                else x--;
            }
            else if(instruction=='L'){
                direction--;
                if(direction==-1) direction = 3;
            }
            else {
                direction++;
                if(direction==4) direction = 0;
            }
        }
        if((x==0 && y==0)||direction!=0)return true;
        return false;
    }
//    public static void main(String[] args){
//
//    }
}
