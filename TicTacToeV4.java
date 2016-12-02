package tictactoev4;

import java.util.Scanner;
import java.util.Random;
import tictactoev4.Player;
import tictactoev4.Board;

public class TicTacToeV4 {

    /*
    PSEUDO CODE:
    
    For convenience sake, the pseudocode for the whole program is listed in this file.
    The other files contain only the portion of pseudocode that pertains to that file specifically.
    
    High level pseudo code:
    1) Prompt the user if they want to go first or not by entering a char
    2) Prompt the user what difficulty the computer player wants 
    3) Prompt the user interface with a demonstration of how the graph should work
    4) If the human player goes first, ask the user to input a coordinate
    5) Output the second players result
    6) Print the results
    7) Repeat until either player wins or until the board is full.
    
    Low level pseudo code:
    I) TicTacToeV4.java
    
    1) the main method that simply accesses and instantiates startGame()
    2) the startGame method is different from the main method to get away from static calls.  
        a) It calls first two methods from different areas of the program, starting with who moves first, and followed by the difficulty level
        b) Then the method prints out a number scheme for the player to reference when playing the game
        c) This is followed by the program instantiating two new Player objects, playerOne and playerTwo.  
            i) playerOne is always a Human and X, so as to write the the ai later more easily.
            ii) playerTwo has a difficulty settting of 0 through 2, but if the player inputs -1 then the game becomes two player.
                A) I may change two player mode from -1 to 3 in the future; this was done originally as a debugging method to test if the mark input in Player class worked, but I decided to keep it.
            iii) Player object was originally named Move object, but its purposes was expanded and I thought it would be more reflective of its functions to rename it as such.
        d) This is by an if loop, which calls into account if player 1 decided to play first or not.
            i) At first, I made an else statement, however this did not catch the intended bug I hoped to catch (where the player etners something other than 'y' or 'n') so I deleted it
        e) The order in each of the while loops is the first move player (which was determined previously), followed by a check if the move wins the game for that player, followed immediatley by a check for a tie.  This is followed by the second player move and a check if that wins the game for the second player.
            i) The while loop continues until either there is a winner or a draw.  This is programmed in the Player class
            ii) There is a frustration of the program only printing the board after Player 2 enters their move.  This makes player 1's move somewhat hidden, which is fine if the player is playing against a computer (which doesn't need the board printed to determine its move).  I may try to find a work around for this, but for now this is the simplest solution.
    3) the next two methods simply assign values to itself to be called in the startGame method
        a) selectFirstMove should, by design, prompt the user to input either 'y' or 'n', or if the user fails to input that, repeat the loop until a correct answer has been inputted.
        b) selectDifficulty should only prompt the user to input any string or integer, 0 1 or 2 for difficulty and -1 for two player.
            i) this was left for debugging purposes (to see if certain victory conditions led to the right output), although I may change that in the future. 
            ii) as stated before, I may change -1 to 3 for two player, but I don't see any real challenges to come out from using a negative integer.  In my perspective, it makes it more clear that the player is selecting 2 player mode, rather than an incresaed difficulty.
    
    Player.java
    
    1) boardWeighted is an array of numbers, of which each corresponds to the position of the array.  Coordinates that have more chances of winning are repeated more frequently than ones with less of a chance of winning.
    2) Player class consits of three fields, six methods, and a constuctor.
        a) instantiating three fields, a random class for AI diffuclty, a char mark (not determined by player input), and an int Lvl (which was determined by player input level).
        b) Player constructor is initialized so when player is called, they can have independant mark and Lvl values.
        c) aiMove method
            i) instantiates two fields, int comp (which is used for the ai) and a Scanner class called scanner (which is used for the player).
            ii) there are four if fields, three for each of the ai levels and one for the human player.  The Human player is defined first, and runs several checks if the inout is correct.
                A) For debugging purposes, entering '9' closes the program.  This was simply for convenience, so I wouldn't have to run the program strait into a tie or loss everytime I detected it beforehand, or if the computer output a move I didn't want when replicating different bugs.
                B) The human player going first is also why I decided to assign the "Human Difficulty" as -1.
            iii) the first difficulty level simply choses a coordinate by random, using the previously declared random field.
            iv) the second difficulty uses a weighted random, which is simply picking a random number between 0 and 23, and what ever that value is in boardWeighted[] is the coordinate in the board[] array
            v) the hardest difficulty first checks if (using an if statment) there is a winning move possible, then if (also if statment) there is a losing move that it can avoid, and finally if all else fails using the same weighted random selection as in the previous difficulty.
        d) aiMoveWin checks if there are any winning moves by a system of if else statements.  This is done by simply checking all possible winning moves (26) and then by checking if the third slot is empty.
            i) there was an amusing bug where I didn't write the code for the ai to check if the winning slot was empty.  So in bug testing, if I tried to block its winning move, it would simply over write my play and declair itself the winner.
        e) aiMoveAvoidLoss is, for all intents an purposes, the same exact code however it checks if the human player (or player 'X') is about to win.  
        f) getWinner method determines if there is a winner, through several if statements.  If there is a winner, it declares the player the winner and terminates the program.
            i) Before I wrote in the code for two player, I wrote this section.  So as of now, it prints out a statment saying that "you" have won.  I will probably change this in the future to say something along the lines of "Player 1 has won!"
        g) getCompWinner method is, for all intents an purposes, the same exact code as getWinner, however it checks for player 'O' rather than player 'X'.
            i) Again, this was written before I intended this program for two players, however I don't think I'll change the name away from getCompWinner.
        h) getTie method simply checks if there are any empty elements in board[] array, and terminates the program if there are none.
     
    Board.java
    
    1) The Board class consists of the board array, an empty constructor, and a toString methods.
            a) This is the board array that is accessed by all the other classes.  It is simply the coordinates of the board as determined by its position in the array.
            b) Since there is nothing to enter into the Board class, the constructor is empty.
            c) The toString method returns the actual board that will be printed so the human player can visualize the game.  In theory, two computers playing against each other would not need such a board.  In other words, this is not the real board that is being stored in the memory, but simply the visualization of one (the actual board would then be the char[] Board array, created earlier in the class).
            
            I) the commented out methods were created for debugging purposes, and currently have no use.  They are currently left here incase they have a use in the future.
    */
    
    public static void main(String[] args) {
        TicTacToeV4 ticTacToe = new TicTacToeV4();
        ticTacToe.startGame();
    }

    void startGame() {
        char play = selectFirstMove();
        int level = selectDifficulty();
        System.out.println("TIC  TAC  TOE\n\n"
                + "Use number scheme\n"
                + "   0 1 2\n"
                + "   3 4 5\n"
                + "   6 7 8\n"
                + "for location input\n\n"
                + "Let’s play!");
        Player playerOne = new Player('X', -1);
        Player playerTwo = new Player('O', level);
        Board board = new Board();
        if (play == 'y') {

            while (true) {
                playerOne.aiMove();
                if (playerTwo.getWinner()) {
                    System.out.println(board.toString());
                    System.out.println("Thanks for playing!");
                    return;
                } else if (playerTwo.getTie()) {
                    System.out.println(board.toString());
                    System.out.println("It's a tie!");
                    System.out.println("Thanks for playing!");
                    return;
                }
                playerTwo.aiMove();
                if (playerTwo.getCompWinner()) {
                    System.out.println(board.toString());
                    System.out.println("Thanks for playing!");
                    return;
                }
                System.out.println(board.toString());
            }
        } else {
            while (true) {
                playerTwo.aiMove();
                System.out.println(board.toString());
                if (playerTwo.getCompWinner()) {
                    System.out.println(board.toString());
                    System.out.println("Thanks for playing!");
                    return;
                } else if (playerTwo.getTie()) {
                    System.out.println(board.toString());
                    System.out.println("It's a tie!");
                    System.out.println("Thanks for playing!");
                    return;
                }

                playerOne.aiMove();
                if (playerTwo.getWinner()) {
                    System.out.println(board.toString());
                    System.out.println("Thanks for playing!");
                    return;
                }
            }
        }
    }
    
    char selectFirstMove() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose if Player 1 wishes to make the first move: (y/n)");
        while (true) {
            String first = scanner.next();
            if (first.toLowerCase().startsWith("y")) {
                return 'y';
            } else if (first.toLowerCase().startsWith("n")) {;
                return 'n';
            } else {
                System.out.println("Please re-enter a correct answer.");
            }
        }
    }

    int selectDifficulty() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please input a difficulty level!:\n"
                + "0 = Easy\n"
                + "1 = Hard\n"
                + "2 = Impossible\n"
                + "\n"
                + "-1 = Two player");
        int Selectedlevel = scanner.nextInt();
        return Selectedlevel;

    }
    
    public char getBoardWeighted(int i) {
        return Player.boardWeighted[i];
    }

    public void setBoardWeighted(int i, char value) {
        Player.boardWeighted[i] = value;
    }

}