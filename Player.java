package tictactoev4;

import java.util.Scanner;
import java.util.Random;
import tictactoev4.Board;

public class Player {
    /*
    Player.java 
    
        Player class consits of a char array, three fields, six methods, and a constuctor.
            a) boardWeighted is an array of numbers, of which each corresponds to the position of the array.  Coordinates that have more chances of winning are repeated more frequently than ones with less of a chance of winning.
            b) instantiating three fields, a random class for AI diffuclty, a char mark (not determined by player input), and an int Lvl (which was determined by player input level).
            c) Player constructor is initialized so when player is called, they can have independant mark and Lvl values.
            d) aiMove method
                i) instantiates two fields, int comp (which is used for the ai) and a Scanner class called scanner (which is used for the player).
                ii) there are four if fields, three for each of the ai levels and one for the human player.  The Human player is defined first, and runs several checks if the inout is correct.
                    A) For debugging purposes, entering '9' closes the program.  This was simply for convenience, so I wouldn't have to run the program strait into a tie or loss everytime I detected it beforehand, or if the computer output a move I didn't want when replicating different bugs.
                    B) The human player going first is also why I decided to assign the "Human Difficulty" as -1.
                iii) the first difficulty level simply choses a coordinate by random, using the previously declared random field.
                iv) the second difficulty uses a weighted random, which is simply picking a random number between 0 and 23, and what ever that value is in boardWeighted[] is the coordinate in the board[] array
                v) the hardest difficulty first checks if (using an if statment) there is a winning move possible, then if (also if statment) there is a losing move that it can avoid, and finally if all else fails using the same weighted random selection as in the previous difficulty.
            e) aiMoveWin checks if there are any winning moves by a system of if else statements.  This is done by simply checking all possible winning moves (26) and then by checking if the third slot is empty.
                i) there was an amusing bug where I didn't write the code for the ai to check if the winning slot was empty.  So in bug testing, if I tried to block its winning move, it would simply over write my play and declair itself the winner.
            f) aiMoveAvoidLoss is, for all intents an purposes, the same exact code however it checks if the human player (or player 'X') is about to win.  
            g) getWinner method determines if there is a winner, through several if statements.  If there is a winner, it declares the player the winner and terminates the program.
                i) Before I wrote in the code for two player, I wrote this section.  So as of now, it prints out a statment saying that "you" have won.  I will probably change this in the future to say something along the lines of "Player 1 has won!"
            h) getCompWinner method is, for all intents an purposes, the same exact code as getWinner, however it checks for player 'O' rather than player 'X'.
                i) Again, this was written before I intended this program for two players, however I don't think I'll change the name away from getCompWinner.
            i) getTie method simply checks if there are any empty elements in board[] array, and terminates the program if there are none.
     
    */
    Random random = new Random();
    char mark;
    int Lvl;

    public static char[] boardWeighted = {
        0, 0, 0, 1, 1, 2, 2, 2, 3, 3, 4, 4, 4, 4, 5, 5, 6, 6, 6, 7, 7, 8, 8, 8
    };

    public Player(char mark, int Lvl) {
        this.mark = mark;
        this.Lvl = Lvl;
    }

    public void aiMove() {
        int comp;
        Scanner scanner = new Scanner(System.in);
        if (Lvl == -1) {
            while (true) {
                System.out.println("Player " + mark + ", enter your move: ");
                int nextMove = scanner.nextInt();
                if (nextMove == 9) {
                    System.out.println("DEBUG: Program has closed.");
                    System.exit(0);
                }
                if (nextMove < 0 || nextMove > 8) {
                    System.out.println("Invalid move.  You can only select a location between 0 and 8!");
                    continue;
                }
                if (Board.board[nextMove] != ' ') {
                    System.out.println("Invalid move.  You can only select a location which has not yet been taken!");
                    continue;
                }

                Board.board[nextMove] = mark;
                break;
            }
        } else if (Lvl == 0) {
            while (true) {
                comp = random.nextInt(9);
                if (Board.board[comp] == ' ') {
                    Board.board[comp] = mark;
                    break;
                }

            }

        } else if (Lvl == 1) {
            while (true) {
                comp = random.nextInt(24);
                if (Board.board[boardWeighted[comp]] == ' ') {
                    Board.board[boardWeighted[comp]] = mark;
                    break;
                }

            }

        } else if (Lvl == 2) {
            if (!aiMoveWin()) {
                if (!aiMoveAvoidLoss()) {
                    while (true) {
                        comp = random.nextInt(24);
                        if (Board.board[boardWeighted[comp]] == ' ') {
                            Board.board[boardWeighted[comp]] = mark;
                            break;
                        }
                    }
                }
            }
        }
    }

    public boolean aiMoveWin() {

        if (Board.board[4] == 'O') {
            if (Board.board[0] == 'O') {
                if (Board.board[8] == ' ') {
                    Board.board[8] = 'O';
                    return true;
                }
            } else if (Board.board[1] == 'O') {
                if (Board.board[7] == ' ') {
                    Board.board[7] = 'O';
                    return true;
                }
            } else if (Board.board[2] == 'O') {
                if (Board.board[6] == ' ') {
                    Board.board[6] = 'O';
                    return true;
                }
            } else if (Board.board[3] == 'O') {
                if (Board.board[5] == ' ') {
                    Board.board[5] = 'O';
                    return true;
                }
            } else if (Board.board[5] == 'O') {
                if (Board.board[3] == ' ') {
                    Board.board[3] = 'O';
                    return true;
                }
            } else if (Board.board[6] == 'O') {
                if (Board.board[2] == ' ') {
                    Board.board[2] = 'O';
                    return true;
                }
            } else if (Board.board[7] == 'O') {
                if (Board.board[1] == ' ') {
                    Board.board[1] = 'O';
                    return true;
                }
            } else if (Board.board[8] == 'O') {
                if (Board.board[0] == ' ') {
                    Board.board[0] = 'O';
                    return true;
                }
            }
        } else if (Board.board[0] == 'O') {
            if (Board.board[1] == 'O') {
                if (Board.board[2] == ' ') {
                    Board.board[2] = 'O';
                    return true;
                }
            } else if (Board.board[2] == 'O') {
                if (Board.board[1] == ' ') {
                    Board.board[1] = 'O';
                    return true;
                }
            } else if (Board.board[3] == 'O') {
                if (Board.board[6] == ' ') {
                    Board.board[6] = 'O';
                    return true;
                }
            } else if (Board.board[6] == 'O') {
                if (Board.board[3] == ' ') {
                    Board.board[3] = 'O';
                    return true;
                }
            } else if (Board.board[4] == 'O') {
                if (Board.board[8] == ' ') {
                    Board.board[8] = 'O';
                    return true;
                }
            } else if (Board.board[8] == 'O') {
                if (Board.board[4] == ' ') {
                    Board.board[4] = 'O';
                    return true;
                }
            }

        } else if (Board.board[8] == 'O') {
            if (Board.board[5] == 'O') {
                if (Board.board[2] == ' ') {
                    Board.board[2] = 'O';
                    return true;
                }
            } else if (Board.board[2] == 'O') {
                if (Board.board[5] == ' ') {
                    Board.board[5] = 'O';
                    return true;
                }
            } else if (Board.board[7] == 'O') {
                if (Board.board[6] == ' ') {
                    Board.board[6] = 'O';
                    return true;
                }
            } else if (Board.board[6] == 'O') {
                if (Board.board[7] == ' ') {
                    Board.board[7] = 'O';
                    return true;
                }
            } else if (Board.board[0] == 'O') {
                if (Board.board[4] == ' ') {
                    Board.board[4] = 'O';
                    return true;
                }
            } else if (Board.board[4] == 'O') {
                if (Board.board[0] == ' ') {
                    Board.board[0] = 'O';
                    return true;
                }
            }
        } else if (Board.board[1] == 'O') {
            if (Board.board[2] == 'O') {
                if (Board.board[0] == ' ') {
                    Board.board[0] = 'O';
                    return true;
                }
            } else if (Board.board[7] == 'O') {
                if (Board.board[4] == ' ') {
                    Board.board[4] = 'O';
                    return true;
                }
            }
        } else if (Board.board[3] == 'O') {
            if (Board.board[5] == 'O') {
                if (Board.board[4] == ' ') {
                    Board.board[4] = 'O';
                    return true;
                }
            } else if (Board.board[6] == 'O') {
                if (Board.board[0] == ' ') {
                    Board.board[0] = 'O';
                    return true;
                }
            }
        } else if (Board.board[2] == 'O') {
            if (Board.board[5] == 'O') {
                if (Board.board[8] == ' ') {
                    Board.board[8] = 'O';
                    return true;
                }
            }
        } else if (Board.board[6] == 'O') {
            if (Board.board[7] == 'O') {
                if (Board.board[8] == ' ') {
                    Board.board[8] = 'O';
                    return true;
                }
            }
        }
        return false;
    }

    public boolean aiMoveAvoidLoss() {

        if (Board.board[4] == 'X') {
            if (Board.board[0] == 'X') {
                if (Board.board[8] == ' ') {
                    Board.board[8] = 'O';
                    return true;
                }
            } else if (Board.board[1] == 'X') {
                if (Board.board[7] == ' ') {
                    Board.board[7] = 'O';
                    return true;
                }
            } else if (Board.board[2] == 'X') {
                if (Board.board[6] == ' ') {
                    Board.board[6] = 'O';
                    return true;
                }
            } else if (Board.board[3] == 'X') {
                if (Board.board[5] == ' ') {
                    Board.board[5] = 'O';
                    return true;
                }
            } else if (Board.board[5] == 'X') {
                if (Board.board[3] == ' ') {
                    Board.board[3] = 'O';
                    return true;
                }
            } else if (Board.board[6] == 'X') {
                if (Board.board[2] == ' ') {
                    Board.board[2] = 'O';
                    return true;
                }
            } else if (Board.board[7] == 'X') {
                if (Board.board[1] == ' ') {
                    Board.board[1] = 'O';
                    return true;
                }
            } else if (Board.board[8] == 'X') {
                if (Board.board[0] == ' ') {
                    Board.board[0] = 'O';
                    return true;
                }
            }
        } else if (Board.board[0] == 'X') {
            if (Board.board[1] == 'X') {
                if (Board.board[2] == ' ') {
                    Board.board[2] = 'O';
                    return true;
                }
            } else if (Board.board[2] == 'X') {
                if (Board.board[1] == ' ') {
                    Board.board[1] = 'O';
                    return true;
                }
            } else if (Board.board[3] == 'X') {
                if (Board.board[6] == ' ') {
                    Board.board[6] = 'O';
                    return true;
                }
            } else if (Board.board[6] == 'X') {
                if (Board.board[3] == ' ') {
                    Board.board[3] = 'O';
                    return true;
                }
            } else if (Board.board[4] == 'X') {
                if (Board.board[8] == ' ') {
                    Board.board[8] = 'O';
                    return true;
                }
            } else if (Board.board[8] == 'X') {
                if (Board.board[4] == ' ') {
                    Board.board[4] = 'O';
                    return true;
                }
            }

        } else if (Board.board[8] == 'X') {
            if (Board.board[5] == 'X') {
                if (Board.board[2] == ' ') {
                    Board.board[2] = 'O';
                    return true;
                }
            } else if (Board.board[2] == 'X') {
                if (Board.board[5] == ' ') {
                    Board.board[5] = 'O';
                    return true;
                }
            } else if (Board.board[7] == 'X') {
                if (Board.board[6] == ' ') {
                    Board.board[6] = 'O';
                    return true;
                }
            } else if (Board.board[6] == 'X') {
                if (Board.board[7] == ' ') {
                    Board.board[7] = 'O';
                    return true;
                }
            } else if (Board.board[0] == 'X') {
                if (Board.board[4] == ' ') {
                    Board.board[4] = 'O';
                    return true;
                }
            } else if (Board.board[4] == 'X') {
                if (Board.board[0] == ' ') {
                    Board.board[0] = 'O';
                    return true;
                }
            }
        } else if (Board.board[1] == 'X') {
            if (Board.board[2] == 'X') {
                if (Board.board[0] == ' ') {
                    Board.board[0] = 'O';
                    return true;
                }
            } else if (Board.board[7] == 'X') {
                if (Board.board[4] == ' ') {
                    Board.board[4] = 'O';
                    return true;
                }
            }
        } else if (Board.board[3] == 'X') {
            if (Board.board[5] == 'X') {
                if (Board.board[4] == ' ') {
                    Board.board[4] = 'O';
                    return true;
                }
            } else if (Board.board[6] == 'X') {
                if (Board.board[0] == ' ') {
                    Board.board[0] = 'O';
                    return true;
                }
            }
        } else if (Board.board[2] == 'X') {
            if (Board.board[5] == 'X') {
                if (Board.board[8] == ' ') {
                    Board.board[8] = 'O';
                    return true;
                }
            }
        } else if (Board.board[6] == 'X') {
            if (Board.board[7] == 'X') {
                if (Board.board[8] == ' ') {
                    Board.board[8] = 'O';
                    return true;
                }
            }
        }
        return false;
    }

    public boolean getWinner() {
        if (Board.board[4] == 'X') {
            if (Board.board[0] == 'X' && Board.board[8] == 'X') {
                System.out.println("Congradulations, you've won!");
                return true;
            } else if (Board.board[1] == 'X' && Board.board[7] == 'X') {
                System.out.println("Congradulations, you've won!");
                return true;
            } else if (Board.board[2] == 'X' && Board.board[6] == 'X') {
                System.out.println("Congradulations, you've won!");
                return true;
            } else if (Board.board[3] == 'X' && Board.board[5] == 'X') {
                System.out.println("Congradulations, you've won!");
                return true;
            }
        }
        if (Board.board[0] == 'X') {
            if (Board.board[1] == 'X' && Board.board[2] == 'X') {
                System.out.println("Congradulations, you've won!");
                return true;
            } else if (Board.board[3] == 'X' && Board.board[6] == 'X') {
                System.out.println("Congradulations, you've won!");
                return true;
            }
        }
        if (Board.board[8] == 'X') {
            if (Board.board[2] == 'X' && Board.board[5] == 'X') {
                System.out.println("Congradulations, you've won!");
                return true;
            } else if (Board.board[7] == 'X' && Board.board[6] == 'X') {
                System.out.println("Congradulations, you've won!");
                return true;
            }

        }
        return false;
    }

    public boolean getCompWinner() {
        if (Board.board[4] == 'O') {
            if (Board.board[0] == 'O' && Board.board[8] == 'O') {
                System.out.println("Sorry, you've lost!");
                return true;
            } else if (Board.board[1] == 'O' && Board.board[7] == 'O') {
                System.out.println("Sorry, you've lost!");
                return true;
            } else if (Board.board[2] == 'O' && Board.board[6] == 'O') {
                System.out.println("Sorry, you've lost!");
                return true;
            } else if (Board.board[3] == 'O' && Board.board[5] == 'O') {
                System.out.println("Sorry, you've lost!");
                return true;
            }
        } else if (Board.board[0] == 'O') {
            if (Board.board[1] == 'O' && Board.board[2] == 'O') {
                System.out.println("Sorry, you've lost!");
                return true;
            } else if (Board.board[3] == 'O' && Board.board[6] == 'O') {
                System.out.println("Sorry, you've lost!");
                return true;
            }
        } else if (Board.board[8] == 'O') {
            if (Board.board[2] == 'O' && Board.board[5] == 'O') {
                System.out.println("Sorry, you've lost!");
                return true;
            } else if (Board.board[7] == 'O' && Board.board[6] == 'O') {
                System.out.println("Sorry, you've lost!");
                return true;
            }

        }

        return false;
    }

    public boolean getTie() {
        for (int i = 0; i <= 8; i++) {
            if (Board.board[i] == ' ') {
                return false;
            }
        }
        return true;
    }

}
