package _AAInterviews.Google;

public class _794_Valid_Tic_Tac_Toe_State_what_the_fuckkkkk {
    public boolean validTicTacToe(String[] board) {
        // get number
        int nx=0,no=0;
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                if(board[i].charAt(j)=='X') nx++;
                else no++;
            }
        }
        if(nx==no) {
            // winner must be O
            if(no<3) return false;
            // check O
            if(!isWinner("O", board)) return false;
            // check x, cannot be valid
            if(isWinner("X", board)) return false;
            return true;
        } else if(nx == no+1){
            // winner must be X or even
            if(isWinner("O", board)) return false;
            if(!isWinner("X", board)) {
                // X is not winner, must be even
                return nx==5;
            }
            return true;
        } else
            return false;

    }

    public boolean diagonal(char x, String[] board) {
        return (board[0].charAt(0)==x && board[1].charAt(1)==x && board[2].charAt(2)==x) ||
                (board[0].charAt(2)==x && board[1].charAt(1)==x && board[2].charAt(0)==x);

    }

    public boolean isWinner(String x, String[] board) {
        String str = x+x+x;
        return board[0].equals(str) || !board[1].equals(str) || !board[2].equals(str) || !diagonal(str.charAt(0), board);

    }
}
