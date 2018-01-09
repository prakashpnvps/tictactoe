package tictactoeapplication;

/**
 * @author prakash
 */
public class TicTacToe {
    protected char[] board;
    protected char userMarker;
    protected char aiMarker;
    protected char winner;
    protected char currentMarker;

    public TicTacToe(char playerToken, char aiMarker) {
     this.userMarker = playerToken;
     this.aiMarker = aiMarker;
     this.winner = '-';
     this.board = setBoard();
     this.currentMarker = userMarker;
    }

    public static char[] setBoard(){
        char[] board = new char[9];
        for(int i = 0; i < board.length; i++)
            board[i] = '-';
        return board;
    }
    
    public boolean playerTurn(int spot){
        boolean isValid = withinRange(spot) && !isSpotTaken(spot);
        if(isValid){
            board[spot-1] = currentMarker;
            currentMarker = (currentMarker == userMarker) ? aiMarker : userMarker;
        }
        return isValid;
    }
    
    //Check if the spot is with in range
    public boolean withinRange(int number) {
        return number > 0 && number < (board.length+1);
    }
    
    //Check if the spot is taken
    public boolean isSpotTaken(int number) {
        return board[number-1] != '-';
    }
    
    public void printBoard() {
        //Printing board in the follwing way
        // | - | - | -
        // -----------
        // | - | - | -
        // -----------
        // | - | - | -
        System.out.println();
        for(int i = 0; i < board.length; i++){
            if(i % 3 == 0 && i != 0){
                System.out.println();
                System.out.println(" ------------");
            }
            System.out.print(" | " + board[i]);
        }
        System.out.println();
    }
    
    public static void printIndexBoard() {
        System.out.println();
        for(int i = 0; i < 9; i++){
            if(i % 3 == 0 && i != 0){
                System.out.println();
                System.out.println(" -----------");
            }
            System.out.print(" | " + (i+1));
        }
        System.out.println();
    }
    
    public boolean isThereAWinner() {
        boolean diagonalsAndMiddles = (leftDiag() || rightDiag() || secondCol() || middleRow()) && board[4] != '-';
        boolean topAndFirst = (topRow() || firstCol()) && board[0] != '-';
        boolean bottomAndThird = (bottomRow() || thirdCol()) && board[8] != '-';
        if(diagonalsAndMiddles) {
            this.winner = board[4];
        } else if(topAndFirst) {
            this.winner = board[0];
        } else if(bottomAndThird) {
            this.winner = board[8];
        }
        return diagonalsAndMiddles || topAndFirst || bottomAndThird;
    }
    
    public boolean topRow() {
        return board[0] == board[1] && board[1] == board[2];
    }
    
    public boolean middleRow() {
        return board[3] == board[4] && board[4] == board[5];
    }
    
    public boolean bottomRow() {
        return board[6] == board[7] && board[7] == board[8];
    }
    
    public boolean leftDiag() {
        return board[0] == board[4] && board[4] == board[8];
    }
    
    public boolean rightDiag() {
        return board[2] == board[4] && board[4] == board[6];
    }
    
    public boolean firstCol() {
        return board[0] == board[3] && board[3] == board[6];
    }
    
    public boolean secondCol() {
        return board[1] == board[4] && board[4] == board[7];
    }
    
    public boolean thirdCol() {
        return board[2] == board[5] && board[5] == board[8];
    }
    
    public boolean isBoardFilled() {
        for(int i = 0; i < board.length; i++) {
            if(board[i] == '-')
                return false;
        }
        return true;
    }
    
    public String gameOver() {
        boolean didSomeoneWin = isThereAWinner();
        if(didSomeoneWin) {
            return "The Winner is " + this.winner + "'s";
        } else if(isBoardFilled()) {
            return "Game Over!";
        } else
            return "notOver";
    }
}
