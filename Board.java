package tictactoev4;

public class Board {
    /*
    Board.java
    
        The Board class consists of the board array, an empty constructor, and a toString methods.
            a) This is the board array that is accessed by all the other classes.  It is simply the coordinates of the board as determined by its position in the array.
            b) Since there is nothing to enter into the Board class, the constructor is empty.
            c) The toString method returns the actual board that will be printed so the human player can visualize the game.  In theory, two computers playing against each other would not need such a board.  In other words, this is not the real board that is being stored in the memory, but simply the visualization of one (the actual board would then be the char[] Board array, created earlier in the class).
            
            I) the commented out methods were created for debugging purposes, and currently have no use.  They are currently left here incase they have a use in the future.
    
    */

    public static char[] board = {
        ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '
    };

    public Board() {
    }
/*
    public char getBoard(int i) {
        return board[i];
    }

    public void setBoard(int i, char value) {
        board[i] = value;
    }
*/
    public String toString() {
        return " " + board[0] + "  | " + board[1] + " | " + board[2] + " \n-----------\n " + board[3] + " | " + board[4]+" | "+board[5]+" \n-----------\n "+board[6]+" | "+board[7]+" | "+board[8];
    }

}
