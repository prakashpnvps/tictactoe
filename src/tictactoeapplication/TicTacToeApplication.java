package tictactoeapplication;

import java.util.Scanner;

/**
 * @author prakash
 */
public class TicTacToeApplication {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //Allow for continous play
        boolean doYouWantToPlay = true;
        while(doYouWantToPlay) {
            //Intializing players and opponents tokens
            System.out.println("Welcome to Tic Tac Toe! \n First you must pick a what character you want to be and which character I will be..");
            System.out.println("");
            System.out.println("Enter a single character that will represent you on the board");
            char playerToken = sc.next().charAt(0);
            System.out.println("Enter a single character that will represent your opponent on the board");
            char opponentToken = sc.next().charAt(0);
            TicTacToe game = new TicTacToe(playerToken, opponentToken);
            AI ai = new AI();
            
            //Setting up the game
            System.out.println();
            System.out.println("Now, we can start the game. To play, enter a number and your token which shall be put in it's " +
                    "place.\nThe numbers go from 1-9, left to right. We shall see who will win this round");
            TicTacToe.printIndexBoard();
            System.out.println();
            game.printBoard();
            System.out.println();
            //Let's play
            while(game.gameOver().equals("notOver")) {
                if(game.currentMarker == game.userMarker) {
                    //User Turn
                    System.out.println("It's your turn. Enter a spot for your token!");
                    int spot = sc.nextInt();
                    while(!game.playerTurn(spot)) {
                        System.out.println("Try again! " + spot + " is invalid. The spot is taken or it is out of range.");
                        spot = sc.nextInt();
                    }
                    System.out.println("You picked " + spot + "!");
                } else {
                    System.out.println("It's my turn!"); 
                    int spot = ai.pickSpot(game);
                    game.playerTurn(spot);
                    System.out.println("I picked " + spot + "!");
                }
                game.printBoard();
            }
            System.out.println();
            System.out.println(game.gameOver());
            System.out.println();
            //Setting a new game depending on the response
            System.out.println("Do you want to play again? Enter 'Y' if you do. Anything else if don't want to play");
            char response = sc.next().charAt(0);
            doYouWantToPlay = (response == 'Y');
            System.out.println();
            System.out.println();
        }
    }
}
